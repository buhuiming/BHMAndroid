[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[![Download](https://api.bintray.com/packages/bikie/bhm-sdk/BHMLibrary/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/BHMLibrary/_latestVersion)
----
BHMAndroid工程：一些常用的工具类，以及常用的控件，主要用来提高开发效率。
===== 

## 集成步骤

### 1、创建类CrashDeal，实现接口ExceptionHandler，重写handlerException方法

    public class CrashDeal implements ExceptionHandler {

        @Override
        public void handlerException(Thread thread, Throwable throwable) {
            //集成此机制，防止APP因为抛异常闪退或者宕机。
            //所有未有try catch处理的异常，统一在此处理。可以给出提示，重启APP。
            //或者上传异常日志到服务器
            String errorMsg = CrashManager.getStackMsg(throwable);
            Toast.makeText(BaseApplication.getApplication(), errorMsg, Toast.LENGTH_LONG).show();
        }
    }

### 2、在Application的onCreate中添加

    //APP异常信息处理
    CrashManager.getCrashManager().init(new CrashDeal());
