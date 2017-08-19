package com.ceemart.ceemart.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by achu on 13/8/17.
 */

public class BeaconMediaModel extends RealmObject {
        @PrimaryKey
        private int id;
        int status;
        private String name, image;
}
