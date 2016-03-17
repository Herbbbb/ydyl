package com.zkrkj.peoplehospital.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkrkj.peoplehospital.R;

import base.BaseFragment;

/**
 * Created by lenovo on 2016/3/16.
 */

public class Frag_User extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_user,null);

        return view;
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
