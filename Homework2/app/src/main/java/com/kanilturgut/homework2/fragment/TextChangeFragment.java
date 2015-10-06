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
public class TextChangeFragment extends Fragment {

    private TextChangeListener listener;

    public interface TextChangeListener {
        void textChange(String text);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof TextChangeListener) {
            listener = (TextChangeListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + " is not" +
                    "implemented TextChangeListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_changed, container, false);

        Button bText1 = (Button) rootView.findViewById(R.id.bText1);
        bText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.textChange("Bilişim");
            }
        });

        Button bText2 = (Button) rootView.findViewById(R.id.bText2);
        bText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.textChange("Mucitleri");
            }
        });

        Button bText3 = (Button) rootView.findViewById(R.id.bText3);
        bText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.textChange("Eğitimi");
            }
        });


        return rootView;
    }
}
