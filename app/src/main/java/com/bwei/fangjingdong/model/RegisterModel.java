package com.bwei.fangjingdong.model;

import com.bwei.fangjingdong.bean.RegisterBean;
import com.bwei.fangjingdong.model.imodel.IRegisterModel;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王建勋
 * 时间：2017-12-11 15:18
 * 类的用途：
 */

public class RegisterModel implements IRegisterModel {

    @Override
    public void register(String account, String pwd, final OnNetListener<RegisterBean> onNetListener) {

        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();
        httpUtils.getRegister(account, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        onNetListener.onSuccess(registerBean);
                    }
                });
    }
}
