package com.kanilturgut.broadcastreceiverdenemeleri;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Context mContext = this;

    private ConnectivityReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnectivityReceiver = new ConnectivityReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mConnectivityReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
        ));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mConnectivityReceiver);

        super.onPause();
    }
}
