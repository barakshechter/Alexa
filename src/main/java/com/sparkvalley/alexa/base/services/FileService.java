package com.sparkvalley.alexa.base.services;

import com.sparkvalley.alexa.base.dao.FilesDao;
import com.sparkvalley.alexa.base.dao.TagDao;
import com.sparkvalley.alexa.base.dao.intf.IFilesDao;
import com.sparkvalley.alexa.base.dao.intf.ITagDao;
import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.File;
import com.sparkvalley.alexa.base.services.intf.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;

/**
 * Created by Barak on 4/11/2015.
 */
@Component
public class FileService implements IFileService {

    @Value("alexa.filesBasePath")
    protected String basePath;

    @Autowired protected IFilesDao filesDao;

    @Autowired protected ITagDao tagDao;


    @Override
    public File saveFile(String key, InputStream is, Path path, BasicFileAttributes attributes) {
        return null;
    }

    @Override
    public File saveFile(String key, InputStream is, Path path, BasicFileAttributes attributes, Collection<Tag> tags) {
        return null;
    }

    @Override
    public Collection<File> loadFiles(Tag tag) {
        return null;
    }

    @Override
    public Collection<File> loadFiles(Tag tag, boolean recursive) {
        return null;
    }

    @Override
    public Collection<File> searchFiles(Tag tag, Collection<?> parameters, boolean recursive) {
        return null;
    }

    @Override
    public InputStream openFile(File file) throws IOException {
        if (file == null)
            return null;

        return Files.newInputStream(computeLocation(file), StandardOpenOption.READ);
    }

    private Path computeLocation(File file) {
        String[] pathParts = new String[16];
        for (int i = 0; i < 64; i+=4)
            pathParts[i] = file.getId().substring(i, i+4);
        if (file.getExtension() != null)
            pathParts[16] += "." + file.getExtension();
        return Paths.get(basePath, pathParts);
    }

    @Override
    public boolean tagFiles(Tag tag, Collection<File> files) {
        return false;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setFilesDao(IFilesDao filesDao) {
        this.filesDao = filesDao;
    }

    public void setTagDao(ITagDao tagDao) {
        this.tagDao = tagDao;
    }
}
