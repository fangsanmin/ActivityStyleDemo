package com.thinkive.bank.tablibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.thinkive.bank.tablibrary.base.BaseBottomWindow;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: sq
 * @date: 2017/8/28
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 通用底部弹出菜单
 */
public class BottomMenuWindow extends BaseBottomWindow implements OnItemClickListener {
    private static final String TAG = "BottomMenuWindow";

    private ListView lvBottomMenu;
    private TextView tvBaseTitle;

    private Intent intent;
    private String title;
    private ArrayList<String> nameList = null;
    private ArrayList<Integer> idList = null;
    private ArrayAdapter<String> adapter;

    /**
     * 启动BottomMenuWindow的Intent
     *
     * @param context
     * @param names
     * @return
     */
    public static Intent createIntent(Context context, String[] names) {
        return createIntent(context, names, new ArrayList<Integer>());
    }

    /**
     * 启动BottomMenuWindow的Intent
     *
     * @param context
     * @param nameList
     * @return
     */
    public static Intent createIntent(Context context, ArrayList<String> nameList) {
        return createIntent(context, nameList, null);
    }

    /**
     * 启动BottomMenuWindow的Intent
     *
     * @param context
     * @param names
     * @param ids
     * @return
     */
    public static Intent createIntent(Context context, String[] names, int[] ids) {
        return new Intent(context, BottomMenuWindow.class).
                putExtra(INTENT_ITEMS, names).
                putExtra(INTENT_ITEM_IDS, ids);
    }

    /**
     * 启动BottomMenuWindow的Intent
     *
     * @param context
     * @param names
     * @param idList
     * @return
     */
    public static Intent createIntent(Context context, String[] names, ArrayList<Integer> idList) {
        return new Intent(context, BottomMenuWindow.class).
                putExtra(INTENT_ITEMS, names).
                putExtra(INTENT_ITEM_IDS, idList);
    }

    /**
     * 启动BottomMenuWindow的Intent
     *
     * @param context
     * @param nameList
     * @param idList
     * @return
     */
    public static Intent createIntent(Context context, ArrayList<String> nameList, ArrayList<Integer> idList) {
        return new Intent(context, BottomMenuWindow.class).
                putStringArrayListExtra(INTENT_ITEMS, nameList).
                putIntegerArrayListExtra(INTENT_ITEM_IDS, idList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.bottom_menu_window;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initObjects() {

    }

    @Override
    public void initViews() {
        super.initViews();

        lvBottomMenu = findView(R.id.lvBottomMenu);
        tvBaseTitle = findView(R.id.tvBaseTitle);
    }

    @Override
    public void initData() {
        intent = getIntent();
        title = intent.getStringExtra(INTENT_TITLE);
        if (!TextUtils.isEmpty(title)) {
            tvBaseTitle.setVisibility(View.VISIBLE);
            tvBaseTitle.setText(title);
        } else {
            tvBaseTitle.setVisibility(View.GONE);
        }

        int[] ids = intent.getIntArrayExtra(INTENT_ITEM_IDS);
        if (ids == null || ids.length <= 0) {
            idList = intent.getIntegerArrayListExtra(INTENT_ITEM_IDS);
        } else {
            idList = new ArrayList<Integer>();
            for (int id : ids) {
                idList.add(id);
            }
        }

        String[] menuItems = intent.getStringArrayExtra(INTENT_ITEMS);
        if (menuItems == null || menuItems.length <= 0) {
            nameList = intent.getStringArrayListExtra(INTENT_ITEMS);
        } else {
            nameList = new ArrayList<String>(Arrays.asList(menuItems));
        }
        if (nameList == null || nameList.size() <= 0) {
            Log.e(TAG, "init   nameList == null || nameList.size() <= 0 >> finish();return;");
            finish();
            return;
        }

        adapter = new ArrayAdapter<>(this, R.layout.bottom_menu_item, R.id.tvBottomMenuItem, nameList);
        lvBottomMenu.setAdapter(adapter);

    }

    @Override
    public void setListeners() {
        lvBottomMenu.setOnItemClickListener(this);

        vBaseBottomWindowRoot.setOnTouchListener(new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent()
                .putExtra(RESULT_TITLE, tvBaseTitle.getText().toString())
                .putExtra(RESULT_ITEM_ID, position);
        if (idList != null && idList.size() > position) {
            intent.putExtra(RESULT_ITEM_ID, idList.get(position));
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View v) {

    }

}