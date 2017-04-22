# ParseMap
简析Map及Map集合的遍历解析、排序

<div id="article_content" class="article_content">

<p><span style="font-size:18px">先上效果图：</span></p>
<p><span style="font-size:18px">&nbsp;<img src="http://img.blog.csdn.net/20170422145146044?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWVjaGFvYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" alt="">&nbsp;&nbsp;&nbsp;&nbsp;<img src="http://img.blog.csdn.net/20170422145156247?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWVjaGFvYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" alt=""></span></p>
<p><span style="font-size:18px">（效果图中的数据皆来自Map）</span></p>
<p><span style="font-size:18px">扯点犊子：</span></p>
<p><span style="font-size:18px">其实<a href="http://lib.csdn.net/base/android" class="replace_word" title="Android知识库" target="_blank" style="color:#df3434; font-weight:bold;">Android</a>对<a href="http://lib.csdn.net/base/javase" class="replace_word" title="Java SE知识库" target="_blank" style="color:#df3434; font-weight:bold;">Java</a>基础的要求并不高，虽然Android是基于Java的，但是Android有更多它自己的东西。</span></p>
<p><span style="font-size:18px">但是对于Java基础还是需要充分的了解，这会对你的开发事半功倍，就像英语不好的开发者一样可以看懂代码，但是英语好的开发者效率一定比英语不好的高。</span></p>
<p><span style="font-size:18px"><br>
</span></p>
<p><span style="font-size:18px">一、Map</span></p>
<p><span style="font-size:18px">Map是以键值对的方式进行数据读写且无序，底层是通过key的hashCode计算hash值，根据hash值得到位置索引， &nbsp;然后对该位置索引进行读写。</span></p>
<p><span style="font-size:18px"><img src="http://img.blog.csdn.net/20170422154002189?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWVjaGFvYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" alt=""><br>
</span></p>
<p><span style="font-size:18px"><img src="http://img.blog.csdn.net/20170422154014986?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWVjaGFvYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" alt=""><br>
</span></p>
<p><span style="font-size:18px"><br>
</span></p>
<p><span style="font-size:18px">HashMap</span></p>
<p><span style="font-size:18px">HashMap是基于哈希表的Map接口的非同步实现。此实现提供所有可选的映射操作，并允许使用null值和null键。<br>
</span></p>
<p><span style="font-size:18px">HashMap底层就是一个数组结构，数组中的每一项又是一个链表。<br>
</span></p>
<p><span style="font-size:18px"><img src="http://img.blog.csdn.net/20170422154657853?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWVjaGFvYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" alt=""><br>
</span></p>
<p><span style="font-size:18px"><img src="http://img.blog.csdn.net/20170422154708837?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWVjaGFvYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" alt=""><br>
</span></p>
<p><span style="font-size:18px"><br>
</span></p>
<p><span style="font-size:18px">二、实战</span></p>
<p><span style="font-size:18px">1.数据源</span></p>
<pre code_snippet_id="2349499" snippet_file_name="blog_20170422_1_4975526" name="code" class="java" style="display: none;"><span style="white-space:pre">	</span>//添加数据

        map1 = new HashMap&lt;&gt;();
        map1.put(100, 10);
        map1.put(300, 30);
        map1.put(200, 20);
        map1.put(400, 40);

        ArrayList&lt;String&gt; list = new ArrayList&lt;&gt;();
        for (int i = 0; i &lt; 2; i++) {
            list.add(" ParseMap " + i);
        }
        map2 = new HashMap&lt;&gt;();
        map2.put(100, list);
        map2.put(200, list);
        map2.put(300, list);</pre><br>
<p></p>
<p><span style="font-size:18px">2.遍历并排序</span></p>
<p><span style="font-size:18px">a）效果图一。</span></p>
<pre code_snippet_id="2349499" snippet_file_name="blog_20170422_2_2991442" name="code" class="java" style="display: none;"><span style="white-space:pre">	</span>//把key转为一个集合
        List&lt;Integer&gt; list = new ArrayList&lt;&gt;(map1.keySet());
        //比较器
        Collections.sort(list, new Comparator&lt;Integer&gt;() {
            @Override
            public int compare(Integer s1, Integer s2) {
                //升序排列 ，降序 把s1 s2调换即可
                return s1.compareTo(s2);
            }
        });

        //遍历得到key和value，添加到集合
        keyList = new ArrayList&lt;&gt;();
        valueList = new ArrayList&lt;&gt;();
        //遍历map的常用方法一
        for (Integer i : list) {
            Log.i("常用方法一", "key = " + i + " , value = " + map1.get(i));
            keyList.add(i);
            valueList.add(map1.get(i));
        }</pre>b）效果图二。<p></p>
<p><span style="font-size:18px">第二种方法没有排序，效果图二中是300、200、100，而数据源添加的顺序是100、200、300，但是显示的效果却是相反的，这是因为Entry是从前向后添加的，也就是最后添加的数据在数组的最前面。</span></p>
<pre code_snippet_id="2349499" snippet_file_name="blog_20170422_3_9545095" name="code" class="java" style="display: none;"><span style="white-space:pre">	</span>//遍历得到key和value，添加到集合
        keyList = new ArrayList&lt;&gt;();
        valueList = new ArrayList&lt;&gt;();
        //遍历map的常用方法二
        for (Map.Entry&lt;Integer, List&lt;String&gt;&gt; entry : map2.entrySet()) {
            Log.i("常用方法二", "key = " + entry.getKey() + " , value = " + entry.getValue());
            keyList.add(entry.getKey());
            valueList.add(entry.getValue());
        }</pre><p></p>
<p><span style="font-size:18px"><br>
</span></p>
<p><span style="font-size:18px">三、代码</span></p>
* activity_main.xml
<pre code_snippet_id="2349499" snippet_file_name="blog_20170422_4_1323002" name="code" class="java" style="display: none;">&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.yechaoa.parsemap.MainActivity"&gt;

    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"&gt;

        &lt;Button
            android:id="@+id/button1"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="解析map并排序" /&gt;

        &lt;Button
            android:id="@+id/button2"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="解析嵌套map" /&gt;

    &lt;/LinearLayout&gt;

    &lt;GridView
        android:id="@+id/gridView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:columnWidth="90dp"
        android:horizontalSpacing="10dp"
        android:numColumns="2"
        android:scrollbars="none"
        android:stretchMode="columnWidth"
        android:verticalSpacing="10dp"
        android:visibility="gone" /&gt;

    &lt;ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="10dp"
        android:visibility="gone" /&gt;

&lt;/LinearLayout&gt;
</pre><p></p>
* MainActivity.java
<pre code_snippet_id="2349499" snippet_file_name="blog_20170422_5_4529368" name="code" class="java" style="display: none;">package com.yechaoa.parsemap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private GridView gridView;
    private ListView listView;
    private HashMap&lt;Integer, Integer&gt; map1;
    private HashMap&lt;Integer, List&lt;String&gt;&gt; map2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        gridView = (GridView) findViewById(R.id.gridView);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void initData() {

        //添加数据

        map1 = new HashMap&lt;&gt;();
        map1.put(100, 10);
        map1.put(300, 30);
        map1.put(200, 20);
        map1.put(400, 40);

        ArrayList&lt;String&gt; list = new ArrayList&lt;&gt;();
        for (int i = 0; i &lt; 2; i++) {
            list.add(" ParseMap " + i);
        }
        map2 = new HashMap&lt;&gt;();
        map2.put(100, list);
        map2.put(200, list);
        map2.put(300, list);

    }

    private void initListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //为演示效果直接控制样式
            case R.id.button1:
                button2.setTextColor(Color.BLACK);
                button1.setTextColor(Color.RED);
                listView.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                gridView.setAdapter(new GridViewAdapter(this, map1));
                break;
            case R.id.button2:
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.RED);
                gridView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                listView.setAdapter(new ListViewAdapter(this, map2));
                break;
        }
    }

}
</pre><br>
* GridViewAdapter.java<br>
<pre code_snippet_id="2349499" snippet_file_name="blog_20170422_6_9687581" name="code" class="java" style="display: none;">package com.yechaoa.parsemap;

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
    private Map&lt;Integer, Integer&gt; map1;
    private final List&lt;Integer&gt; keyList;
    private final List&lt;Integer&gt; valueList;

    public GridViewAdapter(Context mContext, Map&lt;Integer, Integer&gt; map1) {
        super();
        this.mContext = mContext;
        this.map1 = map1;

        //把key转为一个集合
        List&lt;Integer&gt; list = new ArrayList&lt;&gt;(map1.keySet());
        //比较器
        Collections.sort(list, new Comparator&lt;Integer&gt;() {
            @Override
            public int compare(Integer s1, Integer s2) {
                //升序排列 ，降序 把s1 s2调换即可
                return s1.compareTo(s2);
            }
        });

        //遍历得到key和value，添加到集合
        keyList = new ArrayList&lt;&gt;();
        valueList = new ArrayList&lt;&gt;();
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
</pre><br>
* ListViewAdapter.java<p></p>
<pre code_snippet_id="2349499" snippet_file_name="blog_20170422_7_5717847" name="code" class="java" style="display: none;">package com.yechaoa.parsemap;

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
    private HashMap&lt;Integer, List&lt;String&gt;&gt; map2;
    private List&lt;Integer&gt; keyList;
    private List&lt;List&lt;String&gt;&gt; valueList;

    public ListViewAdapter(Context mContext, HashMap&lt;Integer, List&lt;String&gt;&gt; map2) {
        super();
        this.mContext = mContext;
        this.map2 = map2;

        //遍历得到key和value，添加到集合
        keyList = new ArrayList&lt;&gt;();
        valueList = new ArrayList&lt;&gt;();
        //遍历map的常用方法二
        for (Map.Entry&lt;Integer, List&lt;String&gt;&gt; entry : map2.entrySet()) {
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
</pre><br>
<br>
<p></p>
<p><span style="font-size:18px"><br>
</span></p>
   
</div>
