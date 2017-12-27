package com.bwei.fangjingdong.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.adapter.LeftLvAdapter;
import com.bwei.fangjingdong.adapter.RightClassAdapter;
import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.bean.ProductCatagoryBean;
import com.bwei.fangjingdong.presenter.CatagroyPresenter;
import com.bwei.fangjingdong.utils.GlideImageLoader;
import com.bwei.fangjingdong.view.iview.IClassView;
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

public class ClassFragment extends Fragment implements IClassView {
    private View view;
    private ListView mLv;
    private LeftLvAdapter lvAdapter;
    private Banner mBanner;
    private ExpandableListView mElv;
    private CatagroyPresenter catagroyPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_class, null);
        catagroyPresenter = new CatagroyPresenter(this);
        initView(view);
        //去P层，调用getCatagory
        catagroyPresenter.getCatagroy();
        //给左边的条目设置点击事件
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvAdapter.press(position);
                //加载对应的右侧数据
                //先获取cid
                CatagoryBean.DataBean dataBean = (CatagoryBean.DataBean) parent.getItemAtPosition(position);
                int cid = dataBean.getCid();
                catagroyPresenter.getProductCatagory(cid + "");
            }
        });
        return view;
    }

    /**
     * 初始化组件
     *
     * @param view
     */
    private void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);
        mBanner = (Banner) view.findViewById(R.id.right_banner);
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        /**
         * 添加Banner轮播
         */
        List<String> images = new ArrayList<>();
        images.add("http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg");
        images.add("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg");
        images.add("http://pic.58pic.com/58pic/13/74/51/99d58PIC6vm_1024.jpg");
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)//设置banner样式
                .setImageLoader(new GlideImageLoader())//设置图片加载器
                .setImages(images)//设置图片集合
                .setBannerAnimation(Transformer.DepthPage)//设置banner动画效果
                .isAutoPlay(true)//设置自动轮播，默认为true
                .setDelayTime(1500)//设置轮播时间
                .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置（当banner模式中有指示器时）
                .start();//banner设置方法全部调用完毕时最后调用
    }

    /**
     * 展示左边的分类
     *
     * @param list
     */
    @Override
    public void showData(List<CatagoryBean.DataBean> list) {
        lvAdapter = new LeftLvAdapter(getActivity(), list);
        mLv.setAdapter(lvAdapter);
    }

    @Override
    public void showElvData(List<ProductCatagoryBean.DataBean> groupList, List<List<ProductCatagoryBean.DataBean.ListBean>> childList) {
        RightClassAdapter classAdapter = new RightClassAdapter(getActivity(), groupList, childList);
        mElv.setAdapter(classAdapter);
        //设置默认全部展开
        for (int i = 0; i < childList.size(); i++) {
            mElv.expandGroup(i);
        }
    }
}
