package gear.yc.com.gearapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import gear.yc.com.gearapplication.BaseActivity;
import rx.Observable;
import rx.Subscriber;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/25 16:40.
 */
public class RxJavaActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        1.
//        mObservable.subscribe(mSubscriber);
//        2.
//        Observable.just("Hello world").subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                System.out.println(s);
//            }
//        });
        //3.
        Observable.just("Hello world").subscribe(s -> System.out.println(s));
    }

    //1.
    Observable<String> mObservable = rx.Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello world");
            subscriber.onCompleted();
        }
    });

    //1.
    Subscriber<String> mSubscriber =new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            System.out.println(s);
        }
    };

}
