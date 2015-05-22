package com.sparkvalley.alexa.base.dao.intf;

import com.sparkvalley.alexa.base.objects.Tag;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

/**
 * Created by Barak on 4/12/2015.
 */
public interface ITagDao {
    public Collection<Tag> loadTags();
    public Tag getTagById(int id);
    public Tag updateTag(Tag tag);
    public Collection<Tag> getTagChildren(Tag tag);
    public Tag getTagByName(String name, Tag parent);
    public Tag getTagByPath(Path tagPath);
    public List<Tag> createTagsForPath(Path tagPath);
}
