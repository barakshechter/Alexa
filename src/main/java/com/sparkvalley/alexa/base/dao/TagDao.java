package com.sparkvalley.alexa.base.dao;

import com.sparkvalley.alexa.base.dao.intf.ITagDao;
import com.sparkvalley.alexa.base.objects.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class TagDao implements ITagDao {
    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    protected RowMapper<Tag> rowMapper = new BeanPropertyRowMapper(Tag.class);

    public TagDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Tag> loadTags() {
        return jdbcTemplate.query("SELECT * FROM Alexa.Tag;", rowMapper);
    }

    @Override
    public Tag getRootTag() {
        return jdbcTemplate.queryForObject("SELECT * FROM Alexa.Tag were parentId IS NULL", rowMapper);
    }

    @Override
    public Tag getTagById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Alexa.Tag WHERE id = ?", rowMapper, id);
    }

    @Override
    public Tag updateTag(Tag tag) {

        Tag newParent = tag.getParentId() == null ? getRootTag() : getTagById(tag.getParentId());
        if (newParent == null) {
            throw new RuntimeException("Parent tag does not exist for parentId=" + tag.getParentId());
        }
        Tag existingSibling = getTagByName(tag.getName(), newParent);
        if (existingSibling != null && existingSibling.getId() != tag.getId()) {
            throw new RuntimeException(String.format("Cannot update tag, tag with name %s for parentId=%d already exists.", tag.getName(), tag.getParentId()));
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
