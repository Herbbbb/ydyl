package com.zkrkj.peoplehospital.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;
import bean.MyRecord;
import widget.FullyLinearLayoutManager;
import widget.MyRecyclerview;

/**
 * Created by lenovo on 2016/3/16.
 */
public class Frag_Talk extends BaseFragment {

    private RecyclerView rl_listview;
    private List<MyRecord> lists=new ArrayList<MyRecord>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_talk,null);
        init();
        return view;
    }

    private void init() {
        initData();
        initWidget();

    }

    private void initData() {
        MyRecord record;
        for(int i=0;i<6;i++){
            record=new MyRecord();
            record.setTitle("急性阑尾炎");
            record.setContent("中国人民解放军医院  普通外科");
            lists.add(record);
        }
    }

    private void initWidget() {
        rl_listview= (RecyclerView) view.findViewById(R.id.rl_listview);
        rl_listview.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rl_listview.setAdapter(new MyRecyclerView());
        rl_listview.setFocusable(false);
    }

    private class MyRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder=new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.my_record_rl_listitem,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).tv_title.setText(lists.get(position).getTitle());
                ((MyViewHolder) holder).tv_content.setText(lists.get(position).getContent());
            }}

        @Override
        public int getItemCount() {
            return lists.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView tv_title,tv_content;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv_title= (TextView) itemView.findViewById(R.id.tv_title);
                tv_content= (TextView) itemView.findViewById(R.id.tv_content);

            }
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    public int getViewId() {
        return 0;
    }
}
