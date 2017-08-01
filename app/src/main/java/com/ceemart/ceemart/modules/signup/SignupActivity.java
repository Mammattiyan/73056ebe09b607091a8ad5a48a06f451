package com.ceemart.ceemart.modules.signup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.ApiController;
import com.ceemart.ceemart.controllers.BeaconController;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;

public class SignupActivity extends AppCompatActivity

{

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;
    Context context;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
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
                        BeaconController beacon = new BeaconController();
                        boolean beaconStatus = beacon.beaconSynchronization(accessToken, getApplicationContext());

                        boolean tagStatus = beacon.beaconTagSynchronization(accessToken, getApplicationContext());
                        boolean displayStatus = beacon.beaconDisplaySynchronization(accessToken, getApplicationContext());

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

}
