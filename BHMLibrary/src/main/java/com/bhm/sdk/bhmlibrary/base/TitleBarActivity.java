package com.bhm.sdk.bhmlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bhm.sdk.bhmlibrary.R;
import com.bhm.sdk.bhmlibrary.views.TitleBar;

/**
 * Created by bhm on 2018/5/7.
 */

public abstract class TitleBarActivity extends AppCompatActivity{

    protected TitleBar titleBar;
    protected LinearLayout linearLayout;
    protected ViewGroup rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_title_bar, null, false);
        rootView = (ViewGroup) view;
        setContentView(rootView);
        init();
    }

    private void init(){
        titleBar = (TitleBar) findViewById(R.id.titleBar);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        initTitleBar(titleBar);
        if(null != setContentView()){
            linearLayout.addView(setContentView());
        }
        initView();
    }

    public abstract void initTitleBar(TitleBar titleBar);

    public abstract View setContentView();

    public void initView(){}
}
