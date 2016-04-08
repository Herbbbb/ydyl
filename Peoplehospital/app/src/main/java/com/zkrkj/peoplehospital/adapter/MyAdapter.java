package com.zkrkj.peoplehospital.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * Created by ${苗}
 * on 2016/4/7.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    List<String> list=new ArrayList<>();

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item_yuyuedoctor,null);
            holder.textView1= (TextView) view.findViewById(R.id.text1);
            holder.gridView= (GridView) view.findViewById(R.id.gridView1);
             view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView1.setText(list.get(i).toString());
        return view;
    }
    class ViewHolder{

        TextView textView1;
        GridView gridView;
    }

}
