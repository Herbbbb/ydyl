package com.zkrkj.peoplehospital.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.MainActivity;

import base.BaseFragment;
import util.TitleBarUtils;

/**
 * Created by lenovo on 2016/3/16.
 */
public class Frag_Talk extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_talk,null);
        initTitle();
        return view;
    }
    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) view.findViewById(R.id.titleBar);
        titleBarUtils.setTitle("就医记录");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void initView() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    public int getViewId() {
        return 0;
    }
}
