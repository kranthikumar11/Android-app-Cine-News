package com.example.cinemanews;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent=getIntent();
        String url=intent.getStringExtra("URL");
        webView= (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        WebSettings settings=webView.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setBuiltInZoomControls(true);
        settings.setAllowContentAccess(false);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}