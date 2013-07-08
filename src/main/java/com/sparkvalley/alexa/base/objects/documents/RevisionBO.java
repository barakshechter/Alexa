package com.sparkvalley.alexa.base.objects.documents;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 1:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class RevisionBO implements Serializable {
    private int id;
    private Date date;
    private String comments;
    @OneToMany
    @JoinColumn(name = "")
}
