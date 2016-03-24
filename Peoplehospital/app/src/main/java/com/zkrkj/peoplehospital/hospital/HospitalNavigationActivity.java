package com.zkrkj.peoplehospital.hospital;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

/**
 * Created by miao on 2016/3/16.
 * 医院导航activity
 */

public class HospitalNavigationActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.imageView9)
    ImageView imageView9;
    @Bind(R.id.textView38)
    TextView textView38;
    @Bind(R.id.textView39)
    TextView textView39;
    @Bind(R.id.imageView10)
    ImageView imageView10;
    @Bind(R.id.textView40)
    TextView textView40;
    @Bind(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_hospital_navigation);
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
        titleBarUtils.setTitle("医院导航");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
