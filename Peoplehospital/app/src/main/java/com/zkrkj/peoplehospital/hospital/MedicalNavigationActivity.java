package com.zkrkj.peoplehospital.hospital;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
/**
 *
 * Created by miao on 2016/3/16.
 * 就医导航activity
 */
public class MedicalNavigationActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.dangan)
    LinearLayout dangan;
    @Bind(R.id.yuyue)
    LinearLayout yuyue;
    @Bind(R.id.setaccount)
    LinearLayout setaccount;
    @Bind(R.id.setmsg)
    LinearLayout setmsg;
    @Bind(R.id.func)
    LinearLayout func;
    @Bind(R.id.yijian)
    LinearLayout yijian;
    @Bind(R.id.about)
    LinearLayout about;
    @Bind(R.id.update)
    LinearLayout update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_medical_navigation);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        initTitle();
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
        titleBarUtils.setTitle("就医导航");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
