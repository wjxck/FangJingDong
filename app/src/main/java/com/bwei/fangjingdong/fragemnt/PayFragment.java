package com.bwei.fangjingdong.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.adapter.OrderElvAdapter;
import com.bwei.fangjingdong.bean.OrderBean;
import com.bwei.fangjingdong.presenter.OrderPresenter;
import com.bwei.fangjingdong.utils.MyApp;
import com.bwei.fangjingdong.view.iview.IOrderView;

import java.util.List;

/**
 * Created by dell on 2018-01-12  10:25
 */

public class PayFragment extends Fragment implements IOrderView {
    private View view;
    private RecyclerView mRecyclerView;
    private OrderPresenter orderPresenter;
    private String urlTitle;
    private OrderElvAdapter orderElvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_pay, null);
        initView(view);
        Bundle bundle = getArguments();
        //urlTitle = bundle.getString("url").toString();
        int uid = MyApp.sp.getInt("uid", 0);
        orderPresenter = new OrderPresenter(this);
        orderPresenter.getOrder(uid + "");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    @Override
    public void getOrder(OrderBean orderBean) {
        List<OrderBean.DataBean> data = orderBean.getData();
        orderElvAdapter = new OrderElvAdapter(getActivity(), data);
        mRecyclerView.setAdapter(orderElvAdapter);
    }

}
