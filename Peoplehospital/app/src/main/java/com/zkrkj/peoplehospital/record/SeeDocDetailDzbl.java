package com.zkrkj.peoplehospital.record;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkrkj.peoplehospital.R;

import base.BaseFragment;
import util.Constants;

/**
* Describe:     就诊明细的电子病历
* User:         LF
* Date:         2016/3/25 15:07
*/
public class SeeDocDetailDzbl extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_see_doc_detail_dzbl, container, false);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(Constants.TAG,"destory");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(Constants.TAG,"stop");
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
