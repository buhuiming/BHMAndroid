package com.bhm.sdk.bhmlibrary.interfaces;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by bhm on 2018/3/28.
 */

public abstract class WebViewCallBackImp {

    public abstract boolean shouldOverrideUrlLoading(WebView view, String url);//一般返回true,自己处理url跳转

    public abstract void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon);

    public abstract void onPageFinished(WebView view, String url);

    public abstract void onLoadResource(WebView view, String url);

    public abstract void onReceivedError(WebView view, int errorCode, String description, String failingUrl);

    public abstract void onReceivedTitle(WebView view, String title);

    public abstract void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture);

    public abstract boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams);

}
