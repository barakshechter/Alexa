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
@Table(name = "FileAttributes")
public class FileAttribute implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne
    @JoinColumn(name = "fileId")
    private File file;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "value", length = 4096)
    private String value;

    public FileAttribute() {
    }

    public FileAttribute(File file, String name, String value) {
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
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
