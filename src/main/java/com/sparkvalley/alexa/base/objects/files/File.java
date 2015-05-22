package com.sparkvalley.alexa.base.objects.files;

import com.sparkvalley.alexa.base.objects.Tag;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "File", schema = "alexa")
public class File implements Serializable {
    @Id
    @Column(name = "id", length = 128)
    private String id; //TODO Compute via Sha-512 64 byte

    @Column(name = "size")
    private Long size;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifyDate")
    private Date modifyDate;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "extension", length = 12)
    private String extension;

    @OneToMany
    @JoinColumn(name = "fileId")
    Collection<FileAttribute> attributes;

    @ManyToMany
    @JoinTable(name = "FileTags", joinColumns = {
            @JoinColumn(name = "fileId") },
            inverseJoinColumns = { @JoinColumn(name = "tagId") })
    Collection<Tag> tags;

    public File() {
    }

    public File(String id, Long size, Date createDate, Date modifyDate) {
        this(id, size, createDate, modifyDate, null, null, null);
    }

    public File(String id, Long size, Date createDate, Date modifyDate, String extension, String type, Collection<FileAttribute> attributes) {
        this.id = id;
        this.size = size;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.extension = extension;
        this.type = type;
        this.attributes = attributes;
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

    public Collection<FileAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Collection<FileAttribute> attributes) {
        this.attributes = attributes;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (attributes != null ? !attributes.equals(file.attributes) : file.attributes != null) return false;
        if (createDate != null ? !createDate.equals(file.createDate) : file.createDate != null) return false;
        if (extension != null ? !extension.equals(file.extension) : file.extension != null) return false;
        if (id != null ? !id.equals(file.id) : file.id != null) return false;
        if (modifyDate != null ? !modifyDate.equals(file.modifyDate) : file.modifyDate != null) return false;
        if (size != null ? !size.equals(file.size) : file.size != null) return false;
        if (type != null ? !type.equals(file.type) : file.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }
}


