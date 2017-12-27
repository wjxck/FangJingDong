package com.bwei.fangjingdong.model;

import com.bwei.fangjingdong.bean.HomeAdBean;
import com.bwei.fangjingdong.model.imodel.IShouYeModel;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王建勋
 * 时间：2017-12-15 14:45
 * 类的用途：
 */

public class ShouYeModel implements IShouYeModel {
    @Override
    public void getShowYe(final OnNetListener<HomeAdBean> onNetListener) {
        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();
        httpUtils.getAD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeAdBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(HomeAdBean homeAdBean) {
                        onNetListener.onSuccess(homeAdBean);
                    }
                });
    }
}
