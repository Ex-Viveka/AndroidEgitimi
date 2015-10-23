package com.kanilturgut.phonemarketapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kanilturgut.phonemarketapp.R;
import com.kanilturgut.phonemarketapp.helper.ShoppingCart;
import com.kanilturgut.phonemarketapp.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ShoppingCartFragment extends Fragment {

    private final String TAG = ShoppingCartFragment.class.getSimpleName();
    private Context mContext;

    private RecyclerView recyclerView;
    private TextView tvNoProduct;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        tvNoProduct = (TextView) view.findViewById(R.id.tvNotProduct);

        getProductsFromShoppingCart();

        return view;
    }

    private void getProductsFromShoppingCart() {

        List<Product> productList = ShoppingCart.getInstance().getShoppingCart();

        if (productList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            tvNoProduct.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvNoProduct.setVisibility(View.GONE);

            recyclerView.setAdapter(new ShoppingCartAdapter(productList));
        }
    }


    private class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartHolder> {

        List<Product> mProductList;

        public ShoppingCartAdapter(List<Product> productList) {
            mProductList = productList;
        }

        @Override
        public ShoppingCartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.custom_shopping_cart_item_row, parent, false);

            return new ShoppingCartHolder(rootView);
        }

        @Override
        public void onBindViewHolder(ShoppingCartHolder holder, int position) {

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


            holder.bBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final ProgressDialog pd = ProgressDialog.show(mContext, "Lütfen Bekleyiniz", "Alışveriş Tamalanıyor", false, false);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            ShoppingCart.getInstance().removeProductFromCart(product);
                            Toast.makeText(mContext, "İyi günlerde kullanınız", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }, 3000);
                }
            });

            holder.bRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShoppingCart.getInstance().removeProductFromCart(product);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mProductList.size();
        }

        public class ShoppingCartHolder extends RecyclerView.ViewHolder {

            ImageView ivProductImage;
            TextView tvProductName, tvProductPrice;
            RatingBar ratingBar;

            Button bBuy, bRemove;

            public ShoppingCartHolder(View rootView) {
                super(rootView);

                ivProductImage = (ImageView) rootView.findViewById(R.id.ivProductImage);

                tvProductName = (TextView) rootView.findViewById(R.id.tvProductName);
                tvProductPrice = (TextView) rootView.findViewById(R.id.tvProductPrice);

                ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);

                bBuy = (Button) rootView.findViewById(R.id.bBuy);
                bRemove = (Button) rootView.findViewById(R.id.bRemove);
            }
        }
    }
}
