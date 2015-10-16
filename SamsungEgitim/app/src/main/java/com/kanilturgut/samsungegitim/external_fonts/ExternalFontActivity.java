package com.kanilturgut.samsungegitim.external_fonts;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kanilturgut.samsungegitim.R;

public class ExternalFontActivity extends AppCompatActivity {

    TextView tvDefault, tvLight, tvRegular, tvSemiBold;
    AssetManager mAssetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_font);

        tvDefault = (TextView) findViewById(R.id.tvDefault);
        tvLight = (TextView) findViewById(R.id.tvLightFont);
        tvRegular = (TextView) findViewById(R.id.tvRegularFont);
        tvSemiBold = (TextView) findViewById(R.id.tvSemiboldFont);

        mAssetManager = getAssets();

        // this is not a good approach as android tries to load fonts from assets every single setTypeface call
//        tvLight.setTypeface(Typeface.createFromAsset(mAssetManager, "fonts/OpenSans-Light.ttf"));
//        tvRegular.setTypeface(Typeface.createFromAsset(mAssetManager, "fonts/OpenSans-Regular.ttf"));
//        tvSemiBold.setTypeface(Typeface.createFromAsset(mAssetManager, "fonts/OpenSans-Semibold.ttf"));


        tvLight.setTypeface(FontCache.get(FontCache.LIGHT, getApplicationContext()));
        tvRegular.setTypeface(FontCache.get(FontCache.REGULAR, getApplicationContext()));
        tvSemiBold.setTypeface(FontCache.get(FontCache.BOLD, getApplicationContext()));
    }
}
