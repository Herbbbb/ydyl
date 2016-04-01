package com.zkrkj.peoplehospital.User.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/3/25.
 */

public class MessageAdapter extends BaseAdapter {
    List<Map<String, Object>> list=new ArrayList<>();
    Context context;

    public MessageAdapter(List<Map<String, Object>> list, Context context) {
        this.list = list;
        this.context = context;
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
            view=View.inflate(context, R.layout.item_message,null);
            holder=new ViewHolder();

            holder.textView1= (TextView) view.findViewById(R.id.textView43);
            holder.textView2= (TextView) view.findViewById(R.id.textView44);
            holder.textView3= (TextView) view.findViewById(R.id.textView45);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

      holder.textView1.setText(list.get(i).get("context1").toString());
      holder.textView2.setText(list.get(i).get("update1").toString());
        holder.textView3.setText(list.get(i).get("messagetype").toString());

        return view;
    }
    class ViewHolder{

        TextView textView1,textView2,textView3;
    }

}
