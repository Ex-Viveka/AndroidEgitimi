package com.kanilturgut.servicedenemeleri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = this;

    private Button bStart, bStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bStart = (Button) findViewById(R.id.bStartService);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(mContext, MyAwesomeService.class));
            }
        });

        bStop = (Button) findViewById(R.id.bStopService);
        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(mContext, MyAwesomeService.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(mContext, MyAwesomeService.class));
    }

    @Override
    protected void onStop() {
        stopService(new Intent(mContext, MyAwesomeService.class));
        super.onStop();
    }
}
