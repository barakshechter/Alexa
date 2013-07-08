package com.sparkvalley.alexa.base.objects.documents;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "REVISION")
public class RevisionBO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_ID")
    DocumentBO document;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "COMMENTS")
    private String comments;

    @OneToMany
    @JoinColumn(name = "REVISION_ID")
    @OrderBy("position asc")
    private List<DocumentItemBO> items;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentBO getDocument() {
        return document;
    }

    public void setDocument(DocumentBO document) {
        this.document = document;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<DocumentItemBO> getItems() {
        return items;
    }

    public void setItems(List<DocumentItemBO> items) {
        this.items = items;
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
