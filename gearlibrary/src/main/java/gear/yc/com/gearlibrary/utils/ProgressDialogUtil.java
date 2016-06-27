package gear.yc.com.gearlibrary.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 系统默认圆形进度条加载
 * 可设置title以及点击返回按钮关闭activity
 * 也可以直接设置点击返回按钮之后的回调
 * @version 1.1
 * 增加了show()方法显示时候判断context是否为空以及progressDialog为空默认build
 * 增加了dismiss()方法最后解除context 以及 progressDialog 引用，防止activity finish后继续持有context
 * Created by YichenZ on 2016/5/19 14:58.
 */
public class ProgressDialogUtil {
    private static ProgressDialogUtil instance;

    public static ProgressDialogUtil getInstance() {
        if (instance == null) {
            synchronized (ProgressDialogUtil.class) {
                if (instance == null) {
                    instance = new ProgressDialogUtil();
                }
            }
        }
        return instance;
    }

    static ProgressDialog progressDialog;
    Context mContext;
    String title="数据加载中...";
    boolean isBackDismiss=false;
    Activity mActivity;
    DialogInterface.OnCancelListener mListener=null;

    private ProgressDialogUtil() {
    }

    public ProgressDialogUtil setTitle(String title) {
        this.title = title;
        return instance;
    }

    public ProgressDialogUtil setBackDismiss(boolean isBackDismiss, Activity activity) {
        this.isBackDismiss = isBackDismiss;
        mActivity=activity;
        return instance;
    }

    public ProgressDialogUtil setBackDismiss(boolean isBackDismiss, DialogInterface.OnCancelListener mListener) {
        this.isBackDismiss = isBackDismiss;
        this.mListener=mListener;
        return instance;
    }

    public ProgressDialogUtil build(Context mContext) {
        this.mContext=mContext;
        progressDialog = new ProgressDialog(this.mContext,ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        if(isBackDismiss){
            if(mListener!=null){
                progressDialog.setOnCancelListener(mListener);
            }
            progressDialog.setOnCancelListener(l -> mActivity.finish());
        }
        return instance;
    }

    public void show(){
        if(mContext==null){
            new IllegalAccessException("please build()");
        }else if(progressDialog==null){
            build(mContext);
        }
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog.show();
    }

    public void dismiss(){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog=null;
            mContext=null;
        }
    }

}
