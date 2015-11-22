package com.sparkvalley.alexa.base.controllers;

import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.FileMetadata;
import com.sparkvalley.alexa.base.services.intf.IFileService;
import com.sparkvalley.alexa.base.services.intf.ITagService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Barak on 11/22/2015.
 */
@RestController
@RequestMapping("/api/v1/files")
public class FileUploadController {
    @Autowired ITagService tagService;
    @Autowired IFileService fileService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public FileMetadata uploadFile(@RequestBody String path, @RequestParam("tags") List<String> tags) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

        Set<Tag> tagsToApply = tags.parallelStream()
                .map(tagName -> tagService.createTag(tagName.split("/")))
                .collect(Collectors.toSet());

        return fileService.storeFile(path.split("/"), FileUtils.openInputStream(file), attributes, tagsToApply);
    }
}
