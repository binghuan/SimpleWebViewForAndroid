package com.bh.android.webclient;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView = null;
    private Context mContext = null;

    private Button mButtonLoad = null;
    private Button mButtonBack = null;
    private EditText mUrlInputField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        // Retrieve URL
        mUrlInputField = (EditText) findViewById(R.id.textUrl);

        mButtonLoad = (Button) findViewById(R.id.btnLoadUrl);
        mButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // https://binghuan.github.io/QandACard/index2oin1.html
                mWebView.loadUrl(mUrlInputField.getText().toString());
            }
        });

        mButtonBack = (Button) findViewById(R.id.btnBack);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("javascript:history.back()");
            }
        });

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.loadUrl("https://binghuan.github.io/QandACard/index2oin1.html");
    }

}
