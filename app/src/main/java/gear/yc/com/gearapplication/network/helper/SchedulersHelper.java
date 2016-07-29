package gear.yc.com.gearapplication.network.helper;

import gear.yc.com.gearapplication.pojo.ResponseJson;
import rx.Observable;

/**
 * Created by Android on 2016/6/16.
 */
public class SchedulersHelper {
    static String str="暂无数据";

    public static <T> Observable.Transformer<ResponseJson<T>, T> handleResult() {
        return responseJsonObservable -> responseJsonObservable.flatMap(
                tResponseJson -> {
                    if (tResponseJson == null) {
                    } else if(tResponseJson.getErrcode() != 0){
                        str=tResponseJson.getErrmsg();
                    }else {
                        return createData(tResponseJson.getData());
                    }
                    return Observable.error(new Throwable(str));
                });
    }

    /**
     * 检测面包旅行
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<ResponseJson<T>, T> handleResultBread() {
        return responseJsonObservable -> responseJsonObservable.flatMap(
                tResponseJson -> {
                    if (tResponseJson == null) {
                    } else if(tResponseJson.getStatus() != 0){
                        str=tResponseJson.getMessage();
                    }else {
                        return createData(tResponseJson.getData());
                    }
                    return Observable.error(new Throwable(str));
                });
    }

    private static <T> Observable<T> createData(T data) {
        return Observable.create(subscriber -> {
            subscriber.onNext(data);
            subscriber.onCompleted();
        });
    }
}
