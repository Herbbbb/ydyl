package com.zkrkj.peoplehospital.activity;

import android.os.Bundle;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import view.SearchView;

public class FindDocActivity extends BaseActivity {

    @Bind(R.id.finddoc)
    SearchView finddoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_find_doc);
        ButterKnife.bind(this);

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
