package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.CartBean;
import com.bwei.fangjingdong.bean.HomeAdBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-15 14:46
 * 类的用途：
 */

public interface IShouYeModel {
    public void getShowYe(OnNetListener<HomeAdBean> onNetListener);
}
