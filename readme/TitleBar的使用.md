[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[![Download](https://api.bintray.com/packages/bikie/bhm-sdk/BHMLibrary/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/BHMLibrary/_latestVersion)
----
BHMAndroid工程：一些常用的工具类，以及常用的控件，主要用来提高开发效率。
=====

一、TitleBar的使用
-------  
>![image](https://github.com/buhuiming/BHMAndroid/blob/master/screenShots/1.png) ![image](https://github.com/buhuiming/BHMAndroid/blob/master/screenShots/2.png)    

### 1.XML集成方式：
>在layout文件中添加

    <com.bhm.sdk.bhmlibrary.views.TitleBar
        android:id="@+id/titleBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:titleBarHeight="50dp" 
        app:titleTextColor="@color/white"
        app:rightTextColor="@color/white"
        app:leftTextColor="@color/white"
        app:titleText="标题"
        app:rightText="完成"
        app:leftText="返回"
        app:leftViewBackgroundResource="@drawable/arrow_a"
        app:isLeftViewShow="true"
        app:isRightViewShow="true"
        app:dividerHeight="1dp"
        app:dividerColor="@color/black"
        app:backGroundColor="@color/colorPrimary"/>
        
 ### 2.继承TitleBarActivity集成方式：
>继承TitleBarActivity类，
>重写setContentView和initTitleBar方法

    @Override
    public void initTitleBar(final TitleBar titleBar) {
        titleBar.setTitleBarHeight(150f, false);
        ////titleBar.setTitleBarHeight(50f, true);
        titleBar.setTitleTextColor(R.color.black);
        titleBar.setRightTextColor(R.color.black);
        titleBar.setLeftTextColor(R.color.black);
        titleBar.setTitleText("集成Base");
        titleBar.setRightText("提交");
        titleBar.setLeftText("后退");
        titleBar.setLeftViewBackgroundResource(R.drawable.img_close_source);
        titleBar.setIsLeftViewShow(true);
        titleBar.setIsRightViewShow(true);
        titleBar.setDividerHeight(1f, false);
        titleBar.setDividerColor(R.color.black);
        titleBar.setBackGroundColor(R.color.white);
    }

    @Override
    public View setContentView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_extends_title_bar, rootView, false);
    }
    
### 3.代码生成titleBar的方式：

        private TitleBar titleBar;
        private View contentView;
        @BindView(R.id.tv_text)
        protected TextView tv_text;

        contentView = LayoutInflater.from(this).inflate(R.layout.activity_java_title_bar, null, false);
        ButterKnife.bind(this, contentView);
        titleBar = TitleBarBuilder.newBuilder(this)
                .createTitleBar()
                .setContentView(contentView)
                .build()
                .setTitleBarHeight(50f, true)
                .setTitleTextColor(R.color.white)
                .setRightTextColor(R.color.white)
                .setLeftTextColor(R.color.white)
                .setTitleText("代码生成")
                .setRightText("提交")
                .setLeftText("后退")
                .setLeftViewBackgroundResource(R.drawable.arrow_a)
                .setIsLeftViewShow(true)
                .setIsRightViewShow(true)
                .setDividerHeight(1f, false)
                .setDividerColor(R.color.black)
                .setBackGroundColor(R.color.colorPrimary);
        tv_text.setText("ssssssssssssssss");
    
### 4.xml方法介绍：
>app:titleBarHeight设置标题栏的高度，默认48dp</br>
>app:dividerHeight设置分割线的高度</br>
>app:backGroundColor设置标题栏的背景颜色，默认白色</br>
>app:titleTextColort设置标题的颜色，默认黑色</br>
>app:titleText设置标题文本</br>
>app:rightTextColor设置右边文字颜色，默认黑色</br>
>app:rightText设置右边文本</br>
>app:leftTextColor设置左边文字颜色，默认黑色</br>
>app:dividerColor设置分割线的颜色，默认颜色#e9e9eb</br>
>app:leftText设置左边文本</br>
>app:isLeftViewShow设置是否显示左边的文本和图标</br>
>app:isRightViewShow设置是否显示右边的文本和图标</br>
>app:leftViewBackgroundResource设置左边图标的资源</br>
>app:rightViewBackgroundResource设置右边图标的资源</br>

### 5.java方法介绍：

>setTitleBarHeight(float height, boolean isDpValue)设置标题栏的高度</br>
>setDividerHeight(float height, boolean isDpValue)设置分割线的高度</br>
>setDividerColor(int color)设置分割线的颜色</br>
>setBackGroundColor(int color)设置标题栏的背景颜色</br>
>setTitleText(String title)设置标题文本</br>
>setTitleTextColor(int color)设置文本颜色</br>
>setRightText(String title)设置右边文本</br>
>setRightTextColor(int color)设置右边文字颜色</br>
>setLeftText(String title)设置左边文本</br>
>setLeftTextColor(int color)设置左边文本颜色</br>
>setLeftOnClickListener(OnClickListener listener)设置左边控件点击事件</br>
>setRightOnClickListener(OnClickListener listener)设置右边控件点击事件</br>
>setTitleTextOnClickListener(OnClickListener listener)设置标题点击事件</br>
>setTitleBarOnClickListener(OnClickListener listener)设置标题栏点击事件</br>
>setTitleBarOnTwoClickListener(final OnTwoClickListener listener)设置标题栏双击事件</br>
>setIsLeftViewShow(boolean isLeftViewShow)设置左边控件是否显示</br>
>setIsRightViewShow(boolean isRightViewShow)设置右边控件是否显示</br>
>setLeftViewBackgroundResource(int res)设置左边图标的资源</br>
>setRightViewBackgroundResource(int res)设置右边图标的资源</br>
<br>


此外还有一些获取控件的方法。详细用法请参考demo。


### 更新日志

* 1.1.6——1.1.7 添加了7个方法

        app:leftTextSize="14sp"//左边字体大小
        app:titleTextSize="17sp"//标题字体大小
        app:rightTextSize="14sp"//右边边字体大小
        app:leftViewMarginLeft ="20dp"//左边控件到左边的距离
        app:rightViewMarginRight ="20dp"//右边控件到右边的距离
        app:leftTextViewPaddingLeft ="20dp"//左边文字跟左边图标的距离
        app:rightTextViewPaddingRight ="20dp"//右边文字跟右边图标的距离

    对应的方法

        titleBar.setTitleTextSize(18);
        titleBar.setLeftTextSize(16);
        titleBar.setRightTextSize(16);
        titleBar.setLeftTextViewPaddingLeft(18, true);
        titleBar.setRightTextViewPaddingRight(18, true);
        titleBar.setLeftViewMarginLeft(20, true);
        titleBar.setRightViewMarginRight(20, true);
      
