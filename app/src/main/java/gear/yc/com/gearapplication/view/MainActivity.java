package gear.yc.com.gearapplication.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;
import gear.yc.com.gearlibrary.HTTP.OkHttpManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    TextView hello_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello_tv = (TextView) findViewById(R.id.hello_tv);
        hello_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hello_tv.setText("On Click Hello");
                            }
                        }).show();
            }
        });

        testOkHttp();

    }

    /**
     * git
     */
    public void testOkHttp(){
        try {
            OkHttpManager.getInstance().getHttpOfGet("http://www.baidu.com",mHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
//            ExecutorService mExecutorS=Executors.newFixedThreadPool(3);
//            mExecutorS.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        String str = OkHttpManager.getInstance().getHttpOfGet("http://www.baidu.com").body().string();
//                        Message message =new Message();
//                        message.obj=str;
//                        mHandler.sendMessage(message);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//        OkHttpClient okHttpClient =new OkHttpClient();
//
//        FormBody formBodys=new FormBody.Builder()
//                .add("city","248").build();
//
//        final Request request =new Request.Builder()
//                .post(RequestBody.create(MediaType.parse("application/json"),"{\"module_id\":\"101\"}"))
//                .url("http://test.awu.cn/api/index.php?act=main").build();
//
//        final Call call=okHttpClient.newCall(request);
//



//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final Message message = new Message();
//                message.what = 1;
//                message.obj = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mHandler.sendMessage(message);
//                    }
//                });
//
//            }
//        });

    }

    Handler mHandler =new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            hello_tv.setText((String)msg.obj);
            return true;
        }
    });
}
