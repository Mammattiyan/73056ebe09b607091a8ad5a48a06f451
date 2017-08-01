package com.ceemart.ceemart.config;

/**
 * Created by jibi on 30/7/17.
 */

public class Api {


    /*
        Base url of ceemart
         */

    private static String BASE_URL = "http://dev.ceemart.com/beaconapi";

    /*
     *sign up api url
      */
    public static final String SIGNUP = BASE_URL + "/signup";

    /*
     *  beacon list api url
     */
    public static final String BEACON_LIST = BASE_URL + "/beaconlist2?access_key=";

    /*
     *  beacon tag list api url
     */
    public static final String BEACON_TAG_LIST = BASE_URL + "/beacontaglist?access_key=";

 /*
     *  beacon display list api url
     */
    public static final String BEACON_DISPLAY_LIST = BASE_URL + "/beacondisplaylist2?access_key=";


}
