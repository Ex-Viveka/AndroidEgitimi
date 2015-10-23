package com.kanilturgut.phonemarketapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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


public class PromotedProductsFragment extends Fragment {

    private final String TAG = PromotedProductsFragment.class.getSimpleName();
    private Context mContext;
    private RecyclerView recyclerView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_promoted_products, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        getPromotedProducts();

        return rootView;
    }


    private void getPromotedProducts() {

        Rest.getInstance().createService(Endpoints.class)
                .getPromotedProduct(new Callback<List<Product>>() {
                    @Override
                    public void success(List<Product> products, Response response) {
                        recyclerView.setAdapter(new PromotedProductListAdapter(products));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i(TAG, "asdsad");
                    }
                });
    }

    private class PromotedProductListAdapter extends RecyclerView.Adapter<PromotedProductListAdapter.PromotedProductHolder> {

        List<Product> mProductList;

        public PromotedProductListAdapter(List<Product> productList) {
            mProductList = productList;
        }

        @Override
        public PromotedProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.custom_promoted_product_item_row, parent, false);

            return new PromotedProductHolder(rootView);
        }

        @Override
        public void onBindViewHolder(PromotedProductHolder holder, int position) {

            final Product product = mProductList.get(position);

            holder.tvProductName.setText(product.getName());
            holder.tvProductPrice.setText(String.valueOf(product.getPrice()) + " TL");

            holder.tvProductDisplay.setText(product.getDescription().getDisplay() + " inch");
            holder.tvProductRAM.setText(product.getDescription().getRam() + " GHz");
            holder.tvProductStorage.setText(product.getDescription().getStorage() + " GB Bellek");
            holder.tvProductOS.setText(product.getDescription().getOs());


            Picasso.with(mContext)
                    .load(product.getImages()[0])
                    .resize(400, 400)
                    .centerCrop()
                    .into(holder.ivProductImage);

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

        public class PromotedProductHolder extends RecyclerView.ViewHolder {

            CardView mainCard;
            ImageView ivProductImage;
            TextView tvProductName, tvProductPrice;
            TextView tvProductRAM, tvProductDisplay, tvProductStorage, tvProductOS;

            public PromotedProductHolder(View rootView) {
                super(rootView);

                mainCard = (CardView) rootView.findViewById(R.id.mainCard);

                ivProductImage = (ImageView) rootView.findViewById(R.id.ivProductImage);

                tvProductName = (TextView) rootView.findViewById(R.id.tvProductName);
                tvProductPrice = (TextView) rootView.findViewById(R.id.tvProductPrice);

                tvProductRAM = (TextView) rootView.findViewById(R.id.tvProductRAM);
                tvProductDisplay = (TextView) rootView.findViewById(R.id.tvProductDisplay);
                tvProductStorage = (TextView) rootView.findViewById(R.id.tvProductStorage);
                tvProductOS = (TextView) rootView.findViewById(R.id.tvProductOS);
            }
        }
    }
}
