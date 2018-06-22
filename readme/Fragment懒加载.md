[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[![Download](https://api.bintray.com/packages/bikie/bhm-sdk/BHMLibrary/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/BHMLibrary/_latestVersion)
----
BHMAndroid工程：一些常用的工具类，以及常用的控件，主要用来提高开发效率。
=====

一、LazyLoadFragment懒加载的使用
---------  
>![image](https://github.com/buhuiming/BHMAndroid/blob/master/screenShots/5.jpg) 


### 1.用法介绍
    用法特别简单，继承LazyLoadFragment类，重写4个方法，以下介绍一下这这个方法：
    
### setLayoutId()，返回布局id：</br>

        @Override
        protected int setLayoutId() {
            return R.layout.activity_java_title_bar;
        }

### initView(),初始化工作：</br>

        @Override
        protected void initView() {
            super.initView();
            ButterKnife.bind(this, rootView);
            tv_text.setText("fragment position is " + getArguments().get("data"));
            Log.i("DemoFragment", getArguments().get("data") + " 初始化完毕");
        }
        
### lazyLoad(),加载数据，一般是耗时的工作：</br>
       
       例如读取数据库，请求网络数据。

### setLoadDataOnce()</br>
       
       重写此方法，返回true,则lazyLoad()只会回调一次数据，切换fragment不再回调lazyLoad()；
       返回false，则每次fragment可见都会回调lazyLoad()，默认是true。
       注：如果adapter的destroyItem方法去掉super，则只会初始化（initView）一次，切换fragment不再初始化；
       否则，fragment每次可见都初始化



 
