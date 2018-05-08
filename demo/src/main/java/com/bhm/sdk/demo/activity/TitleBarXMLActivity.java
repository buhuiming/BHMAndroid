package com.bhm.sdk.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bhm.sdk.demo.R;
import com.bhm.sdk.bhmlibrary.views.TitleBar;

/**
 * Created by bhm on 2018/5/7.
 */

public class TitleBarXMLActivity extends AppCompatActivity{

    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_title_bar);
        initView();
        initListener();
    }

    private void initView(){
        titleBar = (TitleBar) findViewById(R.id.titleBar);
    }

    private void initListener(){
        titleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleBarXMLActivity.this, titleBar.getRightTextView().
                        getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        titleBar.setTitleBarOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TitleBarXMLActivity.this, "点击标题栏", Toast.LENGTH_SHORT).show();
//            }
//        });

        titleBar.setTitleBarOnTwoClickListener(new TitleBar.OnTwoClickListener() {
            @Override
            public void onTwoClick(View view) {
                Toast.makeText(TitleBarXMLActivity.this, "双击标题栏...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
