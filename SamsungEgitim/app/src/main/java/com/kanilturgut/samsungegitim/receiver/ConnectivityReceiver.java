package com.kanilturgut.samsungegitim.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kanilturgut.samsungegitim.receiver.helper.ConnectionManager;

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isConnected = ConnectionManager.isConnectedToInternet(context);

        if (!isConnected) {
            Toast.makeText(context, "Ooops network is down!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
