package com.kanilturgut.androidstuidodenemesi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageview;
    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview = (ImageView) findViewById(R.id.ivCat);
        textView = (TextView) findViewById(R.id.tvCatWord);
        editText = (EditText) findViewById(R.id.etInputCatWord);

        button = (Button) findViewById(R.id.bClickMe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText();
            }
        });
    }


    private void changeText() {

        String yazi = editText.getText().toString();

        if (TextUtils.isEmpty(yazi)) {
            Toast.makeText(MainActivity.this, "Bos String", Toast.LENGTH_SHORT).show();
        } else {
            textView.setText(editText.getText());
        }
    }
}
