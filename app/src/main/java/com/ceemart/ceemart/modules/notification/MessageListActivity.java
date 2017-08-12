package com.ceemart.ceemart.modules.notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ceemart.ceemart.controllers.QueryController;
import com.ceemart.ceemart.models.BeaconDisplayModel;

import java.util.HashMap;

import io.realm.RealmResults;

/**
 * Created by jibi on 8/8/17.
 */

public class MessageListActivity extends AppCompatActivity {
    QueryController queryController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        queryController = new QueryController();
        super.onCreate(savedInstanceState);
        HashMap<String, String> beaconMap = new HashMap<String, String>();
        beaconMap.put("id","");
        queryController.selectQuery(beaconMap, BeaconDisplayModel.class, new QueryController.RealmCallback() {
            @Override
            public boolean onSuccessResponse(RealmResults result) {
                Log.i("jibiExtra:", String.valueOf(result));
                return false;
            }
        });

    }
}
