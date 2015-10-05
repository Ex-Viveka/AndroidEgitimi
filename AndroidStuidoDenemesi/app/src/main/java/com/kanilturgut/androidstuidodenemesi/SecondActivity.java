package com.kanilturgut.androidstuidodenemesi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextView = (TextView) findViewById(R.id.tvSecondActivity);

        String sayi = getIntent().getStringExtra("key");

        if (!TextUtils.isEmpty(sayi)) {
            Log.i("SecondActivity", "Bos string gelmedi");
            mTextView.setText("Yazdiginiz sayi : " + sayi);
        } else {
            Log.i("SecondActivity", "Bos string geldi");
        }

    }
}
