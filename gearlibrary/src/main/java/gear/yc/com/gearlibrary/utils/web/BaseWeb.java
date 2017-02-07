package gear.yc.com.gearlibrary.utils.web;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;

import java.io.File;

/**
 * BAndroid
 * Created by YichenZ on 2015/10/26 15:50.
 */
public class BaseWeb {
    protected static final String APP_CACAHE_DIRNAME = "/webcache";
    protected WebSettings settings;

    public WebSettings getSettings() {
        return settings;
    }

    public void setSettings(WebSettings settings) {
        this.settings = settings;
    }

    public WebSettings setWeb(Context context){
        if(settings==null)
            new RuntimeException("settings is not null");

        settings.setJavaScriptEnabled(true);//开启JavaScript
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//缓存模式
        settings.setDefaultTextEncodingName("utf-8");//默认加载格式utf-8
        settings.setDomStorageEnabled(true);//开启DOM storage API
        settings.setDatabaseEnabled(true);//启用数据库
        settings.setBuiltInZoomControls(true);//启用页面缩放
        String cacheDirPath = context.getFilesDir().getAbsolutePath()+"test";
        settings.setDatabasePath(cacheDirPath);//设置数据库缓存路径
        settings.setAppCachePath(cacheDirPath);//设置应用换成路径
        settings.setAppCacheEnabled(true);//开启应用缓存
        if(Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);//开启网页图片加载
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        return settings;
    }

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache(Context context){

        //清理Webview缓存数据库
        try {
            context.deleteDatabase("webview.db");
            context.deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //WebView 缓存文件
        File appCacheDir = new File(context.getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME);
//        Log.e(TAG, "appCacheDir path=" + appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(context.getCacheDir().getAbsolutePath()+APP_CACAHE_DIRNAME);
//        Log.e(TAG, "webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());

        //删除webview 缓存目录
        if(webviewCacheDir.exists()){
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if(appCacheDir.exists()){
            deleteFile(appCacheDir);
        }
    }


    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {

//        Log.i(TAG, "delete file path=" + file.getAbsolutePath());

        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
//            Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
    }
}
