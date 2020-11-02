package com.omer.simplelogin.utils;

import android.content.Context;

public class SharedPreferencesUtils {
    private static final String APP = "APP";

    private SharedPreferencesUtils() {
    }

    static public boolean getBooleanData(Context context, String key) {
        return context.getSharedPreferences(APP, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    static public int getIntData(Context context, String key) {
        return context.getSharedPreferences(APP, Context.MODE_PRIVATE).getInt(key, 0);
    }

    static public String getStringData(Context context, String key) {
        return context.getSharedPreferences(APP, Context.MODE_PRIVATE).getString(key, null);
    }

    static public void saveData(Context context, String key, String val) {
        context.getSharedPreferences(APP, Context.MODE_PRIVATE).edit().putString(key, val).apply();
    }

    static public void saveData(Context context, String key, int val) {
        context.getSharedPreferences(APP, Context.MODE_PRIVATE).edit().putInt(key, val).apply();
    }

    static public void saveData(Context context, String key, boolean val) {
        context.getSharedPreferences(APP, Context.MODE_PRIVATE).edit().putBoolean(key, val).apply();
    }

    static public void deleteData(Context context, String key) {
        context.getSharedPreferences(APP, Context.MODE_PRIVATE).edit().remove(key).apply();
    }

    static public android.content.SharedPreferences.Editor getSharedPrefEditor(Context context, String pref) {
        return context.getSharedPreferences(pref, Context.MODE_PRIVATE).edit();
    }

    static public void saveData(android.content.SharedPreferences.Editor editor) {
        editor.apply();
    }
}
