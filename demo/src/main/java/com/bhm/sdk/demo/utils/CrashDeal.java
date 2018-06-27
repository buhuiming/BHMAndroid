package com.bhm.sdk.demo.utils;

import android.widget.Toast;

import com.bhm.sdk.bhmlibrary.crash.CrashManager;
import com.bhm.sdk.bhmlibrary.interfaces.ExceptionHandler;
import com.bhm.sdk.demo.BaseApplication;

/** APP异常信息处理，APP抛异常后，得到异常信息
 * Created by bhm on 2018/6/26.
 */

public class CrashDeal implements ExceptionHandler {

    @Override
    public void handlerException(Thread thread, Throwable throwable) {
        //集成此机制，防止APP因为抛异常闪退或者宕机。
        //所有未有try catch处理的异常，统一在此处理。可以给出提示，重启APP。
        //或者上传异常日志到服务器
        String errorMsg = CrashManager.getStackMsg(throwable);
        Toast.makeText(BaseApplication.getApplication(), errorMsg, Toast.LENGTH_LONG).show();
    }
}
