package com.kanilturgut.fragmentdenemesi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.kanilturgut.fragmentdenemesi.fragment.FirstFragment;
import com.kanilturgut.fragmentdenemesi.fragment.SecondFragment;
import com.kanilturgut.fragmentdenemesi.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm;

    FrameLayout frame1, frame2, frame3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set fragment manager
        fm = getFragmentManager();

        frame1 = (FrameLayout) findViewById(R.id.frameLayout1);
        frame2 = (FrameLayout) findViewById(R.id.frameLayout2);
        frame3 = (FrameLayout) findViewById(R.id.frameLayout3);

        getFragment1();
        getFragment2();
        getFragment3();
    }

    /*
        Create first fragment and replace it with frameLayout1
     */
    private void getFragment1() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout1, new FirstFragment());
        ft.commit();
    }

    /*
        Create second fragment and replace it with frameLayout2
     */
    private void getFragment2() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout2, new SecondFragment());
        ft.commit();
    }

    /*
        Create third fragment and replace it with frameLayout3
     */
    private void getFragment3() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout3, new ThirdFragment());
        ft.commit();
    }
}
