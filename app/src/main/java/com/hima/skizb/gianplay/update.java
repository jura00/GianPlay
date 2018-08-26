package com.hima.skizb.gianplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        WebView wv = (WebView) findViewById(R.id.webView);
        wv.loadUrl(getResources().getString(R.string.update_link));
        finish();
    }
}
