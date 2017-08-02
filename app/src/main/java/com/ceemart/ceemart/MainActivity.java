package com.ceemart.ceemart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ceemart.ceemart.controllers.DeviceCotroller;
import com.ceemart.ceemart.controllers.GPSTracker;

import org.json.JSONObject;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView message;
    DeviceCotroller deviceCntrlr;
    Context mContext;
    GPSTracker gps;
    String macAddress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (TextView) findViewById(R.id.message);
        mContext = this;

        LocationAccess();
//        getMacAddress();


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
