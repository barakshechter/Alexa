package com.sparkvalley.alexa.base.dao.intf;

import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.FileMetadata;

import java.util.Collection;
import java.util.Map;

public interface IFilesDao {
    public FileMetadata getFileById(String id);

    public FileMetadata updateFile(FileMetadata file);

    public Collection<FileMetadata> getFilesForTag(Tag tag);

    public int moveFilesFromTag(Tag src, Tag dst);

    public Collection<FileMetadata> searchFiles(Collection<?> params);

    public Collection<FileMetadata> getFilesForTag(Tag tag, Integer pageSize, Integer pageNumber);

    public int deleteFilesForTag(Tag tag);

    public Collection<FileMetadata> searchFiles(Collection<?> params, Integer pageSize, Integer pageNumber);

    public boolean tagFile(Tag tag, FileMetadata file, String fileName);
    public boolean tagFiles(Tag tag, Map<FileMetadata, String> files);
}
