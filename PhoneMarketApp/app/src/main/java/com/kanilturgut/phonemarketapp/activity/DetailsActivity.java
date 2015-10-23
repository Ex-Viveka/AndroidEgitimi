package com.kanilturgut.phonemarketapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kanilturgut.phonemarketapp.R;
import com.kanilturgut.phonemarketapp.adapter.ViewPagerAdapter;
import com.kanilturgut.phonemarketapp.helper.ShoppingCart;
import com.kanilturgut.phonemarketapp.model.Product;

public class DetailsActivity extends AppCompatActivity {

    private final String TAG = DetailsActivity.class.getSimpleName();
    private Context mContext = this;

    private TextView tvNameOfProduct, tvPriceOfProduct, tvCpuOfProduct,
            tvRamOfProduct, tvStorageOfProduct, tvSdCardOfProduct,
            tvDisplayOfProduct, tvResolutionOfProduct, tvOSOfProduct;

    private Button bAddToCart;
    private RatingBar ratingBar;

    private ViewPager mViewPager;

    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mProduct = (Product) getIntent().getSerializableExtra("product");

        tvNameOfProduct = (TextView) findViewById(R.id.tvNameOfProduct);
        tvNameOfProduct.setText(mProduct.getName());

        tvPriceOfProduct = (TextView) findViewById(R.id.tvPriceOfProduct);
        tvPriceOfProduct.setText(mProduct.getPrice() + " TL");

        tvCpuOfProduct = (TextView) findViewById(R.id.tvCpuOfProduct);
        if (mProduct.getDescription().getCpu() == 0.0) {
            tvCpuOfProduct.setVisibility(View.GONE);
        } else {
            tvCpuOfProduct.setVisibility(View.VISIBLE);
            tvCpuOfProduct.setText(mProduct.getDescription().getCpu() + " GHz İşlemci");
            putBullet(tvCpuOfProduct);
        }

        tvRamOfProduct = (TextView) findViewById(R.id.tvRamOfProduct);
        tvRamOfProduct.setText(mProduct.getDescription().getRam() + " GB RAM");
        putBullet(tvRamOfProduct);

        tvStorageOfProduct = (TextView) findViewById(R.id.tvStorageOfProduct);
        tvStorageOfProduct.setText(mProduct.getDescription().getStorage() + " GB Hafıza");
        putBullet(tvStorageOfProduct);

        tvSdCardOfProduct = (TextView) findViewById(R.id.tvSdCardOfProduct);
        if (mProduct.getDescription().isSdCard()) {
            tvSdCardOfProduct.setText("SDCard destekliyor");
        } else {
            tvSdCardOfProduct.setText("SDCard desteklemiyor");
        }
        putBullet(tvSdCardOfProduct);

        tvDisplayOfProduct = (TextView) findViewById(R.id.tvDisplayOfProduct);
        tvDisplayOfProduct.setText(mProduct.getDescription().getDisplay() + " inch");
        putBullet(tvDisplayOfProduct);

        tvResolutionOfProduct = (TextView) findViewById(R.id.tvResolutionOfProduct);
        tvResolutionOfProduct.setText(mProduct.getDescription().getResolution() + " piksel");
        putBullet(tvResolutionOfProduct);

        tvOSOfProduct = (TextView) findViewById(R.id.tvOSOfProduct);
        tvOSOfProduct.setText(mProduct.getDescription().getOs());
        putBullet(tvOSOfProduct);

        bAddToCart = (Button) findViewById(R.id.bAddToCart);
        bAddToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog pd = ProgressDialog.show(mContext, "Lütfen Bekleyiniz", "Sepetinize Ekleniyor", false, false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShoppingCart.getInstance().addProductToCart(mProduct);
                        pd.dismiss();
                        Toast.makeText(mContext, "Sepete Eklendi", Toast.LENGTH_SHORT).show();
                    }
                }, 1500);
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                mContext, mProduct));

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        try {
            float rating = mProduct.getPoints().getSumOfVote() / mProduct.getPoints().getNumberOfVote();
            ratingBar.setRating(rating);
        } catch (Exception e) {
            ratingBar.setRating(0);
        }
    }

    private void putBullet(TextView textView) {

        SpannableString spannableString = new SpannableString(" " + textView.getText().toString());
        spannableString.setSpan(new BulletSpan(), 0, 0, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }
}
