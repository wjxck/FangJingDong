package com.bwei.fangjingdong.view.iview;

import com.bwei.fangjingdong.bean.CartBean;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-13 20:36
 * 类的用途：
 */

public interface ICartView {
    public void showList(List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList);
}
