package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.RegisterBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-11 15:23
 * 类的用途：
 */

public interface IRegisterModel {
    public void register(String account, String pwd, OnNetListener<RegisterBean> onNetListener);
}
