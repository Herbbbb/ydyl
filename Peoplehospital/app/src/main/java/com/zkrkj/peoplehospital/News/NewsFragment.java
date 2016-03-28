package com.zkrkj.peoplehospital.News;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import base.BaseFragment;

public class NewsFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_news_fragment, container, false);
        init();
        return view;
    }

    private void init() {
        Bundle bundle=getArguments();
        int index=bundle.getInt("index");
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
