package com.bhm.sdk.bhmlibrary.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** 懒加载
 * Created by bhm on 2018/5/25.
 */

public abstract class LazyLoadFragment extends Fragment{

    protected View rootView;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isInit;

    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(setLayoutId(), null);
        }
        if(!isInit) {
            initView();// 控件初始化
            initListener();
        }
        isInit = true;
        onVisible();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    /**
     * 可见
     */
    protected void onVisible() {
        if(setLoadDataOnce()){
            if(mHasLoadedOnce){
                return;
            }
        }
        if (!isInit || !isVisible) {
            return;
        }
        lazyLoad();
        if(setLoadDataOnce()) {
            mHasLoadedOnce = true;
        }
    }

    /** true:加载过数据不再再次加载； false:每次可见都加在数据
     * @return
     */
    protected boolean setLoadDataOnce(){
        return true;
    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    /** 布局的Id
     * @return
     */
    protected abstract int setLayoutId();

    protected void initView(){}

    protected void initListener(){}
}
