package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.HomeAdBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stone.card.library.CardAdapter;

import java.util.List;

public class MyAdapter extends CardAdapter {
    Context context;
    List<HomeAdBean.TuijianBean.ListBean> tjlist;

    public MyAdapter(Context context, List<HomeAdBean.TuijianBean.ListBean> tjlist) {
        this.context = context;
        this.tjlist = tjlist;
        Log.i("000", "onNext()" + tjlist);
    }

    @Override
    public int getLayoutId() {
        return R.layout.card_item;
    }

    @Override
    public int getCount() {
        return tjlist.size();
    }

    @Override
    public void bindView(View view, int index) {
        Object tag = view.getTag();
        ViewHolder viewHolder;
        if (null != tag) {
            viewHolder = (ViewHolder) tag;
        } else {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder.bindData(index,tjlist);
    }

    @Override
    public Object getItem(int index) {
        return tjlist.get(index);
    }
    @Override
    public Rect obtainDraggableArea(View view) {
        // 可滑动区域定制，该函数只会调用一次
        View contentView = view.findViewById(R.id.card_item_content);
        View topLayout = view.findViewById(R.id.card_top_layout);
        View bottomLayout = view.findViewById(R.id.card_bottom_layout);
        int left = view.getLeft() + contentView.getPaddingLeft() + topLayout.getPaddingLeft();
        int right = view.getRight() - contentView.getPaddingRight() - topLayout.getPaddingRight();
        int top = view.getTop() + contentView.getPaddingTop() + topLayout.getPaddingTop();
        int bottom = view.getBottom() - contentView.getPaddingBottom() - bottomLayout.getPaddingBottom();
        return new Rect(left, top, right, bottom);
    }

    class ViewHolder {

        SimpleDraweeView imageView;
        View maskView;
        TextView userNameTv;
        TextView imageNumTv;
        TextView likeNumTv;

        public ViewHolder(View view) {
            imageView = (SimpleDraweeView) view.findViewById(R.id.card_image_view);
            maskView = view.findViewById(R.id.maskView);
            userNameTv = (TextView) view.findViewById(R.id.card_user_name);
            imageNumTv = (TextView) view.findViewById(R.id.card_pic_num);
            likeNumTv = (TextView) view.findViewById(R.id.card_like);
        }

        public void bindData(int index,List<HomeAdBean.TuijianBean.ListBean> tjlist) {
            imageView.setImageURI(tjlist.get(index).getImages());
            userNameTv.setText(tjlist.get(index).getTitle());
            Log.i("2222", "onNext()" + tjlist);
        }

    }
}