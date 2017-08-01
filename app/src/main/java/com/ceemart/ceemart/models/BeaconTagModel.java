package com.ceemart.ceemart.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jibi on 1/8/17.
 */

public class BeaconTagModel extends RealmObject {

    @PrimaryKey
    private int id;
    int user_id, status;
    private String name;
}
