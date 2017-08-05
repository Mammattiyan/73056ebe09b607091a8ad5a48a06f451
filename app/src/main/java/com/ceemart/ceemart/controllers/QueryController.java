package com.ceemart.ceemart.controllers;

import android.app.Application;
import android.util.Log;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.models.BeaconModel;
import com.ceemart.ceemart.models.UserDetailsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by jibi on 2/8/17.
 */

public class QueryController extends Application {

    private Realm realm;
    protected static final String TAG = "QueryController";

    /* function insertJsonData
    * insert JSON data to realm tables
    *
    *  @param :jsonData
    *
    *  @retun null
    */
    void insertOrUpdateJsonData(final JSONArray jsonData, final Class cemartModelClass) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.createOrUpdateAllFromJson(cemartModelClass, jsonData);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.beginTransaction();
                RealmQuery query = realm.where(cemartModelClass);
                RealmResults results = query.findAll();
                Log.d(String.valueOf(cemartModelClass), results.toString());
                realm.commitTransaction();
                realm.close();
            }
        }
    }

    /* function deleteRowById
    * delete row from realm table
    *
    *  @param :rowId,modelclass
    *
    *  @retun null
    */
    void deleteRowById(Integer rowId, final Class cemartModelClass) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            final RealmObject query = (RealmObject) realm.where(cemartModelClass).equalTo("id", rowId).findFirst();
            if (query != null) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        query.deleteFromRealm();
                    }
                });
            } else {
                Log.d(String.valueOf(cemartModelClass), "record not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.beginTransaction();
                RealmQuery query = realm.where(cemartModelClass);
                RealmResults results = query.findAll();
                realm.commitTransaction();
                realm.close();
            }
        }
    }

    /* function selectQuery
    * select row from realm table
    *
    *  @param :model and where
    *
    *  @retun null
    */
    public void selectQuery(Map<String, String> whereMap, final Class cemartModelClass, RealmCallback realmCallback) {
        Log.d(TAG, String.valueOf(whereMap));
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            RealmQuery query = realm.where(cemartModelClass);
            for (Map.Entry<String, String> entry : whereMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                query.equalTo(key, value);
            }
            RealmResults results = query.findAll();
            realmCallback.onSuccessResponse(results);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /* interface RealmCallback
    *  callback function for realm
    *
    *  @param :
    *
    *  @retun
    */
    public interface RealmCallback {
        boolean onSuccessResponse(RealmResults result);
    }
}
