package com.kanilturgut.dialogdenemeleri;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ProgressDialogAcitivity extends AppCompatActivity {

    Button bShowProgressDialog;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog_acitivity);

        bShowProgressDialog = (Button) findViewById(R.id.bShowProgressDialog);
        bShowProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
            }
        });
    }

    private void showProgressDialog() {

        pd = ProgressDialog.show(ProgressDialogAcitivity.this, "Title",
                "message");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pd.dismiss();
            }
        }, 3000);
    }
}
