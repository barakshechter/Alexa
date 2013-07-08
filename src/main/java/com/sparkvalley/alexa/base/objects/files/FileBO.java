package com.sparkvalley.alexa.base.objects.files;

import com.sparkvalley.alexa.base.utils.Tests;

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
@Table(name = "FILE")
public class FileBO implements Serializable {
    @Id
    @Column(name = "HASH")
    private String hash;

    @Column(name = "SIZE")
    private Long size;

    @Column(name = "PATH")
    private String path;

    @Column(name = "TYPE")
    private String type;

    @OneToMany
    @JoinColumn(name = "HASH")
    Collection<FileAttrBO> attributes;

    public FileBO() {
    }

    public FileBO(String hash, Long size, String path) {
        this(hash, size, path, null, null);
    }

    public FileBO(String hash, Long size, String path, String type, Collection<FileAttrBO> attributes) {
        this.hash = hash;
        this.size = size;
        this.path = path;
        this.type = type;
        this.attributes = attributes;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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
