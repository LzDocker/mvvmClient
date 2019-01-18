package com.docker.moduleplayer.ui.common;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.docker.moduleplayer.R;

public class WebActivity extends AppCompatActivity {


    WebView mwebView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.moduleplayer_activity_web);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void init() {
      String targetUrl = (String) getIntent().getExtras().get("targetUrl");
        mwebView = findViewById(R.id.webview);
        mwebView.getSettings().setUseWideViewPort(true);
        mwebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mwebView.getSettings().setLoadWithOverviewMode(true);
        WebSettings setting = mwebView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);

        mwebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.i("console", "[" + consoleMessage.messageLevel() + "] " + consoleMessage.message() + "(" + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + ")");
                return super.onConsoleMessage(consoleMessage);

            }
        });

        mwebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        setting.setDomStorageEnabled(true);
        setting.setAllowFileAccessFromFileURLs(true);
        mwebView.loadUrl(targetUrl);


    }

    @Override
    public void onBackPressed() {

        if (mwebView.canGoBack()) {
            mwebView.goBack();
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (mwebView != null) {
            mwebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mwebView.clearHistory();
            ((ViewGroup) mwebView.getParent()).removeView(mwebView);
            mwebView.destroy();
            mwebView = null;
        }
        super.onDestroy();
    }


}
