package com.zkrkj.peoplehospital.registered;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import base.BaseActivity;
import base.OptsharepreInterface;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.Constants;
import util.JsonUtils;
import util.TitleBarUtils;
import util.ToastUtil;
import widget.ProgressDialogStyle;

/**
* Describe:     科室选择
* User:         LF
* Date:         2016/4/5 14:40
*/
public class DepartmentRegistered extends BaseActivity {

    @Bind(R.id.department_one)
    RecyclerView departmentOne;

    List<Map<String, Object>> listsOne=new ArrayList<Map<String, Object>>();
//    List<String> listsTwo=new ArrayList<String>();
    Map<String, Object> object = null;
    private Dialog pb;

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
    }
    /**
    * Describe:     获取所有科室
    * User:         LF
    * Date:         2016/4/7 10:28
    */
    private void initData() {
        pb = ProgressDialogStyle.createLoadingDialog(this, "请求中...");
        pb.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.SERVER_ADDRESS+"department?hosId=1";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadData(response);
                pb.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.ToastShow(DepartmentRegistered.this, "网络错误", true);
                pb.dismiss();
            }
        });
        queue.add(request);
    }
    /**
    * Describe:     格式化科室数据
    * User:         LF
    * Date:         2016/4/7 10:28
    */
    private void loadData(String json) {
        try {
            object = JsonUtils.getMapObj(json);
            if (object.get("success").toString().equals("0")) {
                ToastUtil.ToastShow(this, object.get("msg").toString(), true);
            } else if (object.get("success").toString().equals("1")) {
                object=JsonUtils.getMapObj(object.get("data").toString());
                listsOne=JsonUtils.getListMap(object.get("departments").toString());
                initWidget();
            } else {
                ToastUtil.ToastShow(this, "登录过期", true);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
        }
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
                ((MyViewHolderOne) holder).tv_department_name.setText(listsOne.get(position).get("deptName").toString());
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
            }

            public View getItemView() {
                return itemView;
            }

            public void setItemView(View itemView) {
                this.itemView = itemView;
            }
        }
    }

    /*private void bindRecyclerviewTwoData(String seleData){
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
    }*/


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
