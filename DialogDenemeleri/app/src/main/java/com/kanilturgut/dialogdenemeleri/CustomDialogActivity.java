package com.kanilturgut.dialogdenemeleri;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialogActivity extends AppCompatActivity {

    Button bShowCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        bShowCustomDialog = (Button) findViewById(R.id.bShowCustomDialog);
        bShowCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialoguGoster();
            }
        });
    }

    private void customDialoguGoster() {

        final Dialog dialog = new Dialog(CustomDialogActivity.this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        dialog.setTitle("Title");
        dialog.setCancelable(false);

        final TextView tvCustomTextView = (TextView) dialog.findViewById(R.id.tvCustomTextView);
        tvCustomTextView.setText("Selam Gençler Keyifler Nasıl?");

        final Button bOkey = (Button) dialog.findViewById(R.id.bOkey);
        Button bCancel = (Button) dialog.findViewById(R.id.bCancel);

        bOkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCustomTextView.setText("Demek ki okeymiş");
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
