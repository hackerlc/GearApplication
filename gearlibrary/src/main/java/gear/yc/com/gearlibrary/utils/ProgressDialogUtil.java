package gear.yc.com.gearlibrary.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

/**
 *
 * 系统默认圆形进度条加载
 * 可设置title以及点击返回按钮关闭activity
 * 也可以直接设置点击返回按钮之后的回调
 * @version 1.3
 * setTitle 后如果dialog显示那么可以改变文字
 * @version 1.2
 * build时 先判断是否为null 并且isShow ,如果为true则先dis
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

    /**
     * 设置加载信息
     * @param title
     * @return
     */
    public ProgressDialogUtil setTitle(String title) {
        this.title = title;
        if(progressDialog!=null && progressDialog.isShowing()) {
            progressDialog.setMessage(title);
            progressDialog.notify();
        }
        return instance;
    }

    /**
     * 设置点击返回后关闭activity
     * @param isBackDismiss
     * @param activity
     * @return
     */
    public ProgressDialogUtil setBackDismiss(boolean isBackDismiss, Activity activity) {
        this.isBackDismiss = isBackDismiss;
        mActivity=activity;
        return instance;
    }

    /**
     * 设置点击返回后的事件
     * @param isBackDismiss
     * @param mListener
     * @return
     */
    public ProgressDialogUtil setBackDismiss(boolean isBackDismiss, DialogInterface.OnCancelListener mListener) {
        this.isBackDismiss = isBackDismiss;
        this.mListener=mListener;
        return instance;
    }

    public ProgressDialogUtil build(@NonNull Context mContext) {
        this.mContext=mContext;
        //防止创建dialog时上一个没有被关闭
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(this.mContext,ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        if(isBackDismiss){
            if(mListener!=null){
                progressDialog.setOnCancelListener(mListener);
            }else {
                progressDialog.setOnCancelListener(l -> mActivity.finish());
            }
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
