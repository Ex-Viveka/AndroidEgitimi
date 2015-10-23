package com.kanilturgut.phonemarketapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kanilturgut.phonemarketapp.helper.MySharedPreferences;
import com.kanilturgut.phonemarketapp.R;
import com.kanilturgut.phonemarketapp.backend.Endpoints;
import com.kanilturgut.phonemarketapp.backend.Rest;
import com.kanilturgut.phonemarketapp.backend.pojo.LoginPojo;
import com.kanilturgut.phonemarketapp.model.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = LoginActivity.class.getSimpleName();
    private Context mContext = this;

    private EditText etMail, etPassword;
    private Button bLogin;
    private TextView tvDoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvDoRegister = (TextView) findViewById(R.id.tvGoRegister);

        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etMail.getText().toString();
                String password = etPassword.getText().toString();

                doLogin(email, password);
            }
        });

        tvDoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
    }

    private void doLogin(String email, final String password) {

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "Please fill all areas", Toast.LENGTH_SHORT).show();
        } else {

            Rest.getInstance().createService(Endpoints.class)
                    .login(new LoginPojo(email, password), new Callback<User>() {
                        @Override
                        public void success(User user, Response response) {

                            user.setPassword(password);

                            User.setMe(user);

                            MySharedPreferences.getInstance(mContext).setUserPref(user);

                            startActivity(new Intent(mContext, MainActivity.class));
                            finish();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e(TAG, "yok");
                        }
                    });
        }
    }
}
