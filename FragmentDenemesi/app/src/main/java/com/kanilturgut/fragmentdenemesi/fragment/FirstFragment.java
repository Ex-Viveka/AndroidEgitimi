package com.kanilturgut.fragmentdenemesi.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kanilturgut.fragmentdenemesi.R;

/**
 * Author   : kanilturgut
 * Date     : 05/10/15
 * Time     : 16:39
 */
public class FirstFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        return rootView;
    }
}
