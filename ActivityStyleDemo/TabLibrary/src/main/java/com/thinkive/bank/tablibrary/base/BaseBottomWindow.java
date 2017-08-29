package com.thinkive.bank.tablibrary.base;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.thinkive.bank.tablibrary.R;

/**
 * @author: sq
 * @date: 2017/8/28
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 基础底部弹出界面Activity【不要在子类重复这个类中onCreate中的代码】
 */
public abstract class BaseBottomWindow extends BaseActivity {
    private static final String TAG = "BaseBottomWindow";

    public static final String INTENT_ITEMS = "INTENT_ITEMS";
    public static final String INTENT_TITLE = "INTENT_TITLE";
    public static final String INTENT_ITEM_IDS = "INTENT_ITEM_IDS";

    public static final String RESULT_TITLE = "RESULT_TITLE";
    public static final String RESULT_ITEM = "RESULT_ITEM";
    public static final String RESULT_ITEM_ID = "RESULT_ITEM_ID";


    //退出时之前的界面进入动画,可在finish();前通过改变它的值来改变动画效果
    protected int enterAnim;

    //退出时该界面动画,可在finish();前通过改变它的值来改变动画效果
    protected int exitAnim;

    protected View vBaseBottomWindowRoot;//子Activity全局背景View

    private boolean isExit = false;

    @SuppressLint("HandlerLeak")
    public Handler exitHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseBottomWindow.super.finish();
            overridePendingTransition(enterAnim, exitAnim);
        }
    };

    /**
     * 如果在子类中调用(即super.initView());则view必须含有initView中初始化用到的id(非@Nullable标记)且id对应的View的类型全部相同；
     * 否则必须在子类initView中重写这个类中initView内的代码(所有id替换成可用id)
     */
    @Override
    public void initViews() {
        enterAnim = exitAnim = R.anim.null_anim;

        vBaseBottomWindowRoot = findView(R.id.vBaseBottomWindowRoot);

        vBaseBottomWindowRoot.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_window_enter));
    }

    /**
     * 带动画退出,并使退出事件只响应一次
     */
    @Override
    public void finish() {
        Log.d(TAG, "finish >>> isExit = " + isExit);
        if (isExit) {
            return;
        }
        isExit = true;

        vBaseBottomWindowRoot.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_window_exit));
        vBaseBottomWindowRoot.setVisibility(View.GONE);

        exitHandler.sendEmptyMessageDelayed(0, 200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        vBaseBottomWindowRoot = null;
    }

}