package com.bwei.fangjingdong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.fangjingdong.R;

public class SearActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSear;
    /**
     * 搜索
     */
    private TextView mSousuo;
    private ImageView mIvLefBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sear);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
    }

    private void initView() {
        mSear = (EditText) findViewById(R.id.sear);
        mSousuo = (TextView) findViewById(R.id.sousuo);
        mSousuo.setOnClickListener(this);
        mIvLefBack = (ImageView) findViewById(R.id.iv_lef_back);
        mIvLefBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.sousuo:
                String s = mSear.getText().toString().trim();
                Intent intent = new Intent(SearActivity.this, SearchActivity.class);
                intent.putExtra("key", s);
                startActivity(intent);
                break;
            case R.id.iv_lef_back:
                this.finish();
                break;
        }
    }
}
