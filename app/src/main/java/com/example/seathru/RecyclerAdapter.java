package com.example.seathru;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 이명남 on 2018-05-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    private ArrayList<Item> mList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mimageView;
        TextView nametext;
        TextView addtext;
        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mimageView = itemView.findViewById(R.id.image);
            nametext = itemView.findViewById(R.id.bookname);
            addtext=itemView.findViewById(R.id.bookprice);
        }
        public void onClick(View v){
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("name", nametext.getText().toString());
            intent.putExtra("add", addtext.getText().toString());
            v.getContext().startActivity(intent);
        }
    }

    public RecyclerAdapter(ArrayList<Item> items){
        mList = items;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mimageView.setImageResource(mList.get(position).img);
        holder.nametext.setText(mList.get(position).name);
        holder.addtext.setText(mList.get(position).add);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class Item{
    public int img;
    public String name;
    public String add;
    public Item(int img, String name, String add){
        this.img = img;
        this.name = name;
        this.add=add;

    }
}