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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ceemart.ceemart.controllers.DeviceCotroller;
import com.ceemart.ceemart.controllers.GPSTracker;

import com.ceemart.ceemart.controllers.InitialSyncingController;
import com.ceemart.ceemart.controllers.UpdateSyncingController;
import com.ceemart.ceemart.models.UserDetailsModel;
import com.ceemart.ceemart.modules.signup.SignupActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView message;
<<<<<<< HEAD
    DeviceCotroller deviceCntrlr;
    Context mContext;
    GPSTracker gps;
    String macAddress;

=======
    Button updateGo;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 5;
>>>>>>> j0308

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        message = (TextView) findViewById(R.id.message);
        mContext = this;

        LocationAccess();
//        getMacAddress();

=======
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
>>>>>>> j0308

    }


    public void LocationAccess() {
        gps = new GPSTracker(mContext, MainActivity.this);

        // check if GPS enabled
        // Check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            message.setText("Late : " + latitude + "\n" + "Long : " + longitude);
            Log.d("Late : ", "Lat :" + latitude + "\n" + "Long : " + longitude);


        } else {
            // Can't get location.
            // GPS or network is not enabled.
            // Ask user to enable GPS/network in settings.
            gps.showSettingsAlert();
        }

    }

    public interface VolleyCallback {
        boolean onSuccessResponse(JSONObject result);

    }

}
