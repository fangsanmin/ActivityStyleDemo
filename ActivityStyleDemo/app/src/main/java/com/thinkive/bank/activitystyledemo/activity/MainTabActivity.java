package com.thinkive.bank.activitystyledemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Toast;

import com.thinkive.bank.activitystyledemo.R;
import com.thinkive.bank.activitystyledemo.fragment.FindFragment;
import com.thinkive.bank.activitystyledemo.fragment.MainFragment;
import com.thinkive.bank.activitystyledemo.fragment.MessageFragment;
import com.thinkive.bank.activitystyledemo.fragment.SettingFragment;
import com.thinkive.bank.tablibrary.base.BaseBottomTabActivity;

/**
 * @author: sq
 * @date: 2017/8/25
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 应用主页
 */
public class MainTabActivity extends BaseBottomTabActivity {

    private static final int FLAG_FRAGMENT_MESSAGE = 1;
    private static final int FLAG_FRAGMENT_FIND = 2;
    private static final int FLAG_FRAGMENT_SETTING = 3;

    private long firstTime = 0;//第一次返回按钮计时

    private MainFragment mainFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_tab;
    }

    @Override
    public void initViews() {
        super.initViews();
        mainFragment = new MainFragment();

    }

    @Override
    protected int[] getTabClickIds() {
        return new int[]{R.id.llBottomTabTab0, R.id.llBottomTabTab1, R.id.llBottomTabTab2, R.id.llBottomTabTab3};
    }

    @Override
    protected int[][] getTabSelectIds() {
        return new int[][]{
                new int[]{R.id.ivBottomTabTab0, R.id.ivBottomTabTab1, R.id.ivBottomTabTab2, R.id.ivBottomTabTab3},//底部图标
                new int[]{R.id.tvBottomTabTab0, R.id.tvBottomTabTab1, R.id.tvBottomTabTab2, R.id.tvBottomTabTab3}//底部文字
        };
    }

    @Override
    public int getFragmentContainerResId() {
        return R.id.flMainTabFragmentContainer;
    }

    @Override
    protected Fragment getFragment(int position) {

        switch (position) {
            case FLAG_FRAGMENT_MESSAGE:
                Bundle bundle = new Bundle();
                bundle.putString("pass_to", "11111>>>>>>>>");
                return MessageFragment.getInstance(bundle);
            case FLAG_FRAGMENT_FIND:
                return new FindFragment();
            case FLAG_FRAGMENT_SETTING:
                return new SettingFragment();
            default:
                return mainFragment;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(context, "再按一次退出", Toast.LENGTH_LONG).show();
                    firstTime = secondTime;
                } else {
                    /**
                     *  nonRoot=false→ 仅当activity为task根（即首个activity例如启动activity之类的）时才生效
                     *  nonRoot=true→ 忽略上面的限制
                     */
                    moveTaskToBack(false);//应用退到后台,等同于home键，并没有finish()
                    System.exit(0);
//                    finish();
                }
                return true;
        }

        return super.onKeyUp(keyCode, event);
    }

}