package com.bwei.fangjingdong.presenter;

import com.bwei.fangjingdong.bean.AddCartBean;
import com.bwei.fangjingdong.bean.ProductDetailBean;
import com.bwei.fangjingdong.model.ProductDetailModel;
import com.bwei.fangjingdong.model.imodel.IProductDetailModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.IProductDetailActivity;

/**
 * 作者：王建勋
 * 时间：2017-12-17 19:22
 * 类的用途：
 */

public class ProductDetailPresenter {

    private final IProductDetailModel iProductDetailModel;
    private final IProductDetailActivity iProductDetailActivity;

    public ProductDetailPresenter(IProductDetailActivity iProductDetailActivity) {
        this.iProductDetailActivity = iProductDetailActivity;
        iProductDetailModel = new ProductDetailModel();
    }

    public void getDetail(String pid) {
        iProductDetailModel.getDetail(new OnNetListener<ProductDetailBean>() {
            @Override
            public void onSuccess(ProductDetailBean detailBean) {
                iProductDetailActivity.showDetail(detailBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, pid);
    }

    public void addCart(String uid, String pid) {
        iProductDetailModel.addCart(new OnNetListener<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                iProductDetailActivity.addCart(addCartBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, uid, pid);
    }
}
