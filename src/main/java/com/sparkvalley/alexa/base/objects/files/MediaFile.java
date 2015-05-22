package com.sparkvalley.alexa.base.objects.files;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 1:24 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "MediaFile")
public class MediaFile extends File {
    @Column(name = "hasAudio")
    private Boolean hasAudio;

    @Column(name = "hasVideo")
    private Boolean hasVideo;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "audioCodec", length = 64)
    private String audioCodec;

    @Column(name = "videoCodec", length = 64)
    private String videoCodec;

    @Column(name = "audioBitrate")
    private Integer audioBitrate;

    @Column(name = "videoBitrate")
    private Integer videoBitrate;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "thumbnail")
    private MediaFile thumbnail;

    public MediaFile() {
    }

    //Image
    public MediaFile(String hash, Long size, Date createDate, Date modifyDate, String extension, String type, Collection<FileAttribute> attributes, Integer height, Boolean hasVideo, Boolean hasAudio, Integer width, MediaFile thumbnail) {
        super(hash, size, createDate, modifyDate, extension, type, attributes);
        this.height = height;
        this.hasVideo = hasVideo;
        this.hasAudio = hasAudio;
        this.width = width;
        this.thumbnail = thumbnail;
    }

    //Audio
    public MediaFile(String hash, Long size, Date createDate, Date modifyDate, String extension, String type, Collection<FileAttribute> attributes, Boolean hasAudio, Boolean hasVideo, Integer duration, String audioCodec, Integer audioBitrate) {
        super(hash, size, createDate, modifyDate, extension, type, attributes);
        this.hasAudio = hasAudio;
        this.hasVideo = hasVideo;
        this.duration = duration;
        this.audioCodec = audioCodec;
        this.audioBitrate = audioBitrate;
        this.thumbnail = null;
    }

    //Video
    public MediaFile(String hash, Long size, Date createDate, Date modifyDate, String extension, String type, Collection<FileAttribute> attributes, Boolean hasAudio, Boolean hasVideo, Integer duration, String audioCodec, String videoCodec, Integer audioBitrate, Integer videoBitrate, Integer width, Integer height, MediaFile thumbnail) {
        super(hash, size, createDate, modifyDate, extension, type, attributes);
        this.hasAudio = hasAudio;
        this.hasVideo = hasVideo;
        this.duration = duration;
        this.audioCodec = audioCodec;
        this.videoCodec = videoCodec;
        this.audioBitrate = audioBitrate;
        this.videoBitrate = videoBitrate;
        this.width = width;
        this.height = height;
        this.thumbnail = thumbnail;
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

    public MediaFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MediaFile thumbnail) {
        this.thumbnail = thumbnail;
    }
}
