package com.bwei.fangjingdong.model.imodel;

import com.bwei.fangjingdong.bean.SearchBean;
import com.bwei.fangjingdong.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-12-16 11:31
 * 类的用途：
 */

public interface ISearchModel {
    public void getSearch(OnNetListener<SearchBean> onNetListener, String key);
}
