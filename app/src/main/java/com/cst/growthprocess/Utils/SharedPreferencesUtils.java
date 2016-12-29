package com.cst.growthprocess.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPreferencesUtils {


    public static final String KEY_IS_CLICKED_ADD_FRIEND = "KEY_IS_CLICKED_ADD_FRIEND";


    public static boolean isClickedAddFriend(Context context) {
        return getBoolean(context, KEY_IS_CLICKED_ADD_FRIEND, false);
    }


    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences settings = getAppSettingsSP(context);
        return settings.getBoolean(key, defValue);
    }


    private static SharedPreferences getAppSettingsSP(Context context) {
        return context
                .getSharedPreferences("settings_", Context.MODE_PRIVATE);
    }
}