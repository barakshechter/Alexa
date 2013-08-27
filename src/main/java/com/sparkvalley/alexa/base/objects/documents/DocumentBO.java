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
@Table(name = "Document")
public class DocumentBO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "authorId")
    private int author;
    /*
    to be replaced with an author entity
     */
    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifyDate")
    private Date modifyDate;

    @OneToMany
    @JoinColumn(name = "documentId")
    @OrderBy("date asc")
    List<RevisionBO> revisions;

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

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
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

    public List<RevisionBO> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<RevisionBO> revisions) {
        this.revisions = revisions;
    }
}
