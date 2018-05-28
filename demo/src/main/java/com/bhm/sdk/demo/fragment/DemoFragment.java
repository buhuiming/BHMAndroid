package com.bhm.sdk.demo.fragment;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bhm.sdk.bhmlibrary.base.LazyLoadFragment;
import com.bhm.sdk.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bhm on 2018/5/25.
 */

public class DemoFragment extends LazyLoadFragment {

    @BindView(R.id.tv_text)
    protected TextView tv_text;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_java_title_bar;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this, rootView);
        tv_text.setText("fragment position is " + getArguments().get("data"));
        Log.i("DemoFragment", getArguments().get("data") + " 初始化完毕");
    }

    @Override
    protected void lazyLoad() {
        Log.i("DemoFragment", getArguments().get("data") + " 加载数据");
        Toast.makeText(getActivity(), getArguments().get("data") + " 加载数据", Toast.LENGTH_SHORT).show();
    }

    /*重写此方法，返回true,则只加载一次数据，切换fragment不再加载数据；返回false，则每次fragment可见都加载数据。默认是true*/
    /*如果adapter的destroyItem方法去掉super，则只会初始化（initView）一次，切换fragment不再初始化；否则，fragment每次可见都初始化*/
    @Override
    protected boolean setLoadDataOnce() {
        return false;
    }
}
