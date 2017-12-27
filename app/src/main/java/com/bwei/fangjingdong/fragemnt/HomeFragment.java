package com.bwei.fangjingdong.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.adapter.ReMenRlvAdapter;
import com.bwei.fangjingdong.adapter.TuiJianRlvAdapter;
import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.bean.HomeAdBean;
import com.bwei.fangjingdong.presenter.ShouYePresenter;
import com.bwei.fangjingdong.utils.GlideImageLoader;
import com.bwei.fangjingdong.view.SearActivity;
import com.bwei.fangjingdong.view.Web2Activity;
import com.bwei.fangjingdong.view.WebActivity;
import com.bwei.fangjingdong.view.iview.IShoYeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jwsd.libzxing.OnQRCodeScanCallback;
import com.jwsd.libzxing.QRCodeManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-05 09:55
 * 类的用途：
 */

public class HomeFragment extends Fragment implements IShoYeView, View.OnClickListener {
    private View view;
    private Banner mSybanner;
    private List<String> images = new ArrayList<>();
    private ShouYePresenter shouYePresenter;
    private ImageView mIvSao;
    private TextView mTvTime;
    private RecyclerView mHdRlvs;
    private XRecyclerView mTjXRlv;
    private ImageView mIvMsg;
    private ImageView mTitSou;
    private ViewFlipper vf;
    private ViewPager mVp;
    private TuiJianRlvAdapter tuiJianRlvAdapter;
    private List<HomeAdBean.TuijianBean.ListBean> list1 = new ArrayList<>();

    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;
    String hourStr;
    String minuteStr;
    String secondStr;
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                if (mHour < 10) {
                    hourStr = "0" + mHour + "";
                } else {
                    hourStr = "0" + mHour + "";
                }
                if (mMin < 10) {
                    minuteStr = mMin + "";
                } else {
                    minuteStr = mMin + "";
                }
                if (mSecond < 10) {
                    secondStr = "0" + mSecond + "";
                } else {
                    secondStr = mSecond + "";
                }
            }
            mTvTime.setText("京东秒杀：" + hourStr + "小时" + minuteStr + "分钟" + secondStr + "秒");
        }
    };
    private List<Fragment> list;
    private ImageView mIvLlMm;
    private LinearLayout mLlMsg;
    private int pid;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        shouYePresenter = new ShouYePresenter(this);
        initView(view);
        //用ShouYePresenter调用Presenter层的方法
        shouYePresenter.getShowYe();
        shouYePresenter.getCatagroy();
        //秒杀倒计时
        startRun();
        initViewFlipper();
        return view;
    }

    private void initView(View view) {
        //Banner轮播图
        mSybanner = view.findViewById(R.id.sy_banner);
        mSybanner.setImageLoader(new GlideImageLoader());
        //二维码扫描
        mIvSao = (ImageView) view.findViewById(R.id.iv_sao);
        mIvSao.setOnClickListener(this);
        //分类
        mVp = view.findViewById(R.id.vp);
        //京东秒杀倒计时
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        //活动RecycleView
        mHdRlvs = (RecyclerView) view.findViewById(R.id.hd_rlvs);
        //推荐RecycleView
        mTjXRlv = (XRecyclerView) view.findViewById(R.id.tj_xrlv);
        mIvMsg = (ImageView) view.findViewById(R.id.iv_msg);
        vf = view.findViewById(R.id.vf);
        mTitSou = (ImageView) view.findViewById(R.id.tit_sou);
        mTitSou.setOnClickListener(this);
        mIvLlMm = (ImageView) view.findViewById(R.id.iv_ll_mm);
        mIvLlMm.setOnClickListener(this);
        mLlMsg = (LinearLayout) view.findViewById(R.id.ll_msg);
        mLlMsg.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sao:
                //扫一扫
                QRCodeManager.getInstance()
                        .with(getActivity())
                        .setReqeustType(1)//可以不设置，默认是0
                        .scanningQRCode(new OnQRCodeScanCallback() {
                            @Override
                            public void onCompleted(String result) {//扫描成功之后回调，result就是扫描的结果
                                Log.i("AA", result);
                            }

                            @Override
                            public void onError(Throwable errorMsg) {//扫描出错的时候回调
                                // controlLog.append("\n\n(错误)" + errorMsg.toString());
                            }

                            @Override
                            public void onCancel() {//取消扫描的时候回调
                                //controlLog.append("\n\n(取消)扫描任务取消了");
                            }
                        });
                break;
            case R.id.tit_sou:
                //跳转到搜索页面
                Intent intent = new Intent(getContext(), SearActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_ll_mm:
                startActivity(new Intent(getActivity(), Web2Activity.class));
                break;
            case R.id.ll_msg:
                startActivity(new Intent(getActivity(), WebActivity.class));
                break;
        }
    }


    @Override
    public void showData(HomeAdBean homeAdBean) {
        /**
         * 轮播图
         */
        List<HomeAdBean.DataBean> data = homeAdBean.getData();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            images.add(icon);
        }
        //设置Banner轮播图
        mSybanner.setImageLoader(new GlideImageLoader())//设置图片加载器
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)//设置banner样式
                .setImages(images)//设置图片集合
                .setBannerAnimation(Transformer.DepthPage)//设置banner动画效果
                .isAutoPlay(true)//设置自动轮播，默认为true
                .setDelayTime(1500)//设置轮播时间
                .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置（当banner模式中有指示器时）
                .start();//banner设置方法全部调用完毕时最后调用

        /**
         * 展示推荐
         */
        HomeAdBean.TuijianBean tuijian = homeAdBean.getTuijian();
        final List<HomeAdBean.TuijianBean.ListBean> list = tuijian.getList();

        mTjXRlv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        tuiJianRlvAdapter = new TuiJianRlvAdapter(getActivity(), list);
        mTjXRlv.setAdapter(tuiJianRlvAdapter);

        mTjXRlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                mTjXRlv.refreshComplete();
            }

            //加载更多
            @Override
            public void onLoadMore() {
                for (int i = 0; i < list.size(); i++) {
                    HomeAdBean.TuijianBean.ListBean listBean = list.get(i);
                    list1.add(listBean);
                }
                if (tuiJianRlvAdapter == null) {
                    tuiJianRlvAdapter = new TuiJianRlvAdapter(getActivity(), list1);
                } else {
                    tuiJianRlvAdapter.notifyDataSetChanged();
                }
            }
        });

        /**
         *热门活动
         */
        //创建线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置 recyclerview 布局方式为横向布局
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //给RecyClerView 添加设置好的布局样式
        mHdRlvs.setLayoutManager(layoutManager);

        ReMenRlvAdapter reMenRlvAdapter = new ReMenRlvAdapter(getActivity(), homeAdBean.getMiaosha().getList());
        mHdRlvs.setAdapter(reMenRlvAdapter);
    }

    /**
     * 首页分类
     *
     * @param catagoryBean
     */
    @Override
    public void showCatagroy(CatagoryBean catagoryBean) {
        list = new ArrayList<>();
        //添加Fragment
        list.add(new VpClassFragment1());
        list.add(new VpClassFragment2());

        mVp.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    /**
     * 京东快报
     */
    private void initViewFlipper() {
        vf.addView(View.inflate(getActivity(), R.layout.jingdong_text1, null));
        vf.addView(View.inflate(getActivity(), R.layout.jingdong_text2, null));
        vf.addView(View.inflate(getActivity(), R.layout.jingdong_text3, null));
    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //注册onActivityResult
        QRCodeManager.getInstance().with(getActivity()).onActivityResult(requestCode, resultCode, data);
    }
}
