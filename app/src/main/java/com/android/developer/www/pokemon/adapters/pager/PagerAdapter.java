package com.android.developer.www.pokemon.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.developer.www.pokemon.fragments.main.Discover;
import com.android.developer.www.pokemon.fragments.main.Pokedex;

public class PagerAdapter extends FragmentPagerAdapter {

    private static final int ITEM_AMOUNT = 2;

    private String[] titles = {"Discover", "Pokedex"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Discover();
            case 1:
                return new Pokedex();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return ITEM_AMOUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
