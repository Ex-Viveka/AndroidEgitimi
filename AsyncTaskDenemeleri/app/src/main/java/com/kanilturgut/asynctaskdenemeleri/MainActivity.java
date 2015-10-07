package com.kanilturgut.asynctaskdenemeleri;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    private TextView tvText;
    private Button bAsync, bTextChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    // initialize, UI bilesenlerinin tanimlanmasi
    private void init() {
        tvText = (TextView) findViewById(R.id.tvText);

        bAsync = (Button) findViewById(R.id.bAsyncOperation);
        bTextChange = (Button) findViewById(R.id.bChangeText);

        bAsync.setOnClickListener(buttonClick);
        bTextChange.setOnClickListener(buttonClick);
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view == bAsync) {
                String[] urller = new String[25];
                for (int i = 0; i < 25; i++) {
                    urller[i] = "http://www.myimages.com/images/image" + i + ".png";
                }

                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute(urller);


            } else if (view == bTextChange) {
                tvText.setText("Degistirdim");
            }

            // looks for id
//            int id = view.getId();
//            if (id == R.id.bAsyncOperation) {
//
//            } else if (id == R.id.bChangeText) {
//
//            }
        }
    };

    private class DownloadTask extends AsyncTask<String, Integer, String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = ProgressDialog.show(mContext, "Lütfen Bekleyiniz",
                    "Bilgilerinizi Getiriliyor...");

            tvText.setText("İşleminiz Başladı");

            Log.i(TAG, "onPreExecute' a girildi");
        }

        @Override
        protected String doInBackground(String... params) {

            Log.i(TAG, "doInBackground' a girildi");

            // 25
            int countOfURL = params.length;
            for (int i = 0; i < countOfURL; i++) {
                try {
                    Thread.sleep(200);
//                    publishProgress((int) ((i / (float) countOfURL) * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(TAG, "onPostExecute' a girildi");
//            Toast.makeText(mContext, "Islem Bitti", Toast.LENGTH_SHORT).show();

            if (pd != null) {
                pd.dismiss();
            }

            tvText.setText("İşlem Bitti, Tebrikler");
        }

    }

}






































