package com.kpu.howareu.activity.user;

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

    public EditText mEditBirth;
    public Button mBtnMan;
    public Button mBtnWoman;
    public EditText mEditPhone;
    public EditText mEditEmail;

    public Button mBtnStudent;
    public Button mBtnAdult;
    public EditText mEditSchool;
    public TextView mTxtSchoolChoice;
    public EditText mEditYear;

    public static JoinSecondFragment getInstance() {
        return new JoinSecondFragment();
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


        return mView;
    }
}
