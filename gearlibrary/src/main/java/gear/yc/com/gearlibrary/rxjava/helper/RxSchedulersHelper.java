package gear.yc.com.gearlibrary.rxjava.helper;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version 1.0
 * 封装了线程调度
 * Created by Android on 2016/6/16.
 */
public class RxSchedulersHelper {
    public static <T> Observable.Transformer<T,T> io_main(){
        return ob -> ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
