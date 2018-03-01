package com.android.developer.www.pokemon;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.developer.www.pokemon.adapters.pager.PagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout_activity_main) TabLayout tabLayout;
    @BindView(R.id.pager_activity_main) ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
    }

    private Fragment getCurrentFragment() {
        //Достать и вернуть текущий фрагмент из viewPager
        return getSupportFragmentManager().findFragmentByTag("android:switcher:"
                + R.id.pager_activity_main
                + ":"
                + pager.getCurrentItem());
    }
}
