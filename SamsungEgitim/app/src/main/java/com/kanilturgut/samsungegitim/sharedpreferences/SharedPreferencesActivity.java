package com.kanilturgut.samsungegitim.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kanilturgut.samsungegitim.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    private final String TAG = SharedPreferencesActivity.class.getSimpleName();
    private Context mContext = this;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);


        // default shared preferences
        SharedPreferences defaultPref = PreferenceManager.getDefaultSharedPreferences(mContext);

        // specific shared preferences
        mPreferences = getSharedPreferences("PersonalPrefs", MODE_PRIVATE);


        findViewById(R.id.bStore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeSomeData();
            }
        });

        findViewById(R.id.bAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessData();
            }
        });

        findViewById(R.id.bDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePrefs();
            }
        });
    }


    private void storeSomeData() {

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("email", "kanilturgut@asd.com");
        editor.putString("password", "123456");
        editor.commit();
    }

    private void accessData() {

        String email = mPreferences.getString("email", "no value");
        String password = mPreferences.getString("password", "no value");
        String phoneNumber = mPreferences.getString("phone", "05551231234");

        Log.i(TAG, "email : " + email);
        Log.i(TAG, "password : " + password);
        Log.i(TAG, "phoneNumber : " + phoneNumber);

    }

    private void deletePrefs() {

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.remove("phone");
        editor.commit();
    }
}
