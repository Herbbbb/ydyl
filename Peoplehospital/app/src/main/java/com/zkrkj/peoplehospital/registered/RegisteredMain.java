package com.zkrkj.peoplehospital.registered;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.User.MyUserActivity;
import com.zkrkj.peoplehospital.activity.TimeSecActivity;
import com.zkrkj.peoplehospital.xzqh.ProcinceActivity;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.Constants;
import util.TitleBarUtils;

/**
 * Describe:     预约挂号首页
 * User:         LF
 * Date:         2016/3/23 15:27
 */
public class RegisteredMain extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_loc)
    TextView tvLoc;
    @Bind(R.id.tv_hospital)
    TextView tvHospital;
    @Bind(R.id.min_hospital)
    RelativeLayout minHospital;
    @Bind(R.id.tv_sjwk)
    TextView tvSjwk;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.min_date)
    RelativeLayout minDate;
    @Bind(R.id.tv_ptmz)
    TextView tvPtmz;
    @Bind(R.id.min_ptmz)
    RelativeLayout minPtmz;

    private RelativeLayout rl_wdjzr, rl_history, min_sjwk, min_city;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_main);
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        initTitle();
        initWidget();
    }

    private void initWidget() {
        rl_wdjzr = (RelativeLayout) findViewById(R.id.rl_wdjzr);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        rl_history = (RelativeLayout) findViewById(R.id.rl_history);
        min_sjwk = (RelativeLayout) findViewById(R.id.min_sjwk);
        min_city = (RelativeLayout) findViewById(R.id.min_city);

        min_sjwk.setOnClickListener(this);
        rl_history.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        rl_wdjzr.setOnClickListener(this);
        min_city.setOnClickListener(this);
        minDate.setOnClickListener(this);
        minPtmz.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_wdjzr://我的就诊人
                intent = new Intent(this, MyUserActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
                break;
            case R.id.btn_submit://预约详情
                Intent intent1 = new Intent(this, RegisteredDetail.class);
                startActivity(intent1);
                break;
            case R.id.rl_history://我的预约
                Intent intent2 = new Intent(this, RegisteredHistory.class);
                startActivity(intent2);
                break;
            case R.id.min_sjwk://科室选择
                Intent intent3 = new Intent(this, DepartmentRegistered.class);
                startActivity(intent3);
                break;
            case R.id.min_city://地区选择
                Intent intent4 = new Intent(this, ProcinceActivity.class);
                startActivityForResult(intent4, 4);
                break;
            case R.id.min_date://时间选择
                Intent intent5 = new Intent(this, TimeSecActivity.class);
                startActivityForResult(intent5, 5);
                break;
            case R.id.min_ptmz://普通门诊
                Intent intent6 = new Intent(this, TimeSecActivity.class);
                startActivityForResult(intent6, 6);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 4 && resultCode == RESULT_OK) {
            String xzqh = data.getStringExtra(Constants.XZQH_CODE);
            tvLoc.setText(xzqh);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
