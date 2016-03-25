package com.zkrkj.peoplehospital.User;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zkrkj.peoplehospital.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

public class UnreadMessagesActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.listView6)
    ListView listView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_unread_messages);
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
        List<String> list=new ArrayList<>();
        list.add(0,"1");
        list.add("22");
       //ArrayAdapter adapter=new ArrayAdapter(this,R.layout.item_message,list);
      // listView6.setAdapter(adapter);
    }

    @Override
    public void initAction() {

    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("消息提醒");
        titleBarUtils.setLeftImage(R.mipmap.menu);
        titleBarUtils.setRightImageOne(R.mipmap.email);

        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
