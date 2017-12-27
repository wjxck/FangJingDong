package com.bwei.fangjingdong.presenter;

import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.bean.HomeAdBean;
import com.bwei.fangjingdong.model.ShouYeModel;
import com.bwei.fangjingdong.model.imodel.IShouYeClassModel;
import com.bwei.fangjingdong.model.imodel.IShouYeModel;
import com.bwei.fangjingdong.model.imodel.ShouYeClassModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.IShoYeView;

/**
 * 作者：王建勋
 * 时间：2017-12-15 14:54
 * 类的用途：
 */

public class ShouYePresenter {
    private static IShouYeModel iShouYeModel;
    private final IShoYeView iShoYeView;
    private final IShouYeClassModel iShouYeClassModel;

    public ShouYePresenter(IShoYeView iShoYeView) {
        this.iShoYeView = iShoYeView;
        iShouYeModel = new ShouYeModel();
        iShouYeClassModel = new ShouYeClassModel();
    }

    public void getShowYe(){
        iShouYeModel.getShowYe(new OnNetListener<HomeAdBean>() {
            @Override
            public void onSuccess(HomeAdBean homeAdBean) {
                iShoYeView.showData(homeAdBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getCatagroy(){
        iShouYeClassModel.getCatagroy(new OnNetListener<CatagoryBean>() {
            @Override
            public void onSuccess(CatagoryBean catagoryBean) {
                iShoYeView.showCatagroy(catagoryBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
