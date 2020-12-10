package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    public static final String PREFERENCES_NAME = "rebuild_preference";

    private static final String DEFAULT_VALUE_STRING = "";


     public static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

     public static void setString(Context context, String key, String value){
        SharedPreferences pref = getPreferences(context);
        SharedPreferences.Editor efitor = pref.edit();
        efitor.putString(key,value);
        efitor.commit();

    }

    public static void setDouble(Context context, String key, Float  value){
        SharedPreferences pref = getPreferences(context);
        SharedPreferences.Editor efitor = pref.edit();
        efitor.putFloat(key,value);
        efitor.commit();

    }

    public static String getString(Context context, String key){
        SharedPreferences pref = getPreferences(context);
        String value = pref.getString(key,DEFAULT_VALUE_STRING);
        return value;
    }

    public static Float getDouble(Context context, String key){
        SharedPreferences pref = getPreferences(context);
        Float value = pref.getFloat(key,1);
        return value;
    }
}
