package com.bwei.fangjingdong.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.bwei.fangjingdong.R;

public class ShowTuActivity extends AppCompatActivity {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //判断接受i的值 如果== 0  自动跳转
            if (msg.what == 0) {
                Intent intent = new Intent(ShowTuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    int i = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Thread() {
            public void run() {
                while (i >= 0) {
                    try {
                        //休眠1秒
                        sleep(1000);
                        i--;
                        //将i的值发送给handler
                        handler.sendEmptyMessage(i);
                        Log.i("TAG", i + "");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
