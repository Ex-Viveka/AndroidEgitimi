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
import com.kanilturgut.phonemarketapp.backend.pojo.RegisterPojo;
import com.kanilturgut.phonemarketapp.model.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = RegisterActivity.class.getSimpleName();
    private Context mContext = this;

    private EditText etMail, etPassword, etFirstName, etLastName;
    private Button bRegister;
    private TextView tvGoLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etMail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        tvGoLogin = (TextView) findViewById(R.id.tvGoLogin);

        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etMail.getText().toString();
                String password = etPassword.getText().toString();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();

                doRegister(email, password, firstName, lastName);
            }
        });

        tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void doRegister(String email, final String password, String firstName, String lastName) {

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
            Toast.makeText(mContext, "Please fill all area", Toast.LENGTH_SHORT).show();
        } else {

            Rest.getInstance().createService(Endpoints.class)
                    .register(new RegisterPojo(email, password, firstName, lastName),
                            new Callback<User>() {
                                @Override
                                public void success(User user, Response response) {

                                    user.setPassword(password);
                                    User.setMe(user);

                                    MySharedPreferences.getInstance(mContext).setUserPref(user);

                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.e(TAG, "ERROR", error);
                                }
                            });
        }
    }
}
