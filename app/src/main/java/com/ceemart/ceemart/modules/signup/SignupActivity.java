package com.ceemart.ceemart.modules.signup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.ApiController;

import com.ceemart.ceemart.controllers.ApplicationController;
import com.ceemart.ceemart.controllers.SyncingController;

import com.ceemart.ceemart.controllers.SessionController;
import com.ceemart.ceemart.models.UserDetailsModel;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;

public class SignupActivity extends AppCompatActivity

{
    public static final String TAG = MainActivity.class.getName();
    Realm realm;
    TextView message;
    String macAddress;
    Context context = this;
    String currentDateTime=null;
    public String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        message = (TextView) findViewById(R.id.message);
        getMacAddress();

        ApplicationController appController=new ApplicationController();
        currentDateTime = appController.getDateTime();



        /* initial realm */
        initRealm();

//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.delete(UserDetailsModel.class);
//            }
//        });

        realm.beginTransaction();

        /*   get user details from realm table     */
        UserDetailsModel user = realm.where(UserDetailsModel.class).equalTo("id", 2).findFirst();
        realm.commitTransaction();

        /*   check user already signup or not     */
        if (user != null) {

             /*  load session and store accesskey to session     */
            SessionController session = new SessionController(context);
            session.setAccessToken(user.getAccess_token());
            session.setLastUpdateTime(user.getLast_update_time());
            Log.d("user", String.valueOf(user.getLast_update_time()));
            /*  redirect to main activity     */
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
            finish();
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
                                    UserDetailsModel userDetailsTable = realm.createObject(UserDetailsModel.class, 2);
                                    userDetailsTable.setAccess_token(accessToken);
                                    userDetailsTable.setLast_update_time(currentDateTime);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (realm != null) {
                                SyncingController initial = new SyncingController();
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

    /* function getMacAddress
    * get mac address of device
    *
    * @param null
    *
    * @retun mac address
    */
    public String getMacAddress() {
        try {

            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0") || !nif.getName().equalsIgnoreCase("eth0"))
                    continue;

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
                Log.d("mac : ", macAddress);
                return response.toString();
            }

        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";

    }

}
