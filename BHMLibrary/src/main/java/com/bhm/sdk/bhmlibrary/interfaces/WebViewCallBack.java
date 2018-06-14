package com.bhm.sdk.bhmlibrary.interfaces;

import android.webkit.WebView;

/**
 * Created by bhm on 2018/3/28.
 */

public interface WebViewCallBack {

    boolean shouldOverrideUrlLoading(WebView view, String url);//一般返回true,自己处理url跳转

    void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon);

    void onPageFinished(WebView view, String url);

    void onLoadResource(WebView view, String url);

    void onReceivedError(WebView view, int errorCode, String description, String failingUrl);

    void onReceivedTitle(WebView view, String title);
}
