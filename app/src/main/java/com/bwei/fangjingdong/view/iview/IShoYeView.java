package com.bwei.fangjingdong.view.iview;

import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.bean.HomeAdBean;

/**
 * 作者：王建勋
 * 时间：2017-12-15 14:52
 * 类的用途：
 */

public interface IShoYeView {
    //首页轮播图
    public void showData(HomeAdBean homeAdBean);

    //首页分类
    public void showCatagroy(CatagoryBean catagoryBean);
}
