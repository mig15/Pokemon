package com.android.developer.www.pokemon;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.developer.www.pokemon.adapters.pager.PagerAdapter;
import com.android.developer.www.pokemon.fragments.main.Discover;
import com.android.developer.www.pokemon.fragments.main.Pokedex;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private boolean firstStart = true;

    @BindView(R.id.tablayout_activity_main) TabLayout tabLayout;
    @BindView(R.id.pager_activity_main) ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Fragment fragment = getCurrentFragment();
        if (position == 0) {
            Discover page = (Discover) fragment;
            page.viewIsReady();
        } else {
            Pokedex page = (Pokedex) fragment;
            page.viewIsReady();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private Fragment getCurrentFragment() {
        //Достать и вернуть текущий фрагмент из viewPager
        return getSupportFragmentManager().findFragmentByTag("android:switcher:"
                + R.id.pager_activity_main
                + ":"
                + pager.getCurrentItem());
    }
}
