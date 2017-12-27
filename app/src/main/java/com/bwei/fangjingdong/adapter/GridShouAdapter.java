package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.CatagoryBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-16 09:13
 * 类的用途：
 */

public class GridShouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CatagoryBean.DataBean> dataList;

    public GridShouAdapter(Context context, List<CatagoryBean.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shouye_catagroy_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        CatagoryBean.DataBean dataBean = dataList.get(position);
        myViewHolder.sdv.setImageURI(dataBean.getIcon());
        myViewHolder.tv.setText(dataBean.getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final SimpleDraweeView sdv;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.grid_sdv);
            tv = itemView.findViewById(R.id.grid_tv);
        }
    }
}
