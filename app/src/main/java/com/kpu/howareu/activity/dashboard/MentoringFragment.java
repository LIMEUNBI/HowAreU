package com.kpu.howareu.activity.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;

public class MentoringFragment extends Fragment {

    private View mView = null;
    private static MentoringFragment instance = null;

    public static MentoringFragment getInstance() {
        if (instance == null) {
            instance = new MentoringFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_mentoring, container, false);


        return mView;
    }
}
