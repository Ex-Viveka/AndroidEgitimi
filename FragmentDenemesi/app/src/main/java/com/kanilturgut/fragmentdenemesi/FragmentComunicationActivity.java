package com.kanilturgut.fragmentdenemesi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FragmentComunicationActivity extends AppCompatActivity implements ComunicationFragment.ButtonClickedInterface {

    private TextView tvActivityText;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_comunication);

        tvActivityText = (TextView) findViewById(R.id.tvActivityText);

        // text view renk degistirme
        tvActivityText.setTextColor(Color.RED);

        fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ComunicationFragment comunicationFragment = ComunicationFragment.newInstance("Click Me!");
        ft.replace(R.id.mainFrame, comunicationFragment);
        ft.commit();
    }

    @Override
    public void butonaBasildi(String text) {
        tvActivityText.setText(text);
    }
}
