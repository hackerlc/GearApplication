### GearApplication 
### library
集成OkHttp3(OkHttp) + Retrofit2 + Rxjava(RxAndroid)

Retrofit2，Fresco 网络访问由OkHttp3负责

集成最简单的Rxjava 生命周期管理，并不成熟后期可能会替换掉，谨慎是使用

####Rxjava生命周期管理使用方式
继承GearActivity

如果已经继承其他activity也可以在activity中加入如下代码
```
    public static CompositeSubscription mCSub= new CompositeSubscription();

    @Override
    protected void onDestroy() {
        super.onDestroy();
       mCSub.unsubscribe();
    }
    
    public void unSubscribe(){
        if(mCSub!=null){
            mCSub.unsubscribe();
            mCSub=new CompositeSubscription();
        }
    }
```

使用是先调用1出方法，会首先删除订阅

第二步add订阅后的Sub

```
unSubscribe();//1
        mCSub.add(//2
                APIServiceManager.getInstance()
                        .getTravelNotesAPI()
                        .getTravelNotesList(query, page + "")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            RxBus.getInstance().post(s.getData().getBookses());
                        })
        );

```
引用
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
