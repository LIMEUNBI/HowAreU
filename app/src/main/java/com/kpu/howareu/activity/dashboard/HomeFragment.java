package com.kpu.howareu.activity.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kpu.howareu.R;
import com.kpu.howareu.common.config.Config;
import com.kpu.howareu.common.utils.SharedPreferenceBase;

public class HomeFragment extends Fragment {

    private View mView = null;
    private static HomeFragment instance = null;

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    private TextView mTxtUserName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home, container, false);

        mTxtUserName = mView.findViewById(R.id.txt_user_name);
        String userName = SharedPreferenceBase.getPrefString(getContext(), Config.USER_NAME, "");
        mTxtUserName.setText(userName);

        return mView;
    }
}
