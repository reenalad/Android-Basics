package com.example.reena.exploreakureyri;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by reena on 04/05/2020.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    //context of the app
    private Context mContext;

    //new categoryadapter
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    //return the fragment that should be displayed
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FoodFragment();
        } else if (position == 1) {
            return new SightsFragment();
        } else if (position == 2) {
            return new MuseumsFragment();
        } else {
            return new NearbyFragment();
        }
    }

    //return the number of pages
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.food);
        } else if (position == 1) {
            return mContext.getString(R.string.sights);
        } else if (position == 2) {
            return mContext.getString(R.string.museums);
        } else {
            return mContext.getString(R.string.nearby);
        }
    }
}
