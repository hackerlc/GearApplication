## GearLibrary
###主要功能
[**RxBus**](https://github.com/hackerlc/GearApplication/blob/master/gearlibrary/readme.md#rxbus)

事件消息

[**RxJava Help**](https://github.com/hackerlc/GearApplication/blob/master/gearlibrary/readme.md#rxjava-help)

RxJava帮助

[**GearHttpServiceManager**](https://github.com/hackerlc/GearApplication/blob/master/gearlibrary/readme.md#gearhttpservicemanager)

retrofit 管理类

[**OkHttpManager**](https://github.com/hackerlc/GearApplication/blob/master/gearlibrary/readme.md#gearhttpservicemanager)

OKHttp帮助管理类

[**ProgressDialogUtil**](https://github.com/hackerlc/GearApplication/blob/master/gearlibrary/readme.md#okhttpmanager)

进度条

[**BaseWeb**](https://github.com/hackerlc/GearApplication/blob/master/gearlibrary/readme.md#baseweb)

Web帮助类

###使用说明

####RxBus
**1.初始化Rxbus**

```
BaseActivity

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.getInstance().init(this);
    }
    
```
**2.@UseRxbus**

需要Rxbus接收事件处理的地方添加注解@UseRxbus
```
Activity

@UseRxBus
public class TravelNotesActivity extends BaseActivity

```
**3.@Subscribe**

接收事件的方法上添加@Subscribe注解并设置tag
RxBus中提供了默认的Tag值，当然也可以使用自定义的Tag，tag主要用于Rxbus识别对应的处理事件
```
    @Subscribe(tag = RxBus.TAG_UPDATE)
    private void dataBinding(ArrayList<TravelNoteBook.Books> bookies) {
    ...
    }
```
**4.发布事件**

使用RxBus.getInstance().post(tag,obj) 发布事件
obj为需要被处理的事件

tag值使用RxBus.getInstance().getTag(class,value)获取

class为Rxbus事件处理的类，value是事件处理的tag。使用getTag主要用于后期维护方便，可以及时找到发布事件的对应处理。
```
  RxBus.getInstance().post(RxBus.getInstance().getTag(Class.class,RxBus.TAG_UPDATE),ArrayList);
```

####RxJava Help
```
.compose(RxSchedulersHelper.io_main())

等同于

ob.subscribeOn(Schedulers.io())
  .observeOn(AndroidSchedulers.mainThread())
```

####GearHttpServiceManager
**1.创建**
创建一个retrofit管理类
```
GearHttpServiceManager.getInstance()
                .setBaseUrl(BASE_URL)
                .build(OkHttpClient);
```
**2.使用**
```
GearHttpServiceManager.getInstance().getRetrofit()
```

####OkHttpManager
**1.创建**
创建OkHttp 管理类

set都为可选
```
OkHttpManager.getInstance()
    .setHeader("key","value")
    .setTimeOut(int timeOut)
    .setLog(boolean isLog)
    .build()
    .getClient();
```

**2.使用**
```
OkHttpManager.getInstance().getClient();
```

####ProgressDialogUtil
**1.显示**
```
ProgressDialogUtil.getInstance()
.setTitle(String title)
.build(this)
.show();
```
**2.隐藏**
```
ProgressDialogUtil.getInstance().dismiss();
```
其他设置可以直接看ProgressDialogUtil源码

####BaseWeb
BaseWeb中加载了默认设置

1.开启JavaScript

2.缓存模式LOAD_CACHE_ELSE_NETWORK

3.默认加载格式utf-8

4.开启DOM storage API

5.启用数据库

6.启用页面缩放

7.开启页面缓存

8.开启网页图片加载

**1.创建**
```
BaseWeb baseWeb=new BaseWeb();
```
**2.设置**
```
BaseWeb.setSettings(WebView.getSettings());
```
**3.清除缓存**
```
BaseWeb.clearWebViewCache(Context context)
```
