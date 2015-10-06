package com.kanilturgut.homework1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TAG = MainActivity olacak
    private String TAG = MainActivity.class.getSimpleName();

    private TextView tvChangeText;

    // Yazıyı degistiren buttonlar
    private Button bText1, bText2, bText3;

    // Yazı rengini degistiren butonlar
    private Button bTextColorRed, bTextColorGreen, bTextColorBlue;

    // Yazının stilini degistiren butonlar
    private Button bTextStyleNormal, bTextStyleBold, bTextStyleItalic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    // View componentları ve onların onClicklListener' larını set edelim
    private void initialize() {

        tvChangeText = (TextView) findViewById(R.id.tvChangeText);

        bText1 = (Button) findViewById(R.id.bText1);
        bText1.setOnClickListener(textChangeListener);

        bText2 = (Button) findViewById(R.id.bText2);
        bText2.setOnClickListener(textChangeListener);

        bText3 = (Button) findViewById(R.id.bText3);
        bText3.setOnClickListener(textChangeListener);

        bTextColorRed = (Button) findViewById(R.id.bTextColorRed);
        bTextColorRed.setOnClickListener(colorChangeListener);

        bTextColorGreen = (Button) findViewById(R.id.bTextColorGreen);
        bTextColorGreen.setOnClickListener(colorChangeListener);

        bTextColorBlue = (Button) findViewById(R.id.bTextColorBlue);
        bTextColorBlue.setOnClickListener(colorChangeListener);


        bTextStyleNormal = (Button) findViewById(R.id.bTextStyleNormal);
        bTextStyleNormal.setOnClickListener(styleChangeListener);

        bTextStyleBold = (Button) findViewById(R.id.bTextStyleBold);
        bTextStyleBold.setOnClickListener(styleChangeListener);

        bTextStyleItalic = (Button) findViewById(R.id.bTextStyleItalic);
        bTextStyleItalic.setOnClickListener(styleChangeListener);
    }

    // OnClickListener
    View.OnClickListener textChangeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == bText1) {
                tvChangeText.setText("Bilişim");
            } else if (v == bText2) {
                tvChangeText.setText("Mucitleri");
            } else if (v == bText3) {
                tvChangeText.setText("Eğitimi");
            }
        }
    };

    // OnClickListener
    View.OnClickListener colorChangeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == bTextColorRed) {
                tvChangeText.setTextColor(Color.RED);
            } else if (v == bTextColorGreen) {
                tvChangeText.setTextColor(Color.GREEN);
            } else if (v == bTextColorBlue) {
                tvChangeText.setTextColor(Color.BLUE);
            }
        }
    };

    // OnClickListener
    View.OnClickListener styleChangeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == bTextStyleNormal) {
                tvChangeText.setTypeface(null, Typeface.NORMAL);
            } else if (v == bTextStyleBold) {
                tvChangeText.setTypeface(null, Typeface.BOLD);
            } else if (v == bTextStyleItalic) {
                tvChangeText.setTypeface(null, Typeface.ITALIC);
            }
        }
    };

}
