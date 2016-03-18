package com.zkrkj.peoplehospital.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.FindDocActivity;

import base.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.IStringRequest;
import util.LogUtil;

/**
 * Created by lenovo on 2016/3/16.
 */
public class Frag_Home extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.guahao)
    TextView guahao;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.finddoc)
    RelativeLayout finddoc;
    @Bind(R.id.tab_hos)
    LinearLayout tabHos;
    @Bind(R.id.tab_doc)
    LinearLayout tabDoc;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.textView6)
    TextView textView6;
    @Bind(R.id.textView7)
    TextView textView7;
    @Bind(R.id.frag_home1)
    LinearLayout fragHome1;
    private ListView listView1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    protected void initView() {
   finddoc.setOnClickListener(this);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        IStringRequest stringRequest = new IStringRequest(Request.Method.GET,
                "http://192.168.1.252:9401/AppointMentServer/api/login?username=ceshi&password=11111111",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //parseJsonPaihang(response);
                        Log.i("aaa", response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.i("aaa", error.toString());

            }
        }) {


        };
        requestQueue.add(stringRequest);

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
            case R.id.finddoc:
                Intent intent =new Intent(getActivity(),FindDocActivity.class);
                startActivity(intent);
                break;
        }
    }
}
