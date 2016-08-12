### GearApplication 
使用OkHttp3(OkHttp) + Retrofit2 + Rxjava(RxAndroid) +Rxlifecycle +Rxbus构建的DEMO项目

暂时没有使用 MVP 或者 MVVM 的方式构建项目

可直接导入项目并运行
### library
集成OkHttp3(OkHttp) + Retrofit2 + Rxjava(RxAndroid) +Rxlifecycle 
事件总线由rxbus负责
Retrofit2，Fresco 网络访问由OkHttp3负责

简书：http://www.jianshu.com/users/02266b406caa/latest_articles
#### GearApplication 引用
````
    compile project(':gearlibrary')

    compile 'com.android.support:design:23.1.1'
    compile 'com.android.support:cardview-v7:23.4.0'

    compile 'com.facebook.fresco:fresco:0.10.0'
    compile 'com.facebook.fresco:imagepipeline-okhttp3:0.10.0'

    compile 'com.trello:rxlifecycle:0.6.1'
    compile 'com.trello:rxlifecycle-components:0.6.1'

    compile 'com.jcodecraeer:xrecyclerview:1.2.7'

    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.android.support:support-v4:23.4.0'
    compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'

    compile 'com.google.android.gms:play-services-appindexing:8.4.0'

    apt 'com.google.guava:guava:19.0'
    compile 'com.google.dagger:dagger:2.4'
    apt 'com.google.dagger:dagger-compiler:2.4'
    provided 'javax.annotation:jsr250-api:1.0'

    testCompile 'junit:junit:4.12'
````
#### gearlibrary 引用
```
    compile 'com.squareup.okio:okio:1.9.0'

    compile 'com.google.code.gson:gson:2.7'

    compile 'io.reactivex:rxjava:1.1.6'
    compile 'io.reactivex:rxandroid:1.2.1'

    compile 'com.android.support:appcompat-v7:23.3.0'

    compile 'com.squareup.okhttp3:okhttp:3.4.1'

    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
    
    compile 'com.android.support:recyclerview-v7:23.1.1'
```
### Use
```
compile 'com.joker.gear:com-joker-gear:1.4.1'
```
### new version
##### 1.4.2
```
更新了引用library
```
