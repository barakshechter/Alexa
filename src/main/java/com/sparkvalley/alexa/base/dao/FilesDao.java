package com.sparkvalley.alexa.base.dao;

import com.sparkvalley.alexa.base.dao.intf.IFilesDao;
import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.File;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * Created by Barak on 4/12/2015.
 */
public class FilesDao implements IFilesDao {
    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    public FilesDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public File getFileById(String id) {
        return null;
    }

    @Override
    public File updateFile(File file) {
        return null;
    }

    @Override
    public Collection<File> getFilesForTag(Tag tag) {
        return null;
    }

    @Override
    public Collection<File> searchFiles(Collection<?> params) {
        return null;
    }

    @Override
    public Collection<File> getFilesForTag(Tag tag, Integer pageSize, Integer pageNumber) {
        return null;
    }

    @Override
    public Collection<File> searchFiles(Collection<?> params, Integer pageSize, Integer pageNumber) {
        return null;
    }

    @Override
    public boolean tagFiles(Tag tag, Collection<File> files) {
        return false;
    }
}
