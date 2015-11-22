package com.sparkvalley.alexa.base.services;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sparkvalley.alexa.base.dao.intf.IFilesDao;
import com.sparkvalley.alexa.base.dao.intf.ITagDao;
import com.sparkvalley.alexa.base.objects.Tag;
import com.sparkvalley.alexa.base.objects.files.FileMetadata;
import com.sparkvalley.alexa.base.services.intf.IFileService;
import com.sparkvalley.alexa.base.services.intf.IStorageService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Component
public class FileService implements IFileService {

    public final static String ID_HASH_FUNCTION = "SHA-512";

    @Autowired
    protected List<IStorageService> storageServices;

    @Autowired protected IFilesDao filesDao;
    @Autowired protected ITagDao tagDao;

    @PostConstruct
    private void init() throws NoSuchAlgorithmException {
        MessageDigest.getInstance(ID_HASH_FUNCTION); //Try to get the hashing function, or throw an exception so that the application fails to start

        storageServices.sort(Comparator.comparingInt(IStorageService::readPriority));
    }

    @Override
    public boolean fileExists(String fileId) {
        return storageServices.stream()
                .filter(s -> s.exists(fileId))
                .findFirst().isPresent();
    }

    @Override
    public Collection<FileMetadata> findFiles(Tag tag, boolean recursive) {
        Queue<Tag> tagsToExplore = new ConcurrentLinkedQueue<>(Collections.singleton(tag));
        Set<Tag> seenTags = Sets.newSetFromMap(Maps.newConcurrentMap());
        Set<FileMetadata> files = Sets.newSetFromMap(Maps.newConcurrentMap());

        while (!tagsToExplore.isEmpty()) {
            tag = tagsToExplore.remove();
            if (!seenTags.add(tag)) continue;

            files.addAll(findFiles(tag));
            if (recursive) {
                Set<Tag> children = tagDao.getTagChildren(tag).stream()
                        .filter(seenTags::contains)
                        .collect(Collectors.toSet());
                tagsToExplore.addAll(children);
            }
        }

        return files;
    }

    @Override
    public Collection<FileMetadata> findFiles(Tag tag) {
        return filesDao.getFilesForTag(tag);
    }

    @Override
    public FileMetadata storeFile(String[] path, InputStream is, BasicFileAttributes attributes, Collection<Tag> tags) {
        try {
            DigestInputStream dis = new DigestInputStream(is, MessageDigest.getInstance(ID_HASH_FUNCTION));
            File tempFile = File.createTempFile("alexa", "storeFile");

            FileUtils.copyInputStreamToFile(dis, tempFile);
            FileMetadata fileMetadata = new FileMetadata(
                    DatatypeConverter.printHexBinary(dis.getMessageDigest().digest()),
                    attributes.size(),
                    new Date(attributes.creationTime().toMillis()),
                    new Date(attributes.lastModifiedTime().toMillis())
            );

            //Store the file metadata object
            filesDao.updateFile(fileMetadata);

            List<String> pathElements = Arrays.asList(path);

            //Tag the file in its path
            filesDao.tagFile(
                    tagDao.createTagsForPath(pathElements.subList(0, pathElements.size()-1)),
                    fileMetadata,
                    pathElements.get(pathElements.size() - 1)
            );

            //Tag the file with any other tags supplied
            tags.parallelStream()
                    .forEach(tag -> filesDao.tagFile(tag, fileMetadata, ""));

            //Store the file with each of the supported storage services
            storageServices.stream()
                    .filter(s -> s.supports(fileMetadata))
                    .forEach(s -> persistFile(s, fileMetadata, tempFile));


            return fileMetadata;

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    private boolean persistFile(IStorageService storageService, FileMetadata fileMetadata, File file) {
        try {
            return storageService.persist(fileMetadata, FileUtils.openInputStream(file));
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public FileMetadata storeFile(String[] path, InputStream is, BasicFileAttributes attributes) {
        return storeFile(path, is, attributes, Collections.emptyList());
    }

    @Override
    public boolean tagFiles(Tag tag, Map<FileMetadata, String> files) {
        return filesDao.tagFiles(tag, files);
    }

    @Override
    public InputStream openFile(String fileId) throws IOException {
        return storageServices.stream()
                .filter(s -> s.exists(fileId))
                .reduce(
                        null,
                        (foundInputStream, nextStorageService) -> tryNextStorageSerice(fileId, foundInputStream, nextStorageService),
                        (lhs, rhs) -> lhs == null ? rhs : lhs
                );
    }

    private InputStream tryNextStorageSerice(String fileId, InputStream found, IStorageService storageService) {
        if (found != null) return found;

        try { return storageService.stream(fileId); } catch (IOException e) {}
        return null;
    }

    @Override
    public Collection<FileMetadata> searchFiles(Tag tag, Collection<?> parameters, boolean recursive) {
        throw new UnsupportedOperationException();
    }
}
