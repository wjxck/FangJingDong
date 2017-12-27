package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.HomeAdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-16 10:14
 * 类的用途：
 */

public class ReMenRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeAdBean.MiaoshaBean.ListBeanX> beanList;

    public ReMenRlvAdapter(Context context, List<HomeAdBean.MiaoshaBean.ListBeanX> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.remen_rlv_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        HomeAdBean.MiaoshaBean.ListBeanX listBeanX = beanList.get(position);
        String[] strings = listBeanX.getImages().split("\\|");
        myViewHolder.image.setImageURI(strings[0]);
        myViewHolder.tv_name.setText("￥" + listBeanX.getPrice());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView image;
        private final TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.remen_image);
            tv_name = itemView.findViewById(R.id.tv_remen);
        }
    }
}
