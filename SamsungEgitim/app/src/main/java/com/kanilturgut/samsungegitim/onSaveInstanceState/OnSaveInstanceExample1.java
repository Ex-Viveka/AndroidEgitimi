package com.kanilturgut.samsungegitim.onSaveInstanceState;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kanilturgut.samsungegitim.R;

public class OnSaveInstanceExample1 extends AppCompatActivity {

    private Context mContext = this;
    private EditText etFirstText, etSecondText;
    private Button button;


    private final String STATE_FIRST = "state_first";
    private final String STATE_SECOND = "state_second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_save_instance_example1);

        etFirstText = (EditText) findViewById(R.id.etFirstText);
        etSecondText = (EditText) findViewById(R.id.etSecondText);

//        if (savedInstanceState != null) {
//
//            Log.i("TAG", "savedInstanceState");
//
//            etFirstText.setText(savedInstanceState.getString(STATE_FIRST));
//            etSecondText.setText(savedInstanceState.getString(STATE_SECOND));
//        }

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, OnSaveInstanceExample2.class));
            }
        });

    }

//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//
//        Log.i("TAG", "onSaveInstanceState");
//
//        // verileri alalım
//        String first = etFirstText.getText().toString().trim();
//        String second = etSecondText.getText().toString().trim();
//
//        // ekranın o anki durumunu kaydedelim
//        outState.putString(STATE_FIRST, first);
//        outState.putString(STATE_SECOND, second);
//
//        // superclass ı çağırarak methodun temel fonksiyonunu yapmasına olanak sağlıyoruz
//        super.onSaveInstanceState(outState, outPersistentState);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_on_save_instance_example1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
