package gear.yc.com.gearlibrary.network.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * GearApplication
 * okHttp3 管理类，封装了okHttp的网络连接方法
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

    private int mTimeOut=15;
    private String headerKey,headerValue;

    public OkHttpManager() {
    }

    public OkHttpManager setHeader(String headerKey,String headerValue) {
        this.headerKey = headerKey;
        this.headerValue = headerValue;
        return instance;
    }

    public OkHttpManager setTimeOut(int timeOut) {
        mTimeOut = timeOut;
        return instance;
    }

    public OkHttpManager build(){
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .retryOnConnectionFailure(true)
                    .connectTimeout(mTimeOut, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request response = chain.request();
                        if (headerKey!=null) {
                            response = chain.request()
                                    .newBuilder()
                                    .addHeader(headerKey, headerValue)
                                    .build();
                        }
                        return chain.proceed(response);
                    })
                    .build();
        }
        return instance;
    }

    /**
     * @return OkHttp3 Client
     */
    public OkHttpClient getClient() {
        return okHttpClient;
    }

}
