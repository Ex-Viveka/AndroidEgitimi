package com.kanilturgut.samsungegitim.lists;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kanilturgut.samsungegitim.R;
import com.kanilturgut.samsungegitim.lists.model.City;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerCardRefreshActivity extends AppCompatActivity {

    private final String TAG = RecyclerCardRefreshActivity.class.getSimpleName();
    private Context mContext = this;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;

    private List<City> mCityList;
    private MySweetRecyclerViewAdapter mMySweetRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card_refresh);

        // internet permission
        // manifest altında no action bar theme

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getListItems();
        setSwipeRefreshLayout();
        setRecyclerView();

    }

    private void setSwipeRefreshLayout() {

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3
        );

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNewListItem();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        });
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        mMySweetRecyclerViewAdapter = new MySweetRecyclerViewAdapter();
        recyclerView.setAdapter(mMySweetRecyclerViewAdapter);
    }

    private void getListItems() {
        mCityList = City.createCityList();
    }

    private void getNewListItem() {

        City ankara = new City();
        ankara.setName("Ankara");
        ankara.setCountry("Turkey");
        ankara.setPopulation("4.59 million");
        ankara.setImage("http://cache-graphicslib.viator.com/graphicslib/destination/ankara-134307.jpg");

        mCityList.add(0, ankara);

        mMySweetRecyclerViewAdapter.notifyItemInserted(0);
        mLinearLayoutManager.scrollToPosition(0);
    }

    private class MySweetRecyclerViewAdapter extends RecyclerView.Adapter<MySweetRecyclerViewAdapter.ViewHolder> {

        @Override
        public MySweetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.custom_recycler_view_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MySweetRecyclerViewAdapter.ViewHolder holder, int position) {

            City city = mCityList.get(position);

            // set image of city
            Picasso.with(mContext)
                    .load(city.getImage())
                    .resize(300, 300)
                    .centerCrop()
                    .into(holder.ivImageOfCity);

            holder.tvNameOfCity.setText(city.getName());
            holder.tvCountryOfCity.setText(city.getCountry());
            holder.tvPopulationOfCity.setText(city.getPopulation());
        }

        @Override
        public int getItemCount() {
            return mCityList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView ivImageOfCity;
            TextView tvNameOfCity;
            TextView tvCountryOfCity;
            TextView tvPopulationOfCity;

            public ViewHolder(View itemView) {
                super(itemView);

                ivImageOfCity = (ImageView) itemView.findViewById(R.id.ivImageOfCity);
                tvNameOfCity = (TextView) itemView.findViewById(R.id.tvNameOfCity);
                tvCountryOfCity = (TextView) itemView.findViewById(R.id.tvCountryOfCity);
                tvPopulationOfCity = (TextView) itemView.findViewById(R.id.tvPopulationOfCity);
            }
        }

    }
}
