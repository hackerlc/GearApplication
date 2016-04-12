package gear.yc.com.gearapplication.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.databinding.ActivityDatabindingBinding;
import gear.yc.com.gearapplication.pojo.User;

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

        new Thread(runnable).start();
    }

    @Override
    public void initUI() {
        super.initUI();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        user=new User();
        user.setId("2213");
        user.setUser("Joker");
        user.setPwd("120.00");
        binding.setUser(user);
    }

    Runnable runnable =new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                user.setPwd("321.10");
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
            binding.setUser(user);
        }
    };
}
