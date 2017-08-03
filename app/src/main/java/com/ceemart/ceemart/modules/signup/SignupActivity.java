package com.ceemart.ceemart.modules.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.ApiController;

import com.ceemart.ceemart.controllers.InitialSyncingController;

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
    public String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        message = (TextView) findViewById(R.id.message);
        getMacAddress();
        initRealm();
        realm.beginTransaction();
        UserDetailsModel user = realm.where(UserDetailsModel.class).equalTo("id", 4).findFirst();
        realm.commitTransaction();
        if (user != null) {
            Log.d("jibi", String.valueOf(user.getAccess_token()));
        } else {

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
                            accessToken = (String) result.get("access_token");
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    UserDetailsModel userDetailsTable = realm.createObject(UserDetailsModel.class, 3);
                                    userDetailsTable.setAccess_token(accessToken);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (realm != null) {
                                InitialSyncingController initial = new InitialSyncingController();
                                try {
                                    initial.beaconSynchronization(accessToken, getApplicationContext());
                                    initial.beaconTagSynchronization(accessToken, getApplicationContext());
                                    initial.beaconDisplaySynchronization(accessToken, getApplicationContext());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return false;
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initRealm() {
        Realm.init(this);
        realm = Realm.getDefaultInstance();
    }

    public String getMacAddress() {
        try {

            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0") || !nif.getName().equalsIgnoreCase("eth0") ) continue;

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
                Log.d("mac : " , macAddress);
                return response.toString();
            }

        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";

    }

}
