package com.sparkvalley.alexa.base.services.intf;

import com.sparkvalley.alexa.base.objects.Tag;

import java.util.Collection;

/**
 * Created by Barak on 4/10/2015.
 */
public interface ITagService {
    /**
     * Find a tag based on a hierarchical path
     * @param path The hierarchy of parents to search for the tag
     * @return a tag object
     */
    public Tag findTag(String... path);

    /**
     * Delete a tag
     * @param tagToDelete the tag to delete
     * @return true if successful
     */
    public boolean deleteTag(Tag tagToDelete);

    /**
     *
     * @param tagToMove
     * @param newParent
     * @return
     */
    public boolean moveTag(Tag tagToMove, Tag newParent);

    /**
     * Create a tag with a hierarchical path, and any intermediate tags in between
     * @param newTagName
     * @param parentPath
     * @return the newly created tag
     */
    public Tag createTag(String newTagName, String... parentPath);

    /**
     * Create a tag with a parent tag specified
     * @param newTagName
     * @param parentTag
     * @return the newly created tag
     */
    public Tag createTag(String newTagName, Tag parentTag);

    public Collection<Tag> findImmediateChildren(Tag parentTag);

    public Collection<Tag> findChildren(Tag parentTag, int depth);

    public Collection<Tag> findAllTags();

    public Tag rootTag();
}
