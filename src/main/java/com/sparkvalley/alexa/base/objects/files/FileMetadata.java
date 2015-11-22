package com.sparkvalley.alexa.base.objects.files;

import java.io.Serializable;
import java.util.Date;

public class FileMetadata implements Serializable {
    private String id; //TODO Compute via Sha-512 64 byte
    private Long size;       //in bytes
    private Date createDate; //min of all files with this id
    private Date modifyDate; //max of all files with this id
    private String type;    //mime type

    public FileMetadata() {
    }

    public FileMetadata(String id, Long size, Date createDate, Date modifyDate) {
        this(id, size, createDate, modifyDate, null);
    }

    public FileMetadata(String id, Long size, Date createDate, Date modifyDate, String type) {
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

        FileMetadata fileMetadata = (FileMetadata) o;

        if (id != null ? !id.equals(fileMetadata.id) : fileMetadata.id != null) return false;
        if (size != null ? !size.equals(fileMetadata.size) : fileMetadata.size != null) return false;
        if (createDate != null ? !createDate.equals(fileMetadata.createDate) : fileMetadata.createDate != null) return false;
        if (modifyDate != null ? !modifyDate.equals(fileMetadata.modifyDate) : fileMetadata.modifyDate != null) return false;
        return !(type != null ? !type.equals(fileMetadata.type) : fileMetadata.type != null);
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


