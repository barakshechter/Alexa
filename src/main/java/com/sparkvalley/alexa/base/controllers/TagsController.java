package com.sparkvalley.alexa.base.controllers;

import com.google.common.collect.Lists;
import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagsController {
    @Autowired
    protected TagService tagService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public List<Tag> list() {
        return Lists.newArrayList(tagService.findAllTags());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tag createTag(@RequestBody String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        if (StringUtils.isEmpty(path)) {
            return tagService.rootTag();
        }

        return tagService.createTag(path.split("/"));
    }
}
