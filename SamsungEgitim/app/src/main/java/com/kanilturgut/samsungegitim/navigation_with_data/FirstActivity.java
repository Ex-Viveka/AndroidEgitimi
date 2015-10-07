package com.kanilturgut.samsungegitim.navigation_with_data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kanilturgut.samsungegitim.R;

public class FirstActivity extends AppCompatActivity {

    private Context mContext = this;
    private EditText etNumber;
    private Button bGotoSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        etNumber = (EditText) findViewById(R.id.etNumber);

        bGotoSecondActivity = (Button) findViewById(R.id.bGotoSecond);
        bGotoSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = etNumber.getText().toString();

                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(mContext, "Lütfen bir sayı giriniz", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(mContext, SecondActivity.class);
                    intent.putExtra(SecondActivity.EXTRA_DATA, number);
                    startActivity(intent);

                    String url = "http://www.example.com";
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url));
                    startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
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
