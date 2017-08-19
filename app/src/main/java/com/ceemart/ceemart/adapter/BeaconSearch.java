package com.ceemart.ceemart.adapter;

import android.app.Application;
import android.util.Log;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;

/**
 * Created by achu on 3/8/17.
 */

public class BeaconSearch extends Application implements BeaconConsumer {
    protected static final String TAG = "Jibi";
    private BeaconManager beaconManager;
    public void search() {
        Log.i(TAG, "****BeaconSearch**** ");
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.bind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        Log.i(TAG, "****BeaconSearchonBeaconServiceConnect**** ");
    }
}