package com.kanilturgut.homework2;

import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kanilturgut.homework2.fragment.TextChangeFragment;
import com.kanilturgut.homework2.fragment.TextColorChangeFragment;
import com.kanilturgut.homework2.fragment.TextStyleChangeFragment;

public class MainActivity extends AppCompatActivity implements TextChangeFragment.TextChangeListener,
        TextColorChangeFragment.TextColorChangeListener, TextStyleChangeFragment.TextStyleChangeListener {

    private TextView tvChangeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvChangeText = (TextView) findViewById(R.id.tvChangeText);

        // fragmentlari olusturup transaction baslatalim
        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
        ft1.replace(R.id.textFrame, new TextChangeFragment());
        ft1.commit();

        FragmentTransaction ft2 = getFragmentManager().beginTransaction();
        ft2.replace(R.id.colorFrame, new TextColorChangeFragment());
        ft2.commit();

        FragmentTransaction ft3 = getFragmentManager().beginTransaction();
        ft3.replace(R.id.styleFrame, new TextStyleChangeFragment());
        ft3.commit();
    }

    @Override
    public void textChange(String text) {
        // TextChangeFragment.TextChangeListener
        tvChangeText.setText(text);
    }

    @Override
    public void colorChange(int color) {
        // TextColorChangeFragment.TextColorChangeListener
        tvChangeText.setTextColor(color);
    }

    @Override
    public void textStyleBold() {
        // TextStyleChangeFragment.TextStyleChangeListener
        tvChangeText.setTypeface(null, Typeface.BOLD);
    }

    @Override
    public void textStyleItalic() {
        // TextStyleChangeFragment.TextStyleChangeListener
        tvChangeText.setTypeface(null, Typeface.ITALIC);
    }

    @Override
    public void textStyleNormal() {
        // TextStyleChangeFragment.TextStyleChangeListener
        tvChangeText.setTypeface(null, Typeface.NORMAL);
    }
}
