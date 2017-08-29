package com.thinkive.bank.activitystyledemo.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.thinkive.bank.activitystyledemo.R;
import com.thinkive.bank.activitystyledemo.util.ResourcesUtils;
import com.thinkive.bank.tablibrary.base.BaseFragment;

/**
 * @author: sq
 * @date: 2017/8/24
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 消息fragment，演示fragment与activity之间的传值
 */
public class MessageFragment extends BaseFragment {

    private Button mBtnSample;
    private TextView mTvSample;
    private TextView mTvTitle;
    private ImageView mIvAdd;
    private PopupWindow mPopupWindow;

    /**
     * 获取Fragment实例
     *
     * @param bundle
     * @return
     */
    public static MessageFragment getInstance(Bundle bundle) {
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_message);

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
        mIvAdd = findViewById(R.id.img_add);
        mBtnSample = findViewById(R.id.btn_sample2);
        mTvSample = findViewById(R.id.tv_sample2);
    }

    private void initViews() {
        mPopupWindow = buildPopWindow();
        mIvAdd.setVisibility(View.VISIBLE);
        mBtnSample.setText("获取从Activity传过来的值");
        mTvTitle.setText(ResourcesUtils.getString(context, R.string.tv_message));
    }

    private void initData() {

    }

    private void initEvent() {
        mBtnSample.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sample2:
                if (bundle != null) {
                    String value = bundle.getString("pass_to");
                    mTvSample.setText(value);
                }
                break;
            case R.id.img_add:
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else {
                    mPopupWindow.showAsDropDown(v);
                }
        }
    }

    /**
     * 构建头部更多功能弹出框
     *
     * @return
     */
    private PopupWindow buildPopWindow() {
        View view = ResourcesUtils.findViewById(context, R.layout.pop_add);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);//点击其他区域关闭popupWindow
        view.findViewById(R.id.tv_add_friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        view.findViewById(R.id.tv_add_public_num).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        view.findViewById(R.id.tv_add_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        view.findViewById(R.id.tv_con_sweep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        return popupWindow;
    }
}
