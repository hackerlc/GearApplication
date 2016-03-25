package gear.yc.com.gearapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 11:14.
 */
public class WelcomeActivity extends BaseActivity {
    Button mainActivity;
    Button mainDataBinding;
    Button btn_RxJava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initUI() {
        super.initUI();
        setContentView(R.layout.activity_welcome);

        mainActivity=(Button)findViewById(R.id.btn_main_activity);
        mainDataBinding=(Button)findViewById(R.id.btn_data_binding);
        btn_RxJava=(Button)findViewById(R.id.btn_RxJava);

        mainActivity.setOnClickListener(this);
        mainDataBinding.setOnClickListener(this);
        btn_RxJava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_data_binding:
                strActivity(this,DataBindingActivity.class);
                break;
            case R.id.btn_main_activity:
                strActivity(this,MainActivity.class);
                break;
            case R.id.btn_RxJava:
                strActivity(this,MainActivity.class);
                break;
        }
    }
}
