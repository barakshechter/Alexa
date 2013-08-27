package com.sparkvalley.alexa.base.objects.files;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "File")
public class FileBO implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "size")
    private Long size;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    private String type;

    @OneToMany
    @JoinColumn(name = "fileId")
    Collection<FileAttrBO> attributes;

    public FileBO() {
    }

    public FileBO(String id, Long size, String path) {
        this(id, size, path, null, null);
    }

    public FileBO(String id, Long size, String path, String type, Collection<FileAttrBO> attributes) {
        this.id = id;
        this.size = size;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<FileAttrBO> getAttributes() {
        return attributes;
    }

    public void setAttributes(Collection<FileAttrBO> attributes) {
        this.attributes = attributes;
    }
}
