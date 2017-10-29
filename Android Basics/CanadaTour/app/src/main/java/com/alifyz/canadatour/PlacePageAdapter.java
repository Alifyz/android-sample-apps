package com.alifyz.canadatour;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Alifyz on 10/28/2017.
 * Custom PageAdapter to Implement the ViewPager
 */

public class PlacePageAdapter extends FragmentPagerAdapter {

    public PlacePageAdapter(FragmentManager fm) {
        super(fm);
    }

    //Returns the Fragments Accordingly with the Position
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new NatureFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
