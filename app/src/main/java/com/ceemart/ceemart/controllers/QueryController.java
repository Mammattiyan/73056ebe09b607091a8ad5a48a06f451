package com.ceemart.ceemart.controllers;

import android.app.Application;
import android.util.Log;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.models.BeaconModel;

import org.json.JSONArray;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by jibi on 2/8/17.
 */

public class QueryController extends Application {

    private Realm realm;

    /* function insertJsonData
    * insert JSON data to realm tables
    *
    *  @param :jsonData
    *
    *  @retun null
    */
    void insertJsonData(final JSONArray jsonData, final Class beaconTagModelClass) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.createAllFromJson(beaconTagModelClass, jsonData);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.beginTransaction();
                RealmQuery query = realm.where(beaconTagModelClass);
                RealmResults results = query.findAll();
                Log.d(String.valueOf(beaconTagModelClass), results.toString());
                realm.commitTransaction();
                realm.close();
            }
        }
    }
}
