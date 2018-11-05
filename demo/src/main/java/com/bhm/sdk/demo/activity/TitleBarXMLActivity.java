package com.bhm.sdk.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.bhm.sdk.bhmlibrary.interfaces.WebViewCallBack;
import com.bhm.sdk.bhmlibrary.views.BaseWebView;
import com.bhm.sdk.bhmlibrary.views.TitleBar;
import com.bhm.sdk.demo.R;

/**
 * Created by bhm on 2018/5/7.
 */

public class TitleBarXMLActivity extends AppCompatActivity{

    private TitleBar titleBar;
    private BaseWebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_title_bar);
        initView();
        initListener();
    }

    private void initView(){
        titleBar = (TitleBar) findViewById(R.id.titleBar);
    }

    private void initListener(){
        titleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data", "resultCode is ");
                setResult(1111, intent);
                finish();
            }
        });
        titleBar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleBarXMLActivity.this, titleBar.getRightTextView().
                        getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        titleBar.setTitleBarOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TitleBarXMLActivity.this, "点击标题栏", Toast.LENGTH_SHORT).show();
//            }
//        });

        titleBar.setTitleBarOnTwoClickListener(new TitleBar.OnTwoClickListener() {
            @Override
            public void onTwoClick(View view) {
                Toast.makeText(TitleBarXMLActivity.this, "双击标题栏...", Toast.LENGTH_SHORT).show();
            }
        });


        webView = (BaseWebView) findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setErrorPagePath("file:///android_asset/t_error.html");
        webView.setProgressBarDrawable(R.drawable.progressbar);
        webView.init(this, "https://1.scrm.china-syqm.com/wxPay/wechat.html?", true, new WebViewCallBack() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
            //可以重写其他方法：onPageStarted、onPageFinished、onLoadResource、onReceivedError、onReceivedTitle
        });
    }
}
