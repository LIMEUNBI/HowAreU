package com.kpu.howareu.activity.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;

public class JoinFirstFragment extends Fragment {

    public View mView;
    public EditText mEditName;
    public EditText mEditId;
    public EditText mEditPw1;
    public EditText mEditPw2;

    public static JoinFirstFragment getInstance() {
        return new JoinFirstFragment();
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

        return mView;
    }
}
