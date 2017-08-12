package com.ceemart.ceemart.modules.notification;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.QueryController;
import com.ceemart.ceemart.models.BeaconDisplayModel;

import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class SingleMessageActivity extends AppCompatActivity {

    QueryController queryController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        queryController = new QueryController();
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String rowId = extras.getString("messageId");
            queryController.selectById(rowId, BeaconDisplayModel.class, new QueryController.RealmModelCallback() {
                @Override
                public boolean onSuccessObjectResponse(RealmModel model) {
                    BeaconDisplayModel obj = (BeaconDisplayModel) model;
                    Log.d("modeljibi", obj.getName());
                    return false;
                }
            });
        }
        setContentView(R.layout.activity_single_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
