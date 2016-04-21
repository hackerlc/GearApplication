package gear.yc.com.gearlibrary.service.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * GearApplication
 * okHttp3 管理类，封装了okHttp的网络连接方法
 * 可以设置Gson解析
 * OkHttp3 开启Stetho听诊器
 * Created by YichenZ on 2016/3/8 13:41.
 */
public class OkHttpManager {

    private static Object obj = new Object();
    private static OkHttpManager instance;

    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (obj) {
                if (instance == null) {
                    instance = new OkHttpManager();
                }
            }
        }
        return instance;
    }

    private static OkHttpClient okHttpClient;
    private static com.squareup.okhttp.OkHttpClient oldOkHttpClient;

    public OkHttpManager() {
        init();
    }

    //初始化一些参数
    private void init() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .retryOnConnectionFailure(true)
                    .connectTimeout(15,TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request response =chain.request()
                                .newBuilder()
                                .addHeader("apikey","beae89ef686795322d5a3c48579875d5")
                                .build();
                        return chain.proceed(response);
                    })
                    .build();

            oldOkHttpClient=new com.squareup.okhttp.OkHttpClient();
            oldOkHttpClient.setConnectTimeout(15,TimeUnit.SECONDS);
            oldOkHttpClient.setRetryOnConnectionFailure(true);
        }
    }

    /**
     * @return OkHttp3 Client
     */
    public OkHttpClient getClient() {
        return okHttpClient;
    }

    /**
     *
     * @return OkHttp Client
     */
    public com.squareup.okhttp.OkHttpClient getOldClient() {
        return oldOkHttpClient;
    }

}
