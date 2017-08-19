package com.ceemart.ceemart.controllers;

import android.util.Log;

import com.ceemart.ceemart.models.BeaconDisplayModel;
import com.ceemart.ceemart.models.BeaconModel;
import com.ceemart.ceemart.modules.MonitoringActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmResults;

/**
 * Created by jibi on 5/8/17.
 */

public class NotificationController {
    private QueryController queryController;
    protected static final String TAG = "NotificationController";

    /* function NotificationController
    * constructor of NotificationController create QueryController obejct
    *
    *  @param :beaconMap
    *
    *  @retun null
    */
    public NotificationController() {
        queryController = new QueryController();
    }

    /* function notifyMeesages
    * get display messages from realm and show as notification
    *
    *  @param :beaconMap
    *
    *  @retun null
    */
    public void notifyMesages(Map<String, String> beaconMap, final MonitoringActivity.NotificationCallback notificationCallback) {
        Log.d(TAG, String.valueOf(beaconMap));

        queryController.getNotificationMessages(beaconMap, BeaconDisplayModel.class, new QueryController.RealmCallback() {
            @Override
            public boolean onSuccessResponse(RealmResults results) {
                if (results.size() > 0) {
                    notificationCallback.onSuccessResponse(results);
                }
                return false;
            }
        });
    }
}
