package com.example.michel.mynews.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by michel on 29/12/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {

    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return TopStoriesFragment.newInstance();
            case 1: //Page number 2
                return MostPopularFragment.newInstance();
            case 2: //Page number 3
                return BusinessFragment.newInstance();
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "Top Stories";
            case 1: //Page number 2
                return "Most Popular";
            case 2: //Page number 3
                return "Business";
            default:
                return null;
        }
    }
}
