package gear.yc.com.gearapplication.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.databinding.ActivityDatabindingBinding;
import gear.yc.com.gearapplication.network.APIServiceManager;
import gear.yc.com.gearapplication.pojo.ResponseJson;
import gear.yc.com.gearapplication.pojo.User;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 15:25.
 */
public class DataBindingActivity extends BaseActivity {
    User user;
    ActivityDatabindingBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initData();
        new Thread(runnable).start();
    }

    @Override
    public void initUI() {
        super.initUI();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
    }

    @Override
    public void initData() {
        super.initData();
        user=new User();
        user.setUid("2213");
        user.setUsername("Joker");
        user.setPassword("120.00");
        binding.setUser(user);
        mCSub.add(APIServiceManager.getInstance()
                .getApiService()
                .getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponseJson -> {
                    Message msg =new Message();
                    msg.obj=userResponseJson;
                    msg.what=1;
                    handler.sendMessage(msg);
                }));

        mCSub.add(APIServiceManager.getInstance()
                .getTravelNotesAPI()
                .getTravelNotesList("","1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tnbs -> {
                    Message msg =new Message();
                    msg.obj=tnbs;
                    msg.what=2;
                    handler.sendMessage(msg);
                }));
    }

    Runnable runnable =new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                user.setPassword("321.10");
                handler.sendEmptyMessage(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj!=null){
                if(msg.what==1) {
                    user = ((ResponseJson<User>) msg.obj).getData();
                    if (user != null) {
                        binding.setUser(user);
                    } else {

                    }
                }
            }else{

            }
        }
    };
}
