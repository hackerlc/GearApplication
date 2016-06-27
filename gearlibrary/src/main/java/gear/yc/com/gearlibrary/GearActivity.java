package gear.yc.com.gearlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import gear.yc.com.gearlibrary.manager.ActivityManager;

/**
 * GearApplication
 * @version 1.1
 * 删除了自带的rx生命周期管理
 * Created by YichenZ on 2016/3/23 11:23.
 */
public class GearActivity extends Activity implements View.OnClickListener {
    //Activity跳转时默认的跳转参数
    protected static final String J_FLAG = "FLAG";
    protected static final String J_FLAG2 = "FLAG2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().getActivities().add(this);

    }

    protected void initUI() {
    }

    protected void initData() {
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * exit app
     */
    public void exitApp() {
        ActivityManager.getInstance().clearAllActivity();
        System.exit(0);
    }


    /**
     * activity跳转
     * 默认进入跳转
     * toActivity(context,cls,false,false);
     *
     * @param context this
     * @param cls     jump class
     */
    protected void strActivity(Context context, Class<?> cls) {
        strActivity(context, cls, false, false);
    }

    /**
     * 快速跳转
     * 先更改跳转样式在finish
     *
     * @param context       this
     * @param cls           jump class
     * @param closeActivity 是否关闭当前activity
     * @param isOut         false 转入   true 转出
     */
    protected void strActivity(Context context, Class<?> cls, Boolean closeActivity, Boolean isOut) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        setGo(isOut);
        if (closeActivity) {
            finish();
        }
    }

    /**
     * 传参数快速跳转(单参数)
     *
     * @param context       this
     * @param cls           jump class
     * @param closeActivity true close false no close
     * @param isOut         A activity B activity A to B
     *                      true B to A
     *                      false A to B
     * @param flg           参数 接收默认为 "flg" 不可修改
     */
    protected void strActivity(Context context, Class<?> cls,
                               Boolean closeActivity, Boolean isOut, String flg) {
        strActivity(context, cls, closeActivity, isOut, flg, "");
    }

    /**
     * 传参跳转(双参数)
     *
     * @param context       this
     * @param cls           jump class
     * @param closeActivity true close false no close
     * @param isOut         A activity B activity A to B
     *                      true B to A
     *                      false A to B
     * @param flg           第一个参数，接收为 "flg"
     * @param flg2Value     第二个参数
     */
    protected void strActivity(Context context, Class<?> cls,
                               Boolean closeActivity, Boolean isOut, String flg,
                               String flg2Value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(J_FLAG, flg);
        if (!flg2Value.equals("")) {
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
     *
     * @param isOut 是否退出
     */
    protected boolean finish(boolean isOut) {
        finish();
        setGo(isOut);
        return true;
    }

    /**
     * 定义了activity的跳转样式
     *
     * @param isOut true or false
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
     * 获取已经约定的数据
     * @return
     */
    public String getStrIntent(){
        return getIntent().getStringExtra(J_FLAG);
    }

    public String getStr2Intent(){
        return getIntent().getStringExtra(J_FLAG2);
    }

    /**
     * 网络判断
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager
                .getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT);
        return false;
    }

}
