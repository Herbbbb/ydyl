package com.zkrkj.peoplehospital.record;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

public class ReportQuery extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_jybg)
    TextView tvJybg;
    @Bind(R.id.tv_jcbg)
    TextView tvJcbg;
    @Bind(R.id.ll_fragment)
    LinearLayout llFragment;
    private FragmentManager mManager;
    private Fragment mJybg;
    private Fragment mJcbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_query);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initWidget();
        initFragment();
    }

    private void initFragment() {
        mJybg=new ReportQueryJybg();
        mJcbg=new ReportQueryJcbg();
        mManager = getSupportFragmentManager();
        FragmentTransaction trans = mManager.beginTransaction();
        trans.add(R.id.ll_fragment, mJybg);
        trans.commit();
    }

    private void seleFragment(int id){
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        switch (id){
            case 0:
                trans.replace(R.id.ll_fragment, mJybg);
                break;
            case 1:
                trans.replace(R.id.ll_fragment, mJcbg);
                break;
        }
        trans.addToBackStack(null);
        trans.commit();
    }

    private void initWidget() {
        tvJybg.setOnClickListener(this);
        tvJcbg.setOnClickListener(this);
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("就诊明细");
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
            case R.id.tv_jybg://检验报告
                seleFragment(0);
                break;
            case R.id.tv_jcbg://检查报告
                seleFragment(1);
                break;
        }
        if(v instanceof TextView){
            ((TextView) v).getPaint().setFakeBoldText(true);
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
