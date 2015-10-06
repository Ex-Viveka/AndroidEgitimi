package com.kanilturgut.resourcesdenemeleri;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        int beyazRenk = getResources().getColor(R.color.beyaz);
        mTextView.setTextColor(beyazRenk);
    }
}
