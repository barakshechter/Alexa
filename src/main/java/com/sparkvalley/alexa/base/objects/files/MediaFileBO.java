package com.sparkvalley.alexa.base.objects.files;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 1:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class MediaFileBO extends FileBO {
    private Boolean hasAudio;private Boolean hasVideo;
    private Integer duration;
    private String audioCodec;
    private String videoCodec;
    private Integer audioBitrate;
    private Integer videoBitrate;
    private Integer width;
    private Integer height;

    public MediaFileBO() {
    }

    //Image
    public MediaFileBO(String hash, Long size, String path, String type, Collection<FileAttrBO> attributes, Integer height, Boolean hasVideo, Boolean hasAudio, Integer width) {
        super(hash, size, path, type, attributes);
        this.height = height;
        this.hasVideo = hasVideo;
        this.hasAudio = hasAudio;
        this.width = width;
    }

    //Audio
    public MediaFileBO(String hash, Long size, String path, String type, Collection<FileAttrBO> attributes, Boolean hasAudio, Boolean hasVideo, Integer duration, String audioCodec, Integer audioBitrate) {
        super(hash, size, path, type, attributes);
        this.hasAudio = hasAudio;
        this.hasVideo = hasVideo;
        this.duration = duration;
        this.audioCodec = audioCodec;
        this.audioBitrate = audioBitrate;
    }

    //Video
    public MediaFileBO(String hash, Long size, String path, String type, Collection<FileAttrBO> attributes, Boolean hasAudio, Boolean hasVideo, Integer duration, String audioCodec, String videoCodec, Integer audioBitrate, Integer videoBitrate, Integer width, Integer height) {
        super(hash, size, path, type, attributes);
        this.hasAudio = hasAudio;
        this.hasVideo = hasVideo;
        this.duration = duration;
        this.audioCodec = audioCodec;
        this.videoCodec = videoCodec;
        this.audioBitrate = audioBitrate;
        this.videoBitrate = videoBitrate;
        this.width = width;
        this.height = height;
    }

    public Boolean getHasAudio() {
        return hasAudio;
    }

    public void setHasAudio(Boolean hasAudio) {
        this.hasAudio = hasAudio;
    }

    public Boolean getHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(Boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public Integer getAudioBitrate() {
        return audioBitrate;
    }

    public void setAudioBitrate(Integer audioBitrate) {
        this.audioBitrate = audioBitrate;
    }

    public Integer getVideoBitrate() {
        return videoBitrate;
    }

    public void setVideoBitrate(Integer videoBitrate) {
        this.videoBitrate = videoBitrate;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
