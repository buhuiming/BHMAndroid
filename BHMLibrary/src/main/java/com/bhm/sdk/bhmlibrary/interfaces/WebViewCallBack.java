package com.bhm.sdk.bhmlibrary.interfaces;

import android.graphics.Bitmap;
import android.webkit.WebView;

/**
 * Created by bhm on 2018/3/28.
 */

public class WebViewCallBack extends WebViewCallBackImp{

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(WebView view, String url) {

    }

    @Override
    public void onLoadResource(WebView view, String url) {

    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onReceivedTitle(WebView view, String title) {

    }
}
