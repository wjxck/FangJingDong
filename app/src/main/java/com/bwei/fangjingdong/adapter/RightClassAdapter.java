package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.ProductCatagoryBean;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-13 14:50
 * 类的用途：
 */

public class RightClassAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ProductCatagoryBean.DataBean> groupList;//一级列表数据
    private List<List<ProductCatagoryBean.DataBean.ListBean>> childList;//二级列表数据
    private LayoutInflater inflater;

    public RightClassAdapter(Context context, List<ProductCatagoryBean.DataBean> groupList, List<List<ProductCatagoryBean.DataBean.ListBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        View view;
        if (convertView == null){
            holder = new GroupViewHolder();
            view = inflater.inflate(R.layout.class_right_item,null);
            holder.tv_title = view.findViewById(R.id.tv_title);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        ProductCatagoryBean.DataBean dataBean = groupList.get(groupPosition);
        holder.tv_title.setText(dataBean.getName());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        View view;
        if (convertView == null){
            holder = new ChildViewHolder();
            view = inflater.inflate(R.layout.class_right_item2,null);
            holder.rv = view.findViewById(R.id.rv);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        List<ProductCatagoryBean.DataBean.ListBean> listBeans = childList.get(groupPosition);
        //给RecycleView设置布局管理器
        holder.rv.setLayoutManager(new GridLayoutManager(context,3));
        //给RecycleView设置适配器
        ElvRvAdapter elvRvAdapter = new ElvRvAdapter(context, listBeans);
        holder.rv.setAdapter(elvRvAdapter);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }

    class GroupViewHolder{
        TextView tv_title;
    }
    class ChildViewHolder{
        RecyclerView rv;
    }
}
