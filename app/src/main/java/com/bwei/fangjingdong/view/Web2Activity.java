package com.bwei.fangjingdong.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.bwei.fangjingdong.R;

public class Web2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1);
        //隐藏标题栏
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
        WebView wb = findViewById(R.id.web1);
        wb.loadUrl("https://channel.jd.com/fashion.html");
    }
}