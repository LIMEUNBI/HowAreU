package com.kpu.howareu.activity.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;

public class ScheduleFragment extends Fragment {

    private View mView = null;
    private static ScheduleFragment instance = null;

    public static ScheduleFragment getInstance() {
        if (instance == null) {
            instance = new ScheduleFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_schedule, container, false);


        return mView;
    }
}
