package com.sparkvalley.alexa.base.dao.intf;

import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.File;

import java.util.Collection;

/**
 * Created by Barak on 4/12/2015.
 */
public interface IFilesDao {
    public File getFileById(String id);

    public File updateFile(File file);

    public Collection<File> getFilesForTag(Tag tag);

    public Collection<File> searchFiles(Collection<?> params);

    public Collection<File> getFilesForTag(Tag tag, Integer pageSize, Integer pageNumber);

    public Collection<File> searchFiles(Collection<?> params, Integer pageSize, Integer pageNumber);

    public boolean tagFiles(Tag tag, Collection<File> files);
}
