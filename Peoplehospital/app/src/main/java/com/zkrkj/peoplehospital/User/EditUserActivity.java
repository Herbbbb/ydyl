package com.zkrkj.peoplehospital.User;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.Button;
import android.widget.EditText;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

public class EditUserActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.ed1)
    EditText ed1;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.spinner1)
    AppCompatSpinner spinner1;
    @Bind(R.id.ed2)
    EditText ed2;
    @Bind(R.id.ed3)
    EditText ed3;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.btn_del)
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_edit_user);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
      String name=getIntent().getStringExtra("name");
        String gender=getIntent().getStringExtra("gender");
        String idNo=getIntent().getStringExtra("idNo");
        String phone=getIntent().getStringExtra("phone");
        if (gender.equals("男")){
            spinner1.setSelection(0);
        }else
        {
            spinner1.setSelection(1);
        }
        ed1.setText(name);
        ed2.setText(idNo);
        ed3.setText(phone);



    }

    @Override
    public void initAction() {

    }
}
