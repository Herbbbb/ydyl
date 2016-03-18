package com.zkrkj.peoplehospital.fragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;

import base.BaseFragment;
import util.IStringRequest;
import util.LogUtil;

/**
 * Created by lenovo on 2016/3/16.
 */
public class Frag_Home extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_home,null);



        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        IStringRequest stringRequest=new IStringRequest(Request.Method.GET,
                "http://192.168.1.252:9401/AppointMentServer/api/login?username=ceshi&password=11111111",
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        //parseJsonPaihang(response);
                        Log.i("aaa",response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.i("aaa",error.toString());

            }
        }){


        };
        requestQueue.add(stringRequest);

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
