package com.zkrkj.peoplehospital.registered;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

/**
 * Describe:     挂号历史
 * User:         LF
 * Date:         2016/3/23 15:25
 */
public class RegisteredHistory extends BaseActivity {

    @Bind(R.id.rl_listview)
    RecyclerView rlListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_history);
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        initTitle();
        initWidget();
    }

    private void initWidget() {
        rlListview.setLayoutManager(new LinearLayoutManager(this));
        rlListview.setAdapter(new MyRegisteredHistory());
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("我的预约");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class MyRegisteredHistory extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder=new MyViewHolder(LayoutInflater.from(RegisteredHistory.this).inflate(R.layout.registered_history_list_item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof  MyViewHolder){
                ((MyViewHolder) holder).tv_hospital_name.setText("解放军总医院第一附属医院  消化内科");
                ((MyViewHolder) holder).tv_deal_date.setText("2015/9/24 10:32");
                ((MyViewHolder) holder).tv_date_and_doctor.setText("2015-9-23  下午  主任医师  张云志");
                ((MyViewHolder) holder).tv_registered_name.setText("王明明  女  23岁");
                ((MyViewHolder) holder).tv_deal_state.setText("交易成功");
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv_deal_date;//交易日期
            TextView tv_hospital_name;//就诊医院
            TextView tv_date_and_doctor;//诊断医生
            TextView tv_registered_name;//就诊人
            TextView tv_deal_state;//交易状态
            Button btn_deal_detail;//交易详情
            Button btn_back_no;//退号
            public MyViewHolder(View itemView) {
                super(itemView);
                tv_deal_date= (TextView) itemView.findViewById(R.id.tv_deal_date);
                tv_hospital_name= (TextView) itemView.findViewById(R.id.tv_hospital_name);
                tv_date_and_doctor= (TextView) itemView.findViewById(R.id.tv_date_and_doctor);
                tv_registered_name= (TextView) itemView.findViewById(R.id.tv_registered_name);
                tv_deal_state= (TextView) itemView.findViewById(R.id.tv_deal_state);
                btn_deal_detail= (Button) itemView.findViewById(R.id.btn_deal_detail);
                btn_back_no= (Button) itemView.findViewById(R.id.btn_back_no);
                btn_deal_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(RegisteredHistory.this,DealDetail.class);
                        startActivity(intent);
                    }
                });
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
