### GearApplication 
使用OkHttp3(OkHttp) + Retrofit2 + Rxjava(RxAndroid) +Rxlifecycle +Rxbus构建的DEMO项目
暂时没有使用 MVP 或者 MVVM 的方式构建项目
可直接导入项目并运行
### library
集成OkHttp3(OkHttp) + Retrofit2 + Rxjava(RxAndroid) +Rxlifecycle 
事件总线由rxbus负责
Retrofit2，Fresco 网络访问由OkHttp3负责
#### GearApplication 引用
````
    compile 'com.android.support:design:23.1.1'
    compile 'com.android.support:cardview-v7:23.4.0'
    compile 'com.facebook.fresco:fresco:0.10.0'
    compile 'com.facebook.fresco:imagepipeline-okhttp3:0.10.0'
    compile 'com.trello:rxlifecycle:0.6.1'
    compile 'com.trello:rxlifecycle-components:0.6.1'
    compile 'com.jcodecraeer:xrecyclerview:1.2.7'
    compile project(':gearlibrary')
````
#### gearlibrary 引用
```
    compile 'com.squareup.okio:okio:1.6.0'
    compile 'com.google.code.gson:gson:2.6.2'
    compile 'io.reactivex:rxjava:1.1.2'
    compile 'io.reactivex:rxandroid:1.1.0'
    compile 'com.android.support:appcompat-v7:23.1.1'
    compile 'com.squareup.okhttp3:okhttp:3.2.0'
    compile 'com.squareup.retrofit2:retrofit:2.0.1'
    compile 'com.squareup.retrofit2:converter-gson:2.0.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.2.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.1'
    compile 'com.android.support:recyclerview-v7:23.1.1'
```
### Use
```
compile 'com.joker.gear:com-joker-gear:1.4.1'
```
### new version
##### 1.4.1
```
RxBus优化
```
##### 1.4.0
```
以 Rxlifecycle 来管理Rx生命周期
增加RxBus来发射数据
```
##### 1.3.6
```
去掉了Fresco的引用，增加了RxBus
```
##### 1.2.1
```
改变OKHttp创建方式，改为简单创建者方式构建

```
##### 1.1.1
```
修改Gearlibrary 支持最低sdk 14
```
