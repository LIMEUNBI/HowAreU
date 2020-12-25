package com.kpu.howareu.application;

import android.app.Application;

import com.kpu.howareu.common.CommonLibrary;

public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        CommonLibrary.getInstance(getApplicationContext());

    }
}
