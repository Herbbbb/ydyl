package com.zkrkj.peoplehospital.login;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DialerFilter;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;

import org.json.JSONObject;

import util.TitleBarUtils;
import util.ToastUtil;
import util.ValidateUtil;
import widget.ProgressDialogStyle;

/**
* Describe:     登录页
* User:         LF
* Date:         2016/3/18 14:20
*/
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText et_account, et_pwd;
    private Button btn_submit, btn_register;
    private Dialog pb;

    private String account, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }


    private void init() {
        initTitle();
        initWidget();
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("登录");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWidget() {
        et_account = (EditText) findViewById(R.id.et_account);
        et_account.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_submit.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                validation();
                break;
            case R.id.btn_register:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void validation() {
        account = et_account.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();

        if (TextUtils.isEmpty(account)) {
            ToastUtil.ToastShow(this,"请输入手机号",true);
        } else if (!ValidateUtil.isMobileNO(account)) {
            ToastUtil.ToastShow(this,"手机格式不正确",true);
        } else {
            if (TextUtils.isEmpty(account)) {
                ToastUtil.ToastShow(this,"请输入密码",true);
            } else if (!ValidateUtil.checkPassWord(pwd)) {
                ToastUtil.ToastShow(this,"密码6-18位或出现非法字符",true);
            } else {
                loginMethod();
            }
        }
    }

    /**
    * Describe:     登录
    * User:         LF
    * Date:         2016/3/18 14:19
    */
    private void loginMethod() {
        pb = ProgressDialogStyle.createLoadingDialog(this, "正在登录...");
        pb.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest requset = new JsonObjectRequest(Request.Method.GET, "https://github.com/github", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pb.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pb.dismiss();
            }
        });
        queue.add(requset);
    }

}
