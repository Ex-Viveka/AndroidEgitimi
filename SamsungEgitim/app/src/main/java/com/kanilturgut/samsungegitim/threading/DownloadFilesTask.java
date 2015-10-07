package com.kanilturgut.samsungegitim.threading;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Author   : kanilturgut
 * Date     : 30/09/15
 * Time     : 12:25
 */
public class DownloadFilesTask extends AsyncTask<String, Integer, Bitmap> {

    private LinearLayout llProgress;
    private ProgressBar mProgressBar;
    private TextView mTextView, tvFinished;

    public DownloadFilesTask(LinearLayout llProgress, TextView tvFinished) {
        this.llProgress = llProgress;
        this.tvFinished = tvFinished;

        this.mProgressBar = (ProgressBar) llProgress.getChildAt(0);
        this.mTextView = (TextView) llProgress.getChildAt(1);
    }

    @Override
    protected void onPreExecute() {
        tvFinished.setVisibility(ProgressBar.INVISIBLE);
        llProgress.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        int countOfURL = params.length;
        for (int i = 0; i < countOfURL; i++) {
            try {
                Thread.sleep(100);
                publishProgress((int) ((i / (float) countOfURL) * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.setProgress(values[0]);
        mTextView.setText("Yükleniyor %" + values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        llProgress.setVisibility(ProgressBar.INVISIBLE);
        tvFinished.setVisibility(ProgressBar.VISIBLE);
    }
}
