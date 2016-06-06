### GearApplication 
### library
集成OkHttp3(OkHttp) + Retrofit2 + Rxjava(RxAndroid)
Fresco 网络访问由OkHttp负责
Retrofit2 网络访问由OkHttp3负责
Rx 暂时没有集成生命周期管理(集成后暂时无法upload JCenter)
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
compile 'com.joker.gear:com-joker-gear:1.3.6'
```

### new version
#####V 1.3.6
```
去掉了Fresco的引用，增加了RxBus
```
#####v 1.2.1
```
改变OKHttp创建方式，改为简单创建者方式构建

```
#####v 1.1.1
```
修改Gearlibrary 支持最低sdk 14
```
