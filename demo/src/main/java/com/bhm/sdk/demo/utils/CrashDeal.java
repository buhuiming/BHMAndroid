package com.bhm.sdk.demo.utils;

import android.widget.Toast;

import com.bhm.sdk.bhmlibrary.crash.CrashManager;
import com.bhm.sdk.bhmlibrary.interfaces.CrashResult;
import com.bhm.sdk.demo.BaseApplication;

/** APP异常信息处理，APP抛异常后，得到异常信息
 * Created by bhm on 2018/6/26.
 */

public class CrashDeal implements CrashResult{

    @Override
    public void handlerException(Thread thread, Throwable throwable) {
        String errorMsg = CrashManager.getStackMsg(throwable);
        Toast.makeText(BaseApplication.getApplication(), errorMsg, Toast.LENGTH_LONG).show();
    }
}
