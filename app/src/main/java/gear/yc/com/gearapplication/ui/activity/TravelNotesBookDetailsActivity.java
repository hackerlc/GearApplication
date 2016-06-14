package gear.yc.com.gearapplication.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;
import gear.yc.com.gearlibrary.utils.web.BaseWeb;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/29 15:11.
 */
public class TravelNotesBookDetailsActivity extends BaseActivity{
    WebView dataWv;
    ImageView mBack;
    TextView mTitle;

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
        setContentView(R.layout.activity_travel_notes_book_details);
        dataWv=(WebView)findViewById(R.id.wv_web_view);
        mBack=(ImageView)findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mTitle=(TextView)findViewById(R.id.tv_title);
    }

    WebViewClient mWebViewClient =new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            cacheUrl=url;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(settings !=null &&!settings.getLoadsImagesAutomatically()) {
                settings.setLoadsImagesAutomatically(true);
            }
            super.onPageFinished(view,url);
        }
    };


    WebChromeClient webChromeClient =new WebChromeClient(){
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if(defTitle!=null){
                title=defTitle;
            }
            mTitle.setText(title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return true;
        }
    };

    public void initData() {
        defUrl=getIntent().getStringExtra(J_FLAG);
        defTitle=getIntent().getStringExtra(J_FLAG2);
        if(defUrl!=null && !defUrl.equals("")){
            BASE_URL[0]=defUrl;
            BASE_URL_TOP[0]=defUrl;
        }
        baseWeb=new BaseWeb();
        dataWv.setWebViewClient(mWebViewClient);
        dataWv.setWebChromeClient(webChromeClient);
        baseWeb.setSettings(dataWv.getSettings());
        settings=baseWeb.setWeb(this);
        dataWv.loadUrl(BASE_URL[0]);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_back:finish(true);break;
        }
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
