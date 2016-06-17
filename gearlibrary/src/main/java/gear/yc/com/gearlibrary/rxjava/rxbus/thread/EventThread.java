package gear.yc.com.gearlibrary.rxjava.rxbus.thread;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Android on 2016/6/8.
 */
public enum EventThread {
    /**
     * 主线程
     */
    MAIN_THREAD,
    /**
     * 新的线程
     */
    NEW_THREAD,
    /**
     * 读写线程
     */
    IO,
    /**
     * 计算工作默认线程
     */
    COMPUTATION,
    /**
     * 在当前线程中按照队列方式执行
     */
    TRAMPOLINE,
    /**
     * 当前线程
     */
    IMMEDIATE;
//    EXECUTOR,
//    HANDLER;

    public static Scheduler getScheduler(EventThread threadMode){
        Scheduler scheduler;
        switch (threadMode){
            case MAIN_THREAD:scheduler=AndroidSchedulers.mainThread();break;
            case NEW_THREAD:scheduler= Schedulers.newThread();break;
            case IO:scheduler=Schedulers.io();break;
            case COMPUTATION:scheduler=Schedulers.computation();break;
            case TRAMPOLINE:scheduler=Schedulers.trampoline();break;
            case IMMEDIATE:scheduler=Schedulers.immediate();break;
            default:scheduler= AndroidSchedulers.mainThread();
        }
        return scheduler;
    }
}
