package com.zkrkj.peoplehospital.registered;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
/**
* Describe:     预约详情页
* User:         LF
* Date:         2016/3/23 15:28
*/
public class RegisteredDetail extends Activity implements View.OnClickListener {

    @Bind(R.id.tv_hospital)
    TextView tvHospital;//医院
    @Bind(R.id.tv_department)
    TextView tvDepartment;//科室
    @Bind(R.id.tv_doc_date)
    TextView tvDocDate;//就诊日期
    @Bind(R.id.tv_outpatient_type)
    TextView tvOutpatientType;//门诊类型
    @Bind(R.id.tv_price)
    TextView tvPrice;//门诊价格
    @Bind(R.id.tv_name)
    TextView tvName;//就诊人
    @Bind(R.id.tv_sex)
    TextView tvSex;//性别
    @Bind(R.id.tv_phone)
    TextView tvPhone;//手机号
    @Bind(R.id.tv_idcard)
    TextView tvIdcard;//身份证号
    @Bind(R.id.iv_head)
    ImageView ivHead;//头像
    @Bind(R.id.btn_submit)
    Button btnSubmit;//预约

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initListener();
    }

    private void initListener() {
        btnSubmit.setOnClickListener(this);
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

    }
}
