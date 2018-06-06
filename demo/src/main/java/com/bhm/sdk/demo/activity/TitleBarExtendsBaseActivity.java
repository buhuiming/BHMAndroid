package com.bhm.sdk.demo.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bhm.sdk.bhmlibrary.base.TitleBarActivity;
import com.bhm.sdk.bhmlibrary.views.TitleBar;
import com.bhm.sdk.demo.R;

/**
 * Created by bhm on 2018/5/7.
 */

public class TitleBarExtendsBaseActivity extends TitleBarActivity {

    @Override
    public void initTitleBar(final TitleBar titleBar) {
//        titleBar.setTitleBarHeight(150f, false);
        titleBar.setTitleBarHeight(50f, true);
        titleBar.setTitleTextColor(R.color.black);
        titleBar.setRightTextColor(R.color.black);
        titleBar.setLeftTextColor(R.color.black);
        titleBar.setTitleText("集成Base");
        titleBar.setRightText("提交");
        titleBar.setLeftText("后退");
        titleBar.setLeftViewBackgroundResource(R.drawable.img_close_source);
        titleBar.setIsLeftViewShow(true);
        titleBar.setIsRightViewShow(true);
        titleBar.setDividerHeight(1f, false);
        titleBar.setDividerColor(R.color.black);
        titleBar.setBackGroundColor(R.color.white);
        titleBar.setTitleTextSize(18);
        titleBar.setLeftTextSize(16);
        titleBar.setRightTextSize(16);

        titleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleBarExtendsBaseActivity.this, titleBar.getRightTextView().
                        getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        titleBar.setTitleBarOnTwoClickListener(new TitleBar.OnTwoClickListener() {
            @Override
            public void onTwoClick(View view) {
                Toast.makeText(TitleBarExtendsBaseActivity.this, "双击标题栏...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View setContentView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_extends_title_bar, rootView, false);
    }
}
