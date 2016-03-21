package com.zkrkj.peoplehospital.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
import view.SearchView;

public class FindHospitalActivity extends BaseActivity {

    @Bind(R.id.finddoc)
    SearchView finddoc;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_find_hospital);
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
    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("找医院");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAction() {

    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
