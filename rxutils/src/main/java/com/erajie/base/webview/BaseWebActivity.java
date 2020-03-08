package com.erajie.base.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author EraJieZhang
 * @data 2020/3/7
 */
public class BaseWebActivity extends Activity {
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivity = this;

    }


    /**
     * 设置一个webview
     * @param webView
     * @param url   加载的连接
     * @param interfaceName js调用的名
     */
    public void initdata(WebView webView, String url, String interfaceName) {


        webView.loadUrl(url);

        /*加载标题*/
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                //				mTitle.setText(title);
            }
        });
        webView.addJavascriptInterface(new AndroidToJs(), interfaceName);
        WebSettings webSettings = webView.getSettings();
        /* 设置支持javascript脚本 */
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        /* 设置可以支持缩放 */
        webSettings.setSupportZoom(true);
        /* 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false */
        webSettings.setBuiltInZoomControls(true);
        /* 隐藏缩放工具 */
        webSettings.setDisplayZoomControls(false);
        /* 扩大比例的缩放 */
        webSettings.setUseWideViewPort(true);
        /* 自适应屏幕 */
        webSettings
                .setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setDatabaseEnabled(true);
        /*
         * 是否开启本地DOM存储 鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android
         * 默认是关闭该功能的。
         */
        webSettings.setDomStorageEnabled(true);
        webView.setSaveEnabled(true);
        webView.setKeepScreenOn(true);
    }


    /**
     * 调用js方法
     * @param webView
     * @param methodName js方法名
     * @param parameter js方法参数（json格式）
     * @param delayMillis 延迟毫秒数 (不延迟就传0)
     */
    private void jsToDo(WebView webView, String methodName, String parameter, long delayMillis) {
        webView.postDelayed(new Runnable() {
            @Override
            public void run() {
                    webView.loadUrl("javascript:"+methodName+"('" + parameter + "')");
            }
        },delayMillis);
    }


}
