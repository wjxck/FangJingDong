package com.bwei.fangjingdong.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.adapter.MyAdapter;
import com.bwei.fangjingdong.bean.CatagoryBean;
import com.bwei.fangjingdong.bean.HomeAdBean;
import com.bwei.fangjingdong.presenter.ShouYePresenter;
import com.bwei.fangjingdong.view.iview.IShoYeView;
import com.stone.card.library.CardSlidePanel;

/**
 * 作者：王建勋
 * 时间：2017-12-05 09:55
 * 类的用途：
 */

public class FindFragment extends Fragment implements IShoYeView {
    private View view;
    private ImageView mNotifyChange;
    private CardSlidePanel mImageSlidePanel;
    private ShouYePresenter shouYePresenter;
    CardSlidePanel.CardSwitchListener cardSwitchListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_find, null);
        initView(view);

        shouYePresenter = new ShouYePresenter(this);
        shouYePresenter.getShowYe();
        return view;
    }

    private void initView(View view) {
        mNotifyChange = (ImageView) view.findViewById(R.id.notify_change);
        mImageSlidePanel = (CardSlidePanel) view.findViewById(R.id.image_slide_panel);
        mNotifyChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageSlidePanel.getAdapter().notifyDataSetChanged();
            }
        });
        // 1. 左右滑动监听
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {
            @Override
            public void onShow(int index) {
                //Toast.makeText(MainActivity.this, "正在显示"+list.get(index).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCardVanish(int index, int type) {
                //Toast.makeText(MainActivity.this, "正在消失"+list.get(index).getTitle(), Toast.LENGTH_SHORT).show();
            }
        };
        mImageSlidePanel.setCardSwitchListener(cardSwitchListener);
    }

    @Override
    public void showData(HomeAdBean homeAdBean) {
        MyAdapter adapter = new MyAdapter(getContext(), homeAdBean.getTuijian().getList());
        mImageSlidePanel.setAdapter(adapter);
    }

    @Override
    public void showCatagroy(CatagoryBean catagoryBean) {

    }


}
