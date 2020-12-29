package com.kpu.howareu.activity.video;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.common.config.Config;

public class InterviewVideoActivity extends BaseActivity {

    private RelativeLayout mInterviewLayout1;
    private RelativeLayout mInterviewLayout2;
    private RelativeLayout mInterviewLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_video);

        mInterviewLayout1 = findViewById(R.id.interview_layout1);
        mInterviewLayout2 = findViewById(R.id.interview_layout2);
        mInterviewLayout3 = findViewById(R.id.interview_layout3);

        mInterviewLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(InterviewVideoActivity.this, VideoPlayActivity.class);
            intent.putExtra(Config.VIDEO_INDEX, 0);
            startActivity(intent);
        });

        mInterviewLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(InterviewVideoActivity.this, VideoPlayActivity.class);
            intent.putExtra(Config.VIDEO_INDEX, 1);
            startActivity(intent);
        });

        mInterviewLayout3.setOnClickListener(v -> {
            Intent intent = new Intent(InterviewVideoActivity.this, VideoPlayActivity.class);
            intent.putExtra(Config.VIDEO_INDEX, 0);
            startActivity(intent);
        });
    }
}
