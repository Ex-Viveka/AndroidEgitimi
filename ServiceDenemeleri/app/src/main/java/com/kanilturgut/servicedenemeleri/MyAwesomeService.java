package com.kanilturgut.servicedenemeleri;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyAwesomeService extends Service {

    private final String TAG = MyAwesomeService.class.getSimpleName();

    Handler mHandler;
    Runnable mRunnable;

    int index = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = this;

        mHandler = new Handler();       // patron
        mRunnable = new Runnable() {    // isci
            @Override
            public void run() {
                Log.e(TAG, "Runnable calisti");

                mHandler.postDelayed(mRunnable, 3000);

                index++;

                if (index > 5) {
                    stopSelf();
                }
            }
        };

        mHandler.post(mRunnable);


        Toast.makeText(context, "asdsad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {

        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }
}
