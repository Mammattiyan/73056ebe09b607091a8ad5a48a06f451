package com.ceemart.ceemart.controllers;

import android.util.Log;

import com.ceemart.ceemart.models.BeaconModel;
import com.ceemart.ceemart.modules.MonitoringActivity;

import org.altbeacon.beacon.Beacon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmResults;

/**
 * Created by jibi on 5/8/17.
 */

public class BeaconController {
    private QueryController queryController;
    protected static final String TAG = "BeaconController";
    Map<String, String> beaconMap;

    public BeaconController() {
        queryController = new QueryController();
        beaconMap = new HashMap<String, String>();
    }

    /* function deleteRowById
    * get beacon UUID major and minar ids from string
    *
    *  @param :beacon
    *
    *  @retun null
    */
    public void beaconMacthing(Collection<Beacon> beacons, MonitoringActivity.NotificationCallback notificationCallback) {
        beaconMap.put("uuid", beacons.iterator().next().getId1().toString());
        beaconMap.put("major", beacons.iterator().next().getId2().toString());
        beaconMap.put("minor", beacons.iterator().next().getId3().toString());
        getBeaconDeatils(beaconMap,beacons, notificationCallback);
    }


    /* function deleteRowById
    * check beacon UUID major and minar ids fis exist in ceemart
    *
    *  @param :beacon
    *
    *  @retun null
    */
    private void getBeaconDeatils(final Map<String, String> beaconMap, final Collection<Beacon> beacons, final MonitoringActivity.NotificationCallback notificationCallback) {
        Log.d(TAG, String.valueOf(beaconMap));
        queryController.selectQuery(beaconMap, BeaconModel.class, new QueryController.RealmCallback() {
            @Override
            public boolean onSuccessResponse(RealmResults results) {
                if (results.size() > 0) {
                    List<BeaconModel> list = new ArrayList<>();
                    list.addAll(results);
                    for (BeaconModel myModel : list) {
                        beaconMap.put("beacon_id","1521");// String.valueOf(myModel.getId()));
                        beaconMap.put("distance", "2");
//                        beaconMap.put("distance", String.valueOf(beacons.iterator().next().getDistance()));
                        NotificationController notification=new NotificationController();
                        notification.notifyMesages(beaconMap,notificationCallback);
                    }
                }
                return false;
            }
        });
    }

    public void beaconMacthingTest(Collection<Beacon> beacons, MonitoringActivity.NotificationCallback notificationCallback) {
        beaconMap.put("uuid", "F7826DA6-4FA2-4E98-8024-BC5B71E0893E");
        beaconMap.put("major", "20310");
        beaconMap.put("minor", "48417");
        getBeaconDeatils(beaconMap, beacons,notificationCallback);
    }
}
