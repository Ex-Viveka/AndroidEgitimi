package com.kanilturgut.recyclecardandrefreshlayoutdenemesi;

import android.content.Context;
import android.os.Bundle;
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

import com.kanilturgut.recyclecardandrefreshlayoutdenemesi.model.City;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Context mContext = this;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MyCityListAdapter mMyCityListAdapter;

    private List<City> mCityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getListItems();
        setSwipeRefreshLayout();
        setRecyclerView();
    }

    private void getListItems() {
        mCityList = City.createCityList();
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
                getNewCity();

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getNewCity() {

        City ankara = new City();
        ankara.setName("Ankara");
        ankara.setCountry("Turkey");
        ankara.setPopulation("4.59 million");
        ankara.setImage("http://cache-graphicslib.viator.com/graphicslib/destination/ankara-134307.jpg");

        mCityList.add(0, ankara);
        mMyCityListAdapter.notifyItemInserted(0);
        mLinearLayoutManager.scrollToPosition(0);

    }

    private void setRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // eger satr buyukulugunu sabit tutacaksaniz bu kodu ekleyin
        mRecyclerView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mMyCityListAdapter = new MyCityListAdapter();
        mRecyclerView.setAdapter(mMyCityListAdapter);
    }

    private class MyCityListAdapter extends RecyclerView.Adapter<MyCityListAdapter.MyCityHolder> {


        @Override
        public MyCityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.custom_recycler_view_row, parent, false);

            return new MyCityHolder(rootView);
        }

        @Override
        public void onBindViewHolder(MyCityHolder holder, int position) {

            City city = mCityList.get(position);

            holder.tvNameOfCity.setText(city.getName());
            holder.tvCountryOfCity.setText(city.getCountry());
            holder.tvPopulationOfCity.setText(city.getPopulation());

            // ımage isine
            Picasso.with(mContext)
                    .load(city.getImage())
                    .resize(300, 300)
                    .centerCrop()
                    .into(holder.ivImageOfCity);

        }

        @Override
        public int getItemCount() {
            return mCityList.size();
        }

        public class MyCityHolder extends RecyclerView.ViewHolder {

            ImageView ivImageOfCity;
            TextView tvNameOfCity, tvCountryOfCity, tvPopulationOfCity;

            public MyCityHolder(View rootView) {
                super(rootView);

                ivImageOfCity = (ImageView) rootView.findViewById(R.id.ivImageOfCity);

                tvNameOfCity = (TextView) rootView.findViewById(R.id.tvNameOfCity);
                tvCountryOfCity = (TextView) rootView.findViewById(R.id.tvCountryOfCity);
                tvPopulationOfCity = (TextView) rootView.findViewById(R.id.tvPopulationOfCity);

            }
        }
    }
}
