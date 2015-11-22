package com.sparkvalley.alexa.base.services.storage;

import com.sparkvalley.alexa.base.objects.files.FileMetadata;
import com.sparkvalley.alexa.base.services.intf.IStorageService;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FilesystemStorageService implements IStorageService {
    private File basePath;
    private int readPriority;

    public FilesystemStorageService(File basePath) {
        this(basePath, STRONGLY_PREFERRED);
    }

    public FilesystemStorageService(File basePath, int readPriority) {
        this.basePath = basePath;
        this.readPriority = readPriority;
    }

    @PostConstruct
    public void init() throws InstantiationException {
        if (!basePath.exists()) {
            basePath.mkdirs();
        }

        if (!basePath.isDirectory()) {
            throw new InstantiationException("Base path for storage exists, but is not a directory!");
        }

    }

    @Override
    public boolean exists(String fileId) {
        return computeLocation(fileId).exists();
    }

    @Override
    public File retrieve(String fileId) throws IOException {
        File copy = File.createTempFile("alexa", fileId);
        FileUtils.copyInputStreamToFile(
                stream(fileId), copy
        );

        return copy;
    }

    @Override
    public InputStream stream(String fileId) throws IOException {
        return FileUtils.openInputStream(computeLocation(fileId));
    }

    @Override
    public boolean persist(FileMetadata metadata, InputStream file) throws IOException {
        if (exists(metadata.getId())) {
            return true;
        }

        File tempFile = File.createTempFile("alexa", metadata.getId());

        FileUtils.copyInputStreamToFile(file, tempFile);
        FileUtils.moveFile(tempFile, computeLocation(metadata.getId()));
        return true;
    }

    @Override
    public boolean delete(String fileId) throws FileNotFoundException {
        if (!exists(fileId)) {
            throw new FileNotFoundException("Unable to find file to delete. Id=" + fileId);
        }

        return computeLocation(fileId).delete();
    }

    @Override
    public int readPriority() {
        return readPriority;
    }

    private File computeLocation(String fileId) {
        StringBuilder sb = new StringBuilder();
        while (fileId.length() > 4) {
            sb.append(fileId.substring(0, 4)).append("/");
            fileId = fileId.substring(4);
        }
        sb.append(fileId);


        File filePath = new File(basePath, sb.toString());
        filePath.getParentFile().mkdirs();
        return filePath;
    }
}
