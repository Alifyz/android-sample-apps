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
            case 2:
                return new RestaurantsFragment();
            case 3:
                return new SportFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Museaums";
            case 1:
                return "Nature";
            case 2:
                return "Food";
            case 3:
                return "Sport";
            default:
                return "Tab";
        }
    }
}
