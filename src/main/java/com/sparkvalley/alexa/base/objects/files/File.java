package com.sparkvalley.alexa.base.objects.files;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {
    private String id; //TODO Compute via Sha-512 64 byte
    private Long size;
    private Date createDate;
    private Date modifyDate;
    private String type;

    public File() {
    }

    public File(String id, Long size, Date createDate, Date modifyDate) {
        this(id, size, createDate, modifyDate, null);
    }

    public File(String id, Long size, Date createDate, Date modifyDate, String type) {
        this.id = id;
        this.size = size;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (id != null ? !id.equals(file.id) : file.id != null) return false;
        if (size != null ? !size.equals(file.size) : file.size != null) return false;
        if (createDate != null ? !createDate.equals(file.createDate) : file.createDate != null) return false;
        if (modifyDate != null ? !modifyDate.equals(file.modifyDate) : file.modifyDate != null) return false;
        return !(type != null ? !type.equals(file.type) : file.type != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}


