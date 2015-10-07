package com.kanilturgut.samsungegitim.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Author   : kanilturgut
 * Date     : 28/09/15
 * Time     : 13:45
 */
public class YesNoDialog extends DialogFragment {

    public YesNoDialog() {
    }

    public interface DialogListener {

        void onYes();

        void onNo();

        void onNeutral();
    }

    DialogListener mDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mDialogListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement bla bla");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogListener.onYes();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogListener.onNo();
                    }
                })
                .setNeutralButton("OOPS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogListener.onNeutral();
                    }
                })
                .create();
    }
}
