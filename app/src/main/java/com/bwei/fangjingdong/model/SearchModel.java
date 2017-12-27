package com.bwei.fangjingdong.model;

import com.bwei.fangjingdong.bean.SearchBean;
import com.bwei.fangjingdong.model.imodel.ISearchModel;
import com.bwei.fangjingdong.net.HttpUtils;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.net.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王建勋
 * 时间：2017-12-16 11:31
 * 类的用途：
 */

public class SearchModel implements ISearchModel {
    @Override
    public void getSearch(final OnNetListener<SearchBean> onNetListener, String key) {
        HttpUtils httpUtils = RetrofitHelper.getHttpUtils();
        httpUtils.getSearch(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        onNetListener.onSuccess(searchBean);
                    }
                });
    }
}
