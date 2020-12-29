package com.kpu.howareu.activity.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.self.SelfInputActivity;
import com.kpu.howareu.activity.self.SelfListActivity;

public class AnalysisFragment extends Fragment {

    private View mView = null;
    private static AnalysisFragment instance = null;

    private TextView mTxtMore;
    private Button mBtnAnalysis;

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

        mTxtMore = mView.findViewById(R.id.txt_more);
        mBtnAnalysis = mView.findViewById(R.id.btn_analysis);

        mTxtMore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SelfListActivity.class);
            startActivity(intent);
        });

        mBtnAnalysis.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SelfInputActivity.class);
            startActivity(intent);
        });

        return mView;
    }
}
