package com.thinkive.bank.activitystyledemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thinkive.bank.activitystyledemo.R;
import com.thinkive.bank.tablibrary.BottomMenuWindow;
import com.thinkive.bank.tablibrary.widget.ActionSheetDialog;

/**
 * @author: sq
 * @date: 2017/8/28
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 测试底部弹出菜单效果
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener, ActionSheetDialog.OnSheetItemClickListener {

    //底部弹出菜单item列表
    private static final String[] TOPBAR_COLOR_NAMES = {"灰色", "蓝色", "黄色", "灰色", "蓝色", "黄色", "蓝色", "黄色", "灰色", "蓝色", "黄色"};
    //底部弹出菜单item列表
    private static final String[] BOTTOM_MENU_ITEMS = {"设置备注", "编辑标签", "设置为星标好友", "删除好友"};
    private static final int REQUEST_TO_BOTTOM_MENU = 1;
    private Button mBtnTest1;
    private Button mBtnTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();
        initEvent();
    }

    private void initEvent() {
        mBtnTest1.setOnClickListener(this);
        mBtnTest2.setOnClickListener(this);
    }

    private void initViews() {
        mBtnTest1 = (Button) findViewById(R.id.btn_test1);
        mBtnTest2 = ((Button) findViewById(R.id.btn_test2));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                Intent intent = BottomMenuWindow.createIntent(this, TOPBAR_COLOR_NAMES);
                intent.putExtra(BottomMenuWindow.INTENT_TITLE, "选择颜色");
                startActivityForResult(intent, REQUEST_TO_BOTTOM_MENU);
                overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
                break;
            case R.id.btn_test2:
                creatDialog();
                break;

        }
    }

    /**
     * 创建底部弹出菜单框
     */
    private void creatDialog() {
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
//                .setTitle("标题")
                .addSheetItem(BOTTOM_MENU_ITEMS[0], ActionSheetDialog.SheetItemColor.Black, this)
                .addSheetItem(BOTTOM_MENU_ITEMS[1], ActionSheetDialog.SheetItemColor.Black, this)
                .addSheetItem(BOTTOM_MENU_ITEMS[2], ActionSheetDialog.SheetItemColor.Black, this)
                .addSheetItem(BOTTOM_MENU_ITEMS[3], ActionSheetDialog.SheetItemColor.Red, this)
                .show();
    }

    @Override
    public void onSheetItemClick(int which) {
        switch (which) {
            case 1:
                Toast.makeText(this, "点击了" + BOTTOM_MENU_ITEMS[0], Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(this, "点击了" + BOTTOM_MENU_ITEMS[1], Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(this, "点击了" + BOTTOM_MENU_ITEMS[2], Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(this, "点击了" + BOTTOM_MENU_ITEMS[3], Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_TO_BOTTOM_MENU:
                if (data != null) {
                    int selectedPosition = data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1);
                    Toast.makeText(this, "点击了" + selectedPosition, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
