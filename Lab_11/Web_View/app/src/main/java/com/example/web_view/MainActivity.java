package com.example.web_view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri data = intent.getData();
        WebView webView = findViewById(R.id.webview1);

        if (data != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(data.toString());
        }
    }
}
