package com.thinkive.bank.activitystyledemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkive.bank.activitystyledemo.R;
import com.thinkive.bank.activitystyledemo.util.ResourcesUtils;
import com.thinkive.bank.tablibrary.base.BaseFragment;

/**
 * @author: sq
 * @date: 2017/8/24
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 主页fragment，演示UI控件初始化、点击事件
 */
public class MainFragment extends BaseFragment {

    private Button mBtnSample;
    private ImageView mIvSample;
    private TextView mTvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //类相关初始化，必须首先调用
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_main);

        //功能归类分区方法，必须调用<<<<<<<<<<
        findViews();
        initViews();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

        return view;
    }

    private void findViews() {
        mTvTitle = findViewById(R.id.tv_tite);
        mBtnSample = findViewById(R.id.btn_sample1);
        mIvSample = findViewById(R.id.iv_sample1);
    }


    private void initViews() {
        mBtnSample.setText("隐藏");
        mTvTitle.setText(ResourcesUtils.getString(context, R.string.tv_main));
        mIvSample.setImageBitmap(ResourcesUtils.resourceToBitmap(context, R.mipmap.ic_launcher));
    }

    private void initEvent() {
        mBtnSample.setOnClickListener(this);
    }

    private void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sample1:
                if (mIvSample.getVisibility() == View.VISIBLE) {
                    mIvSample.setVisibility(View.GONE);
                    mBtnSample.setText("显示");
                } else {
                    mIvSample.setVisibility(View.VISIBLE);
                    mBtnSample.setText("隐藏");
                }
                break;
        }
    }
}
