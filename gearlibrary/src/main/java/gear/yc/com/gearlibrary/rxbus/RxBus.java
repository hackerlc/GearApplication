package gear.yc.com.gearlibrary.rxbus;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Android on 2016/6/6.
 */
public class RxBus {
    private static RxBus instance;

    public static RxBus getInstance(){
        if(instance==null){
            synchronized (RxBus.class){
                if(instance==null){
                    instance=new RxBus();
                }
            }
        }
        return instance;
    }

    private final Subject bus;

    public RxBus(){
        bus=new SerializedSubject<>(PublishSubject.create());
    }

    public void post(Object o){
        bus.onNext(o);
    }

    public Observable<Object> tObservable(){
        return bus;
    }

    public <T> Observable<T> tObservable(Class<T> tClass){
        return bus.ofType(tClass);
    }


}
