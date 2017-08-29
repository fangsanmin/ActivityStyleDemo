package com.thinkive.bank.activitystyledemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thinkive.bank.activitystyledemo.R;
import com.thinkive.bank.activitystyledemo.activity.TestActivity;
import com.thinkive.bank.activitystyledemo.util.ResourcesUtils;
import com.thinkive.bank.tablibrary.base.BaseFragment;

/**
 * @author: sq
 * @date: 2017/8/25
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 设置fragment，演示fragment与fragment之间的传值（接口回调）、fragment跳转Activity
 */
public class SettingFragment extends BaseFragment {

    private ValuesCallBack callBack;
    private Button mBtnSample4;
    private Button mBtnSample5;
    private TextView mTvTitle;

    public interface ValuesCallBack {
        void setArgs(String args);
    }

    public void setCallBack(ValuesCallBack callBack) {
        this.callBack = callBack;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_setting);

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
        mBtnSample4 = findViewById(R.id.btn_sample4);
        mBtnSample5 = findViewById(R.id.btn_sample5);
    }

    private void initViews() {
        mTvTitle.setText(ResourcesUtils.getString(context, R.string.tv_setting));
    }

    private void initData() {

    }

    private void initEvent() {
        mBtnSample4.setOnClickListener(this);
        mBtnSample5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sample4:
                if (callBack == null) {
                    FindFragment findFragment = findFragmentByTag("tag2");
                    if (findFragment != null) {  //判断该fragment是否已added
                        callBack = findFragment;
                        callBack.setArgs("33333>>>>>>>>>>");
                    }
                } else {
                    callBack.setArgs("22222>>>>>>>>>>");
                }
                break;
            case R.id.btn_sample5:  //跳转Activity
                startActivity(TestActivity.class);
                break;
        }
    }
}
