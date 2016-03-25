package gear.yc.com.gearlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;

import gear.yc.com.gearlibrary.Manager.ActivityManager;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 11:23.
 */
public class GearActivity extends FragmentActivity implements View.OnClickListener{

    //Activity跳转时默认的跳转参数
    protected static final String J_FLAG="FLAG";
    protected static final String J_FLAG2="FLAG2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().getActivities().add(this);
        initUI();
    }

    public void initUI(){}

    @Override
    public void onClick(View v) {

    }

    public void exitApp(){
        ActivityManager.getInstance().clearAllActivity();
        System.exit(0);
    }


    /**
     * activity跳转
     * 默认进入跳转
     * toActivity(context,cls,false,false);
     * @param context
     * @param cls
     */
    protected void strActivity(Context context, Class<?> cls){
        strActivity(context, cls, false, false);
    }

    /**
     * 快速跳转
     * 先更改跳转样式在finish
     * @param context
     * @param cls
     * @param closeActivity 是否关闭当前activity
     * @param isOut false 转入   true 转出
     */
    protected void strActivity(Context context,Class<?> cls,Boolean closeActivity,Boolean isOut){
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        setGo(isOut);
        if (closeActivity) {
            finish();
        }
    }

    /**
     * 传参数快速跳转(单参数)
     * @param context
     * @param cls
     * @param closeActivity
     * @param isOut
     * @param flg 参数 接收默认为 "flg" 不可修改
     */
    protected void strActivity(Context context, Class<?> cls,
                               Boolean closeActivity, Boolean isOut, String flg) {
        strActivity(context, cls, closeActivity, isOut, flg, "");
    }

    /**
     * 传参跳转(双参数)
     * @param context
     * @param cls
     * @param closeActivity
     * @param isOut
     * @param flg 第一个参数，接收为 "flg"
     * @param flg2Value 第二个参数
     */
    protected void strActivity(Context context, Class<?> cls,
                               Boolean closeActivity, Boolean isOut, String flg,
                               String flg2Value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(J_FLAG, flg);
        if(!flg2Value.equals("")) {
            intent.putExtra(J_FLAG2, flg2Value);
        }
        context.startActivity(intent);
        if (closeActivity) {
            finish();
        }
        setGo(isOut);
    }

    /**
     * 带跳转样式的关闭activity
     */
    protected void finishActivity(){
        finish();
        setGo(true);
    }

    /**
     * 定义了activity的跳转样式
     */
    protected void setGo(boolean isOut) {
        // 设置默认值
        int inEnter = R.anim.zoomin;
        int inExit = R.anim.zoomout;
        int outEnter = R.anim.backin;
        int outExit = R.anim.backout;

        if (!isOut) {
            overridePendingTransition(inEnter, inExit);
        } else {
            overridePendingTransition(outEnter, outExit);
        }
    }

    /**
     * 返回按钮finish activity
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finishActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
