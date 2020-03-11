package com.yadira.sharedprefsesion;

import android.content.SharedPreferences;

public class Util {

    public static String getUserMailPrefs(SharedPreferences prefs){
        return prefs.getString("email","");
    }

    public static String getUserPassPrefs(SharedPreferences prefs){
        return prefs.getString("pass","");
    }

    public static boolean getLoginFlagPrefs(SharedPreferences prefs){
        return prefs.getBoolean("login",false);
    }
}
