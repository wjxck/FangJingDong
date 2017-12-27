package com.bwei.fangjingdong.view.iview;

import com.bwei.fangjingdong.bean.AddCartBean;
import com.bwei.fangjingdong.bean.ProductDetailBean;

/**
 * 作者：王建勋
 * 时间：2017-12-17 19:23
 * 类的用途：
 */

public interface IProductDetailActivity {
    public void showDetail(ProductDetailBean detailBean);

    public void addCart(AddCartBean addCartBean);
}
