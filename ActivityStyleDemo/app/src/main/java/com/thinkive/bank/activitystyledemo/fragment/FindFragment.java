package com.thinkive.bank.activitystyledemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thinkive.bank.activitystyledemo.R;
import com.thinkive.bank.activitystyledemo.util.ResourcesUtils;
import com.thinkive.bank.tablibrary.base.BaseFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: sq
 * @date: 2017/8/25
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 发现fragment，演示fragment与fragment之间的传值（接口回调）
 */
public class FindFragment extends BaseFragment implements SettingFragment.ValuesCallBack {

    private TextView mTvSample;
    private TextView mTvTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_find);

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

        mTvSample = findViewById(R.id.tv_sample3);
    }

    private void initViews() {
        mTvTitle.setText(ResourcesUtils.getString(context, R.string.tv_find));

    }

    private void initData() {
        showProgressDialog("加载中....");
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        },3000);
    }

    private void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 接口回调，接收从SettingFragment传过来的值
     *
     * @param args
     */
    @Override
    public void setArgs(String args) {
        showProgressDialog("传值","传送中....");
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        },3000);
        mTvSample.setText(args);
    }
}
