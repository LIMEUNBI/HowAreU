package com.kpu.howareu.activity.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.community.EmploymentActivity;
import com.kpu.howareu.activity.community.KnowledgeActivity;
import com.kpu.howareu.activity.community.SchoolActivity;
import com.kpu.howareu.activity.course.CourseQuestion1Activity;
import com.kpu.howareu.activity.video.InterviewVideoActivity;
import com.kpu.howareu.activity.video.VideoPlayActivity;
import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.model.QuestionSetting;
import com.kpu.howareu.common.utils.SharedPreferenceBase;

public class HomeFragment extends Fragment {

    private View mView = null;
    private static HomeFragment instance = null;

    private TextView mTxtRetry;

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    private TextView mTxtUserName;

    private TextView mTxtMore;

    private RelativeLayout mRecommendLayout;
    private RelativeLayout mNoDataLayout;

    private CardView mCardVideo1;
    private CardView mCardVideo2;

    private CardView mCardKnowledge;
    private CardView mCardEmployment;
    private CardView mCardSchool;

    private Button mBtnRetry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home, container, false);

        mTxtUserName = mView.findViewById(R.id.txt_user_name);
        String userName = SharedPreferenceBase.getPrefString(getContext(), Config.USER_NAME, "");
        mTxtUserName.setText(userName);

        mTxtMore = mView.findViewById(R.id.txt_more);
        mTxtMore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InterviewVideoActivity.class);
            startActivity(intent);
        });

        mRecommendLayout = mView.findViewById(R.id.recommend_layout);
        mNoDataLayout = mView.findViewById(R.id.no_recommend_layout);

        mCardVideo1 = mView.findViewById(R.id.card_video1);
        mCardVideo2 = mView.findViewById(R.id.card_video2);

        mCardVideo1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
            intent.putExtra(Config.VIDEO_INDEX, 0);
            startActivity(intent);
        });

        mCardVideo2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
            intent.putExtra(Config.VIDEO_INDEX, 1);
            startActivity(intent);
        });

        mCardKnowledge = mView.findViewById(R.id.card_knowledge);
        mCardEmployment = mView.findViewById(R.id.card_employment);
        mCardSchool = mView.findViewById(R.id.card_school);

        mCardKnowledge.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), KnowledgeActivity.class);
            startActivity(intent);
        });

        mCardEmployment.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EmploymentActivity.class);
            startActivity(intent);
        });

        mCardSchool.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SchoolActivity.class);
            startActivity(intent);
        });

        if (TextUtils.isEmpty(SharedPreferenceBase.getPrefString(getContext(), Config.USER_COURSE, ""))) {
            mRecommendLayout.setVisibility(View.GONE);
            mNoDataLayout.setVisibility(View.VISIBLE);
        } else {
            mRecommendLayout.setVisibility(View.VISIBLE);
            mNoDataLayout.setVisibility(View.GONE);
        }

        mBtnRetry = mView.findViewById(R.id.btn_retry);

        mBtnRetry.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CourseQuestion1Activity.class);
            startActivity(intent);
        });

        mTxtRetry = mView.findViewById(R.id.txt_retry);

        mTxtRetry.setOnClickListener(v -> {
            QuestionSetting questionSetting = new QuestionSetting(getContext());
            questionSetting.getInstance(getContext()).finishQuestion();
            Intent intent = new Intent(getActivity(), CourseQuestion1Activity.class);
            startActivity(intent);
        });

        return mView;
    }
}
