package gear.yc.com.gearlibrary.rxjava.rxbus;


import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import gear.yc.com.gearlibrary.rxjava.rxbus.annotation.Subscribe;
import gear.yc.com.gearlibrary.rxjava.rxbus.annotation.UseRxBus;
import gear.yc.com.gearlibrary.rxjava.rxbus.event.EventThread;
import gear.yc.com.gearlibrary.rxjava.rxbus.pojo.Msg;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @version 2.1
 * 修改取消绑定时使用rx方式处理会出现不能正确解绑的bug
 * @version 2.0
 * 增加默认状态以及状态值
 * 使用注解方式注册，检测activity的onCreate是否有UseRxBus注解
 * 增加tag4Class管理跳转序列
 * @version 1.4
 * bus重新改为线程安全对象
 * @version 1.3
 * RxJava修改为RxJava2方式
 * @version 1.2
 * setAccessible(true) 提前一步
 * @version 1.1
 * List形式管理Sub改为 CompositeSubscription 管理
 * 取消注册改为rx写法
 * @version 1.0
 * 使用RxBus发布网络数据，订阅者通过注册的方式订阅数据
 *
 * Q.如果订阅者不同但同时存在于序列并且都被订阅，那么code相同的情况下是否会出现都收到通知的情况
 * A.测试了一下，在订阅者不同但code相同的相框下，并没有出现订阅者都接到通知的情况
 * Created by Android on 2016/6/6.
 */
public class RxBus {
    protected static RxBus instance;

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }
    //TAG默认值
    public static final int TAG_DEFAULT = -1000;
    public static final int TAG_UPDATE = -1010;
    public static final int TAG_CHANGE = -1020;
    public static final int TAG_OTHER = -1030;
    public static final int TAG_ERROR = -1090;
    //TAG-class
    protected static Map<Class,Integer> tag4Class;
    //发布者
    protected final Subject bus;

    //存放订阅者信息
    protected Map<Object, CompositeDisposable> subscriptions = new HashMap<>();

    /**
     * PublishSubject 创建一个可以在订阅之后把数据传输给订阅者Subject
     */
    public RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public void post(@NonNull Object obj) {
        post(TAG_DEFAULT, obj);
    }

    /**
     * 发布事件
     * @param code 值使用RxBus.getInstance().getTag(class,value)获取
     * @param obj 为需要被处理的事件
     */
    public void post(@NonNull int code,@NonNull Object obj) {
        bus.onNext(new Msg(code, obj));
    }

    public Observable<Object> tObservable() {
        return tObservable(Object.class);
    }

    public <T> Observable<T> tObservable(Class<T> eventType) {
        return tObservable(TAG_DEFAULT, eventType);
    }

    /**
     * 订阅事件
     * @return
     */
    public <T> Observable tObservable(int code, final Class<T> eventType) {
        return bus.ofType(Msg.class)//判断接收事件类型
                .filter(new Predicate<Msg>() {
                    @Override
                    public boolean test(Msg msg) throws Exception {
                        return msg.code==code;
                    }
                })
                .map(new Function<Msg, Object>() {
                    @Override
                    public Object apply(Msg msg) throws Exception {
                        return msg.object;
                    }
                })
                .cast(eventType);
    }

    /**
     * 判断是否需要订阅，如果需要订阅那么自动控制生命周期
     */
    public void init(@NonNull Object object){
        Flowable.just(object)
                .map(o -> o.getClass().getAnnotation(UseRxBus.class))
                .filter(a -> a!=null)
                .subscribe(u -> {
                    addTag4Class(object.getClass());
                    register(object);
                },e -> e.getMessage());
    }

    /**
     * 订阅者注册
     * @param subscriber
     */
    public void register(@NonNull Object subscriber) {
            Flowable.just(subscriber)
                    .filter(s -> subscriptions.get(subscriber)==null) //判断订阅者没有在序列中
                    .flatMap(s -> Flowable.fromArray(s.getClass().getDeclaredMethods()))
                    .map(m -> {m.setAccessible(true);return m;})
                    .filter(m -> m.isAnnotationPresent(Subscribe.class))
                    .subscribe(m -> addSubscription(m,subscriber));
    }

    /**
     * 添加订阅
     * @param m 方法
     * @param subscriber 订阅者
     */
    protected void addSubscription(Method m,Object subscriber){
        //获取方法内参数
        Class[] parameterType = m.getParameterTypes();
        //只获取第一个方法参数，否则默认为Object
        Class cla = Object.class;
        if (parameterType.length > 1) {
            cla = parameterType[0];
        }
        //获取注解
        Subscribe sub = m.getAnnotation(Subscribe.class);
        //订阅事件
        Disposable disposable = tObservable(getTag(subscriber.getClass(),sub.tag()), cla)
                .observeOn(EventThread.getScheduler(sub.thread()))
                .subscribe(o -> {
                            try {
                                m.invoke(subscriber, o);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        },
                        e -> System.out.println("this object is not invoke"));
        putSubscriptionsData(subscriber,disposable);
    }

    /**
     * 添加订阅者到map空间来unRegister
     * @param subscriber 订阅者
     * @param disposable 订阅者 Subscription
     */
    protected void putSubscriptionsData(Object subscriber,Disposable disposable){
        CompositeDisposable subs = subscriptions.get(subscriber);
        if (subs == null) {
            subs = new CompositeDisposable();
        }
        subs.add(disposable);
        subscriptions.put(subscriber, subs);
    }

    /**
     * 添加序列
     * 根据object 生成唯一id
     */
    protected Integer tag=-1000;
    protected void addTag4Class(Class cla){
        if(tag4Class==null){
            tag4Class=new HashMap<>();
        }
        tag4Class.put(cla,tag);
        tag--;
    }

    /**
     * tag值使用RxBus.getInstance().getTag(class,value)获取
     * 使用getTag主要用于后期维护方便，可以及时找到发布事件的对应处理。
     * @param cla 为Rxbus事件处理的类
     * @param value 是事件处理的tag
     * @return tag
     */
    public int getTag(Class cla,int value){
        if(tag4Class==null){
            return value;
        }
        return tag4Class.get(cla).intValue()+value;
    }

    /**
     * 解除订阅者
     * @param subscriber 订阅者
     */
    public void unRegister(Object subscriber) {
        CompositeDisposable compositeDisposable;
        if(subscriber != null){
            compositeDisposable = subscriptions.get(subscriber);
            if(compositeDisposable != null){
                compositeDisposable.dispose();
                subscriptions.remove(subscriber);
            }
        }
    }
}
