package com.ceemart.ceemart.modules.signup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.ApiController;

import com.ceemart.ceemart.controllers.BeaconController;

import com.ceemart.ceemart.models.UserDetailsModel;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class SignupActivity extends AppCompatActivity

{
    public static final String TAG = MainActivity.class.getName();
    Realm realm;
    TextView message;
    String macAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        message = (TextView) findViewById(R.id.message);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
        getMacAddress();
        try {
            JSONObject apiData = new JSONObject();
            apiData.put("device_id", "7878");
            apiData.put("application_key", "5J!FYs#H%J#RHbN4*g6-tqZ9hb#%Mx4&");
            apiData.put("application_source", "ANDROID");
            apiData.put("version", "1.0");
            apiData.put("longitude", "89");
            apiData.put("latitude", "90.9");
            apiData.put("current_time", "10am");
            apiData.put("client_id", "12");
            apiData.put("push_token", "thuis");
            ApiController api = new ApiController();
            api.signUp(apiData, getApplicationContext(), new MainActivity.VolleyCallback() {
                @Override
                public boolean onSuccessResponse(JSONObject result) {
                    try {
                        String accessToken = (String) result.get("access_token");
                        realm.beginTransaction();
                        UserDetailsModel userDetailsTable = realm.createObject(UserDetailsModel.class, 1);
                        userDetailsTable.setAccess_token(accessToken);
                        realm.commitTransaction();
                        realm.close();
                        BeaconController beacon = new BeaconController();
                        beacon.beaconSynchronization(accessToken, getApplicationContext());
                        beacon.beaconTagSynchronization(accessToken, getApplicationContext());
                        beacon.beaconDisplaySynchronization(accessToken, getApplicationContext());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMacAddress() {
        try {

            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder response = new StringBuilder();
                for (byte b : macBytes) {
                    response.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (response.length() > 0) {
                    response.deleteCharAt(response.length() - 1);

                }
                macAddress = response.toString();
                return response.toString();
            }

        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";

    }

}
