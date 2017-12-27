package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-16 08:51
 * 类的用途：
 */

public interface IShouYeClassModel {
    public void getCatagroy(OnNetListener<CatagoryBean> onNetListener);
}
