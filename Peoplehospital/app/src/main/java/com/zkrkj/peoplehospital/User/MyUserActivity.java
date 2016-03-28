package com.zkrkj.peoplehospital.User;
import android.os.Bundle;
import com.zkrkj.peoplehospital.R;
import base.BaseActivity;
public class MyUserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_user);
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
