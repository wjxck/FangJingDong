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
import com.bwei.fangjingdong.bean.HomeAdBean;
import com.bwei.fangjingdong.view.ProductDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-15 20:11
 * 类的用途：
 */

public class TuiJianRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeAdBean.TuijianBean.ListBean> beanList;

    public TuiJianRlvAdapter(Context context, List<HomeAdBean.TuijianBean.ListBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tuijian_rlv_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final HomeAdBean.TuijianBean.ListBean listBean = beanList.get(position);

        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        String images = listBean.getImages();
        String[] strings = images.split("\\|");
        myViewHolder.sdv.setImageURI(strings[0]);

        String title = listBean.getTitle();
        String price = listBean.getPrice();
        myViewHolder.tv.setText(title + "\n" + "京东价：" + price);

        //设置点击事件
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("pid",listBean.getPid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (beanList == null){
            return 0;
        }
        return beanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdv;
        private final LinearLayout ll;
        private final TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.bt);
            ll = itemView.findViewById(R.id.lin);
            sdv = itemView.findViewById(R.id.sdv);
        }
    }

    /**
     * 自定义监听事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
