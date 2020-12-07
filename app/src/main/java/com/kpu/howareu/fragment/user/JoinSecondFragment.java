package com.kpu.howareu.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;

public class JoinSecondFragment extends Fragment {

    private View mView;

    private EditText mEditBirth;
    private Button mBtnMan;
    private Button mBtnWoman;
    private EditText mEditPhone;
    private EditText mEditEmail;

    private Button mBtnStudent;
    private Button mBtnAdult;
    private EditText mEditSchool;
    private TextView mTxtSchoolChoice;
    private EditText mEditYear;

    private Button mBtnJoin;

    private static JoinSecondFragment instance;

    public static JoinSecondFragment getInstance() {
        if (instance == null) {
            new JoinSecondFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_join_second, container, false);

        mEditBirth = mView.findViewById(R.id.edit_birth);
        mBtnMan = mView.findViewById(R.id.btn_man);
        mBtnWoman = mView.findViewById(R.id.btn_woman);
        mEditPhone = mView.findViewById(R.id.edit_phone);
        mEditEmail = mView.findViewById(R.id.edit_email);

        mBtnStudent = mView.findViewById(R.id.btn_student);
        mBtnAdult = mView.findViewById(R.id.btn_adult);
        mEditSchool = mView.findViewById(R.id.edit_school);
        mTxtSchoolChoice = mView.findViewById(R.id.txt_school_choice);
        mEditYear = mView.findViewById(R.id.edit_year);

        mBtnJoin = mView.findViewById(R.id.btn_join);



        return mView;
    }
}
