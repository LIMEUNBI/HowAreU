package com.kpu.howareu.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;
import com.kpu.howareu.activity.course.CourseStartActivity;
import com.kpu.howareu.activity.dashboard.MainActivity;

public class LoginActivity extends BaseActivity {

    private EditText mEditId;
    private EditText mEditPw;
    private CheckBox mCheckAutoLogin;
    private Button mBtnLogin;
    private TextView mTxtFindId;
    private TextView mTxtFindPw;
    private TextView mTxtJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditId = findViewById(R.id.edit_id);
        mEditPw = findViewById(R.id.edit_pw);
        mCheckAutoLogin = findViewById(R.id.check_auto_login);
        mBtnLogin = findViewById(R.id.btn_login);
        mTxtFindId = findViewById(R.id.find_id);
        mTxtFindPw = findViewById(R.id.find_pw);
        mTxtJoin = findViewById(R.id.join);

        mBtnLogin.setOnClickListener(v -> {
            if (mEditId.getText().toString().isEmpty() || mEditPw.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "아이디와 패스워드를 다시 확인해주세요", Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(LoginActivity.this, CourseStartActivity.class);
            startActivity(intent);
        });

        mTxtFindId.setOnClickListener(v -> {

        });

        mTxtFindPw.setOnClickListener(v -> {

        });

        mTxtJoin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
            startActivity(intent);
        });
    }
}
