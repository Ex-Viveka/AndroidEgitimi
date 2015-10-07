package com.kanilturgut.sharedpreferencesdenemeleri;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();
    private Context mContext = this;

    private Button bSakla, bGetir, bSil;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bSakla = (Button) findViewById(R.id.bSakla);
        bGetir = (Button) findViewById(R.id.bGetir);
        bSil = (Button) findViewById(R.id.bSil);

        bSakla.setOnClickListener(this);
        bGetir.setOnClickListener(this);
        bSil.setOnClickListener(this);

//        Toast.makeText(mContext, "1", Toast.LENGTH_SHORT).show();
//        Toast.makeText(mContext, "2", Toast.LENGTH_SHORT).show();
//        Toast.makeText(mContext, "3", Toast.LENGTH_SHORT).show();
//        Toast.makeText(mContext, "4", Toast.LENGTH_SHORT).show();

        mSharedPreferences = createSP();
    }

    private SharedPreferences createSP() {
        return getSharedPreferences("my_preferences", MODE_PRIVATE);
    }

    private void bilgileriKaydet() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("name", "osman");
        editor.putString("surname", "osmanoglu");
        editor.putInt("age", 52);
        editor.putFloat("weight", (float) 72.5);
        editor.commit();
    }

    private void bilgileriGetir() {

        String adi;             // selami
        String soyadi;          // şahin
        int yasi;               // 64
        float kilosu;           // 67.8
        boolean arabasiVarmi;   // var

        adi = mSharedPreferences.getString("name", "selami");
        soyadi = mSharedPreferences.getString("surname", "şahin");
        yasi = mSharedPreferences.getInt("age", 64);
        kilosu = mSharedPreferences.getFloat("weight", (float) 67.8);
        arabasiVarmi = mSharedPreferences.getBoolean("arabasiVarMi", true);

        Log.i(TAG, "adi : " + adi);
        Log.i(TAG, "soyadi : " + soyadi);
        Log.i(TAG, "yasi : " + String.valueOf(yasi));
        Log.i(TAG, "kilosu : " + String.valueOf(kilosu));
        Log.i(TAG, "arabasiVarmi : " + arabasiVarmi);
    }

    private void bilgileriSil() {

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove("name");
        editor.remove("surname");
        editor.remove("age");
        editor.remove("weight");
        editor.remove("arabasiVarmi");
        editor.commit();
    }

    @Override
    public void onClick(View v) {

        if (v == bSakla) {
            bilgileriKaydet();
        } else if (v == bGetir) {
            bilgileriGetir();
        } else if (v == bSil) {
            bilgileriSil();
        }
    }
}
