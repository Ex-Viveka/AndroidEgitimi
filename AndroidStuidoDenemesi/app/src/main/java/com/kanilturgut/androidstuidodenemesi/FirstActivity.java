package com.kanilturgut.androidstuidodenemesi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        final EditText editText = (EditText) findViewById(R.id.etFirstActivity);

        Button button = (Button) findViewById(R.id.bGotoSecond);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FirstActivity", "Butona basildi");

                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("key", editText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
