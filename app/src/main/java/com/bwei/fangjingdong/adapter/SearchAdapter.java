package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.SearchBean;
import com.bwei.fangjingdong.view.ProductDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-16 11:42
 * 类的用途：
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SearchBean.DataBean> dataList;

    public SearchAdapter(Context context, List<SearchBean.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.serach_rlv_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        final SearchBean.DataBean dataBean = dataList.get(position);
        myViewHolder.sdv.setImageURI(dataBean.getImages());
        myViewHolder.tv_title.setText(dataBean.getSubhead());
        myViewHolder.tv_price.setText("￥" + dataBean.getPrice());

        myViewHolder.se_li.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("pid", dataBean.getPid() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dataList.size() == 0) {
            return 0;
        }
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdv;
        private final TextView tv_title;
        private final TextView tv_price;
        private final LinearLayout se_li;

        public MyViewHolder(View itemView) {
            super(itemView);
            se_li = itemView.findViewById(R.id.se_li);
            sdv = itemView.findViewById(R.id.se_sdv);
            tv_title = itemView.findViewById(R.id.se_tv1);
            tv_price = itemView.findViewById(R.id.se_price);

        }
    }
}
