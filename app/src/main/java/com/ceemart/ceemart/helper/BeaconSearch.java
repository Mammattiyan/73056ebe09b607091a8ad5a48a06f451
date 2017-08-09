package com.ceemart.ceemart.helper;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ceemart.ceemart.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

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