package com.bwei.fangjingdong.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.utils.MyApp;
import com.facebook.drawee.view.SimpleDraweeView;

/*
* 已登录的个人信息界面--可退出当前账号
*/
public class MyActivity extends AppCompatActivity {

    private ImageView mMyBack2;
    private SimpleDraweeView mMyXimg;
    /**
     * **
     */
    private TextView mMyNickname2;
    private LinearLayout mMyL;
    /**
     * 退出登录
     */
    private Button mMyTui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        //隐藏标题栏
        ActionBar bar = getSupportActionBar();
        bar.hide();
        initData();
    }

    private void initView() {
        mMyBack2 = (ImageView) findViewById(R.id.my_back2);
        mMyXimg = (SimpleDraweeView) findViewById(R.id.my_ximg);
        mMyNickname2 = (TextView) findViewById(R.id.my_nickname2);
        mMyL = (LinearLayout) findViewById(R.id.my_l);
        mMyTui = (Button) findViewById(R.id.my_tui);
    }

    private void initData() {
        //返回
        mMyBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //退出登录
        mMyTui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
                //    设置Title的图标
                builder.setIcon(R.mipmap.ic_launcher_round);
                //    设置Title的内容
                builder.setTitle("退出操作");
                //    设置Content来显示一个信息
                builder.setMessage("确定退出登录吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.edit.putBoolean("flag", false);
                        MyApp.edit.commit();
                        finish();
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MyActivity.this, "您取消了删除" + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    设置一个NeutralButton
                builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MyActivity.this, "您忽略了该操作" + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    显示出该对话框
                builder.show();
            }
        });
    }

    public void into() {
        String nickname = MyApp.sp.getString("name", "");
        mMyNickname2.setText(nickname + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        into();
    }
}
