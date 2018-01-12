package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.OrderBean;

import java.util.List;

/**
 * Created by dell on 2018-01-12  11:35
 */

public class OrderElvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<OrderBean.DataBean> data;
    private LayoutInflater inflater;

    public OrderElvAdapter(Context context, List<OrderBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_elv_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.price.setText("￥" + data.get(position).getPrice());
        if (data.get(position).getStatus() == 0) {
            myViewHolder.state.setTextColor(Color.RED);
            myViewHolder.state.setText("待支付");
        } else if (data.get(position).getStatus() == 1) {
            myViewHolder.state.setTextColor(Color.BLACK);
            myViewHolder.state.setText("已支付");
        } else if (data.get(position).getStatus() == 2) {
            myViewHolder.state.setTextColor(Color.BLACK);
            myViewHolder.state.setText("已取消");
        } else {

        }
        myViewHolder.listName.setText(data.get(position).getTitle());
        myViewHolder.editTime.setText("创建时间:" + data.get(position).getCreatetime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView listName;
        TextView state;
        TextView price;
        TextView editTime;
        Button btnCancle;

        public MyViewHolder(View itemView) {
            super(itemView);
            listName = itemView.findViewById(R.id.listName);
            state = itemView.findViewById(R.id.state);
            price = itemView.findViewById(R.id.price);
            editTime = itemView.findViewById(R.id.editTime);
            btnCancle = itemView.findViewById(R.id.btnCancle);
        }


    }
}
