package com.bwei.fangjingdong.model.imodel;

import android.content.Context;
import android.content.Intent;

import com.bwei.fangjingdong.bean.UserInfoBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-12 21:00
 * 类的用途：
 */

public interface IUserModel {
    public void getUser(String uid,OnNetListener<UserInfoBean> onNetListener);
}
