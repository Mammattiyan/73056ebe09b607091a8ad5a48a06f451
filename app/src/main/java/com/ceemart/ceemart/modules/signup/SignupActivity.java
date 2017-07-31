package com.ceemart.ceemart.modules.signup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.ceemart.ceemart.MainActivity;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.ApiController;

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
            JSONObject postData = new JSONObject();
            postData.put("device_id", "7878");
            postData.put("application_key", "5J!FYs#H%J#RHbN4*g6-tqZ9hb#%Mx4&");
            postData.put("application_source", "ANDROID");
            postData.put("version", "1.0");
            postData.put("longitude", "89");
            postData.put("latitude", "90.9");
            postData.put("current_time", "10am");
            postData.put("client_id", "12");
            postData.put("push_token", "thuis");
            ApiController api = new ApiController();
            api.signUp(postData, getApplicationContext(), new MainActivity.VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    Log.d("result", String.valueOf(result));
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
