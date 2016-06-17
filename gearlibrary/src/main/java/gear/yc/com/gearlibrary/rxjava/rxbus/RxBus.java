package gear.yc.com.gearlibrary.rxjava.rxbus;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gear.yc.com.gearlibrary.rxjava.rxbus.annotation.Subscribe;
import gear.yc.com.gearlibrary.rxjava.rxbus.pojo.Msg;
import gear.yc.com.gearlibrary.rxjava.rxbus.thread.EventThread;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * V1.0
 * 使用RxBus发布网络数据，订阅者通过注册的方式订阅数据
 * Created by Android on 2016/6/6.
 */
public class RxBus {
    private static RxBus instance;

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
    public final int TAG_DEFAULT = 0;
    public final int TAG_FAILER = -1;
    //发布者
    private final Subject bus;

    //存放订阅者信息
    private Map<Object, List<Subscription>> subscriptions = new HashMap<>();

    /**
     * PublishSubject 创建一个可以在订阅之后把数据传输给订阅者Subject
     * SerializedSubject 序列化Subject为线程安全的Subject
     */
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public void post(Object obj) {
        post(TAG_DEFAULT, obj);
    }

    /**
     * 发布事件
     * @param code
     * @param obj
     */
    public void post(int code, Object obj) {
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
                .filter(new Func1<Msg, Boolean>() {
                    @Override
                    public Boolean call(Msg o) {
                        //过滤code和eventType都相同的事件
                        return o.code == code;//&& eventType.isInstance(o.object)
                    }
                })
                .map(new Func1<Msg, Object>() {
                    @Override
                    public Object call(Msg o) {
                        return o.object;
                    }
                })
                .cast(eventType);
    }

    /**
     * 订阅者注册
     * @param subscriber
     */
    public void register(Object subscriber) {
        Observable.just(subscriber)
                .filter(s -> s != null)//判断订阅者不为空
                .map(s -> s.getClass())
                .flatMap(s -> Observable.from(s.getDeclaredMethods()))//获取订阅者方法并且用Observable装载
                .filter(m -> m.isAnnotationPresent(Subscribe.class))//方法必须被Subscribe注解
                .doOnNext(m -> m.setAccessible(true))//使非public方法可以被invoke
                .subscribe(m -> {
                    addSubscription(m,subscriber);
                });
    }

    /**
     * 添加订阅
     * @param m 方法
     * @param subscriber 订阅者
     */
    private void addSubscription(Method m,Object subscriber){
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
        Subscription subscription = tObservable(sub.tag(), cla)
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
        putSubscriptionsData(subscriber,subscription);
    }

    /**
     * 添加订阅者到map空间来unRegister
     * @param subscriber 订阅者
     * @param subscription 订阅者 Subscription
     */
    private void putSubscriptionsData(Object subscriber,Subscription subscription){
        List<Subscription> subs = subscriptions.get(subscriber);
        if (subs == null) {
            subs = new ArrayList<>();
        }
        subs.add(subscription);
        subscriptions.put(subscriber, subs);
    }

    /**
     * 解除订阅者
     * 可以使用Rx的方式解除，但是因为对 from 不明白所以暂时使用传统方式
     * @param subscriber 订阅者
     */
    public void unRegister(Object subscriber) {
//        Observable.just(subscriber)
//                .filter(s -> s!=null)
//                .map(s -> subscriptions.get(s))
//                .filter(subs -> subs!=null)
//                .flatMap(subs -> Observable.from(subs))
//                .filter(sub -> sub!=null)
//                .map(sub -> {
//                    sub.unsubscribe();
//                    return sub;})
//                .subscribe(s -> subscriptions.remove(subscriber));
        if (subscriber == null) {
            return;
        }
        List<Subscription> subs = subscriptions.get(subscriber);
        if (subs != null) {
            for (Subscription sub : subs) {
                if (sub != null)
                    sub.unsubscribe();
            }
            subscriptions.remove(subscriber);
        }
    }

}
