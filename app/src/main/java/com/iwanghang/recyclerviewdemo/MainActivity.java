package com.iwanghang.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * RecyclerView 可以简单的理解为ListView
 */
public class MainActivity extends AppCompatActivity implements MyAdapter.OnMyItemClickListener {

    RecyclerView recycler;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            //list.add(String.format(Locale.CHINA,"第%03d条数据",i));
            list.add(String.format(Locale.CHINA,"第%03d条数据%s", i, i % 2 == 0 ? "" : "" +
                    "据据据据据据据据据据据据据据据据据据据据据据据据据据据据据"));
        }
        adapter = new MyAdapter(this,list);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(staggeredGridLayoutManager);
        recycler.setAdapter(adapter);
        adapter.setOnMyItemClickListener(this);
    }

    @Override
    public void onMyItemClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        adapter.remove(position); // 删除数据
    }
}
