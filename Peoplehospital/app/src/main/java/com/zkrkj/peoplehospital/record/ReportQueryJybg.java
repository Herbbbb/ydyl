package com.zkrkj.peoplehospital.record;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkrkj.peoplehospital.R;

import base.BaseFragment;
/**
* Describe:     报告查询中的检验报告
* User:         LF
* Date:         2016/3/25 17:00
*/
public class ReportQueryJybg extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_report_query_jybg, container, false);
        return view;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initAction() {

    }
    @Override
    public int getViewId() {
        return 0;
    }
}
