package com.kanilturgut.samsungegitim.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.kanilturgut.samsungegitim.R;

public class DialogsActivity extends AppCompatActivity implements YesNoDialog.DialogListener {

    private final String TAG = DialogsActivity.class.getSimpleName();
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        createDialog();

    }

    private void createDialog() {
        DialogFragment dialog = new YesNoDialog();

        Bundle args = new Bundle();
        args.putString("title", "My Title");
        args.putString("message", "Read This Message Carefully!!!");
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "tag");
    }

    @Override
    public void onYes() {
        Log.i(TAG, "Yes");
    }

    @Override
    public void onNo() {
        Log.i(TAG, "No");
    }

    @Override
    public void onNeutral() {
        Log.i(TAG, "Neutral");
    }
}
