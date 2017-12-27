package com.bwei.fangjingdong.model;

import com.bwei.fangjingdong.bean.CartBean;
import com.bwei.fangjingdong.model.imodel.ICartModel;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王建勋
 * 时间：2017-12-13 20:31
 * 类的用途：
 */

public class CartModel implements ICartModel {
    @Override
    public void getGoods(String uid, String token,final OnNetListener<CartBean> onNetListener) {
        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();
        httpUtils.getGoods(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CartBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(CartBean cartBean) {
                        onNetListener.onSuccess(cartBean);
                    }
                });
    }
}
