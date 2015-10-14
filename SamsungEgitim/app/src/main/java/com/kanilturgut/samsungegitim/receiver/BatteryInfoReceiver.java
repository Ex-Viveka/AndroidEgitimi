package com.kanilturgut.samsungegitim.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatteryInfoReceiver extends BroadcastReceiver {
    public BatteryInfoReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "charging", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "discharging", Toast.LENGTH_SHORT).show();
        }
    }
}
