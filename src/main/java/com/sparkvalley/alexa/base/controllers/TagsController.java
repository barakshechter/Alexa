package com.sparkvalley.alexa.base.controllers;

import com.google.common.collect.Lists;
import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
