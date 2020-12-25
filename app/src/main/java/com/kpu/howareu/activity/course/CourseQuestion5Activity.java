package com.kpu.howareu.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.model.QuestionSetting;
import com.kpu.howareu.common.utils.SharedPreferenceBase;

public class CourseQuestion5Activity extends BaseActivity {

    private ImageView mImgBefore;

    private ImageView mImgOne;
    private ImageView mImgTwo;
    private ImageView mImgThree;
    private ImageView mImgFour;
    private ImageView mImgFive;
    private ImageView mImgSix;
    private ImageView mImgSeven;

    private LinearLayout mResponse1Layout;
    private LinearLayout mResponse2Layout;

    private CheckBox mCheck1;
    private CheckBox mCheck2;

    private TextView mResponse1;
    private TextView mResponse2;

    private TextView mTxtBefore;
    private TextView mTxtAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_question_five);

        mImgBefore = findViewById(R.id.img_before);

        mImgOne = findViewById(R.id.img_one);
        mImgTwo = findViewById(R.id.img_two);
        mImgThree = findViewById(R.id.img_three);
        mImgFour = findViewById(R.id.img_four);
        mImgFive = findViewById(R.id.img_five);
        mImgSix = findViewById(R.id.img_six);
        mImgSeven = findViewById(R.id.img_seven);

        mResponse1Layout = findViewById(R.id.response1_layout);
        mResponse2Layout = findViewById(R.id.response2_layout);

        mCheck1 = findViewById(R.id.check_response1);
        mCheck2 = findViewById(R.id.check_response2);

        mResponse1 = findViewById(R.id.response1);
        mResponse2 = findViewById(R.id.response2);

        mTxtBefore = findViewById(R.id.txt_before);
        mTxtAfter = findViewById(R.id.txt_after);

        setImg();

        mTxtBefore.setClickable(false);

        mResponse1Layout.setOnClickListener(v -> mCheck1.performClick());
        mResponse2Layout.setOnClickListener(v -> mCheck2.performClick());

        mImgBefore.setOnClickListener(v -> {
            QuestionSetting questionSetting = new QuestionSetting(getApplicationContext());
            questionSetting.getInstance(getApplicationContext()).finishQuestion();
            finish();
        });

        mCheck1.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                mCheck2.setChecked(false);
            }
        });

        mCheck2.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                mCheck1.setChecked(false);
            }
        });

        if (SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q5, "").equals(mResponse1.getText().toString())) {
            mCheck1.setChecked(true);
        } else if (SharedPreferenceBase.getPrefString(getApplicationContext(), Config.Q5, "").equals(mResponse2.getText().toString())) {
            mCheck2.setChecked(true);
        }

        mTxtBefore.setOnClickListener(v -> {
            Intent intent = new Intent(CourseQuestion5Activity.this, CourseQuestion4Activity.class);
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        });

        mTxtAfter.setOnClickListener(v -> {
            if (!mCheck1.isChecked() && !mCheck2.isChecked()) {
                Toast.makeText(getApplicationContext(), "답변을 선택해주세요.", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferenceBase.putPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_FIVE, true);

                int computer = SharedPreferenceBase.getPrefInt(getApplicationContext(), Config.COURSE_COMPUTER,  0);
                int medical = SharedPreferenceBase.getPrefInt(getApplicationContext(), Config.COURSE_MEDICAL, 0);
                int design = SharedPreferenceBase.getPrefInt(getApplicationContext(), Config.COURSE_DESIGN, 0);
                int art = SharedPreferenceBase.getPrefInt(getApplicationContext(), Config.COURSE_ART, 0);
                int education = SharedPreferenceBase.getPrefInt(getApplicationContext(), Config.COURSE_EDUCATION, 0);
                int socialScience = SharedPreferenceBase.getPrefInt(getApplicationContext(), Config.COURSE_SOCIAL_SCIENCE, 0);

                if (mCheck1.isChecked()) {
                    SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q5, mResponse1.getText().toString());
                    SharedPreferenceBase.putPrefInt(getApplicationContext(), Config.COURSE_MEDICAL, medical + 1);
                    SharedPreferenceBase.putPrefInt(getApplicationContext(), Config.COURSE_DESIGN, design + 1);
                    SharedPreferenceBase.putPrefInt(getApplicationContext(), Config.COURSE_EDUCATION, education + 1);
                    SharedPreferenceBase.putPrefInt(getApplicationContext(), Config.COURSE_SOCIAL_SCIENCE, socialScience + 1);
                } else {
                    SharedPreferenceBase.putPrefString(getApplicationContext(), Config.Q5, mResponse2.getText().toString());
                    SharedPreferenceBase.putPrefInt(getApplicationContext(), Config.COURSE_COMPUTER, computer + 1);
                    SharedPreferenceBase.putPrefInt(getApplicationContext(), Config.COURSE_ART, art + 1);
                }
                Intent intent = new Intent(CourseQuestion5Activity.this, CourseQuestion6Activity.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void setImg() {

        boolean isCheckOne = SharedPreferenceBase.getPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_ONE, false);
        boolean isCheckTwo = SharedPreferenceBase.getPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_TWO, false);
        boolean isCheckThree = SharedPreferenceBase.getPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_THREE, false);
        boolean isCheckFour = SharedPreferenceBase.getPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_FOUR, false);
        boolean isCheckFive = SharedPreferenceBase.getPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_FIVE, false);
        boolean isCheckSix = SharedPreferenceBase.getPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_SIX, false);
        boolean isCheckSeven = SharedPreferenceBase.getPrefBoolean(getApplicationContext(), Config.COURSE_QUESTION_SEVEN, false);

        if (isCheckOne) {
            mImgOne.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_check));
        } else {
            mImgOne.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_one_white));
        }

        if (isCheckTwo) {
            mImgTwo.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_check));
        } else {
            mImgTwo.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_two_white));
        }

        if (isCheckThree) {
            mImgThree.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_check));
        } else {
            mImgThree.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_three_white));
        }

        if (isCheckFour) {
            mImgFour.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_check));
        } else {
            mImgFour.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_four_white));
        }

        mImgFive.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_five_green));

        if (isCheckSix) {
            mImgSix.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_check));
        } else {
            mImgSix.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_six_white));
        }

        if (isCheckSeven) {
            mImgSeven.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_check));
        } else {
            mImgSeven.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_seven_white));
        }

    }
}
