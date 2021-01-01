package com.longhb.do4life.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import static android.content.Context.MODE_PRIVATE;
import static com.longhb.do4life.utils.Common.MY_PREFS_NAME;

public class SharedUtils {
    private static SharedUtils INSTANCE;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public static SharedUtils getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SharedUtils(context);
        }
        return INSTANCE;
    }

    public String getString(String key, String valueDefault) {
        return sharedPreferences.getString(key, valueDefault);
    }
    public boolean getBoolean(String key, boolean valueDefault) {
        return sharedPreferences.getBoolean(key, valueDefault);
    }
    public void setString(String key, String value) {
        editor.putString(key, value);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        }
    }
    public void setBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        }
    }
}
