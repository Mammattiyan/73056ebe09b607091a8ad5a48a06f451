package com.ceemart.ceemart.controllers;

import android.content.Context;
import android.util.Log;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.config.Api;
import com.ceemart.ceemart.models.BeaconDisplayModel;
import com.ceemart.ceemart.models.BeaconModel;
import com.ceemart.ceemart.models.BeaconTagModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by jibi on 30/7/17.
 */

public class BeaconController {

    private Realm realm;


    public BeaconController() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
    }

    /* function beaconSynchronization
    * get all beacon device details from ceemart
    *
    *  @param :token,Context
    *
    * @retun json data
    */
    public boolean beaconSynchronization(String token, Context applicationContext) throws JSONException {
        ApiController api = new ApiController();
        String apiUrl = Api.BEACON_LIST + token;
        api.apiRequest(apiUrl, applicationContext, new MainActivity.VolleyCallback() {
            @Override
            public boolean onSuccessResponse(JSONObject result) {
                JSONArray beaconList = new JSONArray();
                try {
                    beaconList = result.getJSONArray("beacon");
                    if (beaconList.length() > 0) {
                        insertJsonData(beaconList,BeaconModel.class);
                    }
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return false;
    }


    /* function beaconTagSynchronization
    * get all beacon device details from ceemart
    *
    *  @param :token,Context
    *
    * @retun json data
    */
    public boolean beaconTagSynchronization(String token, Context applicationContext) throws JSONException {
        ApiController api = new ApiController();
        String apiUrl = Api.BEACON_TAG_LIST + token;
        api.apiRequest(apiUrl, applicationContext, new MainActivity.VolleyCallback() {
            @Override
            public boolean onSuccessResponse(JSONObject result) {
                JSONArray beaconTagList = new JSONArray();
                try {
                    beaconTagList = result.getJSONArray("beacon_tag_data");
                    if (beaconTagList.length() > 0) {
                        insertJsonData(beaconTagList,BeaconTagModel.class);
                    }
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return false;
    }

    /* function beaconDisplaySynchronization
    * get all beacon display details from ceemart
    *
    *  @param :token,Context
    *
    * @retun json data
    */
    public boolean beaconDisplaySynchronization(String token, Context applicationContext) throws JSONException {
        ApiController api = new ApiController();
        String apiUrl = Api.BEACON_DISPLAY_LIST + token;
        api.apiRequest(apiUrl, applicationContext, new MainActivity.VolleyCallback() {
            @Override
            public boolean onSuccessResponse(JSONObject result) {
                JSONArray beaconDisplayList = new JSONArray();
                try {
                    beaconDisplayList = result.getJSONArray("beacon_data");
                    if (beaconDisplayList.length() > 0) {
                        insertJsonData(beaconDisplayList,BeaconDisplayModel.class);
                    }
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return false;
    }
    /* function insertJsonData
    * insert JSON data to realm tables
    *
    *  @param :jsonData
    *
    * @retun null
    */
    private void insertJsonData(JSONArray jsonData, Class beaconTagModelClass) {
        realm.beginTransaction();
        try {
            realm.createAllFromJson(beaconTagModelClass, jsonData);
        } catch (Exception ex) {
            realm.cancelTransaction();
        }
        realm.commitTransaction();
        realm.beginTransaction();
        RealmQuery query = realm.where(beaconTagModelClass);
        RealmResults results = query.findAll();
        Log.d(String.valueOf(beaconTagModelClass),results.toString());
        realm.commitTransaction();
    }
}
