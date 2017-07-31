package com.ceemart.ceemart.controllers;


import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ceemart.ceemart.Config.Api;
import com.ceemart.ceemart.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by jibi on 30/7/17.
 */

public class ApiController {
    JSONObject apiRsponse;

    /* function api
     * request to api and get response
     *
     * @param apiUrl,requestData
     *
     * @retun json data
     */
    public JSONObject signUp(final Context context) throws JSONException {
        Toast.makeText(context, "This is my Toast message!",
                Toast.LENGTH_LONG).show();
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
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Api.SIGNUP, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                apiRsponse = response;

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, String.valueOf(error.networkResponse.statusCode),
                        Toast.LENGTH_LONG).show();
                if (String.valueOf(error.networkResponse.statusCode).equals("401")) {

                }
                if (String.valueOf(error.networkResponse.statusCode).equals("500")) {
                    Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();

                }

                if (String.valueOf(error.networkResponse.statusCode).equals("400")) {
                    Toast.makeText(context, "You are not an authorized person", Toast.LENGTH_SHORT).show();

                }
            }
        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        // Adding request to request queue
        ApplicationController.getInstance().addToRequestQueue(jsonObjReq);
        Toast.makeText(context, "cc" + apiRsponse.toString(),
                Toast.LENGTH_LONG).show();
        return apiRsponse;
    }


}
