package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());

        webView.loadData("<h1>Hello</h1>","text/html","UTF-8");

        webView.loadUrl("https://www.google.com");

        webView.loadUrl("file:///android_asset/index.html");

        webView.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if(webView!=null && webView.canGoBack())
        {
            webView.goBack();
            return;
        }

        super.onBackPressed();
    }

    public void handleClick(View v)
    {
        webView.loadUrl("https://"+((EditText)findViewById(R.id.url)).getText().toString());
    }
}