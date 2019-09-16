package com.ohtel.ohtel.database;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceDB {

    private static SharedPreferenceDB instance;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static SharedPreferenceDB defaultInstance() {
        if (instance == null) {
            instance = new SharedPreferenceDB();
        }
        return instance;
    }

    public boolean checkLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences("BhiMart", Context.MODE_PRIVATE);
        return pref.getBoolean("isLogin", false);
    }

    public void saveLogin(Context context, boolean isLogin) {
        SharedPreferences pref = context.getSharedPreferences("BhiMart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLogin", isLogin);
        editor.commit();
    }


}
