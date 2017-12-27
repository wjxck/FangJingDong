package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.CartBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-13 20:31
 * 类的用途：
 */

public interface ICartModel {
    public void getGoods(String uid, String token,OnNetListener<CartBean> onNetListener);
}
