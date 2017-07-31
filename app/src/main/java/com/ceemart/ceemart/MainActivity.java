package com.ceemart.ceemart;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.ceemart.ceemart.controllers.ApiController;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;
Context context;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        try {
            ApiController api = new ApiController();

            JSONObject data = api.signUp(getApplicationContext());
            if (data != null) {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       
    }


}
