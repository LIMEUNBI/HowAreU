package com.kpu.howareu.activity.community;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.common.config.Config;

public class CommunityActivity extends BaseActivity {

    private ImageView mImgBefore;
    private TextView mTxtToolbar;
    private TextView mTxtPostDate;
    private Button mBtnGood;
    private Button mBtnBad;

    private TextView mTxtPostTitle;
    private TextView mTxtPostContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        mImgBefore = findViewById(R.id.img_before);
        mTxtToolbar = findViewById(R.id.toolbar_title);
        mTxtPostDate = findViewById(R.id.post_datetime);

        mBtnGood = findViewById(R.id.btn_good);
        mBtnBad = findViewById(R.id.btn_bad);

        mTxtPostTitle = findViewById(R.id.post_title);
        mTxtPostContent = findViewById(R.id.post_content);

        mImgBefore.setOnClickListener(v -> finish());

        String type = getIntent().getStringExtra(Config.POST_TYPE);
        mTxtToolbar.setText(type);

        String title = getIntent().getStringExtra(Config.POST_TITLE);
        mTxtPostTitle.setText(title);

        String content = getIntent().getStringExtra(Config.POST_CONTENT);
        mTxtPostContent.setText(content);

        String dateTime = getIntent().getStringExtra(Config.POST_DATETIME);
        mTxtPostDate.setText(dateTime);

        mBtnGood.setOnClickListener(v -> {

        });

        mBtnBad.setOnClickListener(v -> {

        });
    }
}
