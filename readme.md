### GearApplication 
集成OkHttp3(OkHttp) + Retrofit2 + Fresco + Rxjava(RxAndroid)
Fresco 网络访问由OkHttp负责
Retrofit2 网络访问由OkHttp3负责
Rx 暂时没有集成生命周期管理(集成后暂时无法upload JCenter)
```
    compile 'com.squareup.okio:okio:1.6.0'
    compile 'com.google.code.gson:gson:2.6.2'
    compile 'io.reactivex:rxjava:1.1.2'
    compile 'io.reactivex:rxandroid:1.1.0'
    compile 'com.android.support:appcompat-v7:23.1.1'
    compile 'com.facebook.fresco:fresco:0.9.0'
    compile 'com.facebook.fresco:imagepipeline-okhttp:0.9.0'
    compile 'com.squareup.okhttp:okhttp:2.7.5'
    compile 'com.squareup.okhttp3:okhttp:3.2.0'
    compile 'com.squareup.retrofit2:retrofit:2.0.1'
    compile 'com.squareup.retrofit2:converter-gson:2.0.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.2.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.1'
    compile 'com.trello:rxlifecycle:0.5.0'
    compile 'com.trello:rxlifecycle-components:0.5.0'
```

### Use
```
compile 'com.joker.gear:com-joker-gear:1.1.0'
```

### new version
#####v 1.1.1
```
修改Gearlibrary 支持最低sdk 14
```
