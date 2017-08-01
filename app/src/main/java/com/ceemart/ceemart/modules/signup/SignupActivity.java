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

        getMacAddress();

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
                message.setText(macAddress);

                RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
                Realm.deleteRealm(realmConfiguration);
                realm = Realm.getInstance(realmConfiguration);


                realm.beginTransaction();
                UserDetailsModel userDetailsTable = realm.createObject(UserDetailsModel.class, 1);
                userDetailsTable.setMacAddress(macAddress);
                realm.commitTransaction();

                /*realm.beginTransaction();
                RealmQuery query = realm.where(UserDetailsModel.class);
                RealmResults results = query.findAll();
                Log.d(String.valueOf(UserDetailsModel.class), results.toString());
                realm.commitTransaction();*/
                return response.toString();
            }

        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";

    }

}
