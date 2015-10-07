package com.kanilturgut.samsungegitim.lists;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kanilturgut.samsungegitim.R;

public class ListViewActivity extends AppCompatActivity {

    private Context mContext = this;
    private final String TAG = ListViewActivity.class.getSimpleName();

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.listView);

        setAsSimpleList();
    }


    private void setAsSimpleList() {

        String[] array = new String[25];
        for (int i = 0; i < 25; i++) {
            array[i] = "Sport " + (i + 1);
        }

        listView.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, array));
    }

    private void setAsCustomList() {
        String[] array = new String[25];
        for (int i = 0; i < 25; i++) {
            array[i] = "Sport " + (i + 1);
        }

    }


    class MyArrayAdapter extends ArrayAdapter {

        public MyArrayAdapter(Context context, int resource, Object[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = layoutInflater.inflate(R.layout.custom_listview_row, parent, false);


            return rootView;
        }
    }


    class MyViewHolderAdapter extends ArrayAdapter {

        public MyViewHolderAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.custom_listview_row, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tvNameOfSport);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mTextView.setText("Deneme");

            return convertView;
        }

        public class ViewHolder {
            public TextView mTextView;
        }
    }
}
