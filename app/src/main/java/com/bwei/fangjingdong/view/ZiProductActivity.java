package com.bwei.fangjingdong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.adapter.ZiProductAdapter;
import com.bwei.fangjingdong.bean.ZiProductBean;
import com.bwei.fangjingdong.presenter.ZiProductPresenter;
import com.bwei.fangjingdong.view.iview.IZiProductActivity;

import java.util.List;

public class ZiProductActivity extends AppCompatActivity implements IZiProductActivity {

    private RecyclerView mZiRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_product);
        ZiProductPresenter productPresenter = new ZiProductPresenter(this);
        initView();
        //隐藏标题栏
        ActionBar bar = getSupportActionBar();
        bar.hide();
        //接收传递过来的值
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        Log.e("PID", pid);
        //调用ZiProductPresenter层里的方法
        productPresenter.getZiProduct(pid);
        //设置线性布局管理器
        mZiRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        mZiRv = (RecyclerView) findViewById(R.id.zi_rv);
    }

    @Override
    public void showZiProduct(ZiProductBean productBean) {
        List<ZiProductBean.DataBean> data = productBean.getData();
        ZiProductAdapter ziProductAdapter = new ZiProductAdapter(this, data);
        mZiRv.setAdapter(ziProductAdapter);
    }


}
