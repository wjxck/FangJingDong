package com.bwei.fangjingdong.presenter;

import com.bwei.fangjingdong.bean.SearchBean;
import com.bwei.fangjingdong.model.SearchModel;
import com.bwei.fangjingdong.model.imodel.ISearchModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.ISearchActivity;

/**
 * 作者：王建勋
 * 时间：2017-12-16 11:34
 * 类的用途：
 */

public class SearchPresenter {

    private final ISearchModel iSearchModel;
    private final ISearchActivity iSearchActivity;

    public SearchPresenter(ISearchActivity iSearchActivity) {
        this.iSearchActivity = iSearchActivity;
        iSearchModel = new SearchModel();
    }

    public void getSearch(String key) {
        iSearchModel.getSearch(new OnNetListener<SearchBean>() {
            @Override
            public void onSuccess(SearchBean searchBean) {
                iSearchActivity.show(searchBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, key);
    }
}
