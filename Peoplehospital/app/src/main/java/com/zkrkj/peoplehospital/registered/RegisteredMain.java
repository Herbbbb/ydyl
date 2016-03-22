package com.zkrkj.peoplehospital.registered;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zkrkj.peoplehospital.R;

import util.TitleBarUtils;

public class RegisteredMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_main);
        init();
    }

    private void init() {
        initTitle();
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("预约挂号");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
