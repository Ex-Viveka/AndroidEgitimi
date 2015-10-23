package com.kanilturgut.phonemarketapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.kanilturgut.phonemarketapp.model.User;

/**
 * Author   : kanilturgut
 * Date     : 21/10/15
 * Time     : 17:48
 */
public class MySharedPreferences {

    private static final String PREFERENCE_NAME = "phone_market_preferences";

    private static MySharedPreferences instance;

    private final static String PREF_KEY_EMAIL = "phone_market_email";
    private final static String PREF_KEY_PASSWORD = "phone_market_password";

    private SharedPreferences sp;

    private MySharedPreferences(Context context) {
        sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static MySharedPreferences getInstance(Context context) {

        if (instance == null)
            instance = new MySharedPreferences(context);

        return instance;
    }

    // login ile gelen user ı sp ye kaydeder
    public void setUserPref(User user) {

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PREF_KEY_EMAIL, user.getEmail());
        editor.putString(PREF_KEY_PASSWORD, user.getPassword());
        editor.commit();
    }

    // sp deki user ı döner
    public User getUserFromPref() {

        User user = new User();

        user.setEmail(sp.getString(PREF_KEY_EMAIL, null));
        user.setPassword(sp.getString(PREF_KEY_PASSWORD, null));

        return user;
    }

    // sp den user ı siler
    public void deleteUserFromPref() {

        SharedPreferences.Editor editor = sp.edit();

        editor.remove(PREF_KEY_EMAIL);
        editor.remove(PREF_KEY_PASSWORD);

        editor.commit();
    }
}
