package com.ceemart.ceemart.controllers;

import android.content.Context;
import android.util.Log;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.config.Api;
import com.ceemart.ceemart.models.BeaconModel;
import com.ceemart.ceemart.models.UserDetailsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by jibi on 2/8/17.
 */

public class UpdateSyncingController {


    ApiController api;
    private QueryController queryController;

    /* function UpdateSyncingController
   * assign QueryController  and ApiController objects
   *
   *  @param :null
   *
   *  @retun :null
   */
    public UpdateSyncingController() {
        api = new ApiController();
        queryController = new QueryController();
    }

    /* function updateSyncing
    * check updates from serever
    *
    *  @param :null
    *
    *  @retun :null
    */
    public boolean updateSyncing(Context applicationContext, MainActivity.VolleyCallback access_token) throws JSONException {
//
        Realm realm =    Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmQuery query = realm.where(BeaconModel.class);
        RealmResults results = query.findAll();
        Log.d(String.valueOf(BeaconModel.class), results.toString());
        realm.commitTransaction();
//   String apiUrl = Api.UPDATES ;
//        api.apiRequest(apiUrl, applicationContext, new MainActivity.VolleyCallback() {
//            @Override
//            public boolean onSuccessResponse(JSONObject result) {
//                JSONArray beaconList = new JSONArray();
//                try {
//                    beaconList = result.getJSONArray("beacon");
//                    if (beaconList.length() > 0) {
//                        queryController.insertJsonData(beaconList, BeaconModel.class);
//                    }
//                    return true;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                return false;
//            }
//        });
        return false;
    }
}
