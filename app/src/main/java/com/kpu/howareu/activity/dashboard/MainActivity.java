package com.kpu.howareu.activity.dashboard;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment = null;
    private static int mCurrentFragmentId = R.id.action_one;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        View view = bottomNavigationView.findViewById(R.id.action_one);
        view.performClick();

        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        bottomNavigationView();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.getInstance());

        transaction.commit();
    }

    public void bottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                mCurrentFragmentId = menuItem.getItemId();

                switch (mCurrentFragmentId) {

                    case R.id.action_one:
                        selectedFragment = HomeFragment.getInstance();
                        break;

                    case R.id.action_two:
                        selectedFragment = ScheduleFragment.getInstance();
                        break;

                    case R.id.action_three:
                        selectedFragment = AnalysisFragment.getInstance();
                        break;

                    case R.id.action_four:
                        selectedFragment = MentoringFragment.getInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commitAllowingStateLoss();
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - backPressedTime;

            if (intervalTime < 2000) {
                finish();
            } else {
                backPressedTime = tempTime;
                Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}