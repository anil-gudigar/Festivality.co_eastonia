package com.festivality.conferenceapp.app;

/**
 * Created by Anil Gudigar on 09/23/2017.
 */

public class Constants {
    public static class API_CONSTANTS {
        public static final String X_API_CLIENT = "x-apiclient";
        public static final String X_HEADER_REQUEST = "x-header-request";
        public static final int TIME_OUT_API = 30; // second
        // format for all timestamps returned from apis
        public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    }
    public static final String PREF_FILE_NAME = "APP_PREF_NAME";
    public static final String PREF_USER_ID = "USER_ID";
    public static final String PREF_NOTIFICATION_SKIP = "PREF_NOTIFICATION_SKIP";
    public static final String PREF_NOTIFICATION_ALLOW = "PREF_NOTIFICATION_ALLOW";
    public static final String PREF_LOCATION_SKIP = "PREF_LOCATION_SKIP";
    public static final String PREF_LOCATION_ALLOW = "PREF_LOCATION_ALLOW";
    public static final String PREF_APP_STARTED = "PREF_APP_STARTED";
}
