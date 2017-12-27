package com.bwei.fangjingdong.model;

import com.bwei.fangjingdong.bean.ZiProductBean;
import com.bwei.fangjingdong.model.imodel.IZiProductModel;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import java.nio.channels.SelectableChannel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王建勋
 * 时间：2017-12-17 19:59
 * 类的用途：
 */

public class ZiProductModel implements IZiProductModel {

    @Override
    public void getZiProduct(final OnNetListener<ZiProductBean> onNetListener, String pid) {
        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();
        httpUtils.getZiProduct(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZiProductBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(ZiProductBean ziProductBean) {
                        onNetListener.onSuccess(ziProductBean);
                    }
                });
    }
}
