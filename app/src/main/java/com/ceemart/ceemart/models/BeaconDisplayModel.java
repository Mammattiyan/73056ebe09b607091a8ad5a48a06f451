package com.ceemart.ceemart.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jibi on 1/8/17.
 */

public class BeaconDisplayModel extends RealmObject {

    @PrimaryKey
    private int id;
    int beacon_id,type,pushtype,image_display, show_type,distance,display_time,status;
    private String name,tag,title,content,image,video,audio,from_time,to_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeacon_id() {
        return beacon_id;
    }

    public void setBeacon_id(int beacon_id) {
        this.beacon_id = beacon_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPushtype() {
        return pushtype;
    }

    public void setPushtype(int pushtype) {
        this.pushtype = pushtype;
    }

    public int getImage_display() {
        return image_display;
    }

    public void setImage_display(int image_display) {
        this.image_display = image_display;
    }

    public int getShow_type() {
        return show_type;
    }

    public void setShow_type(int show_type) {
        this.show_type = show_type;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDisplay_time() {
        return display_time;
    }

    public void setDisplay_time(int display_time) {
        this.display_time = display_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }
}
