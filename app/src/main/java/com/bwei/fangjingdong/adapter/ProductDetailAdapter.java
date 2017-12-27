package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.ProductDetailBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-17 19:09
 * 类的用途：
 */

public class ProductDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductDetailBean.DataBean> dataList;

    public ProductDetailAdapter(Context context, List<ProductDetailBean.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productdetail_rlv_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        ProductDetailBean.DataBean dataBean = dataList.get(position);
        //设置图片
        String[] strings = dataBean.getImages().split("\\|");
        myViewHolder.sdv.setImageURI(strings[0]);
        myViewHolder.name.setText(dataBean.getSubhead());
        myViewHolder.price.setText("￥" + dataBean.getPrice());

    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdv;
        private final TextView name;
        private final TextView price;
        private final TextView pj;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.de_sdv);
            name = itemView.findViewById(R.id.de_tv1);
            price = itemView.findViewById(R.id.de_price);
            pj = itemView.findViewById(R.id.de_pj);
        }
    }
}
