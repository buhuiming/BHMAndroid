[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[![Download](https://api.bintray.com/packages/bikie/bhm-sdk/BHMLibrary/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/BHMLibrary/_latestVersion)
----
BHMAndroid工程：一些常用的工具类，以及常用的控件，主要用来提高开发效率。
===== 

## 一、ShadowView的使用，拒绝重复写shape文件，就可以让控件改变颜色和圆角。

### 用法

        int[] mColor = new int[]{Color.parseColor("#FF6176EC"), Color.parseColor("#FF46309F"), Color.parseColor("#FF593FB5")};
        ShadowView.newBuilder()
                .setTargetView(btn_text)
                .setColor(mColor[0])//View颜色
                .setShadowColor(Color.parseColor("#FFA6D9A8"))//阴影颜色
                .setGradientColorArray(mColor)//如果View是渐变色，则设置color数组
                .setRadius(DisplayUtil.dp2px(this, 25))//圆角
        //                .setShadowRadius(DisplayUtil.dp2px(this, 4))//阴影圆角
        //                .setOffsetX(DisplayUtil.dp2px(this, 2))//阴影横向偏移
        //                .setOffsetY(DisplayUtil.dp2px(this, 2))//阴影纵向偏移
                .build();


