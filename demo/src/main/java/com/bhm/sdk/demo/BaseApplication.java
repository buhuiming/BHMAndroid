package com.bhm.sdk.demo;

import android.app.Application;

import com.bhm.sdk.bhmlibrary.crash.CrashManager;
import com.bhm.sdk.demo.utils.CrashDeal;

/**
 * Created by bhm on 2018/6/26.
 */

public class BaseApplication extends Application {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        application = this;
        super.onCreate();
        //APP异常信息处理
        CrashManager.getCrashManager().init(new CrashDeal());
    }

    public static BaseApplication getApplication() {
        return application;
    }
}
