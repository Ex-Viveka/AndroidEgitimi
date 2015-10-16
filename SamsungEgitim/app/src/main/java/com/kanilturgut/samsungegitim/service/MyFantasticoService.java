package com.kanilturgut.samsungegitim.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MyFantasticoService extends Service {

    Handler mHandler;
    Runnable mRunnable;

    int index = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;

//        Service.START_NOT_STICKY

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = this;

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("Service", "Bla bla");

                mHandler.postDelayed(mRunnable, 2000);

                index++;

//                if (index > 5) {
//                    stopSelf();
//                }
            }
        };

        mHandler.post(mRunnable);
    }
}
