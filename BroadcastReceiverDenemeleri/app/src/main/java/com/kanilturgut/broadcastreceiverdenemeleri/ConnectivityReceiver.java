package com.kanilturgut.broadcastreceiverdenemeleri;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kanilturgut.broadcastreceiverdenemeleri.helper.ConnectionManager;

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isConnected = ConnectionManager.isConnectedToInternet(context);

        if (!isConnected) {
            Toast.makeText(context, "Aman Tanrım internetten düştük !!", Toast.LENGTH_SHORT).show();
        }
    }
}
