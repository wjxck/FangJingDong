package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.OrderBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * Created by dell on 2018-01-12  10:31
 */

public interface IOrderModel {
    public void getOrder(String uid,OnNetListener<OrderBean> onNetListener);
}
