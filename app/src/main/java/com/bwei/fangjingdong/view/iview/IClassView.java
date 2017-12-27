package com.bwei.fangjingdong.view.iview;

import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.bean.ProductCatagoryBean;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-13 09:18
 * 类的用途：
 */

public interface IClassView {
    public void showData(List<CatagoryBean.DataBean> list);
    public void showElvData(List<ProductCatagoryBean.DataBean> groupList,List<List<ProductCatagoryBean.DataBean.ListBean>> childList);
}
