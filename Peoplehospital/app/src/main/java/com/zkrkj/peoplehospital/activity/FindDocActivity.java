package com.zkrkj.peoplehospital.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.adapter.FindDocAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
import view.SearchView;
/**
 *
 * Created by miao on 2016/3/16.
 * 找医生ctivity
 */
public class FindDocActivity extends BaseActivity {


    @Bind(R.id.finddoc)
    SearchView finddoc;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_doc);
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
        finddoc.setHint(this,"医生");
       List<Map<String,Object>> list=new ArrayList<>();        list.add(1,new HashMap<String, Object>());
        FindDocAdapter adapter = new FindDocAdapter(list, this);
        listView.setAdapter(adapter);


    }
    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("找医生");
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
