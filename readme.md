### GearApplication 
使用OkHttp3(OkHttp) + Retrofit2 + Rxjava(RxAndroid) +Rxlifecycle2 +Rxbus构建的DEMO项目

主要模块使用MVP+MVVM的方式构建，按照功能分包

可直接导入项目并运行
### [gearlibrary 主项目基础架构程序](https://github.com/hackerlc/GearApplication/tree/master/gearlibrary)
集成OkHttp3(OkHttp) + Retrofit2 + Rxjava2(RxAndroid2) +Rxbus
事件总线由rxbus负责
Retrofit2，Fresco 网络访问由OkHttp3负责

简书：http://www.jianshu.com/users/02266b406caa/latest_articles
#### GearApplication 引用
````
    compile fileTree(include: ['*.jar'], dir: 'libs')
    compile project(':gearlibrary')
    compile 'com.android.support:support-v4:23.4.0'
    compile 'com.android.support:design:23.1.1'
    compile 'com.android.support:cardview-v7:23.4.0'
    
    compile 'com.jcodecraeer:xrecyclerview:1.2.7'
    
    compile 'com.github.bumptech.glide:glide:3.7.0'
  
    compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'
    
    compile 'com.trello.rxlifecycle2:rxlifecycle:2.0.1'
    compile 'com.trello.rxlifecycle2:rxlifecycle-android:2.0.1'
    compile 'com.trello.rxlifecycle2:rxlifecycle-components:2.0.1'
    
    compile 'com.google.android.gms:play-services-appindexing:8.4.0'
    annotationProcessor 'com.google.guava:guava:19.0'
    compile 'com.google.dagger:dagger:2.4'
    annotationProcessor 'com.google.dagger:dagger-compiler:2.4'
    provided 'javax.annotation:jsr250-api:1.0'
    
    annotationProcessor project(':gearapifinder-compiler')
    
    testCompile 'junit:junit:4.12'
````
#### gearlibrary 引用
```
    compile fileTree(include: ['*.jar'], dir: 'libs')
    compile 'com.android.support:appcompat-v7:23.3.0'
    compile 'com.android.support:recyclerview-v7:23.1.1'
    
    compile 'com.squareup.okhttp3:okhttp:3.4.1'
    compile 'com.squareup.okio:okio:1.9.0'
    
    compile 'com.google.code.gson:gson:2.7'
    
    compile 'io.reactivex.rxjava2:rxjava:2.0.2'
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'

    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0-RC3'
    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
    
    compile project(':gearapifinder-annotation')
```
###使用library框架
```
compile 'com.joker.gear:com-joker-gear:1.5.1'
```
