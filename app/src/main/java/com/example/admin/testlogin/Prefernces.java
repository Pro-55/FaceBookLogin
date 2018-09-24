package com.example.admin.testlogin;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefernces {

    public static final String LOGIN_FLAG = "login_flag";

    public static boolean getLoginFlag(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(LOGIN_FLAG, false);
    }

    public static void setLoginFlag(Context context, boolean isLoggedIn) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_FLAG, isLoggedIn);
        editor.commit();
    }
}
