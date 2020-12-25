package com.kpu.howareu.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.kpu.howareu.activity.dashboard.MainActivity;
import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.utils.SharedPreferenceBase;

public class CourseStartActivity extends BaseActivity {

    private TextView mTxtUserName;
    private Button mBtnCourse;
    private TextView mTxtSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_start);

        mTxtUserName = findViewById(R.id.txt_user_name);
        mBtnCourse = findViewById(R.id.btn_course);
        mTxtSkip = findViewById(R.id.txt_skip);

        String userName = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.USER_NAME, "");
        mTxtUserName.setText(userName);

        mBtnCourse.setOnClickListener(v -> {
            Intent intent = new Intent(CourseStartActivity.this, CourseQuestion1Activity.class);
            startActivity(intent);
        });

        mTxtSkip.setOnClickListener(v -> {
            Intent intent = new Intent(CourseStartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
