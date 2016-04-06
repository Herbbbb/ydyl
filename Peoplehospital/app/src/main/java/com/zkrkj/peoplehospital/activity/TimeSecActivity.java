package com.zkrkj.peoplehospital.activity;

import android.os.Bundle;
import android.view.View;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.ButterKnife;
import util.TitleBarUtils;

/**
 * Describe:预约挂号时间选择
 * Created by ${苗}
 * on 2016/4/6.
 */

public class TimeSecActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.yuyuetime);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);


    }


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
         initTitle();
    }

    @Override
    public void initAction() {

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

}
