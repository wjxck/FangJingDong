package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.LoginBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-09 15:55
 * 类的用途：
 */

public interface ILoginModel {
    public void getLogin(String mobile,String password,OnNetListener<LoginBean> onNetListener);
}
