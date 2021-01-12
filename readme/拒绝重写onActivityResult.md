[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[![Download](https://api.bintray.com/packages/bikie/bhm-sdk/ActivityResult/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/ActivityResult/_latestVersion)
----

## 参考RxPermissions的做法，RxPermissions内部持有一个Fragment，这个fragment没有视图，只负责请求权限和返回结果，相当于一个桥梁的作用，通过rxPermissions发起request的时候，其实并不是activity去request，而是通过这个fragment去请求，然后在fragment的onRequestPermissionsResult中把结果发送出来，如此来避开activity的onRequestPermissionsResult方法。

#### v1.0.4为androidx版本，如果不用androidx就使用v1.0.3，但是后续有更新将只支持androidx的版本

### 引用
   已合并在BHM

### 用法

                Intent intent = new Intent();
                intent.setClass(this, Target.class);
                new ActivityResult(this).startForResult(intent, new ActivityResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        Toast.makeText(MainActivity.this, data == null? "null data" + resultCode :
                                data.getStringExtra("data") + resultCode, Toast.LENGTH_SHORT).show();
                    }
                });
                
                或者：
                new ActivityResult(this).startForResult(intent)
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if(null == resultData){
                                    return;
                                }
                                int resultCode = resultData.getResultCode();
                                Intent data = resultData.getData();
                                Toast.makeText(MainActivity.this, data == null? "null data" + resultCode :
                                        data.getStringExtra("data") + resultCode, Toast.LENGTH_SHORT).show();
                            }
                        });
                
                
                
        Activity关闭的时候
                Intent intent = new Intent();
                intent.putExtra("data", "resultCode is ");
                setResult(1111, intent);
                finish();


