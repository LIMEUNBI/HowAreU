package com.kpu.howareu.common.model;

import android.content.Context;

import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.utils.SharedPreferenceBase;

public class QuestionSetting {

    public QuestionSetting instance = null;
    public Context context;

    public QuestionSetting(Context context) {
        this.context = context;
    }

    public QuestionSetting getInstance(Context context) {
        if (instance == null) {
            instance = new QuestionSetting(context);
        }
        return instance;
    }

    public void finishQuestion() {
        SharedPreferenceBase.putPrefInt(context, Config.COURSE_COMPUTER, 0);
        SharedPreferenceBase.putPrefInt(context, Config.COURSE_MEDICAL, 0);
        SharedPreferenceBase.putPrefInt(context, Config.COURSE_DESIGN, 0);
        SharedPreferenceBase.putPrefInt(context, Config.COURSE_ART, 0);
        SharedPreferenceBase.putPrefInt(context, Config.COURSE_EDUCATION, 0);
        SharedPreferenceBase.putPrefInt(context, Config.COURSE_SOCIAL_SCIENCE, 0);

        SharedPreferenceBase.putPrefBoolean(context, Config.COURSE_QUESTION_ONE, false);
        SharedPreferenceBase.putPrefBoolean(context, Config.COURSE_QUESTION_TWO, false);
        SharedPreferenceBase.putPrefBoolean(context, Config.COURSE_QUESTION_THREE, false);
        SharedPreferenceBase.putPrefBoolean(context, Config.COURSE_QUESTION_FOUR, false);
        SharedPreferenceBase.putPrefBoolean(context, Config.COURSE_QUESTION_FIVE, false);
        SharedPreferenceBase.putPrefBoolean(context, Config.COURSE_QUESTION_SIX, false);
        SharedPreferenceBase.putPrefBoolean(context, Config.COURSE_QUESTION_SEVEN, false);

        SharedPreferenceBase.putPrefString(context, Config.Q1, "");
        SharedPreferenceBase.putPrefString(context, Config.Q2, "");
        SharedPreferenceBase.putPrefString(context, Config.Q3, "");
        SharedPreferenceBase.putPrefString(context, Config.Q4, "");
        SharedPreferenceBase.putPrefString(context, Config.Q5, "");
        SharedPreferenceBase.putPrefString(context, Config.Q6, "");
        SharedPreferenceBase.putPrefString(context, Config.Q7, "");
    }
}
