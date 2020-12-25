package com.kpu.howareu.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.activity.dashboard.MainActivity;
import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.utils.SharedPreferenceBase;

public class CourseRecommendActivity extends BaseActivity {

    private TextView mUserName;

    private ImageView mImgCourse;

    private TextView mCourse;
    private TextView mQuestion1;
    private TextView mQuestion2;
    private TextView mQuestion3;
    private TextView mQuestion4;
    private TextView mQuestion5;
    private TextView mQuestion6;
    private TextView mQuestion7;

    private Button mBtnRetry;
    private Button mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_recommend);

        mUserName = findViewById(R.id.txt_user_name);
        mImgCourse = findViewById(R.id.img_course);

        mCourse = findViewById(R.id.txt_course);
        mQuestion1 = findViewById(R.id.txt_q1);
        mQuestion2 = findViewById(R.id.txt_q2);
        mQuestion3 = findViewById(R.id.txt_q3);
        mQuestion4 = findViewById(R.id.txt_q4);
        mQuestion5 = findViewById(R.id.txt_q5);
        mQuestion6 = findViewById(R.id.txt_q6);
        mQuestion7 = findViewById(R.id.txt_q7);

        mBtnRetry = findViewById(R.id.btn_retry);
        mBtnStart = findViewById(R.id.btn_start);

        String userName = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.USER_NAME, "");
        mUserName.setText(userName);

        String course = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.USER_COURSE, "");
        String courseTxt = "";
        if (course.equals(Config.COURSE_COMPUTER)) {
            courseTxt = "컴퓨터 공학과";
        } else if (course.equals(Config.COURSE_MEDICAL)) {
            courseTxt = "의료";
        } else if (course.equals(Config.COURSE_DESIGN)) {
            courseTxt = "디자인학과";
        } else if (course.equals(Config.COURSE_ART)) {
            courseTxt = "예술(음악, 미술)";
        } else if (course.equals(Config.COURSE_EDUCATION)) {
            courseTxt = "교육학과";
        } else if (course.equals(Config.COURSE_SOCIAL_SCIENCE)) {
            courseTxt = "사회과학과";
        }
        mCourse.setText(courseTxt);

        String q1 = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q1, "");
        String q2 = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q2, "");
        String q3 = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q3, "");
        String q4 = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q4, "");
        String q5 = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q5, "");
        String q6 = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q6, "");
        String q7 = SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q7, "");

        mQuestion1.setText(q1);
        mQuestion2.setText(q2);
        mQuestion3.setText(q3);
        mQuestion4.setText(q4);
        mQuestion5.setText(q5);
        mQuestion6.setText(q6);
        mQuestion7.setText(q7);

        mBtnRetry.setOnClickListener(v -> {
            SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q1, "");
            SharedPreferenceBase.putPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_ONE, false);

            SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q2, "");
            SharedPreferenceBase.putPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_TWO, false);

            SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q3, "");
            SharedPreferenceBase.putPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_THREE, false);

            SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q4, "");
            SharedPreferenceBase.putPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_FOUR, false);

            SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q5, "");
            SharedPreferenceBase.putPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_FIVE, false);

            SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q6, "");
            SharedPreferenceBase.putPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_SIX, false);

            SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q7, "");
            SharedPreferenceBase.putPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_SEVEN, false);

            Intent intent = new Intent(CourseRecommendActivity.this, CourseQuestion1Activity.class);
            startActivity(intent);
            finish();
        });

        mBtnStart.setOnClickListener(v -> {
            Intent intent = new Intent(CourseRecommendActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
