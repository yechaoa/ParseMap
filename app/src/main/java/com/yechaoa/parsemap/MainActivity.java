package com.yechaoa.parsemap;

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
    private HashMap<Integer, Integer> map1;
    private HashMap<Integer, List<String>> map2;

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

        map1 = new HashMap<>();
        map1.put(100, 10);
        map1.put(300, 30);
        map1.put(200, 20);
        map1.put(400, 40);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(" ParseMap " + i);
        }
        map2 = new HashMap<>();
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
