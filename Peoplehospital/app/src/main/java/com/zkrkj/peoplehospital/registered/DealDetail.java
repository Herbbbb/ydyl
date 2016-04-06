package com.zkrkj.peoplehospital.registered;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import util.TitleBarUtils;
/**
* Describe:     订单详情--预约单
* User:         LF
* Date:         2016/4/5 14:39
*/
public class DealDetail extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);
        init();
    }


    private void init() {
        initTitle();
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("预约单");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
