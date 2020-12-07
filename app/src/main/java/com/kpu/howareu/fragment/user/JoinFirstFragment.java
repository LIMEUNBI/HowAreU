package com.kpu.howareu.fragment.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.user.JoinActivity;

public class JoinFirstFragment extends Fragment {

    private View mView;
    private EditText mEditName;
    private EditText mEditId;
    private EditText mEditPw1;
    private EditText mEditPw2;
    private Button mBtnNext;

    private static JoinFirstFragment instance;

    public static JoinFirstFragment getInstance() {
        if (instance == null) {
            new JoinFirstFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_join_first, container, false);

        mEditName = mView.findViewById(R.id.edit_name);
        mEditId = mView.findViewById(R.id.edit_id);
        mEditPw1 = mView.findViewById(R.id.edit_pw1);
        mEditPw2 = mView.findViewById(R.id.edit_pw2);

        mBtnNext = mView.findViewById(R.id.btn_next);

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditName.getText().toString())) {
                    Toast.makeText(getContext(), "이름을 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(mEditId.getText().toString())) {
                    Toast.makeText(getContext(), "아이디를 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(mEditPw1.getText().toString()) || TextUtils.isEmpty(mEditPw2.getText().toString())) {
                    Toast.makeText(getContext(), "패스워드를 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if (!mEditPw1.getText().toString().equals(mEditPw2.getText().toString())) {
                        Toast.makeText(getContext(), "패스워드와 패스워드 확인이 다릅니다. 다시 확인해주세요.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                JoinActivity.replaceFragment();
            }
        });

        return mView;
    }
}
