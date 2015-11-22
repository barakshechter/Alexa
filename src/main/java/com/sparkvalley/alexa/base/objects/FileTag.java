package com.sparkvalley.alexa.base.objects;

import com.sparkvalley.alexa.base.objects.files.FileMetadata;

import java.io.Serializable;

/**
 * This object puts files in paths that are specified by a tag under the specific file name.
 * Multiple ffiles can therefore exist in the same path (ie., tag) under the same name.
 */
public class FileTag implements Serializable {
    private String fileId;
    private int tagId;
    private String fileName;

    public FileTag(String fileId, int tagId) {
        this(fileId, tagId, fileId);
    }

    public FileTag(String fileId, int tagId, String fileName) {
        this.fileId = fileId;
        this.tagId = tagId;
        this.fileName = fileName;
    }

    public FileTag(FileMetadata file, Tag tag) {
        this(file.getId(), tag.getId());
    }

    public FileTag(FileMetadata file, Tag tag, String fileName) {
        this(file.getId(), tag.getId(), fileName);
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileTag fileTag = (FileTag) o;

        if (tagId != fileTag.tagId) return false;
        if (fileId != null ? !fileId.equals(fileTag.fileId) : fileTag.fileId != null) return false;
        return !(fileName != null ? !fileName.equals(fileTag.fileName) : fileTag.fileName != null);

    }

    @Override
    public int hashCode() {
        int result = fileId != null ? fileId.hashCode() : 0;
        result = 31 * result + tagId;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }
}
