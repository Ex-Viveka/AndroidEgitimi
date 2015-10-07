package com.kanilturgut.samsungegitim.threading;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kanilturgut.samsungegitim.R;

public class ThreadingActivity extends AppCompatActivity {

    private final String TAG = ThreadingActivity.class.getSimpleName();
    private Context mContext = this;

    private LinearLayout llProgress;
    private TextView tvFinished;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threading);

        llProgress = (LinearLayout) findViewById(R.id.llProgress);
        tvFinished = (TextView) findViewById(R.id.tvFinished);

        String[] urlList = new String[25];
        for (int i = 0; i < 25; i++) {
            urlList[i] = "http://birseyler.com/" + i + "/resim.png";
        }

        DownloadFilesTask downloadFilesTask = new DownloadFilesTask(llProgress, tvFinished);
        downloadFilesTask.execute(urlList);
//        downloadFilesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urlList);


        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                Log.i(TAG, "diger operasyon basladi");

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Log.i(TAG, "diger operasyon bitti");
            }
        }.execute();
//        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                Log.i(TAG, "Runnable calisti " + System.currentTimeMillis());
            }
        };

        handler.postDelayed(runnable, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();

        handler.removeCallbacks(runnable);
    }
}
