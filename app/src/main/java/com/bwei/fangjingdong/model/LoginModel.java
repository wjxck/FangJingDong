package com.bwei.fangjingdong.model;

import com.bwei.fangjingdong.bean.LoginBean;
import com.bwei.fangjingdong.model.imodel.ILoginModel;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王建勋
 * 时间：2017-12-09 15:54
 * 类的用途：
 */

public class LoginModel implements ILoginModel{

    @Override
    public void getLogin(String mobile, String password, final OnNetListener<LoginBean> onNetListener) {
        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();
        httpUtils.getLogin(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        onNetListener.onSuccess(loginBean);
                    }
                });
    }
}
