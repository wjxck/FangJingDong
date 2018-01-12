package com.bwei.fangjingdong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.adapter.SearchAdapter;
import com.bwei.fangjingdong.bean.SearchBean;
import com.bwei.fangjingdong.presenter.SearchPresenter;
import com.bwei.fangjingdong.view.iview.ISearchActivity;

public class SearchActivity extends AppCompatActivity implements ISearchActivity, View.OnClickListener {

    private EditText mSearch;
    private RecyclerView mSouRv;
    private ImageView mBacks;
    private ImageView mGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchPresenter searchPresenter = new SearchPresenter(this);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        //获取传递的值
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        //调用Presenter层的方法
        searchPresenter.getSearch(key);
        mSearch.setText(key);
        mSouRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        mSearch = (EditText) findViewById(R.id.search);
        mSouRv = (RecyclerView) findViewById(R.id.sou_rv);
        mBacks = (ImageView) findViewById(R.id.backs);
        mBacks.setOnClickListener(this);
        mGrid = (ImageView) findViewById(R.id.grid);
        mGrid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        bai();
        switch (v.getId()) {
            case R.id.backs:
                finish();
                break;
            case R.id.grid:
                mGrid.setBackgroundResource(R.drawable.kind_grid);
                break;
        }
    }

    @Override
    public void show(SearchBean searchBean) {
        mSouRv.setAdapter(new SearchAdapter(this, searchBean.getData()));
    }

    public void bai() {
        mGrid.setBackgroundResource(R.drawable.kind_liner);
    }
}
