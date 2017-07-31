package com.ceemart.ceemart;

import android.app.Activity;

import org.json.JSONObject;

public class MainActivity extends Activity {

    public interface VolleyCallback {
        void onSuccessResponse(JSONObject result);

    }
}
