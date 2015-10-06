package com.kanilturgut.homework2.fragment;

import android.app.Activity;
import android.app.Fragment;
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
public class TextStyleChangeFragment extends Fragment {

    private TextStyleChangeListener listener;

    public interface TextStyleChangeListener {
        void textStyleBold();

        void textStyleItalic();

        void textStyleNormal();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof TextStyleChangeListener) {
            listener = (TextStyleChangeListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + " is not" +
                    "implemented TextChangeListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_style_changed, container, false);

        Button bTextStyleNormal = (Button) rootView.findViewById(R.id.bTextStyleNormal);
        bTextStyleNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.textStyleNormal();
            }
        });

        Button bTextStyleBold = (Button) rootView.findViewById(R.id.bTextStyleBold);
        bTextStyleBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.textStyleBold();
            }
        });

        Button bTextStyleItalic = (Button) rootView.findViewById(R.id.bTextStyleItalic);
        bTextStyleItalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.textStyleItalic();
            }
        });


        return rootView;
    }
}
