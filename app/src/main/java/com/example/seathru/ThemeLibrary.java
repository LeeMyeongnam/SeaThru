package com.example.seathru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ThemeLibrary extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mLayoutManger;
    private ArrayList<Item> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_library);

        recyclerView = findViewById(R.id.recycleview);

        recyclerView.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManger);

        mList = new ArrayList<>();
        madapter = new RecyclerAdapter(mList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(madapter);

        mList.add(new Item(R.mipmap.ic_launcher, "test1", "test2"));
    }
}
