package gear.yc.com.gearlibrary.network.http;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * GearApplication
 * 使用OkHttp get or post 信息
 * Created by YichenZ on 2016/4/15 11:30.
 */
public class HttpInfo {
    protected final String MEDIA_TYPE_JSON="application/json";

    protected static HttpInfo instance;

    public static HttpInfo getInstance(){
        if(instance==null){
            synchronized (HttpInfo.class){
                if(instance==null){
                    instance=new HttpInfo();
                }
            }
        }
        return instance;
    }

    protected OkHttpClient okHttpClient;

    //连接池大小
    protected static final int DEFAULT_FIXED_THREAD = 2;
    //线程连接池
    protected ExecutorService executorService;
    //Gson解析类型
    protected  Class<?> tClass;
    protected Gson gson;

    public HttpInfo(){
        okHttpClient=OkHttpManager.getInstance().getClient();

        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(DEFAULT_FIXED_THREAD);
        }
        if(gson==null){
            gson=new Gson();
        }

    }

    /**
     * 设置解析Gson类型，不设置直接返回String字符串
     */
    public void setClass(Class<?> tClass){
        this.tClass=tClass;
    }

    /**
     * get方式获取参数
     * @param url
     * @param handler
     * @throws Exception
     */
    public void getHttpOfGet(String url, final Handler handler) throws Exception {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        executorService.execute(() ->{
            Message message=null;
            try {
                final Response response = call.execute();
                message = new Message();
                if(response==null){
                    message.what=-1;
                }else{
                    message.obj = response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.sendMessage(message);
        });
    }

    /**
     * 直接获取Response
     * @param url
     * @return
     * @throws Exception
     */
    public Response getHttpOfGet(String url) throws Exception {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    public void getHttpOfPost(String url,String content,final Handler handler) throws Exception {
        this.getHttpOfPost(url,null,content,handler);
    }

    public void getHttpOfPost(String url, FormBody formBody, final Handler handler) throws Exception {
        this.getHttpOfPost(url,formBody,null,handler);
    }

    /**
     * post 方式获取参数
     * @param url
     * @param formBody 表单提交方式
     * @param content 内容提交方式
     * @param handler
     * @throws Exception
     */
    public void getHttpOfPost(String url,FormBody formBody,String content,final Handler handler) throws Exception {
        //判断postBody的两种提交方式是否为空
        if(formBody==null && content==null){
            new Exception("formBody or content not null");
        }
        RequestBody requestBody=formBody;
        //非表单提交则用content
        if(requestBody==null){
            //设置提交方式为Json
            requestBody=RequestBody.create(MediaType.parse(MEDIA_TYPE_JSON),content);
        }
        final Request request =new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        executorService.execute(() ->{
            Message message=null;
            try {
                final Response response = call.execute();
                message = new Message();
                //判断是否null防止特殊情况连接无法访问response为空时程序异常跳出，
                // 并且message传值为-1给后续判断
                if(response==null){
                    message.what=-1;
                }else if(tClass!=null){//判断是否需要Gson解析 TODO: 以后需要更好地写法
                    message.obj = gson.fromJson(response.body().string(),tClass);
                    //赋值为null防止后续请求错误
                    tClass=null;
                }else{//不需要Gson解析直接返回内容字符串
                    message.obj = response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.sendMessage(message);
        });
    }

    /**
     * 可以设置自定义OkHttpClient
     * @param okHttpClient
     */
    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
}
