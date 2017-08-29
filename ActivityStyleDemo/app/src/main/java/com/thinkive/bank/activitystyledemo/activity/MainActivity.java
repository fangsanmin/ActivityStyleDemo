package com.thinkive.bank.activitystyledemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.thinkive.bank.activitystyledemo.R;
import com.thinkive.bank.tablibrary.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private Button mBtnStart;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        mBtnStart = findView(R.id.btn_start, this);
    }

    @Override
    protected void initObjects() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListeners() {
//        mBtnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainTabActivity.class));
        finish();
    }

}
