package com.sparkvalley.alexa.base.dao;

import com.sparkvalley.alexa.base.dao.intf.IFilesDao;
import com.sparkvalley.alexa.base.dao.intf.ITagDao;
import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.File;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

/**
 * Created by Barak on 4/12/2015.
 */
public class TagDao implements ITagDao {
    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    public TagDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Tag> loadTags() {
        return jdbcTemplate.query("SELECT * FROM Alexa.Tag;", new BeanPropertyRowMapper(Tag.class));
    }

    @Override
    public Tag getTagById(int id) {
        return null;
    }

    @Override
    public Tag updateTag(Tag tag) {
        return null;
    }

    @Override
    public Collection<Tag> getTagChildren(Tag tag) {
        return null;
    }

    @Override
    public Tag getTagByName(String name, Tag parent) {
        return null;
    }

    @Override
    public Tag getTagByPath(Path tagPath) {
        return null;
    }

    @Override
    public List<Tag> createTagsForPath(Path tagPath) {
        return null;
    }
}
