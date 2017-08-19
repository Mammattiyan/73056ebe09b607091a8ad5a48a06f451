package com.ceemart.ceemart.helpers;

import android.net.Uri;

/**
 * Created by achu on 15/8/17.
 */

public class Message {
    private String name, title;
    private int id;
    private int thumbnail;
    private Uri VideoUri;

    public Message() {
    }

    public Message(int id, String name, String title, int thumbnail) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public Message(int id, String name, String title, int thumbnail, Uri VideoUri) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.thumbnail = thumbnail;
        this.VideoUri = VideoUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumOfSongs() {
        return title;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.title = title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Uri getVideoUri() {
        return VideoUri;
    }

    public void setVideoUri(Uri videoUri) {
        VideoUri = videoUri;
    }

}