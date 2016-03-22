package gear.yc.com.gearlibrary.HTTP;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/8 13:41.
 */
public class OkHttpManager {
    private static Object obj=new Object();
    private static OkHttpManager instance;

    public static OkHttpManager getInstance(){
        if(instance==null){
            synchronized (obj){
                if(instance==null){
                    instance=new OkHttpManager();
                }
            }
        }
        return instance;
    }

    private static OkHttpClient okHttpClient;

    //http请求成功
    public static final int HTTP_DATA_OK=1;
    private Message message;
    private Handler handler;
    private static final int DEFAULT_FIXED_THREAD=2;
    private ExecutorService executorService;
    private Response response;
    public OkHttpManager(){
        init();
    }

    private void init(){
        if(okHttpClient==null){
            okHttpClient=new OkHttpClient();
        }
        if(executorService==null){
            executorService= Executors.newFixedThreadPool(DEFAULT_FIXED_THREAD);
        }
    }

    public OkHttpClient getClient(){
        return okHttpClient;
    }

    public Response getHttpOfGet(String url) throws Exception {
        final Request request =new Request.Builder()
                .url(url)
                .build();
        final Call call=okHttpClient.newCall(request);
        return  call.execute();
    }


}
