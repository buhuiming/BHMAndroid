[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[![Download](https://api.bintray.com/packages/bikie/bhm-sdk/BHMLibrary/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/BHMLibrary/_latestVersion)
----
RxLibrary工程：一些常用的工具类，以及常用的控件，主要用来提高开发效率。
=====

一、webView的封装
-------  
>![image](https://github.com/buhuiming/BHMAndroid/blob/master/screenShots/3.png)

>![image](https://github.com/buhuiming/BHMAndroid/blob/master/screenShots/4.png) 


## 使用步骤
>

### 1.xml布局中添加

    <com.bhm.sdk.bhmlibrary.views.BaseWebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

### 2.设置

    webView = (BaseWebView) findViewById(R.id.webView);
        //webView.getSettings().setJavaScriptEnabled(true);
       //webView.setErrorPagePath("file:///android_asset/t_error.html");
        webView.setProgressBarDrawable(R.drawable.progressbar);
        webView.init(this, "https://www.baidu.com", true, new WebViewCallBack() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            //可以重写其他方法：onPageStarted、onPageFinished、onLoadResource、onReceivedError、onReceivedTitle
        });
        
### 3.方法介绍

* （1）通过webView.getSettings()获取setting，对webView的属性重新进行设置。
* （2）添加进度条webView.setProgressBarDrawable(R.drawable.progressbar);默认为蓝色的。若需要隐藏，则init方法第三个参数传递false即可。
* （3）webView.setErrorPagePath加载失败显示的页面，默认请参考图片显示，可自定义。
* （4）解决了跨域请求问题，Https SSL认证不通过问题，部分机型显示空白页问题，重定向问题等。
