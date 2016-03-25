package com.zkrkj.peoplehospital.record;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.record.adapter.MyExpandableListAdapter;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

/*
门诊处方
苗坤
 */
public class OutpatientPrescriptionsActivity extends BaseActivity {



    @Bind(R.id.mrjp_lv)
    ExpandableListView mrjpLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_medical_records);
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
        MyExpandableListAdapter adapter=new MyExpandableListAdapter(this);
        mrjpLv.setAdapter(adapter);
        mrjpLv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
               // ToastUtil.ToastShow(getBaseContext(), "dianji", true);
                return false;
            }

        });

    }

    @Override
    public void initAction() {

    }
    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("处方明细");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
