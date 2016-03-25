package com.zkrkj.peoplehospital.record;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.record.adapter.MyExpandableListAdapter1;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
/*
* 医疗费用Activity
* 苗坤
*/
public class MedicalExpensesActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.mrjp_lv)
    ExpandableListView mrjpLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_medical_expenses);
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
        MyExpandableListAdapter1 adapter=new MyExpandableListAdapter1(this);
        mrjpLv.setAdapter(adapter);
    }

    @Override
    public void initAction() {

    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("费用明细");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
