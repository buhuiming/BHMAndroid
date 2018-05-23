package com.bhm.sdk.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bhm.sdk.bhmlibrary.utils.TitleBarBuilder;
import com.bhm.sdk.bhmlibrary.views.TitleBar;
import com.bhm.sdk.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bhm on 2018/5/7.
 */

public class TitleBarJavaActivity extends AppCompatActivity{

    private TitleBar titleBar;
    private View contentView;
    @BindView(R.id.tv_text)
    protected TextView tv_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView(){
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_java_title_bar, null, false);
        ButterKnife.bind(this, contentView);
        titleBar = TitleBarBuilder.newBuilder(this)
                .createTitleBar()
                .setContentView(contentView)
                .build()
                .setTitleBarHeight(50f, true)
                .setTitleTextColor(R.color.white)
                .setRightTextColor(R.color.white)
                .setLeftTextColor(R.color.white)
                .setTitleText("代码生成")
                .setRightText("提交")
                .setLeftText("后退")
                .setLeftViewBackgroundResource(R.drawable.arrow_a)
                .setIsLeftViewShow(true)
                .setIsRightViewShow(true)
                .setDividerHeight(1f, false)
                .setDividerColor(R.color.black)
                .setBackGroundColor(R.color.colorPrimary);
        tv_text.setText("ssssssssssssssss");
    }

    private void initListener(){
        titleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
