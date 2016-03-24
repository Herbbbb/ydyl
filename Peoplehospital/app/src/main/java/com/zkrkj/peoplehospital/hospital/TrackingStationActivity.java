package com.zkrkj.peoplehospital.hospital;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

/**
 * Created by miao on 2016/3/16.
 * 叫号跟踪activity
 */
public class TrackingStationActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.textView41)
    TextView textView41;
    @Bind(R.id.textView42)
    TextView textView42;
    @Bind(R.id.listView3)
    ListView listView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_tracking_station);
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
        titleBarUtils.setTitle("叫号跟踪");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
