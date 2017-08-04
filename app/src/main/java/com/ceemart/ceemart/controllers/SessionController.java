package com.ceemart.ceemart.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by jibi on 3/8/17.
 */

public class SessionController {


    SharedPreferences sharedPreferences;

    /*
    *Editor for Shared preferences
    */
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Ceemart";
    public static final String ACCES_TOKEN = "access_token";
    public static final String LAST_UPDATED_TIME = "last_update_time";
    private static final Boolean LOGIN = true;

    /*
    * SessionController Constructor
    */
    public SessionController(Context context) {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }


    /**
     * function setAccessToken
     * <p>
     * set access key to session variable
     *
     * @Param : accessToken
     * @Return : Null
     */
    public void setAccessToken(String accesstoken) {
        editor.putString(ACCES_TOKEN, accesstoken);
        editor.commit();
    }

    /**
     * function setLastUpdateTime
     * <p>
     * set last updated time to session variable
     *
     * @Param : lastUpdateTime
     * @Return : Null
     */
    public void setLastUpdateTime(String lastUpdateTime) {
        Log.d("UPDATE_TIME", lastUpdateTime);
        editor.putString(LAST_UPDATED_TIME, lastUpdateTime);
        editor.commit();
    }

    /**
     * function setLoginStatus
     * <p>
     * set login status is true
     *
     * @Param : Null
     * @Return : Null
     */
    public void setLoginStatus() {
        editor.putBoolean(String.valueOf(LOGIN), true);
        editor.commit();
    }

    /**
     * function getLoginStatus
     * <p>
     * get login status
     *
     * @Param : Null
     * @Return : status
     */

    public HashMap<String, String> getLoginStatus() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(String.valueOf(LOGIN), sharedPreferences.getString(String.valueOf(LOGIN), null));
        return user;
    }

    /**
     * function getAccessToken
     *
     * get access key
     *
     * @Param : Null
     * @Return : access token
     */

    public String getAccessToken() {
        return  sharedPreferences.getString(ACCES_TOKEN, null);
    }

    /**
     * function getLastUpdateTime
     *
     * get last updated time
     *
     * @Param : Null
     * @Return : last updated time
     */
    public String getLastUpdateTime() {
        return sharedPreferences.getString(LAST_UPDATED_TIME, null);
    }

}