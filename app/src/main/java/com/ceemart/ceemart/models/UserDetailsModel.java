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
    String macAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMacAddress(String macAddress) {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
