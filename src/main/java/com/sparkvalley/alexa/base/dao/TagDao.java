package com.sparkvalley.alexa.base.dao;

import com.sparkvalley.alexa.base.dao.intf.IFilesDao;
import com.sparkvalley.alexa.base.dao.intf.ITagDao;
import com.sparkvalley.alexa.base.objects.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component
public class TagDao implements ITagDao {
    @Autowired protected DataSource dataSource;
    @Autowired protected IFilesDao filesDao;

    protected JdbcTemplate jdbcTemplate;
    protected RowMapper<Tag> rowMapper = new BeanPropertyRowMapper(Tag.class);

    @PostConstruct
    public void init() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected ThreadLocal<Tag> rootTag = new ThreadLocal<Tag>() {
        @Override
        protected Tag initialValue() {
            return jdbcTemplate.queryForObject("SELECT * FROM Alexa.Tag were parentId IS NULL", rowMapper);
        }
    };

    @Override
    public Collection<Tag> loadTags() {
        return jdbcTemplate.query("SELECT * FROM Alexa.Tag;", rowMapper);
    }

    @Override
    public Tag getRootTag() {
        return rootTag.get();
    }

    @Override
    public Tag getTagById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Alexa.Tag WHERE id = ?", rowMapper, id);
    }

    @Override
    public Tag updateTag(Tag tag) {
        return updateTag(tag, false);
    }

    private Tag updateTag(Tag tag, boolean mergeWithExistingSibling) {

        Tag newParent = tag.getParentId() == null ? getRootTag() : getTagById(tag.getParentId());
        if (newParent == null) {
            throw new RuntimeException("Parent tag does not exist for parentId=" + tag.getParentId());
        }
        Tag existingSibling = getTagByName(tag.getName(), newParent);
        if (existingSibling != null && existingSibling.getId() != tag.getId()) {
            if (mergeWithExistingSibling) {
                return mergeTags(tag, existingSibling);
            } else {
                throw new RuntimeException(String.format("Cannot update tag, tag with name %s for parentId=%d already exists.", tag.getName(), tag.getParentId()));
            }
        }

        jdbcTemplate.update("UPDATE Alexa.Tag SET name = ?, description = ?, parentId = ? WHERE id = ?",
                tag.getName(), tag.getDescription(), tag.getParentId(), tag.getId()
        );
        return tag;
    }

    @Override
    public Collection<Tag> getTagChildren(Tag tag) {
        return jdbcTemplate.query("SELECT * FROM Alexa.Tag WHERE parentId = ?", rowMapper, tag.getId());
    }

    @Override
    public Tag getTagByName(String name, Tag parent) {
        return jdbcTemplate.queryForObject("SELECT * FROM Alexa.Tag WHERE parentId = ? AND name = ?", rowMapper, parent.getId(), name);
    }

    @Override
    public Tag getTagByPath(List<String> tagPath) {
        return getTagByPath(tagPath, false);
    }

    @Override
    public Tag createTagsForPath(List<String> tagPath) {
        return getTagByPath(tagPath, true);
    }

    @Override
    public Tag mergeTags(Tag src, Tag dest) {
        //Copy all children of src into dest
        for (Tag child : getTagChildren(src)) {
            child.setParentId(dest.getParentId());
            updateTag(child, true);
        }
        filesDao.moveFilesFromTag(src, dest);
        deleteTag(src, false);
        return dest;
    }

    @Override
    public boolean deleteTag(Tag tag, boolean recursive) {
        //TODO What if there are files under this tag?
        if (getRootTag().getId() == tag.getId()) {
            throw new RuntimeException("Unable to delete root tag.");
        }
        Collection<Tag> children = getTagChildren(tag);
        if (!recursive && children.size() > 0) {
            throw new RuntimeException(String.format("Tag %s (id=%d) still has %d children. Cannot delete tag.", tag.getName(), tag.getId(), children.size()));
        }

        if (recursive) {
            filesDao.deleteFilesForTag(tag);
        } else if (filesDao.getFilesForTag(tag, 1, 1).size() > 0) {
            throw new RuntimeException(String.format("Tag still has files associated with it. Cannot delete tag."));
        }

        for (Tag child : children) {
            if (!deleteTag(child, recursive))
                return false;
        }
        return jdbcTemplate.update("DELETE FROM Alexa.Tag WHERE id = ?", tag.getId()) == 1;
    }

    private Tag getTagByPath(List<String> tagPath, boolean createIfMissing) {
        Tag currentTag = getRootTag();
        for (String pathElement : tagPath) {
            Tag nextTag = getTagByName(pathElement, currentTag);
            if (nextTag == null) {
                if (!createIfMissing) return null;
                nextTag = createTag(new Tag(pathElement, currentTag));
            }
            currentTag = nextTag;
        }
        return currentTag;
    }

    private Tag createTag(final Tag tag) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement("INSERT INTO Alexa.Tag (name, description, parentId) VALUES (?, ?, ?)");
                stmt.setString(1, tag.getName());
                stmt.setString(2, tag.getDescription());
                stmt.setInt(3, tag.getParentId());
                return stmt;
            }
        }, keyHolder);
        return getTagById(keyHolder.getKey().intValue());
    }


}
