package com.yechaoa.parsemap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yechao on 2017/4/21.
 * Describe :
 */

public class ListViewSubAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> subList;

    public ListViewSubAdapter(Context mContext, List<String> subList) {
        super();
        this.mContext = mContext;
        this.subList = subList;
    }

    @Override
    public int getCount() {
        return subList.size();
    }

    @Override
    public Object getItem(int position) {
        return subList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.item_listview_sub, null, false);
            holder = new ViewHolder();
            holder.subItemLvTitle = (TextView) convertView.findViewById(R.id.sub_item_lv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.subItemLvTitle.setText(String.valueOf(subList.get(position)));
        return convertView;
    }

    private class ViewHolder {
        TextView subItemLvTitle;
    }

}
