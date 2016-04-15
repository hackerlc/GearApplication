package gear.yc.com.gearapplication.manager.api;

import gear.yc.com.gearapplication.api.service.APIService;
import gear.yc.com.gearapplication.config.APIConfig;
import gear.yc.com.gearlibrary.http.OkHttpManager;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/13 14:41.
 */
public class APIServiceManager {
    private static APIServiceManager instance;

    public static APIServiceManager getInstance(){
        if(instance==null){
            synchronized (APIServiceManager.class){
                if(instance==null){
                    instance=new APIServiceManager();
                }
            }
        }
        return instance;
    }

    Retrofit retrofit;

    private APIService apiService;

    public APIServiceManager(){
        init();
    }

    private void init() {
        retrofit=new Retrofit.Builder()
                .client(OkHttpManager.getInstance().getClient())
                .baseUrl(APIConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService=retrofit.create(APIService.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public APIService getApiService() {
        return apiService;
    }
}
