package com.ceemart.ceemart.modules;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.BeaconController;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class MonitoringActivity extends AppCompatActivity implements BeaconConsumer {
    protected static final String TAG = "Jibi";
    private BeaconManager beaconManager;
    private BeaconController beaconController;
    private BackgroundPowerSaver backgroundPowerSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);
//        backgroundPowerSaver = new BackgroundPowerSaver(this);
        search();
    }

    public void search() {
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                BeaconController beaconController = new BeaconController();
                if (beacons.size() > 0) {
                    Log.i(TAG, "The first beacon I see is about " + beacons.iterator().next().getDistance() + " meters away.");

                    Log.i(TAG, "Beacon found ");
//                    beaconController.beaconMacthing(beacons);
                    beaconController.beaconMacthingTest(beacons);
                } else {
                    beaconController.beaconMacthingTest(beacons);
                    Log.i(TAG, "No beacon ");
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }
    }

//    @Override
//    public void onBeaconServiceConnect() {
//        beaconManager.addRangeNotifier(new RangeNotifier() {
//            @Override
//            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
//                BeaconController beaconController = new BeaconController();
//                Log.i(TAG, "The first beacon I see is about "+beacons.iterator().next()+" meters away.");
//                if (beacons.size() > 0) {
//                    Log.i(TAG, "Beacon found ");
//                    beaconController.beaconMacthing(beacons);
//                } else {
//                    beaconController.beaconMacthingTest();
//                    Log.i(TAG, "No beacon ");
//                }
//            }
//        });
//        Log.i(TAG, "T******** ");
//        try {
//            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
//        } catch (RemoteException e) {
//        }
//    }

}
