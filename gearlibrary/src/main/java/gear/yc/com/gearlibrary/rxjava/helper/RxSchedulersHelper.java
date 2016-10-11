package gear.yc.com.gearlibrary.rxjava.helper;


import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @version 1.0
 * 封装了线程调度
 * Created by Android on 2016/6/16.
 */
public class RxSchedulersHelper {
    public static <T> FlowableTransformer<T,T> io_main(){
        return ob -> ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
