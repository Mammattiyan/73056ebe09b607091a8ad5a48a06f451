package com.ceemart.ceemart.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by achu on 13/8/17.
 */

public class BeaconTimeframeModel extends RealmObject {

    @PrimaryKey
    private int id;
    int status, user_id;
    private String start_time, end_time, days;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }


}
