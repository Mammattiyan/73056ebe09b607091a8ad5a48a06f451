package com.ceemart.ceemart.modules.notification;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.NotificationController;
import com.ceemart.ceemart.controllers.QueryController;
import com.ceemart.ceemart.models.BeaconDisplayModel;
import com.ceemart.ceemart.models.BeaconModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmResults;

public class SingleMessageActivity extends AppCompatActivity {

    QueryController queryController;
    String beaconMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        queryController = new QueryController();
        String messageId = null;

        Bundle extras = getIntent().getExtras();
        messageId = extras.getString("messageId");

        setSupportActionBar(toolbar);

        Log.d("beaconMap",messageId);
    }

}
