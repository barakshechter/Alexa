package com.sparkvalley.alexa.base.objects.files;

import java.io.Serializable;

public class FileAttribute implements Serializable {
    private int id = -1;
    private String fileId;
    private String name;
    private String value;

    public FileAttribute() {
    }

    public FileAttribute(String fileId, String name, String value) {
        this.fileId = fileId;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileAttribute that = (FileAttribute) o;

        if (id != that.id) return false;
        if (fileId != null ? !fileId.equals(that.fileId) : that.fileId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
