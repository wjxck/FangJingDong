package com.bwei.fangjingdong.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.bwei.fangjingdong.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        //隐藏标题栏
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
        WebView wb = findViewById(R.id.web);
        wb.loadUrl("https://search.jd.com/Search?keyword=%E5%85%A8%E7%90%83%E8%B4%AD&enc=utf-8&suggest=1.def.0.V08&wq=quanqs&pvid=5abc563a71ac4c728fdba08f6c97c908");
    }
}