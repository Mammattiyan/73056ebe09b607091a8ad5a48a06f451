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
}
