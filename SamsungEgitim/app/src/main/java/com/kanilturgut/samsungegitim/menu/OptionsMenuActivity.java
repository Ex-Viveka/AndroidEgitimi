package com.kanilturgut.samsungegitim.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.kanilturgut.samsungegitim.R;

public class OptionsMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_popup_first:
                        Toast.makeText(OptionsMenuActivity.this, "First!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_popup_second:
                        Toast.makeText(OptionsMenuActivity.this, "Second!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_popup_third:
                        Toast.makeText(OptionsMenuActivity.this, "Third!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_popup_fourth:
                        Toast.makeText(OptionsMenuActivity.this, "Fourth!", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popup.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_first) {
            Toast.makeText(OptionsMenuActivity.this, "1. item", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_second) {
            Toast.makeText(OptionsMenuActivity.this, "2. item", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
