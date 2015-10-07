package com.kanilturgut.menudenemeleri;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Context mContext = this;

    private Button osman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        osman = (Button) findViewById(R.id.bPopupMenu);
        osman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(mContext, osman);

                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int menuItemId = item.getItemId();
                        
                        if (menuItemId == R.id.action_popup_first) {
                            Toast.makeText(mContext, getResources().getString(R.string.popup_menu_first), Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (menuItemId == R.id.action_popup_second) {
                            Toast.makeText(mContext, getResources().getString(R.string.popup_menu_second), Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (menuItemId == R.id.action_popup_third) {
                            Toast.makeText(mContext, getResources().getString(R.string.popup_menu_third), Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (menuItemId == R.id.action_popup_fourth) {
                            Toast.makeText(mContext, getResources().getString(R.string.popup_menu_fourth), Toast.LENGTH_SHORT).show();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_acitivity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuItemId = item.getItemId();

        if (menuItemId == R.id.action_first) {
            Toast.makeText(mContext, "Ilk buton basildi", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "This is first menu item");
        } else if (menuItemId == R.id.action_second) {
            Toast.makeText(mContext, "Ikinci buton basildi", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "This is second menu item");
        }

        return super.onOptionsItemSelected(item);
    }

}
