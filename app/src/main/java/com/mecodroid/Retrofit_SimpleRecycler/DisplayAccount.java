package com.mecodroid.Retrofit_SimpleRecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DisplayAccount extends AppCompatActivity {
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_display);
        wv = findViewById(R.id.wview);
        showdata();

    }

    private void showdata() {
        Intent n = getIntent();
        if (n != null && n.hasExtra("view WebView")) {
            Item view_webView = n.getExtras().getParcelable("view WebView");
            wv.setWebViewClient(new WebViewClient());
            wv.loadUrl(view_webView.getHtmlUrl());
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            finish();
        }
    }

}
