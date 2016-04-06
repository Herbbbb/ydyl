package com.zkrkj.peoplehospital.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.zkrkj.peoplehospital.activity.DrugPriceActivity;
import com.zkrkj.peoplehospital.activity.FindDocActivity;
import com.zkrkj.peoplehospital.login.LoginActivity;
import com.zkrkj.peoplehospital.activity.FindHospitalActivity;
import com.zkrkj.peoplehospital.activity.ServicePriceActivity;
import com.zkrkj.peoplehospital.registered.RegisteredMain;


import base.BaseFragment;
import base.OptsharepreInterface;
import bean.DataAddDataBase;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.Constants;
import util.IStringRequest;
import util.LogUtil;
import util.TitleBarUtils;
import util.ToastUtil;
import widget.ProgressDialogStyle;

/**
 * Created by lenovo on 2016/3/16.
 */
public class Frag_Home extends BaseFragment implements View.OnClickListener {

    LinearLayout finddoc;
    LinearLayout findhos1;

    LinearLayout tabHos;
    LinearLayout tabDoc;
    LinearLayout ll_yygh;

    private OptsharepreInterface share;
    private Dialog pb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, null);
        share=new OptsharepreInterface(getActivity());
        Log.e(Constants.TAG,"行政区划状态："+share.getPres("isFirstSavaXzqh"));
        if(share.getPres("isFirstSavaXzqh").equals("0")){
            new MyTask().execute();//加载行政区划数据
        }else{
            initWidget();
            initView();
            initTitle();
        }
        return view;
    }

    private void initWidget() {
        tabHos= (LinearLayout) view.findViewById(R.id.tab_hos);
        tabDoc= (LinearLayout) view.findViewById(R.id.tab_doc);
        finddoc=(LinearLayout) view.findViewById(R.id.finddoc);
        findhos1=(LinearLayout) view.findViewById(R.id.findhos1);
        ll_yygh=(LinearLayout) view.findViewById(R.id.ll_yygh);

    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) view.findViewById(R.id.titleBar);
        titleBarUtils.setTitle("百姓医院");
        titleBarUtils.setLeftImage(R.mipmap.menu);
        titleBarUtils.setRightImageOne(R.mipmap.email);

        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        finddoc.setOnClickListener(this);
        findhos1.setOnClickListener(this);
        tabHos.setOnClickListener(this);
        tabDoc.setOnClickListener(this);
        ll_yygh.setOnClickListener(this);


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
        Intent intent;
        switch (view.getId()) {
            case R.id.finddoc:
                intent = new Intent(getActivity(), FindDocActivity.class);
                startActivity(intent);
                break;
            case R.id.findhos1:
                Intent intent1 =new Intent(getActivity(),FindHospitalActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_hos:
                Intent intent2 =new Intent(getActivity(),DrugPriceActivity.class);
                startActivity(intent2);
                break;
            case R.id.tab_doc:
                Intent intent3 =new Intent(getActivity(),ServicePriceActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_yygh:
                Intent intent4 = new Intent(getActivity(), RegisteredMain.class);
                startActivity(intent4);
                break;

        }
    }

    class MyTask extends AsyncTask<Void,Void,Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb = ProgressDialogStyle.createLoadingDialog(getActivity(), "首次启动，初始化中...");
            pb.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try{
                DataAddDataBase.setProvinceData(getActivity());
                return 1;
            }catch (Exception e){
                return 0;
            }
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            if(aVoid==0){
                pb.dismiss();
                ToastUtil.ToastShow(getActivity(),"数据加载失败",true);
            }else{
                pb.dismiss();
                share.putPres("isFirstSavaXzqh","1");
                initWidget();
                initView();
                initTitle();
            }
        }
    }
}
