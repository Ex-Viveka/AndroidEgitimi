package com.kanilturgut.phonemarketapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kanilturgut.phonemarketapp.R;
import com.kanilturgut.phonemarketapp.activity.DetailsActivity;
import com.kanilturgut.phonemarketapp.backend.Endpoints;
import com.kanilturgut.phonemarketapp.backend.Rest;
import com.kanilturgut.phonemarketapp.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class AllProductsFragment extends Fragment {

    private final String TAG = AllProductsFragment.class.getSimpleName();
    private Context mContext;
    private RecyclerView mRecyclerView;

    private ProgressDialog mProgressDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_products, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));

        getAllProducts();

        return rootView;
    }

    private void getAllProducts() {

        mProgressDialog = ProgressDialog.show(mContext, "Lütfen Bekleyiniz", "Ürünler yükleniyor", false, false);

        Rest.getInstance().createService(Endpoints.class)
                .getAllProducts(new Callback<List<Product>>() {
                    @Override
                    public void success(List<Product> products, Response response) {
                        mRecyclerView.setAdapter(new AllProductListAdapter(products));

                        if (mProgressDialog != null)
                            mProgressDialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "ERROR", error);

                        if (mProgressDialog != null)
                            mProgressDialog.dismiss();
                    }
                });

    }

    private class AllProductListAdapter extends RecyclerView.Adapter<AllProductListAdapter.AllProductHolder> {

        List<Product> mProductList;

        public AllProductListAdapter(List<Product> productList) {
            mProductList = productList;
        }

        @Override
        public AllProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.custom_product_item_row, parent, false);

            return new AllProductHolder(rootView);
        }

        @Override
        public void onBindViewHolder(AllProductHolder holder, int position) {

            final Product product = mProductList.get(position);

            holder.tvProductName.setText(product.getName());
            holder.tvProductPrice.setText(String.valueOf(product.getPrice()) + " TL");

            Picasso.with(mContext)
                    .load(product.getImages()[0])
                    .resize(300, 300)
                    .centerCrop()
                    .into(holder.ivProductImage);

            try {
                float rating = product.getPoints().getSumOfVote() / product.getPoints().getNumberOfVote();
                holder.ratingBar.setRating(rating);
            } catch (Exception e) {
                holder.ratingBar.setRating(0);
            }


            holder.mainCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("product", product);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mProductList.size();
        }

        public class AllProductHolder extends RecyclerView.ViewHolder {

            CardView mainCard;
            ImageView ivProductImage;
            TextView tvProductName, tvProductPrice;
            RatingBar ratingBar;

            public AllProductHolder(View rootView) {
                super(rootView);

                mainCard = (CardView) rootView.findViewById(R.id.mainCard);

                ivProductImage = (ImageView) rootView.findViewById(R.id.ivProductImage);

                tvProductName = (TextView) rootView.findViewById(R.id.tvProductName);
                tvProductPrice = (TextView) rootView.findViewById(R.id.tvProductPrice);

                ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
            }
        }
    }

}
