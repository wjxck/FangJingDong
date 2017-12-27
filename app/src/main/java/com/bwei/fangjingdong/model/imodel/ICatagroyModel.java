package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.bean.ProductCatagoryBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-13 09:16
 * 类的用途：
 */

public interface ICatagroyModel {
    public void getCatagroy(OnNetListener<CatagoryBean> onNetListener);
    public void getProductCatagory(String cid, OnNetListener<ProductCatagoryBean> onNetListener);
}
