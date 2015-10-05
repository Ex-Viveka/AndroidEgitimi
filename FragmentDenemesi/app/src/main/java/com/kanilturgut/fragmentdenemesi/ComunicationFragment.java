package com.kanilturgut.fragmentdenemesi;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Author   : kanilturgut
 * Date     : 05/10/15
 * Time     : 17:47
 */
public class ComunicationFragment extends Fragment {

    private EditText etFragment;
    private Button bFragment;
    private ButtonClickedInterface listener;


    public interface ButtonClickedInterface {
        void butonaBasildi(String text);
    }


    public static ComunicationFragment newInstance(String buttonText) {

        ComunicationFragment comunicationFragment = new ComunicationFragment();

        Bundle arguments = new Bundle();
        arguments.putString("text", buttonText);

        comunicationFragment.setArguments(arguments);

        return comunicationFragment;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ButtonClickedInterface) {
            listener = (ButtonClickedInterface) activity;
        } else {
            throw new ClassCastException(activity.toString() +
                    "ButtonCLickedInterface i implement etmemistir");
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        Activity activity = (Activity) context;
//
//        if (activity instanceof ButtonClickedInterface) {
//            listener = (ButtonClickedInterface) activity;
//        } else {
//            throw new ClassCastException(activity.toString() +
//                    "ButtonCLickedInterface i implement etmemistir");
//        }
//
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_communication, container, false);

        etFragment = (EditText) rootView.findViewById(R.id.etFragmentText);

        bFragment = (Button) rootView.findViewById(R.id.bFragment);
        bFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.butonaBasildi(etFragment.getText().toString());
            }
        });

        String buttonTexti = getArguments().getString("text");
        bFragment.setText(buttonTexti);

        return rootView;
    }
}
