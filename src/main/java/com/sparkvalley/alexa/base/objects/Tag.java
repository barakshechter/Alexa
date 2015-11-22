package com.sparkvalley.alexa.base.objects;

import java.io.Serializable;

public class Tag implements Serializable {
    private int id = -1;
    private String name;
    private String description;
    private Integer parentId;

    public Tag() {
    }

    public Tag(String name, Integer parentId) {
        this(name, null, parentId);
    }

    public Tag(String name, String description, Integer parentId) {
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    public Tag(String name, Tag parent) {
        this(name, null, parent);
    }

    public Tag(String name, String description, Tag parent) {
        this.name = name;
        this.description = description;
        this.parentId = parent != null ? parent.getId() : null;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != tag.id) return false;
        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        if (description != null ? !description.equals(tag.description) : tag.description != null) return false;
        return !(parentId != null ? !parentId.equals(tag.parentId) : tag.parentId != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        return result;
    }
}
