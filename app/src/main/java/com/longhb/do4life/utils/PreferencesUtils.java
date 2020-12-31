package com.longhb.do4life.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import static android.content.Context.MODE_PRIVATE;
import static com.longhb.do4life.utils.Common.MY_PREFS_NAME;

public class PreferencesUtils {
    private static PreferencesUtils INSTANCE;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private PreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public static PreferencesUtils getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesUtils(context);
        }
        return INSTANCE;
    }

    public String getString(String key, String value) {
        return sharedPreferences.getString(key, value);
    }
    public void setString(String key, String value) {
        editor.putString(key, value);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        }
    }
}
