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


/**
 * Created by jibi on 30/7/17.
 */

public class SyncingController {


    private ApiController api;
    private QueryController queryController;

    /* function SyncingController
    * assign QueryController object
    *
    *  @param :null
    *
    *  @retun :null
    */
    public SyncingController() {
        queryController = new QueryController();
        api = new ApiController();
    }


    /* function beaconSynchronization
    * get all beacon device details from ceemart
    *
    *  @param :token,Context
    *
    *  @retun json data
    */
    public boolean beaconSynchronization(String token, Context applicationContext) throws JSONException {
        String apiUrl = Api.BEACON_LIST + token;
        api.apiRequest(apiUrl, applicationContext, new MainActivity.VolleyCallback() {
            @Override
            public boolean onSuccessResponse(JSONObject result) {
                JSONArray beaconList = new JSONArray();
                try {
                    beaconList = result.getJSONArray("beacon");
                    if (beaconList.length() > 0) {
                        queryController.insertOrUpdateJsonData(beaconList, BeaconModel.class);
                        return true;
                    }
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
    *  @retun json data
    */
    public boolean beaconTagSynchronization(String token, Context applicationContext) throws JSONException {
        String apiUrl = Api.BEACON_TAG_LIST + token;
        api.apiRequest(apiUrl, applicationContext, new MainActivity.VolleyCallback() {
            @Override
            public boolean onSuccessResponse(JSONObject result) {
                JSONArray beaconTagList = new JSONArray();
                try {
                    beaconTagList = result.getJSONArray("beacon_tag_data");
                    if (beaconTagList.length() > 0) {
                        queryController.insertOrUpdateJsonData(beaconTagList, BeaconTagModel.class);
                        return true;
                    }
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
    *  @retun json data
    */
    public boolean beaconDisplaySynchronization(String token, Context applicationContext) throws JSONException {
        String apiUrl = Api.BEACON_DISPLAY_LIST + token;
        api.apiRequest(apiUrl, applicationContext, new MainActivity.VolleyCallback() {
            @Override
            public boolean onSuccessResponse(JSONObject result) {
                JSONArray beaconDisplayList = new JSONArray();
                try {
                    beaconDisplayList = result.getJSONArray("beacon_data");
                    if (beaconDisplayList.length() > 0) {
                        queryController.insertOrUpdateJsonData(beaconDisplayList, BeaconDisplayModel.class);
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return false;
    }

}
