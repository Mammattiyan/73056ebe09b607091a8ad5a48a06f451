package com.ceemart.ceemart.controllers;

import android.content.Context;
import android.util.Log;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.config.Api;
import com.ceemart.ceemart.models.BeaconMediaModel;
import com.ceemart.ceemart.models.BeaconModel;
import com.ceemart.ceemart.models.UserDetailsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import com.ceemart.ceemart.models.BeaconDisplayModel;
import com.ceemart.ceemart.models.BeaconModel;
import com.ceemart.ceemart.models.BeaconTagModel;

import static android.R.attr.value;

/**
 * Created by jibi on 2/8/17.
 */

public class UpdateController {


    ApiController api;
    ApplicationController app;
    SyncingController syncing;
    private QueryController queryController;
    private String accessToken;
    Context context;

    /* function UpdateController
   * assign QueryController  and ApiController objects
   *
   *  @param :null
   *
   *  @retun :null
   */
    public UpdateController() {
        api = new ApiController();
        app = new ApplicationController();
        syncing = new SyncingController();
        queryController = new QueryController();
    }

    /* function updateSyncing
     * check updates from serever
     *
     *  @param :null
     *
     *  @retun :null
     */
    public boolean updateSyncing(String token, String updateDateTime, Context applicationContext, MainActivity.VolleyCallback access_token) throws JSONException {
        accessToken = token;
        context = applicationContext;
        String apiUrl = Api.UPDATES + token + "&updated_date=2017-06-29 19:32:20";// + updateDateTime;
        Log.d("apiUrl", apiUrl);
        api.apiRequest(apiUrl, applicationContext, new MainActivity.VolleyCallback() {
            @Override
            public boolean onSuccessResponse(JSONObject result) {
                JSONObject beaconList = new JSONObject();
                JSONObject display = new JSONObject();
                try {
                    beaconList = result.getJSONObject("beacon_update");
                    Log.d("beaconList", String.valueOf(beaconList));


                    Iterator<String> iterator = beaconList.keys();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        try {
                            JSONObject updateData = (JSONObject) beaconList.get(key);
                            switch (key) {
                                case "display":
                                    displaySyncing(updateData);
                                    break;
                                case "beacon":
                                    beaconSyncing(updateData);
                                    break;
                                case "tag":
                                    beaconTagSyncing(updateData);
                                    break;
                                case "media":
                                    beaconMediaSyncing(updateData);
                                    break;
                                default:
                            }

                        } catch (JSONException e) {
                            // Something went wrong!
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }
                return true;
            }
        });
        return false;
    }

    /* function displaySyncing
     * get display updates from server
     *
     *  @param :displayUpdates
     *
     *  @retun :null
     */
    public void displaySyncing(JSONObject displayUpdates) {
        Iterator<String> iterator = displayUpdates.keys();
        while (iterator.hasNext()) {
            String status = iterator.next();
            try {
                JSONArray displayUpdateData = (JSONArray) displayUpdates.get(status);
                switch (status) {
                    case "update":
                    case "insert":
                        accessToken += "&_ids=" + app.serializeJsonArray(displayUpdateData);
                        syncing.beaconDisplaySynchronization(accessToken, context);
                        break;
                    case "delete":
                        Log.d("delete", String.valueOf(displayUpdateData));
                        for (int i = 0; i < displayUpdateData.length(); i++) {
                            queryController.deleteRowById(6, BeaconDisplayModel.class);
                        }
                        break;
                    default:
                }

            } catch (JSONException e) {
                // Something went wrong!
            }
        }

    }

    /* function beaconSyncing
     * get beacon updates from server
     *
     *  @param :beaconUpdates
     *
     *  @retun :null
     */
    public void beaconSyncing(JSONObject beaconUpdates) {
        Iterator<String> iterator = beaconUpdates.keys();
        while (iterator.hasNext()) {
            String status = iterator.next();
            try {
                JSONArray beaconUpdateData = (JSONArray) beaconUpdates.get(status);
                switch (status) {
                    case "update":
                    case "insert":
                        accessToken += "&_ids=" + app.serializeJsonArray(beaconUpdateData);
                        syncing.beaconSynchronization(accessToken, context);
                        break;
                    case "delete":
                        for (int i = 0; i < beaconUpdateData.length(); i++) {
                            queryController.deleteRowById(6, BeaconModel.class);
                        }
                        break;
                    default:
                }
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }

    /* function beaconTagSyncing
     * get beacon updates from server
     *
     *  @param :beaconTagUpdates
     *
     *  @retun :null
     */
    public void beaconTagSyncing(JSONObject beaconTagUpdates) {
        Log.d("beaconUpdates", String.valueOf(beaconTagUpdates));
        Iterator<String> iterator = beaconTagUpdates.keys();
        while (iterator.hasNext()) {
            String status = iterator.next();
            try {
                JSONArray beaconTagUpdateData = (JSONArray) beaconTagUpdates.get(status);
                switch (status) {
                    case "update":
                    case "insert":
                        accessToken += "&_ids=" + app.serializeJsonArray(beaconTagUpdateData);
                        syncing.beaconTagSynchronization(accessToken, context);
                        break;
                    case "delete":
                        for (int i = 0; i < beaconTagUpdateData.length(); i++) {
                            queryController.deleteRowById(6, BeaconTagModel.class);
                        }
                        break;
                    default:
                }
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }

    /* function beaconMediaSyncing
     * get beacon media updates from server
     *
     *  @param :beaconMediaUpdates
     *
     *  @retun :null
     */
    public void beaconMediaSyncing(JSONObject beaconTagUpdates) {
        Log.d("beaconUpdates", String.valueOf(beaconTagUpdates));
        Iterator<String> iterator = beaconTagUpdates.keys();
        while (iterator.hasNext()) {
            String status = iterator.next();
            try {
                JSONArray beaconTagUpdateData = (JSONArray) beaconTagUpdates.get(status);
                switch (status) {
                    case "update":
                    case "insert":
                        accessToken += "&_ids=" + app.serializeJsonArray(beaconTagUpdateData);
                        syncing.beaconMediaSynchronization(accessToken, context);
                        break;
                    case "delete":
                        for (int i = 0; i < beaconTagUpdateData.length(); i++) {
                            queryController.deleteRowById(6, BeaconMediaModel.class);
                        }
                        break;
                    default:
                }
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }
}
