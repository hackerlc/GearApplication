## GearLibrary
###主要功能
**RxBus**

事件消息

**RxJava Help**

RxJava帮助

**GearHttpServiceManager**

retrofit 管理类

**OkHttpManager**

OKHttp帮助管理类

**SocketTCPService**

Socket帮助类

**ProgressDialogUtil**

ProgressDialog 进度条

**BaseWeb**

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
