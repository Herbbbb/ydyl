package com.zkrkj.peoplehospital.User;

/*
Build.MODEL，得到手机型号，比如我的就是ME865

Build.VERSION.RELEASE，得到系统版本号，比我我的4.0.4

getPackageManager().getPackageInfo(getPackageName(),0).versionName，得到应用程序的当前版本号

 */
import android.os.Bundle;
import android.view.View;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import util.TitleBarUtils;

public class FeedBackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_feed_back);
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
        titleBarUtils.setTitle("意见反馈");


        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
