package com.bhm.sdk.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bhm.sdk.demo.fragment.DemoFragment;

import java.util.List;

/**
 * Created by bhm on 2018/5/25.
 */

public class DemoViewPagerAdapter extends FragmentPagerAdapter {

    private List<DemoFragment> fragments;

    public DemoViewPagerAdapter(FragmentManager fm, List<DemoFragment> list) {
        super(fm);
        fragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(View container, int position, Object object){
        // TODO Auto-generated method stub
        //去掉super，不销毁 避免滑动后重新请求数据
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        // TODO Auto-generated method stub
    }
}
