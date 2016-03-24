package com.zkrkj.peoplehospital.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.MainActivity;
import com.zkrkj.peoplehospital.hospital.ExpertIntroductionActivity;
import com.zkrkj.peoplehospital.hospital.HospitalNavigationActivity;
import com.zkrkj.peoplehospital.hospital.MedicalNavigationActivity;
import com.zkrkj.peoplehospital.hospital.PriceSearchActivity;
import com.zkrkj.peoplehospital.hospital.SpecialDepartmentActivity;
import com.zkrkj.peoplehospital.hospital.TrackingStationActivity;

import base.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

/**
 *
 * Created by miao on 2016/3/16.
 */
public class Frag_Hospitals extends BaseFragment implements View.OnClickListener {
    Intent intent;

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.textView100)
    TextView textView100;
    @Bind(R.id.textView12)
    TextView textView12;
    @Bind(R.id.textView13)
    TextView textView13;
    @Bind(R.id.textView14)
    TextView textView14;
    @Bind(R.id.textView15)
    TextView textView15;
    @Bind(R.id.textView16)
    TextView textView16;
    @Bind(R.id.imageView1)
    ImageView imageView1;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.tab_yuyueguahao)
    RelativeLayout tabYuyueguahao;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.tab_jiaohaogenzong)
    RelativeLayout tabJiaohaogenzong;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.textView6)
    TextView textView6;
    @Bind(R.id.tab_jiuyidaohang)
    RelativeLayout tabJiuyidaohang;
    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.textView7)
    TextView textView7;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.tab_jiagechaxun)
    RelativeLayout tabJiagechaxun;
    @Bind(R.id.imageView5)
    ImageView imageView5;
    @Bind(R.id.textView9)
    TextView textView9;
    @Bind(R.id.textView10)
    TextView textView10;
    @Bind(R.id.tab_menzhenchufangchaxun)
    RelativeLayout tabMenzhenchufangchaxun;
    @Bind(R.id.imageView6)
    ImageView imageView6;
    @Bind(R.id.textView28)
    TextView textView28;
    @Bind(R.id.textView29)
    TextView textView29;
    @Bind(R.id.tab_jiancha)
    RelativeLayout tabJiancha;
    @Bind(R.id.imageView7)
    ImageView imageView7;
    @Bind(R.id.textView30)
    TextView textView30;
    @Bind(R.id.textView31)
    TextView textView31;
    @Bind(R.id.tab_menzhenfei)
    RelativeLayout tabMenzhenfei;
    @Bind(R.id.tab_tesekeshi)
    LinearLayout tabTesekeshi;
    @Bind(R.id.tab_zhuanjiajieshao)
    LinearLayout tabZhuanjiajieshao;
    @Bind(R.id.tab_yiyuandaohang)
    LinearLayout tabYiyuandaohang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_hospitals, null);
        initTitle();
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) view.findViewById(R.id.titleBar);
        titleBarUtils.setTitle("医院主站");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        tabJiaohaogenzong.setOnClickListener(this);
        tabTesekeshi.setOnClickListener(this);
        tabZhuanjiajieshao.setOnClickListener(this);
        tabYiyuandaohang.setOnClickListener(this);
        tabJiuyidaohang.setOnClickListener(this);
        tabJiagechaxun.setOnClickListener(this);

    }

    @Override
    protected void initAction() {

    }

    @Override
    public int getViewId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_jiuyidaohang:
                intent = new Intent(getActivity(), MedicalNavigationActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_tesekeshi:
                intent = new Intent(getActivity(), SpecialDepartmentActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_zhuanjiajieshao:
                intent = new Intent(getActivity(), ExpertIntroductionActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_yiyuandaohang:
                intent = new Intent(getActivity(), HospitalNavigationActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_jiaohaogenzong:
                intent = new Intent(getActivity(), TrackingStationActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_jiagechaxun:
                intent = new Intent(getActivity(), PriceSearchActivity.class);
                startActivity(intent);
                break;


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
