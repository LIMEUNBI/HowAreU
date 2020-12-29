package com.kpu.howareu.activity.mentor;

import android.os.Bundle;
import android.widget.ImageView;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;

public class MentorInfoActivity extends BaseActivity {

    private ImageView mImgBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_info);

        mImgBefore = findViewById(R.id.img_before);

        mImgBefore.setOnClickListener(v -> finish());
    }
}
