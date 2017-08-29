package com.thinkive.bank.tablibrary.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author: sq
 * @date: 2017/8/23
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 基础底部标签Activity
 */
public abstract class BaseBottomTabActivity extends BaseActivity {
    private static final String TAG = "BaseBottomTabActivity";

    protected static int[] tabClickIds;//底部标签父布局id集合（如：LinearLayout）

    protected View[] vTabClickViews;//底部标签父控件集合
    protected View[][] vTabSelectViews;//底部标签子控件集合

    //调用getFragment方法时，是否对点击的tab对应的fragment重新赋值
    protected boolean needReload = false;
    //当前显示的tab所在位置，对应fragment所在位置，此处默认为0
    protected int currentPosition = 0;

    protected Fragment[] fragments;

    @Override
    public void initViews() {

        tabClickIds = getTabClickIds();

        vTabClickViews = new View[getCount()];
        for (int i = 0; i < getCount(); i++) {
            vTabClickViews[i] = findViewById(tabClickIds[i]);
        }

        int[][] tabSelectIds = getTabSelectIds();
        if (tabSelectIds != null && tabSelectIds.length > 0) {
            vTabSelectViews = new View[tabSelectIds.length][getCount()];
            for (int i = 0; i < tabSelectIds.length; i++) {
                if (tabSelectIds[i] != null) {
                    for (int j = 0; j < tabSelectIds[i].length; j++) {
                        vTabSelectViews[i][j] = findViewById(tabSelectIds[i][j]);
                    }
                }
            }
        }
    }

    @Override
    public void initData() {
        // fragmentActivity子界面初始化
        fragments = new Fragment[getCount()];
        selectFragment(currentPosition);
    }

    /**
     * 选择并显示fragment
     *
     * @param position
     */
    public void selectFragment(int position) {

        setTabSelection(position);

        if (currentPosition == position) {
            if (needReload) {
                if (fragments[position] != null && fragments[position].isAdded()) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.remove(fragments[position]).commit();
                    fragments[position] = null;
                }
            } else {
                if (fragments[position] != null && fragments[position].isVisible()) {
                    Log.w(TAG, "selectFragment currentPosition == position" +
                            " >> fragments[position] != null && fragments[position].isVisible()" +
                            " >> return;	");
                    return;
                }
            }
        }

        if (fragments[position] == null) {
            fragments[position] = getFragment(position);
        }

        //全局的fragmentTransaction因为already committed 崩溃
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.hide(fragments[currentPosition]);
        if (!fragments[position].isAdded()) {
//            ft.add(getFragmentContainerResId(), fragments[position]);
            ft.add(getFragmentContainerResId(), fragments[position], "tag" + position);//加个tag标签--后面方便获取
        }
        ft.show(fragments[position]).commit();

        this.currentPosition = position;
    }

    /**
     * 设置选中状态
     *
     * @param position
     */
    protected void setTabSelection(int position) {
        if (vTabSelectViews == null) {
            Log.e(TAG, "setTabSelection  vTabSelectViews == null >> return;");
            return;
        }
        for (int i = 0; i < vTabSelectViews.length; i++) {
            if (vTabSelectViews[i] == null) {
                Log.w(TAG, "setTabSelection  vTabSelectViews[" + i + "] == null >> continue;");
                continue;
            }
            for (int j = 0; j < vTabSelectViews[i].length; j++) {
                vTabSelectViews[i][j].setSelected(j == position);
            }
        }
    }

    /**
     * 获取Tab(或Fragment)的数量
     *
     * @return
     */
    public int getCount() {
        return tabClickIds == null ? 0 : tabClickIds.length;
    }

    /**
     * 获取tab内设置点击事件的View的id
     *
     * @return
     */
    protected abstract int[] getTabClickIds();

    /**
     * 获取tab内设置选择事件的View的id，setSelected(position == currentPositon)
     *
     * @return
     * @warn 返回int[leghth0][leghth1]
     * 必须满足leghth0 >= 1 && leghth1 = getCount() = getTabClickIds().length
     */
    protected abstract int[][] getTabSelectIds();

    /**
     * 获取Fragment容器的id
     *
     * @return
     */
    public abstract int getFragmentContainerResId();

    /**
     * 获取新的Fragment
     *
     * @param position
     * @return
     */
    protected abstract Fragment getFragment(int position);

    protected void findViews() {

    }

    protected void initObjects() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void setListeners() {

        for (int i = 0; i < vTabClickViews.length; i++) {
            final int which = i;
            vTabClickViews[which].setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    selectFragment(which);
                }
            });
        }
    }

}