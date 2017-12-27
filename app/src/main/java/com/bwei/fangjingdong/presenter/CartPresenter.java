package com.bwei.fangjingdong.presenter;

import com.bwei.fangjingdong.bean.CartBean;
import com.bwei.fangjingdong.model.CartModel;
import com.bwei.fangjingdong.model.imodel.ICartModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.ICartView;
import com.bwei.fangjingdong.view.iview.IClassView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-13 20:35
 * 类的用途：
 */

public class CartPresenter {

    private final ICartModel iCartModel;
    private ICartView iCartView;

    public CartPresenter(ICartView iCartView) {
        this.iCartView = iCartView;
        iCartModel = new CartModel();
    }

    public void getGoods(String uid, String token) {
        iCartModel.getGoods(uid, token, new OnNetListener<CartBean>() {
            @Override
            public void onSuccess(CartBean cartBean) {
                List<CartBean.DataBean> groupList = cartBean.getData();
                List<List<CartBean.DataBean.ListBean>> childList = new ArrayList<>();
                for (int i = 0; i < groupList.size(); i++) {
                    List<CartBean.DataBean.ListBean> list = groupList.get(i).getList();
                    childList.add(list);
                }
                iCartView.showList(groupList, childList);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void detachView() {
        if (iCartView != null) {
            iCartView = null;
        }
    }
}
