package gear.yc.com.gearlibrary.network.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/13 14:41.
 */
public class GearHttpServiceManager {
    private static GearHttpServiceManager instance;

    public static GearHttpServiceManager getInstance(){
        if(instance==null){
            synchronized (GearHttpServiceManager.class){
                if(instance==null){
                    instance=new GearHttpServiceManager();
                }
            }
        }
        return instance;
    }

    private static String sBaseUrl;

    Retrofit retrofit;

    public GearHttpServiceManager(){
    }

    public GearHttpServiceManager setBaseUrl(String url){
        sBaseUrl=url;
        return instance;
    }

    public void build(OkHttpClient okHttpClient){
        if(sBaseUrl==null)
            new IllegalAccessException("need setBaseUrl");
        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        if(retrofit==null)
            new IllegalAccessException("Please build");
        return retrofit;
    }

}
