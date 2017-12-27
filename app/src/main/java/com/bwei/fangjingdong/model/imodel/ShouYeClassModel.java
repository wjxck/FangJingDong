package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王建勋
 * 时间：2017-12-16 08:51
 * 类的用途：
 */

public class ShouYeClassModel implements IShouYeClassModel {
    @Override
    public void getCatagroy(final OnNetListener<CatagoryBean> onNetListener) {
        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();
        httpUtils.getCatagory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CatagoryBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(CatagoryBean catagoryBean) {
                        onNetListener.onSuccess(catagoryBean);
                    }
                });
    }
}
