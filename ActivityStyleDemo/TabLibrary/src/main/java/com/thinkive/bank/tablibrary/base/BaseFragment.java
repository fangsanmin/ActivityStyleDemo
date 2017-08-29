package com.thinkive.bank.tablibrary.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: sq
 * @date: 2017/8/24
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 基础android.support.v4.app.Fragment，通过继承可获取或使用里面创建的组件和方法
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BaseFragment";

    protected Context context;

    //该Fragment全局视图,不能在子类中创建【非abstract子类的onCreateView中return view】
    protected View view = null;

    //布局解释器,不能在子类中创建
    protected LayoutInflater inflater = null;

    //添加这个Fragment视图的布局,不能在子类中创建
    protected ViewGroup container = null;

    //activity与fragment，fragment与fragment之间的通讯（传值）
    protected Bundle bundle = null;

    //Fragment管理器
    protected FragmentManager fragmentManager;

    //进度弹窗
    protected ProgressDialog progressDialog = null;

    private boolean isAlive = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        isAlive = true;
        this.inflater = inflater;
        this.container = container;
        bundle = getArguments();
        fragmentManager = getFragmentManager();
        return view;
    }

    /**
     * 设置界面布局，在onCreateView后调用【最多调用一次】
     *
     * @param layoutResID
     */
    public void setContentView(int layoutResID) {
        setContentView(inflater.inflate(layoutResID, container, false));
    }

    /**
     * 设置界面布局，在onCreateView后调用【最多调用一次】
     *
     * @param v
     */
    public void setContentView(View v) {
        setContentView(v, null);
    }

    /**
     * 设置界面布局，在onCreateView后调用【最多调用一次】
     *
     * @param v
     * @param params
     */
    public void setContentView(View v, ViewGroup.LayoutParams params) {
        view = v;
    }

    /**
     * 通过id查找并获取控件，使用时不需要强转【调用前必须调用setContentView】
     *
     * @param id
     * @param <V>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findViewById(int id) {
        return (V) view.findViewById(id);
    }

    /**
     * 通过id查找并获取控件，并setOnClickListener，使用时不需要强转【调用前必须调用setContentView】
     *
     * @param id
     * @param l
     * @param <V>
     * @return
     */
    public <V extends View> V findViewById(int id, View.OnClickListener l) {
        V v = findViewById(id);
        v.setOnClickListener(l);
        return v;
    }

    /**
     * 通过tag查找并获取Fragment,使用时不需要强转【调用前必须调用setContentView】
     *
     * @param tag
     * @param <F>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <F extends Fragment> F findFragmentByTag(String tag) {
        F f = (F) fragmentManager.findFragmentByTag(tag);
        return f;
    }

    /**
     * 使用class来启动一个Activity
     *
     * @param activity 需要启动的Activity的.class实例
     */
    public void startActivity(Class<?> activity) {
        final Intent intent = new Intent(context, activity);
        startActivity(intent);
    }

    /**
     * 使用class来启动一个Activity
     *
     * @param activity 需要启动的Activity的.class实例
     * @param flag     启动模式
     */
    public void startActivity(Class<?> activity, int flag) {
        final Intent intent = new Intent(context, activity);
        intent.addFlags(flag);
        startActivity(intent);
    }

    /**
     * 使用class来启动一个Activity
     *
     * @param activity 需要启动的Activity的.class实例
     * @param bundle   需要传的参数
     */
    public void startActivity(Class<?> activity, Bundle bundle) {
        final Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 使用class来启动一个Activity
     *
     * @param activity 需要启动的Activity的.class实例
     * @param bundle   需要传的参数
     * @param flag     启动模式
     */
    public void startActivity(Class<?> activity, Bundle bundle, int flag) {
        final Intent intent = new Intent(context, activity);
        intent.addFlags(flag);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public final boolean isAlive() {
        return isAlive && context != null;// & ! isRemoving();导致finish，onDestroy内runUiThread不可用
    }

    /**
     * 在UI线程中运行，建议用这个方法代替runOnUiThread
     *
     * @param action
     */
    public final void runUiThread(Runnable action) {
        if (!isAlive()) {
            Log.w(TAG, "runUiThread  isAlive() == false >> return;");
            return;
        }
        ((Activity) context).runOnUiThread(action);
    }


    /**
     * 展示加载进度条,无标题
     *
     * @param stringResId
     */
    public void showProgressDialog(int stringResId) {
        try {
            showProgressDialog(null, context.getResources().getString(stringResId));
        } catch (Exception e) {
            Log.e(TAG, "showProgressDialog  showProgressDialog(null, context.getResources().getString(stringResId));");
        }
    }

    /**
     * 展示加载进度条,无标题
     *
     * @param message
     */
    public void showProgressDialog(String message) {
        showProgressDialog(null, message);
    }

    /**
     * 展示加载进度条
     *
     * @param title   标题
     * @param message 信息
     */
    public void showProgressDialog(final String title, final String message) {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(context);
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (!TextUtils.isEmpty(title)) {
                    progressDialog.setTitle(title);
                }
                if (!TextUtils.isEmpty(message)) {
                    progressDialog.setMessage(message);
                }
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        });
    }

    /**
     * 隐藏加载进度
     */
    public void dismissProgressDialog() {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                //把判断写在runOnUiThread外面导致有时dismiss无效，可能不同线程判断progressDialog.isShowing()结果不一致
                if (progressDialog == null || progressDialog.isShowing() == false) {
                    Log.w(TAG, "dismissProgressDialog  progressDialog == null" +
                            " || progressDialog.isShowing() == false >> return;");
                    return;
                }
                progressDialog.dismiss();
            }
        });
    }


    @Override
    public void onDestroy() {
        dismissProgressDialog();
        if (view != null) {
            try {
                view.destroyDrawingCache();
            } catch (Exception e) {
                Log.w(TAG, "onDestroy  try { view.destroyDrawingCache();" +
                        " >> } catch (Exception e) {\n" + e.getMessage());
            }
        }
        isAlive = false;
        super.onDestroy();
        view = null;
        inflater = null;
        container = null;
        bundle = null;
        context = null;

        Log.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }
}
