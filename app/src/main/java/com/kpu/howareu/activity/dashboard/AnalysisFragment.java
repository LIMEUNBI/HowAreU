package com.kpu.howareu.activity.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kpu.howareu.R;

public class AnalysisFragment extends Fragment {

    private View mView = null;
    private static AnalysisFragment instance = null;

    public static AnalysisFragment getInstance() {
        if (instance == null) {
            instance = new AnalysisFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_analysis, container, false);


        return mView;
    }
}
