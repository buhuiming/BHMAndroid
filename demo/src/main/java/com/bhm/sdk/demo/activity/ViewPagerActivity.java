package com.bhm.sdk.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bhm.sdk.bhmlibrary.views.TitleBar;
import com.bhm.sdk.demo.R;
import com.bhm.sdk.demo.adapter.DemoViewPagerAdapter;
import com.bhm.sdk.demo.fragment.DemoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bhm on 2018/5/25.
 */

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.titleBar)
    protected TitleBar titleBar;
    @BindView(R.id.viewpager)
    protected ViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        init();
        initListener();
    }

    private void init(){
        ButterKnife.bind(this);
        List<DemoFragment> list = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            DemoFragment one = new DemoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("data", i + "");
            one.setArguments(bundle);
            list.add(one);
        }
        DemoViewPagerAdapter adapter = new DemoViewPagerAdapter(getSupportFragmentManager(), list);
//        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(adapter);
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
