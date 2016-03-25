package com.zkrkj.peoplehospital.User;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
import util.ToastUtil;
import util.ValidateUtil;

public class ChangePasswordActivity extends BaseActivity {
    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.et_pwd1)
    EditText etPwd1;
    @Bind(R.id.btn_submit)
    Button btnSubmit;

    /**
     * Describe:     修改密码页
     * User:         苗坤
     * Date:         2016/3/25 11:20
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_change_password);
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
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=etAccount.getText().toString();
                String s2=etPwd.getText().toString();
                String s3=etPwd1.getText().toString();
                if(TextUtils.isEmpty(s1)||TextUtils.isEmpty(s2)||TextUtils.isEmpty(s3)){
                    ToastUtil.ToastShow(getBaseContext(),"输入内容不能为空",true);

                }else if (s2.length()<6||s2.length()>18){
                    ToastUtil.ToastShow(getBaseContext(),"请输入正确的密码位数",true);
                }

                else if (!s2.equals(s3)){
                    ToastUtil.ToastShow(getBaseContext(),"再次输入的密码不一致",true);
                }
            }
        });
    }

    @Override
    public void initAction() {

    }

   /* private void validation() {

        pwd = et_pwd.getText().toString().trim();

        if (TextUtils.isEmpty(account)) {
            ToastUtil.ToastShow(this, "请输入手机号", true);
        } else if (!ValidateUtil.isMobileNO(account)) {
            ToastUtil.ToastShow(this, "手机格式不正确", true);
        } else {
            if (TextUtils.isEmpty(account)) {
                ToastUtil.ToastShow(this, "请输入密码", true);
            } else if (!ValidateUtil.checkPassWord(pwd)) {
                ToastUtil.ToastShow(this, "密码6-18位或出现非法字符", true);
            } else {
                loginMethod();
            }
        }
    }
    */
   private void initTitle() {
       TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
       titleBarUtils.setTitle("修改密码");


       titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               finish();
           }
       });
   }
}
