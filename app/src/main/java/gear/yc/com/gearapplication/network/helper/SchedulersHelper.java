package gear.yc.com.gearapplication.network.helper;

import gear.yc.com.gearapplication.pojo.ResponseJson;
import rx.Observable;

/**
 * Created by Android on 2016/6/16.
 */
public class SchedulersHelper {

    public static <T> Observable.Transformer<ResponseJson<T>, T> handleResult() {
        return responseJsonObservable -> responseJsonObservable.flatMap(
                tResponseJson -> {
                    if (tResponseJson == null || tResponseJson.getErrcode() != 0) {
                        return Observable.error(new Throwable(tResponseJson.getErrmsg()));
                    } else {
                        return createData(tResponseJson.getData());
                    }
                });
    }

    private static <T> Observable<T> createData(T data) {
        return Observable.create(subscriber -> {
            subscriber.onNext(data);
            subscriber.onCompleted();
        });
    }
}
