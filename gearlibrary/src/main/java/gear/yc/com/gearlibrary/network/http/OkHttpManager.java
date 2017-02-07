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
 * @version 1.1
 * 增加了isLog参数判断是否显示log
 * @version 1.0
 * 创建
 */
public class OkHttpManager {
    protected static OkHttpManager instance;

    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                if (instance == null) {
                    instance = new OkHttpManager();
                }
            }
        }
        return instance;
    }

    protected static OkHttpClient okHttpClient;

    protected int mTimeOut=15;
    protected String headerKey,headerValue;
    protected boolean isLog=true;

    public OkHttpManager() {
    }

    /**
     * 设置header
     * 暂时不支持多个header
     * @param headerKey
     * @param headerValue
     * @return
     */
    public OkHttpManager setHeader(String headerKey,String headerValue) {
        this.headerKey = headerKey;
        this.headerValue = headerValue;
        return instance;
    }

    /**
     * 设置超时时间
     * @param timeOut 数值
     * @return
     */
    public OkHttpManager setTimeOut(int timeOut) {
        mTimeOut = timeOut;
        return instance;
    }

    /**
     * 是否显示Log 默认为true
     * @param isLog true 显示
     * @return
     */
    public OkHttpManager setLog(boolean isLog){
        this.isLog=isLog;
        return instance;
    }

    public OkHttpManager build(){
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(isLog?HttpLoggingInterceptor.Level.BODY: HttpLoggingInterceptor.Level.NONE))
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
