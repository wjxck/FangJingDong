package com.bwei.fangjingdong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.adapter.ProductDetailAdapter;
import com.bwei.fangjingdong.bean.AddCartBean;
import com.bwei.fangjingdong.bean.ProductDetailBean;
import com.bwei.fangjingdong.presenter.ProductDetailPresenter;
import com.bwei.fangjingdong.utils.MyApp;
import com.bwei.fangjingdong.view.iview.IProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements IProductDetailActivity {

    private RecyclerView mDeRv;
    private List<ProductDetailBean.DataBean> list;
    /**
     * 添加到购物车
     */
    private Button mBtAddcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ProductDetailPresenter detailPresenter = new ProductDetailPresenter(this);
        initView();
        //隐藏标题栏
        ActionBar bar = getSupportActionBar();
        bar.hide();

        list = new ArrayList<>();
        //获取Intent传递过来的值
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        Log.e("PID", pid + "");
        //调用ProductDetailPresenter层的方法
        detailPresenter.getDetail(pid);

        //设置线性布局管理器
        mDeRv.setLayoutManager(new LinearLayoutManager(this));
        int uid = MyApp.sp.getInt("uid", 0);
        detailPresenter.addCart(uid+"",pid);

    }

    /**
     * 初始化组件
     */
    private void initView() {
        mDeRv = (RecyclerView) findViewById(R.id.de_rv);
        mBtAddcart = (Button) findViewById(R.id.bt_addcart);
    }

    @Override
    public void showDetail(ProductDetailBean detailBean) {
        ProductDetailBean.DataBean dataBean = detailBean.getData();
        list.add(dataBean);
        //创建适配器
        ProductDetailAdapter detailAdapter = new ProductDetailAdapter(this, list);
        mDeRv.setAdapter(detailAdapter);
    }

    @Override
    public void addCart(final AddCartBean addCartBean) {
        final String code = addCartBean.getCode();
        mBtAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code.equals("0")) {
                    Toast.makeText(ProductDetailActivity.this, addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductDetailActivity.this, addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
