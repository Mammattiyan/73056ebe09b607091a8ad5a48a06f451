package com.ceemart.ceemart.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jibi on 1/8/17.
 */

public class BeaconModel extends RealmObject {

    @PrimaryKey
    private int id;
    int client_id, status;
    float longitude, latitude;
    private String identifier, tag, name, mac_address, uuid, major, minor, expiry;

}
