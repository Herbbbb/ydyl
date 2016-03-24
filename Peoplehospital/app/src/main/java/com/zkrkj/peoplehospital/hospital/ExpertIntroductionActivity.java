package com.zkrkj.peoplehospital.hospital;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
/*
专家介绍Activity
cause by  miao

 */
public class ExpertIntroductionActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.listView4)
    ListView listView4;
    @Bind(R.id.hosname)
    TextView hosname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_expert_introduction);
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
        titleBarUtils.setTitle("科室信息");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
