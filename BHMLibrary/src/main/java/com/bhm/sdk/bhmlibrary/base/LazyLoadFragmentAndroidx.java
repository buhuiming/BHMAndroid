package com.bhm.sdk.bhmlibrary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/** androidx懒加载
 * Created by bhm on 2020/6/22.
 */

public abstract class LazyLoadFragmentAndroidx extends Fragment {

    private boolean isFirstLoad = true; // 是否第一次加载
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(setLayoutId(), null);
        }
        initView();
        initListener();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            // 将数据加载逻辑放到onResume()方法中
            lazyLoad();
            isFirstLoad = false;
            return;
        }

        if(!setLoadDataOnce()){
            //每次可见都加载
            lazyLoad();
        }
    }

    /**
     * 设置布局资源id
     *
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 初始化视图
     *
     */
    protected void initView() {

    }

    /**
     * 初始化数据
     */
    protected void lazyLoad() {

    }

    /**
     * 初始化事件
     */
    protected void initListener() {

    }

    /** true:加载过数据不再再次加载； false:每次可见都加在数据
     * @return
     */
    protected boolean setLoadDataOnce(){
        return true;
    }
}
