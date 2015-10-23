package com.kanilturgut.phonemarketapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kanilturgut.phonemarketapp.R;
import com.kanilturgut.phonemarketapp.activity.LoginActivity;
import com.kanilturgut.phonemarketapp.backend.Endpoints;
import com.kanilturgut.phonemarketapp.backend.Rest;
import com.kanilturgut.phonemarketapp.backend.pojo.SettingsPojo;
import com.kanilturgut.phonemarketapp.backend.pojo.UserSettingsPojo;
import com.kanilturgut.phonemarketapp.helper.MySharedPreferences;
import com.kanilturgut.phonemarketapp.model.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ProfileSettingsFragment extends Fragment {

    private String TAG = ProfileSettingsFragment.class.getSimpleName();
    private Context mContext;
    private EditText etEmail, etFistName, etLastName, etPassword;
    private Button bSave, bLogout;

    String userId;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_settings, container, false);

        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etFistName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etPassword = (EditText) view.findViewById(R.id.etPassword);

        bSave = (Button) view.findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etFistName.getText().toString().trim();
                String lastname = etLastName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                User user = new User();
                user.setId(userId);

                if (!TextUtils.isEmpty(name))
                    user.setFirstName(name);

                if (!TextUtils.isEmpty(lastname))
                    user.setLastName(lastname);

                if (!TextUtils.isEmpty(password))
                    user.setPassword(password);

                changeUserSettings(user);
            }
        });

        bLogout = (Button) view.findViewById(R.id.bLogout);
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreferences.getInstance(mContext).deleteUser();
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        userId = MySharedPreferences.getInstance(mContext).getUser().getId();
        getUserInformation(userId);

        return view;
    }

    private void getUserInformation(String id) {

        Rest.getInstance().createService(Endpoints.class)
                .getUserSettings(new SettingsPojo(id),
                        new Callback<User>() {
                            @Override
                            public void success(User user, Response response) {
                                if (user != null) {
                                    etEmail.setText(user.getEmail());
                                    etFistName.setText(user.getFirstName());
                                    etLastName.setText(user.getLastName());
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.e(TAG, "ERROR", error);
                            }
                        });
    }

    private void changeUserSettings(User user) {

        final ProgressDialog pd = ProgressDialog.show(mContext, "Lütfen Bekleyiniz", "Kaydediliyor", false, false);

        Rest.getInstance().createService(Endpoints.class)
                .changeUserSettings(new UserSettingsPojo(user.getId(), user.getFirstName(), user.getLastName(),
                        user.getPassword()), new Callback<User>() {
                    @Override
                    public void success(User user, Response response) {
                        if (user != null) {
                            etEmail.setText(user.getEmail());
                            etFistName.setText(user.getFirstName());
                            etLastName.setText(user.getLastName());

                            User.setMe(user);
                            MySharedPreferences.getInstance(mContext).setUserPref(user);
                        }

                        pd.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "ERROR", error);
                        pd.dismiss();
                    }
                });
    }
}
