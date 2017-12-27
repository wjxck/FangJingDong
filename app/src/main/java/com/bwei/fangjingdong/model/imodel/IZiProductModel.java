package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.ZiProductBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-17 19:59
 * 类的用途：
 */

public interface IZiProductModel {
    public void getZiProduct(OnNetListener<ZiProductBean> onNetListener, String pid);
}
