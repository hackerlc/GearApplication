package gear.yc.com.gearlibrary.rxbus.thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Android on 2016/6/15.
 */
public class SubscriberMethod {
    public Method method;
    public EventThread eventThread;
    public int code;
    public Class<?> aClass;
    public Object subscriber;

    public SubscriberMethod(Method method,EventThread eventThread,int code,Class<?> aClass,Object subscriber){
        this.method=method;
        this.eventThread=eventThread;
        this.code=code;
        this.aClass=aClass;
        this.subscriber=subscriber;
    }

    public void invoke(Object obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(subscriber,obj);
    }
}
