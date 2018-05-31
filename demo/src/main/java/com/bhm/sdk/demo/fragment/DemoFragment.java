package com.bhm.sdk.demo.fragment;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bhm.sdk.bhmlibrary.base.LazyLoadFragment;
import com.bhm.sdk.demo.R;
import com.bhm.sdk.demo.entity.DoGetEntity;
import com.bhm.sdk.demo.http.HttpApi;
import com.bhm.sdk.rxlibrary.rxjava.CallBack;
import com.bhm.sdk.rxlibrary.rxjava.RxBaseActivity;
import com.bhm.sdk.rxlibrary.rxjava.RxBuilder;
import com.bhm.sdk.rxlibrary.rxjava.RxManager;
import com.bhm.sdk.rxlibrary.utils.RxLoadingDialog;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by bhm on 2018/5/25.
 */

public class DemoFragment extends LazyLoadFragment {

    @BindView(R.id.tv_text)
    protected TextView tv_text;
    private RxBaseActivity activity;
    protected RxManager rxManager = new RxManager();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (RxBaseActivity) context;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_java_title_bar;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this, rootView);
        tv_text.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv_text.setText("fragment position is " + getArguments().get("data"));
        Log.i("DemoFragment", getArguments().get("data") + " 初始化完毕");
    }

    @Override
    protected void lazyLoad() {
        Log.i("DemoFragment", getArguments().get("data") + " 加载数据");
        RxBuilder builder = RxBuilder.newBuilder(activity)
                .setLoadingDialog(RxLoadingDialog.getDefaultDialog())
                .setDialogAttribute(true, false, false)
                //.setHttpTimeOut()
                .setIsLogOutPut(true)//默认是false
                .setIsDefaultToast(true, rxManager)
                .bindRx();
        Observable<DoGetEntity> observable = builder
                .createApi(HttpApi.class, "http://news-at.zhihu.com")
                .getData("Bearer aedfc1246d0b4c3f046be2d50b34d6ff", "1");
        builder.setCallBack(observable, new CallBack<DoGetEntity>() {
            @Override
            public void onStart(Disposable disposable) {

            }

            @Override
            public void onSuccess(DoGetEntity response) {
                Log.i("MainActivity--> ", response.getDate());
                Toast.makeText(activity, response.getDate(), Toast.LENGTH_SHORT).show();
                tv_text.setText(new Gson().toJson(response));
            }

            @Override
            public void onFail(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /*重写此方法，返回true,则只加载一次数据，切换fragment不再加载数据；返回false，则每次fragment可见都加载数据。默认是true*/
    /*如果adapter的destroyItem方法去掉super，则只会初始化（initView）一次，切换fragment不再初始化；否则，fragment每次可见都初始化*/
    @Override
    protected boolean setLoadDataOnce() {
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        this.rxManager.unSubscribe();
    }
}
