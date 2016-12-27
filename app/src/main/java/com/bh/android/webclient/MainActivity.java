package com.bh.android.webclient;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView = null;
    private Context mContext = null;

    private ImageView mButtonLoad = null;
    private ImageView mButtonBack = null;
    private EditText mUrlInputField = null;

    private static final String TAG = "BH_MainActivity";
    private static final boolean DBG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        CookieManager.getInstance().setAcceptCookie(true);

        // Retrieve URL
        mUrlInputField = (EditText) findViewById(R.id.textUrl);

        mButtonLoad = (ImageView) findViewById(R.id.btnLoadUrl);
        mButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = mUrlInputField.getText().toString();
                if (inputString == null || inputString.trim().length() == 0) {
                    return;
                }

                if (inputString.startsWith("http://") ||
                        inputString.startsWith("https://") ||
                        inputString.startsWith("javascript:")) {
                    mWebView.loadUrl(inputString);
                } else {
                    mWebView.loadData(inputString, "text/html; charset=UTF-8;", null);
                    //mWebView = new WebView(mContext);
                    // New solution
                    //mWebView.loadDataWithBaseURL("http://localhost/", inputString, "text/html", "utf-8", null);

                }
            }
        });

        mButtonBack = (ImageView) findViewById(R.id.btnBack);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("javascript:history.back()");
            }
        });

        mWebView = (WebView) findViewById(R.id.webview);

        //20161228@BH_Lin: Method to debug the WebView.
        // Reference: https://developers.google.com/web/tools/chrome-devtools/remote-debugging/webviews
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.setWebContentsDebuggingEnabled(true);
        }

        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.loadUrl("https://binghuan.github.io/QandACard/index2oin1.html");
    }

}
