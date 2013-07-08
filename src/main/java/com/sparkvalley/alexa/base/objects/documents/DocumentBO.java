package com.sparkvalley.alexa.base.objects.documents;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 1:39 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "DOCUMENT")
public class DocumentBO {
    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    @OneToMany
    @JoinColumn(name = "DOCUMENT_ID")
    @OrderBy("date asc")
    List<RevisionBO> revisions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
