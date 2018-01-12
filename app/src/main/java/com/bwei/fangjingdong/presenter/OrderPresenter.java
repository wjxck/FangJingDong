package com.bwei.fangjingdong.presenter;

import com.bwei.fangjingdong.bean.OrderBean;
import com.bwei.fangjingdong.model.OrderModel;
import com.bwei.fangjingdong.model.imodel.IOrderModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.IOrderView;

/**
 * Created by dell on 2018-01-12  10:35
 */

public class OrderPresenter {

    private final IOrderModel iOrderModel;
    private final IOrderView iOrderView;

    public OrderPresenter(IOrderView iOrderView) {
        this.iOrderView = iOrderView;
        iOrderModel = new OrderModel();
    }

    public void getOrder(String uid) {
        iOrderModel.getOrder(uid, new OnNetListener<OrderBean>() {
            @Override
            public void onSuccess(OrderBean orderBean) {
                iOrderView.getOrder(orderBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
