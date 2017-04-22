package com.yechaoa.parsemap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yechao on 2017/4/21.
 * Describe :
 */

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private HashMap<Integer, List<String>> map2;
    private List<Integer> keyList;
    private List<List<String>> valueList;

    public ListViewAdapter(Context mContext, HashMap<Integer, List<String>> map2) {
        super();
        this.mContext = mContext;
        this.map2 = map2;

        //遍历得到key和value，添加到集合
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
        //遍历map的常用方法二
        for (Map.Entry<Integer, List<String>> entry : map2.entrySet()) {
            Log.i("常用方法二", "key = " + entry.getKey() + " , value = " + entry.getValue());
            keyList.add(entry.getKey());
            valueList.add(entry.getValue());
        }
    }

    @Override
    public int getCount() {
        return map2.size();
    }

    @Override
    public Object getItem(int position) {
        return map2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.item_listview, parent, false);
            holder = new ViewHolder();
            holder.itemLvTitle = (TextView) convertView.findViewById(R.id.item_lv_title);
            holder.itemLvListView = (MyListView) convertView.findViewById(R.id.item_lv_listView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemLvTitle.setText(String.valueOf(keyList.get(position)));
        //再把value集合传给嵌套的ListViewSubAdapter
        holder.itemLvListView.setAdapter(new ListViewSubAdapter(mContext, valueList.get(position)));
        return convertView;
    }

    private class ViewHolder {
        TextView itemLvTitle;
        MyListView itemLvListView;
    }

}
