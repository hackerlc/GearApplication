package gear.yc.com.gearapplication.network.helper;

import org.reactivestreams.Publisher;

import gear.yc.com.gearapplication.pojo.ResponseJson;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by Android on 2016/6/16.
 */
public class SchedulersHelper {
    static String str="暂无数据";

    public static <T> FlowableTransformer<ResponseJson<T>, T> handleResult() {
        return responseJsonObservable -> responseJsonObservable.flatMap(
                tResponseJson -> {
                    if (tResponseJson == null) {
                    } else if(tResponseJson.getErrcode() != 0){
                        str=tResponseJson.getErrmsg();
                    }else {
                        return createData(tResponseJson.getData());
                    }
                    return Flowable.error(new Throwable(str));
                });
    }

    /**
     * 检测面包旅行
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<ResponseJson<T>, T> handleResultBread() {
        return resJsonObs -> resJsonObs.flatMap(new Function<ResponseJson<T>, Publisher<T>>() {
            @Override
            public Publisher<T> apply(ResponseJson<T> tResponseJson) throws Exception {
                if (tResponseJson == null) {
                } else if(tResponseJson.getStatus() != 0){
                    str=tResponseJson.getMessage();
                }else {
                    return createData(tResponseJson.getData());
                }
                return Flowable.error(new Throwable(str));
            }
        });
    }

    private static <T> Flowable<T> createData(T data) {
        return Flowable.create(subscriber -> {
            subscriber.onNext(data);
            subscriber.onComplete();
        }, FlowableEmitter.BackpressureMode.NONE);
    }
}
