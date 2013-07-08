package com.sparkvalley.alexa.base.objects.files;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 1:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileAttrBO implements Serializable {
    @ManyToOne
    @JoinColumn("HASH")
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
