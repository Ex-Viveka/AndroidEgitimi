package com.kanilturgut.externalfontdenemesi;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Context mContext = this;

    private TextView tvDefault, tvLightFont, tvRegularFont, tvBoldFont;

    private LinearLayout llMain;

    private AssetManager mAssetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDefault = (TextView) findViewById(R.id.tvDefault);
        tvLightFont = (TextView) findViewById(R.id.tvLightFont);
        tvRegularFont = (TextView) findViewById(R.id.tvRegularFont);
        tvBoldFont = (TextView) findViewById(R.id.tvBoldFont);

        llMain = (LinearLayout) findViewById(R.id.llMainLayout);

        TextView newTextView = new TextView(mContext);
        newTextView.setText("elle ekledim");
        newTextView.setTextColor(Color.BLUE);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        newTextView.setLayoutParams(layoutParams);
        newTextView.setPadding(10, 10, 10, 10);

        llMain.addView(newTextView);


//        mAssetManager = getAssets();

//        tvLightFont.setTypeface(Typeface.createFromAsset(mAssetManager, "fonts/OpenSans-Light.ttf"));
//        tvRegularFont.setTypeface(Typeface.createFromAsset(mAssetManager, "fonts/OpenSans-Regular.ttf"));
//        tvBoldFont.setTypeface(Typeface.createFromAsset(mAssetManager, "fonts/OpenSans-Semibold.ttf"));

        tvLightFont.setTypeface(FontCache.get(FontCache.LIGHT, getApplicationContext()));
        tvRegularFont.setTypeface(FontCache.get(FontCache.REGULAR, getApplicationContext()));
        tvBoldFont.setTypeface(FontCache.get(FontCache.BOLD, getApplicationContext()));


    }
}
