package com.gayratrakhimov.jobdispatcherplayground;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class PreferencesUtil {

    public static String getLog(Context context) {
        return getString(context, "log", "");
    }

    public static void updateLog(Context context, String newLog) {
        String logs = getString(context, "log", "");
        if (!TextUtils.isEmpty(logs)) {
            logs = logs + "\n";
        }
        logs = logs + newLog;
        setString(context, "log", logs);
    }

    public static void clearLog(Context context){
        setString(context, "logs", "");
    }

    private static String getString(Context context, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences("logs", 0);
        return settings.getString(key, defValue);
    }

    private static void setString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences("logs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

}
