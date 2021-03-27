# BHMAndroid

[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[![Download](https://api.bintray.com/packages/bikie/bhm-sdk/BHMLibrary/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/BHMLibrary/_latestVersion)

BHMAndroid工程：一些常用的工具类，以及常用的控件，主要用来提高开发效率。
=====
---------

#### v1.2.9及以上为androidx版本，如果不用androidx就使用v1.2.8，但是后续有更新将只支持androidx的版本

集成：
-------
        compile 'com.bhm.sdk.bhmlibrary:BHMLibrary:1.5.0'

        
#### 目前库包含了一些项目中常用的模块，后续会慢慢的添加完善。


### [一、TitleBar的使用](https://github.com/buhuiming/BHMAndroid/blob/master/readme/TitleBar%E7%9A%84%E4%BD%BF%E7%94%A8.md)

### [二、Fragment懒加载](https://github.com/buhuiming/BHMAndroid/blob/master/readme/Fragment%E6%87%92%E5%8A%A0%E8%BD%BD.md)
 
### [三、敏感权限申请RxPermission](https://github.com/buhuiming/BHMAndroid/blob/master/readme/%E6%95%8F%E6%84%9F%E6%9D%83%E9%99%90%E7%94%B3%E8%AF%B7.md)

### [四、读取图片（拍照/相册）](https://github.com/buhuiming/BHMAndroid/blob/master/readme/%E8%AF%BB%E5%8F%96%E5%9B%BE%E7%89%87%EF%BC%88%E6%8B%8D%E7%85%A7%26%E7%9B%B8%E5%86%8C%EF%BC%89.md)

### [五、webView的封装](https://github.com/buhuiming/BHMAndroid/blob/master/readme/webView%E7%9A%84%E5%B0%81%E8%A3%85.md)

### [六、APP抛异常处理](https://github.com/buhuiming/BHMAndroid/blob/master/readme/APP%E6%8A%9B%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86.md)

### [七、常用的一些控件](https://github.com/buhuiming/BHMAndroid/blob/master/readme/%E5%B8%B8%E7%94%A8%E7%9A%84%E6%8E%A7%E4%BB%B6.md)

### [八、拒绝重写Activity或Fragment的onActivityResult方法](https://github.com/buhuiming/BHMAndroid/blob/master/readme/%E6%8B%92%E7%BB%9D%E9%87%8D%E5%86%99onActivityResult.md)

<br>
<br>

### 更新日志：

   * 1.1.6——1.1.7</br>
        >TitleBar添加新的方法。
   * 1.1.7——1.1.8</br>
        >webView的封装。
   * 1.1.8——1.1.9</br>
        >webView的回调函数更新。
   * 1.1.9——1.2.0</br>
        >代码优化。
   * 1.2.0——1.2.1</br>
        >添加APP集成异常处理模块。
   * 1.2.1——1.2.2</br>
        >添加一些控件。
   * 1.2.2——1.2.4</br>
        >添加一些工具类，如FileUtils,DateUtils。
   * 1.2.4——1.2.5</br>
        >优化<读取图片（拍照/相册)>的用法，拒绝重写onActivityResult方法。
   * 1.2.5——1.2.6</br>
        >增加AutoScaleTextView，固定宽度的TextView,根据显示字符长度缩小字体大小。
   * 1.2.6——1.2.7</br>
        >增加TextImageView，实现类似【请选择>】的效果; 修改<读取图片（拍照/相册)>的bug。
   * 1.2.7——1.2.8</br>
        >公开support包
   * 1.2.8——1.2.9</br>
        >迁移Androidx
   * 1.2.9——1.3.0</br>
        >解决5.0、5.1系统webview闪退
   * 1.3.0——1.3.3</br>
        >优化一些场景，添加androidx的懒加载LazyLoadFragmentAndroidx
   * 1.3.3——1.3.4</br>
        >StatusLayoutManager和ActivityResult迁移合并在一起，修复android11相册剪裁保存失败问题
   * 1.3.4——1.5.0</br>
        >TitleBar、TextImageView添加setTypeface方法，修复若干Bug

### 

        MIT License

        Copyright (c) 2018 javaKepp

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.
