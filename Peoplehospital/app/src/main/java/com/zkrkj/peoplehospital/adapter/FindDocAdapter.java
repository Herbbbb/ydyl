package com.zkrkj.peoplehospital.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.findDoc.FindDocDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/3/21.
 */

public class FindDocAdapter extends BaseAdapter {
    private List<Map<String,Object>> list1=new ArrayList<>();
    List<String> list=new ArrayList<>();
    private Context context;

    public FindDocAdapter(List<Map<String,Object>> list1, Context context) {
        this.list1 = list1;
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
        if (view==null){
            view=View.inflate(context, R.layout.item_doctoryuyue,null);

        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FindDocDetail.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
    class ViewHolder{
        TextView textView1,textView2,textView3,textView4;
        Button button1;
        ImageView imageView1;
    }
}
