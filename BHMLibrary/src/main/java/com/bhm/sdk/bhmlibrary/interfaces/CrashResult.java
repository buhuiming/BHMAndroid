package com.bhm.sdk.bhmlibrary.interfaces;

/**
 * Created by bhm on 2018/6/26.
 */

public interface CrashResult {

    void handlerException(final Thread thread, final Throwable throwable);
}
