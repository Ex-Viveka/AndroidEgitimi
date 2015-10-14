package com.kanilturgut.samsungegitim.receiver;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kanilturgut.samsungegitim.R;

public class ConnectivityReceiverActivity extends AppCompatActivity {

    Context mContext = this;
    ConnectivityReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity_receiver_denemesi);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mConnectivityReceiver = new ConnectivityReceiver();
        registerReceiver(mConnectivityReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mConnectivityReceiver);

        super.onPause();
    }
}
