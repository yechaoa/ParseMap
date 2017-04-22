package com.yechaoa.parsemap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by yechao on 2017/4/21.
 * Describe :
 */

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private Map<Integer, Integer> map1;
    private final List<Integer> keyList;
    private final List<Integer> valueList;

    public GridViewAdapter(Context mContext, Map<Integer, Integer> map1) {
        super();
        this.mContext = mContext;
        this.map1 = map1;

        //把key转为一个集合
        List<Integer> list = new ArrayList<>(map1.keySet());
        //比较器
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer s1, Integer s2) {
                //升序排列 ，降序 把s1 s2调换即可
                return s1.compareTo(s2);
            }
        });

        //遍历得到key和value，添加到集合
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
        //遍历map的常用方法一
        for (Integer i : list) {
            Log.i("常用方法一", "key = " + i + " , value = " + map1.get(i));
            keyList.add(i);
            valueList.add(map1.get(i));
        }
    }

    @Override
    public int getCount() {
        return map1.size();
    }

    @Override
    public Object getItem(int position) {
        return map1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.item_gridview, parent, false);
            holder = new ViewHolder();
            holder.item_gv_textView1 = (TextView) convertView.findViewById(R.id.item_gv_textView1);
            holder.item_gv_textView2 = (TextView) convertView.findViewById(R.id.item_gv_textView2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item_gv_textView1.setText(String.valueOf("充￥" + keyList.get(position)));
        holder.item_gv_textView2.setText(String.valueOf("送￥" + valueList.get(position)));
        return convertView;
    }

    private class ViewHolder {
        TextView item_gv_textView1;
        TextView item_gv_textView2;
    }

}
