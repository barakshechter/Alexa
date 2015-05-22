package com.sparkvalley.alexa.base.objects;

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
@Table(name = "Tag")
public class Tag {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "parentId")
    private int parentId;

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Tag parent;

    @OneToMany
    @JoinColumn(name = "parentId")
    private Collection<Tag> children;

    public Tag() {
    }

    public Tag(String name, Tag parent) {
        this(name, null, parent);
    }

    public Tag(String name, String description, Tag parent) {
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

    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    public Collection<Tag> getChildren() {
        return children;
    }

    public void setChildren(Collection<Tag> children) {
        this.children = children;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
