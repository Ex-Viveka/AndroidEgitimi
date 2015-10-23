package com.kanilturgut.phonemarketapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kanilturgut.phonemarketapp.R;
import com.kanilturgut.phonemarketapp.backend.Endpoints;
import com.kanilturgut.phonemarketapp.backend.Rest;
import com.kanilturgut.phonemarketapp.backend.pojo.LoginPojo;
import com.kanilturgut.phonemarketapp.helper.MySharedPreferences;
import com.kanilturgut.phonemarketapp.model.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashActivity extends AppCompatActivity {

    private final String TAG = SplashActivity.class.getSimpleName();
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MySharedPreferences mySharedPreferences = MySharedPreferences.getInstance(mContext);

        String email = mySharedPreferences.getUser().getEmail();
        final String pass = mySharedPreferences.getUser().getPassword();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {

            Rest.getInstance().createService(Endpoints.class)
                    .login(new LoginPojo(email, pass), new Callback<User>() {
                        @Override
                        public void success(User user, Response response) {

                            user.setPassword(pass);
                            User.setMe(user);

                            startActivity(new Intent(mContext, MainActivity.class));
                            finish();
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                }
            }, 1500);
        }
    }
}
