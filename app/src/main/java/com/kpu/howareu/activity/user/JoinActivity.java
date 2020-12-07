package com.kpu.howareu.activity.user;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.custom.CustomViewPager;
import com.kpu.howareu.fragment.user.JoinFirstFragment;
import com.kpu.howareu.fragment.user.JoinSecondFragment;

public class JoinActivity extends BaseActivity {

    private String INDEX = "startIndex";
    private CustomViewPager mViewPager;
    private int mCurrIndex = 0;

    private TabLayout mTabLayout;
    private CustomViewPagerAdapter mPageAdapter;

    private static JoinFirstFragment joinFirstFragment;
    private static JoinSecondFragment joinSecondFragment;

    private static FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mTabLayout = findViewById(R.id.tab);
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        mViewPager = findViewById(R.id.viewpager);
        mPageAdapter = new CustomViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(mPageAdapter);

        mViewPager.disableScroll(false);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        joinFirstFragment = JoinFirstFragment.getInstance();
        joinSecondFragment = JoinSecondFragment.getInstance();

        mFragmentManager = getSupportFragmentManager();
    }

    public static void replaceFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewpager, joinSecondFragment).commit();
    }

    public class CustomViewPagerAdapter extends FragmentStatePagerAdapter {
        private int mPageCount;

        public CustomViewPagerAdapter(FragmentManager fm, int mPageCount) {
            super(fm);
            this.mPageCount = mPageCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    joinFirstFragment = new JoinFirstFragment();
                    return joinFirstFragment;
                case 1:
                    joinSecondFragment = new JoinSecondFragment();
                    return joinSecondFragment;
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            return mPageCount;
        }
    }
}
