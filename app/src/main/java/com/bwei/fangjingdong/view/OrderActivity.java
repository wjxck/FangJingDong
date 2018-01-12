package com.bwei.fangjingdong.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.fragemnt.PayFragment;

public class OrderActivity extends AppCompatActivity {

    private RelativeLayout mTop;
    private TabLayout mMyTab;
    private ViewPager mViewPage;
    private String[] title = {"全部", "待支付", "已支付", "已取消"};
    private String[] urlTitle = {"9", "0", "1", "2"};
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        myAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPage.setAdapter(myAdapter);
        mMyTab.setupWithViewPager(mViewPage);
    }

    private void initView() {
        mTop = (RelativeLayout) findViewById(R.id.top);
        mMyTab = (TabLayout) findViewById(R.id.myTab);
        mViewPage = (ViewPager) findViewById(R.id.viewPage);
    }

    //内部适配器
    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {

            //创建fragment对象并返回
            Bundle bundle = new Bundle();
            //bundle.putString("url", urlTitle[position]);
            Log.i("qqqq==============   ", urlTitle[position]);
            PayFragment payFragment = new PayFragment();
            //payFragment.setArguments(bundle);
            return payFragment;
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }
}
