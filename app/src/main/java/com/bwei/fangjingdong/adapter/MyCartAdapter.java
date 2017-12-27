package com.bwei.fangjingdong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.CartBean;
import com.bwei.fangjingdong.eventbus.MessageEvent;
import com.bwei.fangjingdong.eventbus.PriceAndCountEvent;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-12-13 20:13
 * 类的用途：
 */

public class MyCartAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CartBean.DataBean> groupList;
    private List<List<CartBean.DataBean.ListBean>> childList;
    private LayoutInflater inflater;

    public MyCartAdapter(Context context, List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList) {
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
        return childList.get(groupPosition).size();
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//声明GroupViewHolder
        View view;
        final GroupViewHolder holder;
        //判断convertView是否为空
        if (convertView == null) {
            //初始化GroupViewHolder
            holder = new GroupViewHolder();
            //使用布局加载器加载写好的布局
            view = inflater.inflate(R.layout.item_parent_market, null);
            //加载布局中的组件
            holder.cbGroup = (CheckBox) view.findViewById(R.id.cb_parent);
            holder.tv_number = (TextView) view.findViewById(R.id.tv_number);
            //关联GroupViewHolder
            view.setTag(holder);
        } else {
            //如果convertView不为空，就取出参数
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        //给获取到的组件赋值
        final CartBean.DataBean dbGroup = groupList.get(groupPosition);
        holder.cbGroup.setChecked(dbGroup.isChecked());
        holder.tv_number.setText(dbGroup.getSellerName());

        //设置一级列表checkbox的点击事件
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置checkbox的选中状态
                dbGroup.setChecked(holder.cbGroup.isChecked());
                //当全选选中时，所有的二级列表的checkbox被选中
                changeChildCbState(groupPosition, holder.cbGroup.isChecked());
                //当全选选中时，发送数量和价格
                EventBus.getDefault().post(compute());
                //一级列表的checkbox全选
                changeAllCbState(isAllGroupCbSelected());
                //刷新列表
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //声明ChildViewHolder
        View view;
        final ChildViewHolder holder;
        //判断convertView是否为空
        if (convertView == null) {
            //初始化GroupViewHolder
            holder = new ChildViewHolder();
            //使用布局加载器加载写好的布局
            view = inflater.inflate(R.layout.item_child_market, null);
            //加载布局中的组件
            holder.cbChild = (CheckBox) view.findViewById(R.id.cb_child);
            holder.tv_tel = (TextView) view.findViewById(R.id.tv_tel);
            holder.iv_image = (SimpleDraweeView) view.findViewById(R.id.iv_image);
            holder.tv_pri = (TextView) view.findViewById(R.id.tv_pri);
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            holder.iv_del = (ImageView) view.findViewById(R.id.iv_del);
            holder.iv_add = (ImageView) view.findViewById(R.id.iv_add);
            holder.tv_number = (TextView) view.findViewById(R.id.tv_numm);
            holder.tv_del = (TextView) view.findViewById(R.id.tv_del);
            //关联GroupViewHolder
            view.setTag(holder);
        } else {
            //如果convertView不为空，就取出参数
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        //给获取到的组件赋值
        final CartBean.DataBean.ListBean lbChild = childList.get(groupPosition).get(childPosition);
        holder.cbChild.setChecked(lbChild.isChecked());
        holder.tv_tel.setText(lbChild.getTitle());
        holder.tv_content.setText(lbChild.getSubhead().substring(0,15)+"...");
        holder.tv_pri.setText("￥" + lbChild.getPrice());
        holder.tv_number.setText(lbChild.getNum() + "");
        String images = lbChild.getImages();
        String[] strings = images.split("\\|");
        holder.iv_image.setImageURI(strings[0]);

        //设置二级列表checkbox的点击事件
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置该条目对象里的checkbox的属性
                lbChild.setChecked(holder.cbChild.isChecked());
                //调用方法
                PriceAndCountEvent priceAndCountEvent = compute();
                //发送消息
                EventBus.getDefault().post(priceAndCountEvent);
                //判断二级列表checkbox的状态值
                if (holder.cbChild.isChecked()) {
                    //当前checkbox是选中状态
                    changeGroupCbState(groupPosition, true);
                    changeAllCbState(isAllGroupCbSelected());
                } else {
                    //当前checkbox是未选中状态
                    changeGroupCbState(groupPosition, false);
                    changeAllCbState(isAllGroupCbSelected());
                }
                //刷新列表
                notifyDataSetChanged();
            }
        });

        //加号
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到数量num
                int num = lbChild.getNum();
                //动态改变num的值
                holder.tv_number.setText(++num + "");
                lbChild.setNum(num);
                //判断二级列表的checkbox是否选中
                if (holder.cbChild.isChecked()) {
                    //选中的时候讲价格和数量返回
                    PriceAndCountEvent priceAndCountEvent = compute();
                    EventBus.getDefault().post(priceAndCountEvent);
                }
            }
        });

        //减号
        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到数量num
                int num = lbChild.getNum();
                if (num == 1) {
                    return;
                }
                //动态改变num的值
                holder.tv_number.setText(--num + "");
                lbChild.setNum(num);
                //判断二级列表的checkbox是否选中
                if (holder.cbChild.isChecked()) {
                    //选中的时候讲价格和数量返回
                    PriceAndCountEvent priceAndCountEvent = compute();
                    EventBus.getDefault().post(priceAndCountEvent);
                }
            }
        });

        //删除
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CartBean.DataBean.ListBean> listBeen = childList.get(groupPosition);
                CartBean.DataBean.ListBean remove = listBeen.remove(childPosition);
                if (listBeen.size() == 0) {
                    childList.remove(groupPosition);
                    groupList.remove(groupPosition);
                }
                EventBus.getDefault().post(compute());
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //一级列表的ViewHolder
    class GroupViewHolder {
        CheckBox cbGroup;
        TextView tv_number;
    }

    //二级列表的ViewHolder
    class ChildViewHolder {
        CheckBox cbChild;
        TextView tv_tel;
        SimpleDraweeView iv_image;
        TextView tv_pri;
        TextView tv_content;
        TextView tv_time;
        ImageView iv_del;
        ImageView iv_add;
        TextView tv_number;
        TextView tv_del;
    }

    /**
     * 改变全选的状态
     *
     * @param flag
     */
    private void changeAllCbState(boolean flag) {
        //创建MessageEvent
        MessageEvent messageEvent = new MessageEvent();
        //赋值
        messageEvent.setChecked(flag);
        //发送全选的状态值
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 改变一级列表checkbox的状态
     *
     * @param groupPosition
     * @param flag
     */
    private void changeGroupCbState(int groupPosition, boolean flag) {
        CartBean.DataBean dataBean = groupList.get(groupPosition);
        dataBean.setChecked(flag);
    }

    /**
     * 改变二级列表checkbox的状态
     *
     * @param groupPosition
     * @param flag
     */
    public void changeChildCbState(int groupPosition, boolean flag) {
        List<CartBean.DataBean.ListBean> listBeans = childList.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            CartBean.DataBean.ListBean listBean = listBeans.get(i);
            listBean.setChecked(flag);
        }
    }

    /**
     * 判断一级列表checkbox是否全部选中
     *
     * @return
     */
    public boolean isAllGroupCbSelected() {
        for (int i = 0; i < groupList.size(); i++) {
            CartBean.DataBean dataBean = groupList.get(i);
            if (!dataBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二级列表checkbox是否全部选中
     *
     * @param groupPosition
     * @return
     */
    private boolean isAllChildCbSelected(int groupPosition) {
        List<CartBean.DataBean.ListBean> childLb = childList.get(groupPosition);
        for (int i = 0; i < childLb.size(); i++) {
            CartBean.DataBean.ListBean listBean = childLb.get(i);
            if (!listBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算列表中，选中的钱和数量
     */
    private PriceAndCountEvent compute() {
        //设置变量
        int count = 0;
        float price = 0;
        //遍历
        for (int i = 0; i < childList.size(); i++) {
            List<CartBean.DataBean.ListBean> lb = childList.get(i);
            for (int j = 0; j < lb.size(); j++) {
                CartBean.DataBean.ListBean listBean = lb.get(j);
                if (listBean.isChecked()) {
                    //给变量设置数据
                    Float aFloat = Float.valueOf(listBean.getPrice());
                    price += aFloat * listBean.getNum();
                    count += listBean.getNum();
                }
            }

        }

        //创建PriceAndCountEvent
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        //给PriceAndCountEvent添加数据
        priceAndCountEvent.setCount(count);
        priceAndCountEvent.setPrice(price);
        //返回PriceAndCountEvent
        return priceAndCountEvent;
    }

    /**
     * 设置全选、反选
     *
     * @param flag
     */
    public void changeAllListCbState(boolean flag) {
        //遍历所有的checkbox
        for (int i = 0; i < groupList.size(); i++) {
            //调用方法
            changeGroupCbState(i, flag);
            changeChildCbState(i, flag);
        }
        //发送价格和数量
        EventBus.getDefault().post(compute());
        //刷新列表
        notifyDataSetChanged();
    }
}
