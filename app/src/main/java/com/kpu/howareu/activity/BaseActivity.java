package com.kpu.howareu.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kpu.howareu.MainActivity;
import com.kpu.howareu.R;
import com.kpu.howareu.activity.user.LoginActivity;

public class BaseActivity extends AppCompatActivity {

    private int mActivityAnimationType = 0;
    private static final int SLIDE_LEFT_IN_RIGHT_OUT = 1;
    private static final int SLIDE_RIGHT_IN_LEFT_OUT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivityAnimation();
        setActivityAnimationType();
    }

    public void startActivityAnimation() {
        switch (mActivityAnimationType) {
            case SLIDE_LEFT_IN_RIGHT_OUT:
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                break;

            case SLIDE_RIGHT_IN_LEFT_OUT:
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
        }
    }

    public void finishActivityAnimation() {
        switch (mActivityAnimationType) {
            case SLIDE_LEFT_IN_RIGHT_OUT:
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;

            case SLIDE_RIGHT_IN_LEFT_OUT:
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        finishActivityAnimation();
    }

    private void setActivityAnimationType() {

        if (this instanceof MainActivity ||
            this instanceof LoginActivity) {

            mActivityAnimationType = SLIDE_RIGHT_IN_LEFT_OUT;
        }
    }
}
