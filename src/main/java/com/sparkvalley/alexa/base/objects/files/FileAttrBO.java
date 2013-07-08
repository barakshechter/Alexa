package com.sparkvalley.alexa.base.objects.files;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 1:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "FILE_ATTR")
public class FileAttrBO implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne
    @JoinColumn(name = "HASH")
    private FileBO file;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private String value;

    public FileAttrBO() {
    }

    public FileAttrBO(FileBO file, String name, String value) {
        this.file = file;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FileBO getFile() {
        return file;
    }

    public void setFile(FileBO file) {
        this.file = file;
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
}
