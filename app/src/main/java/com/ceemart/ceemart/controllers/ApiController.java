package com.ceemart.ceemart.controllers;


import android.content.Context;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ceemart.ceemart.config.Api;
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
    public JSONObject signUp(JSONObject postData, final Context context, final MainActivity.VolleyCallback callback) throws JSONException {
        Toast.makeText(context, "This is my Toast message!", Toast.LENGTH_LONG).show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Api.SIGNUP, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                apiRsponse = response;
                callback.onSuccessResponse(response);
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
        ApplicationController.getInstance().addToRequestQueue(jsonObjReq);
        return apiRsponse;
    }
}
