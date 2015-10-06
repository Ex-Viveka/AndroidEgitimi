package com.kanilturgut.listviewdenemesi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private String[] iller = {"Adana", "Adıyaman", "Afyon", "Ağrı", "Ankara", "İstanbul",
            "İzmir", "Bursa", "Bolu", "Muş", "Konya", "Denizli", "Eskişehir", "Balıkesir", "Osmaniye",
            "Çorum", "Bayburt", "Van", "Zonguldak", "Burdur", "Sivas", "Ordu", "Antalya", "Samsun", "Çankırı",
            "Rize", "Sakarya", "Edirne", "Tekirdağ", "Kırıkkale"};


    private ListView mLovelyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mLovelyList = (ListView) findViewById(R.id.listview);

//        mLovelyList.setAdapter(new ArrayAdapter<String>(ListActivity.this,
//                android.R.layout.simple_list_item_1, iller));

        MyArrayAdapter myArrayAdapter = new MyArrayAdapter(ListActivity.this);
        mLovelyList.setAdapter(myArrayAdapter);

    }

    private class MyArrayAdapter extends ArrayAdapter<String> {

        public MyArrayAdapter(Context context) {
            super(context, R.layout.custom_simple_list_item, iller);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = layoutInflater.inflate(R.layout.custom_simple_list_item, parent, false);

            ImageView ivListItemAvatar = (ImageView) rootView.findViewById(R.id.ivListItemAvatar);
            TextView tvListItemPost = (TextView) rootView.findViewById(R.id.tvListItemPost);
            TextView tvListItemTime = (TextView) rootView.findViewById(R.id.tvListItemTime);

            tvListItemPost.setText(iller[position]);

            if (position % 5 == 0) {
                ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_to_photos_black_48dp));
                tvListItemTime.setText("10 saniye önce");
            } else if (position % 5 == 1) {
                ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_cake_black_48dp));
                tvListItemTime.setText("10 dakika önce");
            } else if (position % 5 == 2) {
                ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_domain_black_48dp));
                tvListItemTime.setText("50 dakika önce");
            } else if (position % 5 == 3) {
                ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_group_add_black_48dp));
                tvListItemTime.setText("1 saat önce");
            } else if (position % 5 == 4) {
                ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_city_black_48dp));
                tvListItemTime.setText("3 saat önce");
            }

            return rootView;
        }


        // ipucu veriyorum su anda :D
        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }
}
