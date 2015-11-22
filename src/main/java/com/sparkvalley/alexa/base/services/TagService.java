package com.sparkvalley.alexa.base.services;

import com.sparkvalley.alexa.base.dao.intf.ITagDao;
import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.services.intf.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Barak on 4/11/2015.
 */
@Component
public class TagService implements ITagService {
    @Autowired ITagDao tagDao;

    @Override
    public Tag findTag(String... path) {
        return null;
    }

    @Override
    public boolean deleteTag(Tag tagToDelete) {
        return false;
    }

    @Override
    public boolean moveTag(Tag tagToMove, Tag newParent) {
        return false;
    }

    @Override
    public Tag createTag(String... path) {
        return tagDao.createTagsForPath(Arrays.asList(path));
    }

    @Override
    public Tag createTag(String newTagName, Tag parentTag) {
        return null;
    }

    @Override
    public Collection<Tag> findImmediateChildren(Tag parentTag) {
        return null;
    }

    @Override
    public Collection<Tag> findChildren(Tag parentTag, int depth) {
        return null;
    }

    @Override
    public Tag rootTag() {
        return tagDao.getRootTag();
    }

    public Collection<Tag> findAllTags() {
        return tagDao.loadTags();
    }

    public void setTagDao(ITagDao tagDao) {
        this.tagDao = tagDao;
    }
}

