package com.kpu.howareu.activity.self;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.kpu.howareu.R;
import com.kpu.howareu.activity.BaseActivity;

public class SelfInputActivity extends BaseActivity {

    private ImageView mImgBefore;
    private EditText mEditSelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_input);

        mImgBefore = findViewById(R.id.img_before);
        mEditSelf = findViewById(R.id.edit_self);

        mEditSelf.setFocusable(true);
        showSoftKeyboard();

        mImgBefore.setOnClickListener(v -> finish());

    }

    protected void showSoftKeyboard() {
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(getCurrentFocus(), InputMethodManager.SHOW_FORCED);
    }
}
