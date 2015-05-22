package com.sparkvalley.alexa.base.services.intf;

import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.File;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;

/**
 * Created by Barak on 4/10/2015.
 */
public interface IFileService {
    public File saveFile(String key, InputStream is, Path path, BasicFileAttributes attributes);
    public File saveFile(String key, InputStream is, Path path, BasicFileAttributes attributes, Collection<Tag> tags);

    public Collection<File> loadFiles(Tag tag);
    public Collection<File> loadFiles(Tag tag, boolean recursive);

    public InputStream openFile(File file) throws IOException;

    //TODO come up with a search api
    public Collection<File> searchFiles(Tag tag, Collection<?> parameters, boolean recursive);

    public boolean tagFiles(Tag tag, Collection<File> files);

}
