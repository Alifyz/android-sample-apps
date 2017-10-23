package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by AlifyzFPires on 10/23/2017.
 */

public class MiwakPageAdapter extends FragmentPagerAdapter {

    public MiwakPageAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyMembersFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default:
                return null;
        }


    }
}
