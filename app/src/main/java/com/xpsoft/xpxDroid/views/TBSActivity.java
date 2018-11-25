package com.xpsoft.xpxDroid.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.widget.AppTitleBar;

/**
 * Created by XPSoft on 2018/9/26.
 */

public class TBSActivity extends baseActivity {

    private AppTitleBar appTitleBar;
    private com.tencent.smtt.sdk.WebView tbsWebView;
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbs);
        appTitleBar= (AppTitleBar) findViewById(R.id.appTitleBar);
        appTitleBar.setTitle("TBS浏览器");
        appTitleBar.setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }
        });
        webView= (WebView) findViewById(R.id.webview);
//        webView.loadUrl("https://www.baidu.com/");
        webView.loadUrl("http://www.c-go.com.cn/index.php");
        android.webkit.WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new android.webkit.WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });

        tbsWebView = (com.tencent.smtt.sdk.WebView)findViewById(R.id.tbs);
        int width = tbsWebView.getView().getWidth();
//        tbsWebView.loadUrl("https://www.baidu.com/");
        tbsWebView.loadUrl("http://www.c-go.com.cn/index.php");
        WebSettings tbswebSettings= tbsWebView.getSettings();
        tbswebSettings.setJavaScriptEnabled(true);//允许使用js


        tbsWebView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient(){
            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String s) {
                super.onPageFinished(webView, s);
            }
        });
        tbsWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(com.tencent.smtt.sdk.WebView webView, int i) {
                super.onProgressChanged(webView, i);
            }
        });

    }
}
