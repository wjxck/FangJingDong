package com.bwei.fangjingdong.presenter;

import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.bean.ProductCatagoryBean;
import com.bwei.fangjingdong.model.CatagroyModel;
import com.bwei.fangjingdong.model.imodel.ICatagroyModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.IClassView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-13 09:18
 * 类的用途：
 */

public class CatagroyPresenter {

    private final ICatagroyModel iCatagroyModel;
    private final IClassView iClassView;

    public CatagroyPresenter(IClassView iClassView) {
        this.iClassView = iClassView;
        iCatagroyModel = new CatagroyModel();
    }

    public void getCatagroy() {
        iCatagroyModel.getCatagroy(new OnNetListener<CatagoryBean>() {
            @Override
            public void onSuccess(CatagoryBean catagoryBean) {
                iClassView.showData(catagoryBean.getData());
                //拿到右侧第一条数据
                List<CatagoryBean.DataBean> data = catagoryBean.getData();
                int cid = data.get(0).getCid();
                //获取右侧的数据
                getProductCatagory(cid + "");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getProductCatagory(String cid) {
        iCatagroyModel.getProductCatagory(cid + "", new OnNetListener<ProductCatagoryBean>() {


            private List<ProductCatagoryBean.DataBean.ListBean> childList;

            @Override
            public void onSuccess(ProductCatagoryBean productCatagoryBean) {
                List<ProductCatagoryBean.DataBean> groupList = productCatagoryBean.getData();
                List<List<ProductCatagoryBean.DataBean.ListBean>> childList = new ArrayList<List<ProductCatagoryBean.DataBean.ListBean>>();
                //遍历添加数据
                for (int i = 0; i < groupList.size(); i++) {
                    List<ProductCatagoryBean.DataBean.ListBean> list = groupList.get(i).getList();
                    childList.add(list);
                }
                //给二级列表设置数据
                iClassView.showElvData(groupList, childList);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
