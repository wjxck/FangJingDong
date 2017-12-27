package com.bwei.fangjingdong.model;

import com.bwei.fangjingdong.bean.UserInfoBean;
import com.bwei.fangjingdong.model.imodel.IUserModel;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王建勋
 * 时间：2017-12-12 20:59
 * 类的用途：
 */

public class UserModel implements IUserModel {

    @Override
    public void getUser(String uid, final OnNetListener<UserInfoBean> onNetListener) {
        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();
        httpUtils.getUserInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        onNetListener.onSuccess(userInfoBean);
                    }
                });
    }
}
