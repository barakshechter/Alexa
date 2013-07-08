package com.sparkvalley.alexa.base.objects.documents;

import com.sparkvalley.alexa.base.objects.files.FileBO;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "DOCUMENT_ITEM")
public class DocumentItemBO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "REVISION_ID")
    private RevisionBO revision;

    @Column(name = "POSITION")
    private int position;

    @ManyToOne
    @JoinColumn(name = "HASH")
    private FileBO item;

    public DocumentItemBO() {
    }

    public DocumentItemBO(RevisionBO revision, int position, FileBO item) {
        this.revision = revision;
        this.position = position;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RevisionBO getRevision() {
        return revision;
    }

    public void setRevision(RevisionBO revision) {
        this.revision = revision;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FileBO getItem() {
        return item;
    }

    public void setItem(FileBO item) {
        this.item = item;
    }
}
