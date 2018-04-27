package cc.cwalk.com.webview;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cc.cwalk.com.R;
import cc.cwalk.com.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    private final static String TITLE= "tile" ;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected String setTitle() {
        return getIntent().getStringExtra(TITLE);
    }

    @Override
    protected void initView() {
        initUI();
    }

    @Override
    protected void initData() {

    }


    private WebView mWebView;
    private String mUrl= "http://chengw32.com:8080/videos/sample.mp4";


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void initUI() {


        mWebView = (WebView) findViewById(R.id.webView1);
        WebSettings webSettings = mWebView.getSettings();
        //对离线应用的支持
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 10);//设置缓冲大小，10M
        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mUrl);
    }


    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mWebView != null) {
            mWebView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }


    public static void startActivity(Context xContext,String titile) {
        Intent intent = new Intent(xContext, WebViewActivity.class);
        intent.putExtra(TITLE,titile);
        xContext.startActivity(intent);
    }
}
