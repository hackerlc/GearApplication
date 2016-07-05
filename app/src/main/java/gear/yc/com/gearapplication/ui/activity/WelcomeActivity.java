package gear.yc.com.gearapplication.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.base.BaseActivity;
import gear.yc.com.gearapplication.databinding.ActivityWelcomeBinding;
import gear.yc.com.gearapplication.pojo.Clock;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * GearApplication
 * 欢饮页面并在页面中显示一个时钟，时钟计时为Rx方式
 * Created by YichenZ on 2016/3/23 11:14.
 */
public class WelcomeActivity extends BaseActivity {
    //几秒之后跳转页面
    static final short TIME_COUNT=3000;
    ActivityWelcomeBinding binding;
    Clock mClock;

    long startTime;
    Subscription mSub;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initData();

        gotoPage();
    }

    @Override
    public void initUI() {
        super.initUI();
        binding= DataBindingUtil.setContentView(this,R.layout.activity_welcome);
    }

    @Override
    public void initData() {
        super.initData();
        mClock=new Clock();
    }

    /**
     * 获取到开始时间戳，并在n秒后跳转页面
     */
    private void gotoPage() {
        startTime= System.currentTimeMillis();
        Observable.just(mClock)
                .compose(bindToLifecycle())
                .map(f -> {
                    while (true){
                        binding.setClock(f.setTime());
                        if(System.currentTimeMillis()-startTime >=TIME_COUNT) {
                            break;
                        }
                    }
                    return true;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    startActivity(new Intent(this, gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesActivity.class));
                    finish();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }
}
