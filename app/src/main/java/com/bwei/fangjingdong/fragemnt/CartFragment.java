package com.bwei.fangjingdong.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.adapter.MyCartAdapter;
import com.bwei.fangjingdong.bean.CartBean;
import com.bwei.fangjingdong.eventbus.MessageEvent;
import com.bwei.fangjingdong.eventbus.PriceAndCountEvent;
import com.bwei.fangjingdong.presenter.CartPresenter;
import com.bwei.fangjingdong.utils.MyApp;
import com.bwei.fangjingdong.view.iview.ICartView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-05 09:55
 * 类的用途：
 */

public class CartFragment extends Fragment implements ICartView {
    private View view;
    private CheckBox mCheckbox2;
    /**
     * 全选
     */
    private TextView mTvQx;
    /**
     * 总价：0.0
     */
    private TextView mTvPrice;
    /**
     * 共0件商品
     */
    private TextView mTvNum;
    /**
     * 去结算
     */
    private TextView mTvJiesuan;
    private LinearLayout mActivityMain;
    private MyCartAdapter myAdapter;
    private CartPresenter cartPresenter;
    private ExpandableListView mElv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_cart, null);
        cartPresenter = new CartPresenter(this);
        initView(view);
        int uid = MyApp.sp.getInt("uid", 0);
        String token = MyApp.sp.getString("token", "");
        cartPresenter.getGoods(uid + "", token);

        //设置全选的checkbox的点击事件
        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });
        return view;
    }


    //初始化组件
    private void initView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mTvQx = (TextView) view.findViewById(R.id.tv_qx);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
        mTvJiesuan = (TextView) view.findViewById(R.id.tv_jiesuan);
        mActivityMain = (LinearLayout) view.findViewById(R.id.activity_main);

    }

    @Override
    public void showList(List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList) {
        //创建适配器
        myAdapter = new MyCartAdapter(getActivity(), groupList, childList);
        //关联，展示数据
        mElv.setAdapter(myAdapter);
        //去掉条目前面的箭头
        mElv.setGroupIndicator(null);
        //默认让其全部展开
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        if (cartPresenter != null) {
            cartPresenter.detachView();
        }
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.ischecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvPrice.setText("总价：￥" + event.getPrice());
        mTvNum.setText("共" + event.getCount() + "件商品");
    }


}
