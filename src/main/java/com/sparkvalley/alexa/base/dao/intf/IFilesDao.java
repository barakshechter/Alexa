package com.sparkvalley.alexa.base.dao.intf;

import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.File;

import java.util.Collection;
import java.util.Map;

public interface IFilesDao {
    public File getFileById(String id);

    public File updateFile(File file);

    public Collection<File> getFilesForTag(Tag tag);

    public int moveFilesFromTag(Tag src, Tag dst);

    public Collection<File> searchFiles(Collection<?> params);

    public Collection<File> getFilesForTag(Tag tag, Integer pageSize, Integer pageNumber);

    public int deleteFilesForTag(Tag tag);

    public Collection<File> searchFiles(Collection<?> params, Integer pageSize, Integer pageNumber);

    public boolean tagFile(Tag tag, File file, String fileName);
    public boolean tagFiles(Tag tag, Map<File, String> files);
}
