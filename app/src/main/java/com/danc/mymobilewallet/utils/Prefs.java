package com.danc.mymobilewallet.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Prefs {

    public static void storePrefs(String key, Object value, Context context) {
        deletePrefs(key, context);
        try {
            String store = new Gson().toJson(value);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, store);
            editor.apply();
        } catch (Exception se) {
            Log.d("storePref", se.toString());
        }
    }


    public static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String data = preferences.getString(key, null);
        return data;
    }

    public static <T> T getPref(String key, Class<T> T, Context context) {
        String data = getPref(key, context);
        if (data != null) {
            return new Gson().fromJson(data, T);
        } else {
            try {
                return T.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static void deletePrefs(String key, Context context) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.apply();
        } catch (Exception se) {
            // Log.d("deletePrefs", se.toString());
        }
    }


}
