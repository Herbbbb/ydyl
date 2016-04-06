package com.zkrkj.peoplehospital.registered;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

public class RegisteredSuccess extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_hospital)
    TextView tvHospital;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_doc)
    TextView tvDoc;
    @Bind(R.id.tv_name_age)
    TextView tvNameAge;
    @Bind(R.id.tv_deal_state)
    TextView tvDealState;
    @Bind(R.id.btn_deal_detail)
    Button btnDealDetail;
    @Bind(R.id.btn_back_num)
    Button btnBackNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initListener();
    }

    private void initListener() {
        btnDealDetail.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_deal_detail:
                Intent intent=new Intent(this,DealDetail.class);
                startActivity(intent);
                break;
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
