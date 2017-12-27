package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.ProductCatagoryBean;
import com.bwei.fangjingdong.view.ZiProductActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-13 15:06
 * 类的用途：
 */

public class ElvRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductCatagoryBean.DataBean.ListBean> listBeans;

    public ElvRvAdapter(Context context, List<ProductCatagoryBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.class_right_elv_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        ProductCatagoryBean.DataBean.ListBean listBean = listBeans.get(position);
        myViewHolder.iv.setImageURI(listBean.getIcon());
        myViewHolder.tv.setText(listBean.getName());
        myViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到子分类的商品列表
                Intent intent = new Intent(context, ZiProductActivity.class);
                intent.putExtra("pid", listBeans.get(position).getPcid() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
