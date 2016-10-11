package gear.yc.com.gearapplication.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import gear.yc.com.gearapplication.base.BaseActivity;
import io.reactivex.Observable;

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
//        Observable.just("Hello world").subscribe(s -> System.out.println(s));

        //3. map
//        Observable.just("Hello world")
//                .map(s -> s+"Joker")
//                .map(s -> s.hashCode())
//                .map(i -> Integer.toString(i))
//                .subscribe(s -> System.out.println(s));

        //4
//        List<String> urls =new ArrayList<String>();
//        urls.add("a");
//        urls.add("b");
//        Observable.just(urls)
//                .flatMap( u -> Observable.from(u))
//                .subscribe(url -> System.out.println(url));
        //5 Demo
        Observable.just("#Basic Markdown to HTML with lambda")
                .filter(s -> s!=null && s.startsWith("#"))
                .map(s -> "<h1>"+s.substring(1,s.length())+"</h1>")
                .subscribe(System.out::println);


    }
    //4
//    Observable<List<String>> query(String text){
//        return Observable.create(Observable<List<String>>);
//    }

//    //1.
//    Flowable<String> mObservable = Flowable.create(new FlowableOnSubscribe<String>() {
//        @Override
//        public void subscribe(FlowableEmitter<String> e) throws Exception {
//            e.onNext("Hello world");
//            e.onComplete();
//        }
//    });

    //1.
    Subscriber<String> mSubscriber =new Subscriber<String>() {
        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(String s) {
            System.out.println(s);
        }
    };

}
