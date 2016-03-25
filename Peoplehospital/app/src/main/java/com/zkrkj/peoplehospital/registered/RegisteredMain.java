package com.zkrkj.peoplehospital.registered;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import util.TitleBarUtils;
/**
* Describe:     预约挂号首页
* User:         LF
* Date:         2016/3/23 15:27
*/
public class RegisteredMain extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_wdjzr,rl_history,min_sjwk;
    private Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_main);
        init();
    }


    private void init() {
        initTitle();
        initWidget();
    }

    private void initWidget() {
        rl_wdjzr= (RelativeLayout) findViewById(R.id.rl_wdjzr);
        btn_submit= (Button) findViewById(R.id.btn_submit);
        rl_history= (RelativeLayout) findViewById(R.id.rl_history);
        min_sjwk= (RelativeLayout) findViewById(R.id.min_sjwk);

        min_sjwk.setOnClickListener(this);
        rl_history.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        rl_wdjzr.setOnClickListener(this);
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
            case R.id.rl_wdjzr://我的就诊人
                Intent intent=new Intent(this,MyOutpatients.class);
                startActivity(intent);
                break;
            case R.id.btn_submit://预约详情
                Intent intent1=new Intent(this,RegisteredDetail.class);
                startActivity(intent1);
                break;
            case R.id.rl_history://我的预约
                Intent intent2=new Intent(this,RegisteredHistory.class);
                startActivity(intent2);
                break;
            case R.id.min_sjwk:
                Intent intent3=new Intent(this,DepartmentRegistered.class);
                startActivity(intent3);
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
