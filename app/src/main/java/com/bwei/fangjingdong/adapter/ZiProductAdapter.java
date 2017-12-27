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
import com.bwei.fangjingdong.bean.ZiProductBean;
import com.bwei.fangjingdong.view.ProductDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-17 20:12
 * 类的用途：
 */

public class ZiProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ZiProductBean.DataBean> dataList;

    public ZiProductAdapter(Context context, List<ZiProductBean.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ziproduct_rlv_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        final ZiProductBean.DataBean dataBean = dataList.get(position);
        //展示图片
        String[] strings = dataBean.getImages().split("\\|");
        myViewHolder.sdv.setImageURI(strings[0]);
        myViewHolder.name.setText(dataBean.getSubhead());
        myViewHolder.price.setText("￥" + dataBean.getPrice());
        myViewHolder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("pid", dataBean.getPid() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout lin;
        private final SimpleDraweeView sdv;
        private final TextView name;
        private final TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            lin = itemView.findViewById(R.id.zi_li);
            sdv = itemView.findViewById(R.id.zi_sdv);
            name = itemView.findViewById(R.id.zi_tv1);
            price = itemView.findViewById(R.id.zi_price);
        }
    }
}
