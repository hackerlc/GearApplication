package gear.yc.com.gearapplication.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.KeyEvent;

import gear.yc.com.gearlibrary.GearActivity;
import gear.yc.com.gearlibrary.rxjava.rxbus.RxBus;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 11:26.
 */
public class BaseActivity extends GearActivity{

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.getInstance().register(this);

    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        RxBus.getInstance().unRegister(this);
        super.onDestroy();
    }


    /**
     * 返回按钮finish activity
     *
     * @param keyCode
     * @param event
     * @return true or false
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            }else{
                finish(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
