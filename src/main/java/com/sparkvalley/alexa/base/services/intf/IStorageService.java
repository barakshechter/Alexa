package com.sparkvalley.alexa.base.services.intf;

import com.sparkvalley.alexa.base.objects.files.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface IStorageService {
    static int STRONGLY_PREFERRED = 0;
    static int PREFERRED = 300;
    static int MILDLY_PREFERRED = 1000;
    static int LAST_DITCH_EFFORT = Integer.MAX_VALUE;

    default boolean supports(File metdata) { return true; }
    boolean persist(File metadata, InputStream file) throws IOException;

    boolean exists(String fileId);
    java.io.File retrieve(String fileId) throws IOException;
    InputStream stream(String fileId) throws IOException;

    boolean delete(String fileId) throws FileNotFoundException;
    default boolean deleteQuietly(String fileId) {
        try { delete(fileId); } catch (FileNotFoundException exception) {}
        return false;
    }

    int readPriority();
}
