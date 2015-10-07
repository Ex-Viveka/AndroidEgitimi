package com.kanilturgut.samsungegitim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kanilturgut.samsungegitim.database.DatabaseActivity;
import com.kanilturgut.samsungegitim.dialogs.DialogsActivity;
import com.kanilturgut.samsungegitim.lifecycle.ActivityOne;
import com.kanilturgut.samsungegitim.lists.ListViewActivity;
import com.kanilturgut.samsungegitim.menu.OptionsMenuActivity;
import com.kanilturgut.samsungegitim.menu.SideMenuActivity;
import com.kanilturgut.samsungegitim.multimedia.camera_intent.AudioActivity;
import com.kanilturgut.samsungegitim.multimedia.camera_intent.CameraIntentActivity;
import com.kanilturgut.samsungegitim.multimedia.camera_intent.VideoActivity;
import com.kanilturgut.samsungegitim.navigation_with_data.FirstActivity;
import com.kanilturgut.samsungegitim.onSaveInstanceState.OnSaveInstanceExample1;
import com.kanilturgut.samsungegitim.sharedpreferences.SharedPreferencesActivity;
import com.kanilturgut.samsungegitim.threading.ThreadingActivity;

public class MainActivity extends AppCompatActivity {

    private ListView lvSubjectList;
    private final String[] SUBJECTS = {"Activity Class", "Basic View Components", "Activity LifeCycle",
            "Navigation with Data", "onSaveInstanceState", "Lists", "Dialogs", "SharedPreferences",
            "Database", "Threading", "OptionMenu", "Side Menu", "Simple Camera", "Video", "Audio"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml icerisinde listemizi java kodumuza baglayalim
        lvSubjectList = (ListView) findViewById(R.id.lvSubjectList);

        // listemizi dolduracak adapter yapisini cagiralim
        lvSubjectList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, SUBJECTS));

        // listeye listener ekleyerek hangi elemana tiklandigini algilayalim
        lvSubjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ActivityOne.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, FirstActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, OnSaveInstanceExample1.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, DialogsActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, SharedPreferencesActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, DatabaseActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, ThreadingActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, OptionsMenuActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, SideMenuActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, CameraIntentActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, VideoActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, AudioActivity.class));
                        break;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
