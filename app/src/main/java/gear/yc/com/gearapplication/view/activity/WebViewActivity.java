package gear.yc.com.gearapplication.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;
import gear.yc.com.gearlibrary.utils.web.BaseWeb;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/29 15:11.
 */
public class WebViewActivity extends BaseActivity{
    WebView dataWv;

    final String[] BASE_URL={"https://github.com/hackerlc/wiki"};
    final String[] BASE_URL_TOP={"https://github.com/hackerlc/wiki"};

    //缓存当前URL
    String cacheUrl="";

    WebSettings settings;
    BaseWeb baseWeb;

    String defUrl;
    String defTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(baseWeb!=null) {
            settings=null;
            baseWeb = null;
        }
    }

    public void initUI() {
        setContentView(R.layout.activity_web_view);
        dataWv=(android.webkit.WebView)findViewById(R.id.wv_web_view);

    }

    public void initData() {

        defUrl=getIntent().getStringExtra("defUrl");
        defTitle=getIntent().getStringExtra("defTitle");
        if(defUrl!=null && !defUrl.equals("")){
            BASE_URL[0]=defUrl;
            BASE_URL_TOP[0]=defUrl;
        }
        baseWeb=new BaseWeb();
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                if(url.substring(0,4).equals("wtai")){
                    final String tel ="tel:"+ url.substring(13);
                    final Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
                    builder.setMessage("确认给客户拨打电话？\n "+tel)
                            .setNegativeButton("取消",
                                    (a0,a1) -> a0.cancel())
                            .setPositiveButton("确定",
                                    (a0,a1) -> startActivity(it));
                    AlertDialog alert = builder.create();
                    alert.show();
                }else{
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                cacheUrl=url;
            }

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                if(settings !=null &&!settings.getLoadsImagesAutomatically()) {
                    settings.setLoadsImagesAutomatically(true);
                }
            }
        };
        WebChromeClient webChromeClient =new WebChromeClient(){
            @Override
            public void onReceivedTitle(android.webkit.WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(android.webkit.WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        };
        dataWv.setWebViewClient(webViewClient);
        dataWv.setWebChromeClient(webChromeClient);
        baseWeb.setSettings(dataWv.getSettings());
        settings=baseWeb.setWeb(this);
        dataWv.loadUrl(BASE_URL[0]);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && dataWv.canGoBack()) {
            return isGoBack();
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isGoBack(){
        for(String url:BASE_URL){
            if(cacheUrl.equals(url)){
                finish();
                return true;
            }
        }
        dataWv.goBack();
        return true;
    }
}
