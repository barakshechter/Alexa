package com.sparkvalley.alexa.base.objects.documents;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 1:39 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TAG")
public class TagBO {
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private TagBO parent;

    @OneToMany
    @JoinColumn(name = "PARENT_ID")
    private Collection<TagBO> children;

    public TagBO() {
    }

    public TagBO(String name, TagBO parent) {
        this(name, null, parent);
    }

    public TagBO(String name, String description, TagBO parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TagBO getParent() {
        return parent;
    }

    public void setParent(TagBO parent) {
        this.parent = parent;
    }

    public Collection<TagBO> getChildren() {
        return children;
    }

    public void setChildren(Collection<TagBO> children) {
        this.children = children;
    }
}
