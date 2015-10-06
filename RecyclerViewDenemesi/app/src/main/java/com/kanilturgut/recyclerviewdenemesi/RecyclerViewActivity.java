package com.kanilturgut.recyclerviewdenemesi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private String[] iller = {"Adana", "Adıyaman", "Afyon", "Ağrı", "Ankara", "İstanbul",
            "İzmir", "Bursa", "Bolu", "Muş", "Konya", "Denizli", "Eskişehir", "Balıkesir", "Osmaniye",
            "Çorum", "Bayburt", "Van", "Zonguldak", "Burdur", "Sivas", "Ordu", "Antalya", "Samsun", "Çankırı",
            "Rize", "Sakarya", "Edirne", "Tekirdağ", "Kırıkkale"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter());
    }

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = layoutInflater.inflate(R.layout.custom_simple_list_item, parent, false);

            return new ViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            if (position % 5 == 0) {
                holder.ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_to_photos_black_48dp));
                holder.tvListItemTime.setText("10 saniye önce");
            } else if (position % 5 == 1) {
                holder.ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_cake_black_48dp));
                holder.tvListItemTime.setText("10 dakika önce");
            } else if (position % 5 == 2) {
                holder.ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_domain_black_48dp));
                holder.tvListItemTime.setText("50 dakika önce");
            } else if (position % 5 == 3) {
                holder.ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_group_add_black_48dp));
                holder.tvListItemTime.setText("1 saat önce");
            } else if (position % 5 == 4) {
                holder.ivListItemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_city_black_48dp));
                holder.tvListItemTime.setText("3 saat önce");
            }

            holder.tvListItemPost.setText(iller[position]);
        }

        @Override
        public int getItemCount() {
            return iller.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView ivListItemAvatar;
            TextView tvListItemPost;
            TextView tvListItemTime;

            public ViewHolder(View itemView) {
                super(itemView);

                ivListItemAvatar = (ImageView) itemView.findViewById(R.id.ivListItemAvatar);
                tvListItemPost = (TextView) itemView.findViewById(R.id.tvListItemPost);
                tvListItemTime = (TextView) itemView.findViewById(R.id.tvListItemTime);
            }
        }
    }
}
