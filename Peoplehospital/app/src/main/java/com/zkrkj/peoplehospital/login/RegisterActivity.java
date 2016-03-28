package com.zkrkj.peoplehospital.login;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;

import org.json.JSONObject;

import base.BaseActivity;
import util.IStringRequest;
import util.TitleBarUtils;
import util.ToastUtil;
import util.ValidateUtil;
/**
* Describe:     注册页
* User:         LF
* Date:         2016/3/18 14:20
*/
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_account,et_pwd,et_yzm,et_pwd_sure;
    private Button btn_yzm,btn_register;

    private TextView tv_pwd_diff_error;

    private String account,yzm,pwd,pwd_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        initTitle();
        initWidget();
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("注册");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWidget() {
        et_account = (EditText) findViewById(R.id.et_account);
        et_yzm= (EditText) findViewById(R.id.et_yzm);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_pwd_sure= (EditText) findViewById(R.id.et_pwd_sure);
        btn_yzm = (Button) findViewById(R.id.btn_yzm);
        btn_register = (Button) findViewById(R.id.btn_register);
        tv_pwd_diff_error= (TextView) findViewById(R.id.tv_pwd_diff_error);
        btn_yzm.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        //设置弹出数字键
        et_account.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_yzm.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //判断密码是否相同
        et_pwd_sure.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!et_pwd.getText().toString().trim().equals(et_pwd_sure.getText().toString().trim())){
                        tv_pwd_diff_error.setVisibility(View.VISIBLE);
                        btn_register.setClickable(false);
                    }else{
                        tv_pwd_diff_error.setVisibility(View.GONE);
                        btn_register.setClickable(true);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yzm:
                vaidate();
                break;
            case R.id.btn_register:
                break;
            default:
                break;
        }
    }

    /**
    * Describe:     验证输入是否正确
    * User:         LF
    * Date:         2016/3/18 14:13
    */
    private void vaidate() {
        boolean validateRule=false;
        String errorMsg="";
        account=et_account.getText().toString().trim();
        yzm=et_yzm.getText().toString().trim();
        pwd=et_pwd.getText().toString().trim();
        pwd_sure=et_pwd_sure.getText().toString().trim();
        if(!ValidateUtil.isMobileNO(account)){
            errorMsg="手机格式不正确";
        }else{
            validateRule=true;
        }
        if(validateRule){
            if(!ValidateUtil.checkPassWord(pwd)){
                errorMsg="密码6-18位或出现非法字符";
                validateRule=false;
            }
        }
        if(validateRule){
            register();
        }else{
            ToastUtil.ToastShow(this,errorMsg,true);
        }
    }

    /**
    * Describe:     注册
    * User:         LF
    * Date:         2016/3/18 14:19
    */
    private void register() {
        RequestQueue queue = Volley.newRequestQueue(this);
        IStringRequest requset = new IStringRequest(Request.Method.GET,
                "http://192.168.1.252:9401/AppointMentServer/api/register?phone=15539793157&password=m12345678&verifyCode=123456",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("aaa",response);
                     //   parseL(response);
                        ToastUtil.ToastShow(getBaseContext(), "注册成功", true);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("aaa",error.toString());

                    }
                }
        );
        queue.add(requset);
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
