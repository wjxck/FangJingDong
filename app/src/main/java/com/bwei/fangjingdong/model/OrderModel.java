package com.bwei.fangjingdong.model;

import com.bwei.fangjingdong.bean.OrderBean;
import com.bwei.fangjingdong.model.imodel.IOrderModel;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dell on 2018-01-12  10:31
 */

public class OrderModel implements IOrderModel {
    @Override
    public void getOrder(String uid, final OnNetListener<OrderBean> onNetListener) {
        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();

        httpUtils.getOrder(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(OrderBean orderBean) {
                        onNetListener.onSuccess(orderBean);
                    }
                });
    }

}
