package com.kanilturgut.samsungegitim.navigation_with_data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.kanilturgut.samsungegitim.R;

public class SecondActivity extends AppCompatActivity {

    public static String EXTRA_DATA = "givenNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String givenNumber = getIntent().getStringExtra(EXTRA_DATA);
        TextView tvGivenNumber = (TextView) findViewById(R.id.tvGivenNumber);

        if (TextUtils.isEmpty(givenNumber)) {
            tvGivenNumber.setText("Böyle bir içerik bulunamadı");
        } else {
            tvGivenNumber.setText("Seçilen Sayı : " + givenNumber);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
