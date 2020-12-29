package com.kpu.howareu.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kpu.howareu.R;
import com.kpu.howareu.activity.community.CommunityActivity;
import com.kpu.howareu.activity.community.EmploymentActivity;
import com.kpu.howareu.activity.community.KnowledgeActivity;
import com.kpu.howareu.activity.community.SchoolActivity;
import com.kpu.howareu.activity.course.CourseStartActivity;
import com.kpu.howareu.activity.dashboard.MainActivity;
import com.kpu.howareu.activity.mentor.MentorInfoActivity;
import com.kpu.howareu.activity.self.SelfInputActivity;
import com.kpu.howareu.activity.self.SelfListActivity;
import com.kpu.howareu.activity.user.JoinActivity;
import com.kpu.howareu.activity.user.LoginActivity;
import com.kpu.howareu.activity.video.InterviewVideoActivity;
import com.kpu.howareu.activity.video.VideoPlayActivity;
import com.kpu.howareu.common.model.SelfInfo;
import com.kpu.howareu.common.utils.Users;

public class BaseActivity extends AppCompatActivity {

    private int mActivityAnimationType = 0;
    private static final int SLIDE_LEFT_IN_RIGHT_OUT = 1;
    private static final int SLIDE_RIGHT_IN_LEFT_OUT = 2;

    public DatabaseReference mRootRef;
    public ValueEventListener mValueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        startActivityAnimation();
        setActivityAnimationType();

        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    Users users = postSnapshot.getValue(Users.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        //사용
        mRootRef.addValueEventListener(mValueEventListener);

        //삭제
        mRootRef.removeEventListener(mValueEventListener);
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
            this instanceof LoginActivity ||
            this instanceof JoinActivity ||
            this instanceof CourseStartActivity ||
            this instanceof KnowledgeActivity ||
            this instanceof EmploymentActivity ||
            this instanceof SchoolActivity ||
            this instanceof SelfInputActivity ||
            this instanceof SelfListActivity ||
            this instanceof CommunityActivity ||
            this instanceof MentorInfoActivity ||
            this instanceof InterviewVideoActivity) {

            mActivityAnimationType = SLIDE_RIGHT_IN_LEFT_OUT;
        }
    }
}
