package com.kanilturgut.homework2.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kanilturgut.homework2.R;

/**
 * Author   : kanilturgut
 * Date     : 06/10/15
 * Time     : 08:26
 */
public class TextColorChangeFragment extends Fragment {

    private TextColorChangeListener listener;

    public interface TextColorChangeListener {
        void colorChange(int color);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof TextColorChangeListener) {
            listener = (TextColorChangeListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + " is not" +
                    "implemented TextChangeListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_color_changed, container, false);

        Button bTextColorRed = (Button) rootView.findViewById(R.id.bTextColorRed);
        bTextColorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.colorChange(Color.RED);
            }
        });

        Button bTextColorGreen = (Button) rootView.findViewById(R.id.bTextColorGreen);
        bTextColorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.colorChange(Color.GREEN);
            }
        });

        Button bTextColorBlue = (Button) rootView.findViewById(R.id.bTextColorBlue);
        bTextColorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.colorChange(Color.BLUE);
            }
        });


        return rootView;
    }
}
