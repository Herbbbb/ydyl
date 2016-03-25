package com.zkrkj.peoplehospital.registered;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zkrkj.peoplehospital.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

public class DepartmentRegistered extends BaseActivity {

    @Bind(R.id.department_one)
    RecyclerView departmentOne;
    @Bind(R.id.department_two)
    RecyclerView departmentTwo;

    List<String> listsOne=new ArrayList<String>();
    List<String> listsTwo=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_registered);
        ButterKnife.bind(this);
        init();
    }



    private void init() {
        initTitle();
        initData();
        initWidget();
    }

    private void initData() {
        listsOne.add("特色科室");
        listsOne.add("内科");
        listsOne.add("外科");
        listsOne.add("妇产科");
        listsOne.add("耳鼻喉科");
        listsOne.add("中医科");
        listsOne.add("神经科");
        listsOne.add("全科");
        listsOne.add("精神心理科");
    }

    private void initWidget() {
        DepartmentOneAdapter oneAdapter=new DepartmentOneAdapter();
        departmentOne.setLayoutManager(new LinearLayoutManager(this));
        departmentOne.setAdapter(oneAdapter);
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("预约挂号");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
    * Describe:     左侧list适配器
    * User:         LF
    * Date:         2016/3/24 10:42
    */
    class DepartmentOneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private HashSet<MyViewHolderOne> allHolders;
        public DepartmentOneAdapter(){
            allHolders = new HashSet<MyViewHolderOne>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView=LayoutInflater.from(DepartmentRegistered.this).inflate(R.layout.departmnet_one_item,parent,false);
            MyViewHolderOne holder=new MyViewHolderOne(itemView);
            allHolders.add(holder);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof  MyViewHolderOne){
                ((MyViewHolderOne) holder).tv_department_name.setText(listsOne.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return listsOne.size();
        }

        class MyViewHolderOne extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tv_department_name;
            private View itemView;
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public MyViewHolderOne(View itemView) {
                super(itemView);
                tv_department_name= (TextView) itemView.findViewById(R.id.tv_department_name);
                this.itemView=itemView;
                itemView.setOnClickListener(this);
                if(getPosition()==0){
                    itemView.setBackground(getResources().getDrawable(R.drawable.department_item_one_true));
                }
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                  for(MyViewHolderOne holder:allHolders){
                      if(holder.getPosition()==getPosition()){
                          holder.itemView.setBackground(getResources().getDrawable(R.drawable.department_item_one_true));
                      }else{
                          holder.itemView.setBackground(getResources().getDrawable(R.drawable.department_item_one_false));
                      }
                  }
                  bindRecyclerviewTwoData(listsOne.get(getPosition()));
            }

            public View getItemView() {
                return itemView;
            }

            public void setItemView(View itemView) {
                this.itemView = itemView;
            }
        }
    }

    private void bindRecyclerviewTwoData(String seleData){
        listsTwo.clear();
        for(int i=0;i<10;i++){
            listsTwo.add(seleData+i);
        }
        departmentTwo.setLayoutManager(new LinearLayoutManager(this));
        departmentTwo.setAdapter(new DepartmentTwoAdapter());
    }

    class DepartmentTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView=LayoutInflater.from(DepartmentRegistered.this).inflate(R.layout.departmnet_one_item,parent,false);
            MyViewHolderTwo holder=new MyViewHolderTwo(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof  MyViewHolderTwo){
                ((MyViewHolderTwo) holder).tv_department_name.setText(listsTwo.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return listsTwo.size();
        }

        class MyViewHolderTwo extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tv_department_name;
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public MyViewHolderTwo(View itemView) {
                super(itemView);
                tv_department_name= (TextView) itemView.findViewById(R.id.tv_department_name);
                itemView.setOnClickListener(this);
                itemView.setBackground(getResources().getDrawable(R.drawable.department_item_one_true));
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(DepartmentRegistered.this,listsTwo.get(getPosition()),Toast.LENGTH_SHORT);
            }
        }
    }


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initAction() {

    }
}
