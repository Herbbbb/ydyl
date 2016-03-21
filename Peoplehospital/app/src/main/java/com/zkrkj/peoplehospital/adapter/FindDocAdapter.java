package com.zkrkj.peoplehospital.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zkrkj.peoplehospital.R;

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

    public FindDocAdapter(List<String> list, Context context) {
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
        if (view==null){
            view=View.inflate(context, R.layout.item_doctoryuyue,null);

        }
        return view;
    }
}
