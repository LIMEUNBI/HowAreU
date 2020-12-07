package com.kpu.howareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.user.LoginActivity;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                finish();
            }
        }, 800);
    }
}
