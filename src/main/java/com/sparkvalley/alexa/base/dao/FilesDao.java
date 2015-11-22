package com.sparkvalley.alexa.base.dao;

import com.google.common.collect.Lists;
import com.sparkvalley.alexa.base.dao.intf.IFilesDao;
import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.FileMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class FilesDao implements IFilesDao {
    @Autowired protected DataSource dataSource;

    protected JdbcTemplate jdbcTemplate;
    protected RowMapper<FileMetadata> rowMapper = new BeanPropertyRowMapper(FileMetadata.class);

    @PostConstruct
    public void init() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public FileMetadata getFileById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Alexa.File WHERE id = ?", rowMapper, id);
    }

    @Override
    public FileMetadata updateFile(FileMetadata fileMetadata) {
        //TODO Validate file integrity

        jdbcTemplate.update("MERGE INTO Alexa.File (id, size, createDate, modifyDate, type) VALUES (?, ?, ?, ?, ?)",
                fileMetadata.getId(), fileMetadata.getSize(), fileMetadata.getCreateDate(), fileMetadata.getModifyDate(), fileMetadata.getType()
        );
        return fileMetadata;
    }

    @Override
    public Collection<FileMetadata> getFilesForTag(Tag tag) {
        return getFilesForTag(tag, null, null);
    }

    @Override
    public int moveFilesFromTag(Tag src, Tag dst) {
        return jdbcTemplate.update("UPDATE Alexa.FileTags SET tagId = ? WHERE tagId = ?", dst.getId(), src.getId());
    }

    @Override
    public Collection<FileMetadata> getFilesForTag(Tag tag, Integer pageSize, Integer pageNumber) {
        String limitClause = " LIMIT ALL";
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize != null) {
            if (pageSize < 0) {
                throw new RuntimeException("Page size must be non-negative when getting files for tag.");
            }
            if (pageNumber <= 0) {
                throw new RuntimeException("Page number must be positive when getting files for tag.");
            }

            String.format("LIMIT %d OFFSET %d", pageSize, (pageNumber - 1) * pageSize);
        }
        return jdbcTemplate.query("SELECT * FROM Alexa.File WHERE id IN (SELECT fileId FROM Alexa.FileTags WHERE tagId = ?) " + limitClause, rowMapper, tag.getId());
    }

    @Override
    public int deleteFilesForTag(Tag tag) {
        return jdbcTemplate.update("DELETE FROM Alexa.FileTags WHERE tagId = ?", tag.getId());
    }

    @Override
    public Collection<FileMetadata> searchFiles(Collection<?> params) {
        return searchFiles(params, null, null);
    }

    @Override
    public Collection<FileMetadata> searchFiles(Collection<?> params, Integer pageSize, Integer pageNumber) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tagFile(Tag tag, FileMetadata file, String fileName) {
        return tagFiles(tag, Collections.singletonMap(file, fileName));
    }

    @Override
    public boolean tagFiles(final Tag tag, Map<FileMetadata, String> files) {
        final List<Map.Entry<FileMetadata, String>> fileEntries = Lists.newArrayList(files.entrySet());

        int[] filesTagged = jdbcTemplate.batchUpdate("MERGE INTO Alexa.FileTags (tagId, fileId, fileName) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, tag.getId());
                        ps.setString(2, fileEntries.get(i).getKey().getId());
                        ps.setString(3, fileEntries.get(i).getValue());
                    }

                    @Override
                    public int getBatchSize() {
                        return fileEntries.size();
                    }
                }
        );
        for (int i : filesTagged) {
            if (i == 0) return false;
        }
        return true;
    }
}
