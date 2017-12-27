package com.bwei.fangjingdong.presenter;

import com.bwei.fangjingdong.bean.ZiProductBean;
import com.bwei.fangjingdong.model.ZiProductModel;
import com.bwei.fangjingdong.model.imodel.IZiProductModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.IZiProductActivity;

/**
 * 作者：王建勋
 * 时间：2017-12-17 20:09
 * 类的用途：
 */

public class ZiProductPresenter {

    private final IZiProductModel iZiProductModel;
    private final IZiProductActivity iZiProductActivity;

    public ZiProductPresenter(IZiProductActivity iZiProductActivity) {
        this.iZiProductActivity = iZiProductActivity;
        iZiProductModel = new ZiProductModel();
    }

    public void getZiProduct(String cid) {
        iZiProductModel.getZiProduct(new OnNetListener<ZiProductBean>() {
            @Override
            public void onSuccess(ZiProductBean productBean) {
                iZiProductActivity.showZiProduct(productBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, cid);
    }
}
