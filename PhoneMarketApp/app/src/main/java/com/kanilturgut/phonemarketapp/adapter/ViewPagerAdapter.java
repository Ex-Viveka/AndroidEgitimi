package com.kanilturgut.phonemarketapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kanilturgut.phonemarketapp.R;
import com.kanilturgut.phonemarketapp.model.Product;
import com.squareup.picasso.Picasso;

/**
 * Author   : kanilturgut
 * Date     : 23/10/15
 * Time     : 18:50
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String ARG_PAGE = "page";
    private static Context sContext = null;
    private static Product sProduct;

    public ViewPagerAdapter(FragmentManager fm, Context context, Product product) {
        super(fm);

        sContext = context;
        sProduct = product;
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return sProduct.getImages().length;
    }


    public static class PagerFragment extends Fragment {
        int myPageNumber;

        public static PagerFragment newInstance(int myPageNumber) {

            PagerFragment pagerFragment = new PagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_PAGE, myPageNumber);
            pagerFragment.setArguments(bundle);

            return pagerFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            myPageNumber = getArguments().getInt(ARG_PAGE);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_view_pager_page, container, false);

            ImageView ivImageOfProduct = (ImageView) rootView.findViewById(R.id.ivImageOfProduct);
            Picasso.with(sContext)
                    .load(sProduct.getImages()[myPageNumber])
                    .into(ivImageOfProduct);


            return rootView;
        }
    }
}
