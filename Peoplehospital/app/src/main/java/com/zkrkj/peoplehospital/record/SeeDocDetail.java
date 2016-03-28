package com.zkrkj.peoplehospital.record;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
/**
* Describe:     就诊明细
* User:         LF
* Date:         2016/3/25 17:53
*/
public class SeeDocDetail extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_dzbl)
    TextView tvDzbl;
    @Bind(R.id.tv_jyd)
    TextView tvJyd;
    @Bind(R.id.tv_jcd)
    TextView tvJcd;
    @Bind(R.id.tv_cfd)
    TextView tvCfd;
    @Bind(R.id.ll_fragment)
    LinearLayout llFragment;

    private Fragment mDzbl;
    private Fragment mJyd;
    private Fragment mJcd;
    private Fragment mCfd;
    private FragmentManager mManager;
//    private  FragmentTransaction trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_doc_detail);
        ButterKnife.bind(this);
        mManager=getSupportFragmentManager();
        init();
    }

    private void init() {
        initTitle();
        initWidget();
        initFragment();
    }

    private void initFragment() {
        mDzbl=new SeeDocDetailDzbl();
        mJyd=new SeeDocDetailJyd();
        mJcd=new SeeDocDetailJcd();
        mCfd=new SeeDocDetailCfd();
        FragmentTransaction trans = mManager.beginTransaction();
        trans.add(R.id.ll_fragment, mDzbl);
        trans.commit();
    }

    private void seleFragment(int id){
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        switch (id){
            case 0:
                trans.replace(R.id.ll_fragment, new SeeDocDetailDzbl());
                break;
            case 1:
                trans.replace(R.id.ll_fragment, new SeeDocDetailJyd());
                break;
            case 2:
                trans.replace(R.id.ll_fragment, new SeeDocDetailJcd());
                break;
            case 3:
                trans.replace(R.id.ll_fragment, new SeeDocDetailCfd());
                break;
        }
        trans.addToBackStack(null);
        trans.commit();
    }

    private void initWidget() {
        tvDzbl.setOnClickListener(this);
        tvJyd.setOnClickListener(this);
        tvJcd.setOnClickListener(this);
        tvCfd.setOnClickListener(this);
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("就诊明细");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    @Override
    public void onClick(View v) {
        tvDzbl.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tvJyd.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tvJcd.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tvCfd.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        switch (v.getId()){
            case R.id.tv_dzbl://电子病历
                seleFragment(0);
                break;
            case R.id.tv_jyd://检验单
                seleFragment(1);
                break;
            case R.id.tv_jcd://检查单
                seleFragment(2);
                break;
            case R.id.tv_cfd://处方单
                seleFragment(3);
                break;
        }
        if(v instanceof TextView){
            ((TextView) v).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }
}
