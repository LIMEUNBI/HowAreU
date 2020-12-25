package com.kpu.howareu.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.custom.CustomViewPager;

public class JoinActivity extends BaseActivity {

    private String INDEX = "startIndex";
    private CustomViewPager mViewPager;
    private int mCurrIndex = 0;

    private TabLayout mTabLayout;
    private CustomViewPagerAdapter mPageAdapter;

    private static JoinFirstFragment joinFirstFragment;
    private static JoinSecondFragment joinSecondFragment;

    private Button mBtnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mTabLayout = findViewById(R.id.tab);
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        joinFirstFragment = JoinFirstFragment.getInstance();
        joinSecondFragment = JoinSecondFragment.getInstance();

        mViewPager = findViewById(R.id.viewpager);
        mPageAdapter = new CustomViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        mBtnJoin = findViewById(R.id.btn_join);

        mBtnJoin.setOnClickListener(v -> {
            if (mCurrIndex == 0) {
                if (TextUtils.isEmpty(joinFirstFragment.mEditName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(joinFirstFragment.mEditId.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(joinFirstFragment.mEditPw1.getText().toString()) || TextUtils.isEmpty(joinFirstFragment.mEditPw2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "패스워드를 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if (!joinFirstFragment.mEditPw1.getText().toString().equals(joinFirstFragment.mEditPw2.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "패스워드와 패스워드 확인이 다릅니다. 다시 확인해주세요.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                replaceFragment();
            } else {

            }
        });

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
    }

    public void replaceFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
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
