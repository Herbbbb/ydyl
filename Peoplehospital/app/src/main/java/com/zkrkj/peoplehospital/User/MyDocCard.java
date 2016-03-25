package com.zkrkj.peoplehospital.User;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import bean.MyRecord;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
/**
* Describe:     我的就医卡
* User:         LF
* Date:         2016/3/25 14:04
*/
public class MyDocCard extends BaseActivity {

    @Bind(R.id.tv_hospital_name)
    TextView tvHospitalName;
    @Bind(R.id.rl_lists)
    RecyclerView rlLists;

    private List<MyRecord> lists=new ArrayList<MyRecord>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doc_card);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initWidget();
    }

    private void initWidget() {
        rlLists.setLayoutManager(new LinearLayoutManager(this));
        rlLists.setAdapter(new DocCardAdapter());
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("我的就医卡");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class DocCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder=new MyViewHolder(LayoutInflater.from(MyDocCard.this).inflate(R.layout.doc_card_lists_item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyViewHolder){
                ((MyViewHolder) holder).tv_date.setText("03/25");
                ((MyViewHolder) holder).tv_time.setText("14:01");
                ((MyViewHolder) holder).tv_price.setText("-94.00");
                ((MyViewHolder) holder).tv_note.setText("检验项目消费94.00元。");
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv_date,tv_time,tv_price,tv_note;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv_date= (TextView) itemView.findViewById(R.id.tv_date);
                tv_time= (TextView) itemView.findViewById(R.id.tv_time);
                tv_price= (TextView) itemView.findViewById(R.id.tv_price);
                tv_note= (TextView) itemView.findViewById(R.id.tv_note);
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
