package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.AddCartBean;
import com.bwei.fangjingdong.bean.ProductDetailBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-17 19:13
 * 类的用途：
 */

public interface IProductDetailModel {
    public void getDetail(OnNetListener<ProductDetailBean> onNetListener, String pid);

    public void addCart(OnNetListener<AddCartBean> onNetListener, String uid, String pid);
}
