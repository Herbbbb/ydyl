package com.zkrkj.peoplehospital.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zkrkj.peoplehospital.R;

import base.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

/**
 * Created by lenovo on 2016/3/16.
 */

public class Frag_User extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.username_text)
    TextView usernameText;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.textView9)
    TextView textView9;
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
    @Bind(R.id.resiglogin)
    LinearLayout resiglogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_user, null);

        ButterKnife.bind(this, view);
        initView();
        initTitle();
        return view;
    }
    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) view.findViewById(R.id.titleBar);
        titleBarUtils.setTitle("登录");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    protected void initView() {
      usernameText.setOnClickListener(this);
    }

    @Override
    protected void initAction() {

    }

    @Override
    public int getViewId() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.username_text:
                Toast.makeText(getActivity(),"点击了人",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
