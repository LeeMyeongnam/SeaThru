package com.example.seathru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 이명남 on 2018-05-10.
 */

public class ListviewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Listviewitem> data =null;
    private ArrayList<Listviewitem> displaydata;
    private int layout;

    public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data){
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
        this.displaydata = new ArrayList<Listviewitem>();
        this.displaydata.addAll(data);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getStaName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }
        Listviewitem listviewitem = data.get(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.imageview);
        icon.setImageResource(listviewitem.getIcon());
        TextView guname = convertView.findViewById(R.id.textviewgugun);
        guname.setText(listviewitem.getGuName());
        TextView staname = convertView.findViewById(R.id.textviewsta);
        staname.setText(listviewitem.getStaName());

        return convertView;
    }
    public void filter(String searchText){
        data.clear();
        if(searchText.length()==0){
           data.addAll(displaydata);
        }
        else{
            for(Listviewitem item : displaydata){
                if(item.getStaName().contains(searchText)){
                    data.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
