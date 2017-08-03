package com.ceemart.ceemart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ceemart.ceemart.controllers.InitialSyncingController;
import com.ceemart.ceemart.controllers.UpdateSyncingController;
import com.ceemart.ceemart.models.UserDetailsModel;
import com.ceemart.ceemart.modules.signup.SignupActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView message;
    Button updateGo;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final UpdateSyncingController updateSyncingController = new UpdateSyncingController();
////        finish();
////        Intent homepage = new Intent(this, SignupActivity.class);
////        startActivity(homepage);
//
//        updateGo = (Button) findViewById(R.id.updateGo);
//        updateGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    updateSyncingController.updateSyncing(getApplicationContext(),new VolleyCallback() {
//                        @Override
//                        public boolean onSuccessResponse(JSONObject result) {
//                            try {
//                                String accessToken = (String) result.get("access_token");
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            return false;
//                        }
//                    });
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }


    public interface VolleyCallback {
        boolean onSuccessResponse(JSONObject result);

    }
}
