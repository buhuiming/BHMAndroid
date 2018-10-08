[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[![Download](https://api.bintray.com/packages/bikie/bhm-sdk/ActivityResult/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/ActivityResult/_latestVersion)
----

## 参考RxPermissions的做法，RxPermissions内部持有一个Fragment，这个fragment没有视图，只负责请求权限和返回结果，相当于一个桥梁的作用，通过rxPermissions发起request的时候，其实并不是activity去request，而是通过这个fragment去请求，然后在fragment的onRequestPermissionsResult中把结果发送出来，如此来避开activity的onRequestPermissionsResult方法。

### 引用
        compile 'com.bhm.sdk.bhmlibrary:ActivityResult:1.0.0'
        
        
        <dependency>
          <groupId>com.bhm.sdk.bhmlibrary</groupId>
          <artifactId>ActivityResult</artifactId>
          <version>1.0.0</version>
          <type>pom</type>
        </dependency>

### 用法

        new ActivityResult(this).startForResult(Target.class, new ActivityResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            
                        }else{
                            
                        }
                    }
                });


