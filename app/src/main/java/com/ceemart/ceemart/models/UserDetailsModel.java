package com.ceemart.ceemart.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by achu on 31/7/17.
 */

public class UserDetailsModel extends RealmObject {

    @PrimaryKey
    int id;

    String access_token,last_update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setLast_update_time(String last_update_time) {
        this.last_update_time = last_update_time;
    }

    public String getLast_update_time() {
        return last_update_time;
    }



}
