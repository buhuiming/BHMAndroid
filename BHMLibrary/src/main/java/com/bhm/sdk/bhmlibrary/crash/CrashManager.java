package com.bhm.sdk.bhmlibrary.crash;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.bhm.sdk.bhmlibrary.interfaces.CrashResult;

/** 捕获APP崩溃日志管理类
 * Created by bhm on 2018/4/3.
 */

public class CrashManager {

    private static CrashManager crashManager;
    private CrashResult crashResult;

    public static CrashManager getCrashManager(){
        if(null == crashManager){
            crashManager = new CrashManager();
        }
        return crashManager;
    }

    public void init(CrashResult crashResult) {
        this.crashResult = crashResult;
        initCrash();
    }
    public void unInit() {
        Cockroach.uninstall();
    }
    private void  initCrash(){
        Cockroach.install(new Cockroach.ExceptionHandler() {
            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，
            //以防handlerException内部再次抛出异常，导致循环调用handlerException
            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(null != crashResult){
                            try {
                                crashResult.handlerException(thread, throwable);
                            }catch (Exception e){
                                Log.e("CrashManager--> ", "An exception is thrown when an exception is handled, " + getStackMsg(e));
                            }
                        }
                    }
                });
            }
        });
    }

    public static String getStackMsg(Throwable e) {

        StringBuffer sb = new StringBuffer();
        sb.append(e.getMessage() + "\n");
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }
}