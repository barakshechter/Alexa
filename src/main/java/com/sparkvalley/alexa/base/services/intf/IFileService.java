package com.sparkvalley.alexa.base.services.intf;

import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.File;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Barak on 4/10/2015.
 */
public interface IFileService {
    boolean fileExists(String fileId);
    Collection<File> findFiles(Tag tag);
    Collection<File> findFiles(Tag tag, boolean recursive);

    InputStream openFile(String fileId) throws IOException;

    File storeFile(Path path, InputStream is, BasicFileAttributes attributes);
    File storeFile(Path path, InputStream is, BasicFileAttributes attributes, Collection<Tag> tags);

    //TODO come up with a search api
    Collection<File> searchFiles(Tag tag, Collection<?> parameters, boolean recursive);

    boolean tagFiles(Tag tag, Map<File, String> files);

}
