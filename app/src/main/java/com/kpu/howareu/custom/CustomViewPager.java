package com.kpu.howareu.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    private boolean mIsEnabled;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewPager(Context context) {
        super(context);
        mIsEnabled = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mIsEnabled) {
            return super.onInterceptTouchEvent(event);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mIsEnabled) {
            return super.onTouchEvent(event);
        } else {
            return false;
        }
    }

    public void setPagingEnabled(boolean enabled) {
        this.mIsEnabled = enabled;
    }

    public void disableScroll(Boolean disable){
        this.mIsEnabled = disable;
    }
}
