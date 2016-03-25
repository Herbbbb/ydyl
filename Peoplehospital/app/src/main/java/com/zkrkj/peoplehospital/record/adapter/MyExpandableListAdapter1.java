package com.zkrkj.peoplehospital.record.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * Created by ${苗}
 * on 2016/3/25.
 */

public class MyExpandableListAdapter1 extends BaseExpandableListAdapter {
    Context context;
    List<Map<String,Object>> list=new ArrayList<>();
    public String[] groups = { "我的好友", "新疆同学", "亲戚", "同事" };
    public String[][] children = {
            { "胡算林", "张俊峰", "王志军", "二人" },
            { "李秀婷", "蔡乔", "别高", "余音" },
            { "摊派新", "张爱明" },
            { "马超", "司道光" }
    };

    public MyExpandableListAdapter1(Context context) {
        this.context = context;

    }

    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return children[i].length;

    }

    @Override
    public Object getGroup(int i) {
        return groups[i];

    }

    @Override
    public Object getChild(int i, int i1) {
        return children[i][i1];

    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;

    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder=new ViewHolder();
        if (view==null){
            view=View.inflate(context, R.layout.item_yiliaofeiyong1,null);
            holder.textView1= (TextView) view.findViewById(R.id.textView50);
            view.setTag(holder);
            // convertView.setBackgroundResource(R.drawable.group);
        }else {
            holder= (ViewHolder) view.getTag();

        }
        if(b){
            holder.textView1.setText("收起▲");
            //convertView.setBackgroundResource(R.drawable.group_e);
        }else{
            holder.textView1.setText("展开▼");
        }


        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if (view==null){
            view=View.inflate(context, R.layout.item_chufang2,null);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;

    }
    public TextView getGenericView() {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 64);

        TextView textView = new TextView(context);

        textView.setLayoutParams(lp);
        // Center the text vertically
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        textView.setPadding(36, 0, 0, 0);
        return textView;
    }
    class ViewHolder{
        TextView textView1,textView2,textView3,textView4,textView5;
    }
}
