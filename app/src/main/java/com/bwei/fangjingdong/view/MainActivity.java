package com.bwei.fangjingdong.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.fragemnt.CartFragment;
import com.bwei.fangjingdong.fragemnt.ClassFragment;
import com.bwei.fangjingdong.fragemnt.FindFragment;
import com.bwei.fangjingdong.fragemnt.HomeFragment;
import com.bwei.fangjingdong.fragemnt.MineFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton mRbHome;
    private RadioButton mRbClass;
    private RadioButton mRbFind;
    private RadioButton mRbCart;
    private RadioButton mRbMine;
    private HomeFragment homeFragment;
    private ClassFragment classFragment;
    private FindFragment findFragment;
    private CartFragment cartFragment;
    private MineFragment mineFragment;
    private RadioGroup mRg;
    private FrameLayout mFramelayout;
    private LinearLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //初始化组件
        initView();
        //设置默认
        shou();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        mRbHome = (RadioButton) findViewById(R.id.rb_home);
        mRbClass = (RadioButton) findViewById(R.id.rb_class);
        mRbFind = (RadioButton) findViewById(R.id.rb_find);
        mRbCart = (RadioButton) findViewById(R.id.rb_cart);
        mRbMine = (RadioButton) findViewById(R.id.rb_mine);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mFramelayout = (FrameLayout) findViewById(R.id.framelayout);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);

        mRbHome.setOnClickListener(this);
        mRbClass.setOnClickListener(this);
        mRbFind.setOnClickListener(this);
        mRbCart.setOnClickListener(this);
        mRbMine.setOnClickListener(this);
        mRg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        bai();
        switch (v.getId()) {
            case R.id.rb_home:
                shou();
                break;
            case R.id.rb_class:
                classFragment = new ClassFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, classFragment).commit();
                mRbClass.setBackgroundResource(R.drawable.abx);
                break;
            case R.id.rb_find:
                findFragment = new FindFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, findFragment).commit();
                mRbFind.setBackgroundResource(R.drawable.abz);
                break;
            case R.id.rb_cart:
                cartFragment = new CartFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, cartFragment).commit();
                mRbCart.setBackgroundResource(R.drawable.abv);
                break;
            case R.id.rb_mine:
                my();
                break;
        }
    }

    //进去展示的界面
    private void shou() {
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, homeFragment).commit();
        mRbHome.setBackgroundResource(R.drawable.ac1);
    }


    private void bai() {
        mRbHome.setBackgroundResource(R.drawable.ac0);
        mRbClass.setBackgroundResource(R.drawable.abw);
        mRbFind.setBackgroundResource(R.drawable.aby);
        mRbCart.setBackgroundResource(R.drawable.abu);
        mRbMine.setBackgroundResource(R.drawable.ac2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int flag = getIntent().getIntExtra("flag", 0);
        if (flag == 5) {
            bai();
            my();
        }
    }

    private void my() {
        mineFragment = new MineFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, mineFragment).commit();
        mRbMine.setBackgroundResource(R.drawable.ac3);
    }
}
