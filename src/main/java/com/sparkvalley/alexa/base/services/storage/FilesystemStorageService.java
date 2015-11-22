package com.sparkvalley.alexa.base.services.storage;

import com.sparkvalley.alexa.base.objects.files.File;
import com.sparkvalley.alexa.base.services.intf.IStorageService;
import org.apache.commons.io.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesystemStorageService implements IStorageService {
    private Path basePath;
    private int readPriority;

    public FilesystemStorageService(Path basePath) {
        this(basePath, STRONGLY_PREFERRED);
    }

    public FilesystemStorageService(Path basePath, int readPriority) {
        this.basePath = basePath;
        this.readPriority = readPriority;
    }

    @Override
    public boolean exists(String fileId) {
        return computeLocation(fileId).toFile().exists();
    }

    @Override
    public java.io.File retrieve(String fileId) throws IOException {
        java.io.File copy = java.io.File.createTempFile("alexa", fileId);
        FileUtils.copyInputStreamToFile(
                stream(fileId), copy
        );

        return copy;
    }

    @Override
    public InputStream stream(String fileId) throws IOException {
        return FileUtils.openInputStream(computeLocation(fileId).toFile());
    }

    @Override
    public boolean persist(File metadata, InputStream file) throws IOException {
        if (exists(metadata.getId())) {
            return true;
        }

        java.io.File tempFile = java.io.File.createTempFile("alexa", metadata.getId());

        FileUtils.copyInputStreamToFile(file, tempFile);
        FileUtils.moveFile(tempFile, computeLocation(metadata.getId()).toFile());
        return true;
    }

    @Override
    public boolean delete(String fileId) throws FileNotFoundException {
        if (!exists(fileId)) {
            throw new FileNotFoundException("Unable to find file to delete. Id=" + fileId);
        }

        return computeLocation(fileId).toFile().delete();
    }

    @Override
    public int readPriority() {
        return readPriority;
    }

    private Path computeLocation(String fileId) {
        String[] pathParts = new String[15];
        for (int i = 1; i < 64; i+=4)
            pathParts[i] = fileId.substring(i, i+4);

        Path filePath = Paths.get(fileId.substring(0, 4), pathParts);
        return basePath.resolve(filePath);
    }
}
