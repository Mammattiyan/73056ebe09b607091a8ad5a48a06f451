package com.ceemart.ceemart.models;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by jibi on 30/7/17.
 */

public class University extends RealmObject {
    @Index
    private int id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

