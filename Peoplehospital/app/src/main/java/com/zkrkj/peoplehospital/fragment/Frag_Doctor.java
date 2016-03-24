package com.zkrkj.peoplehospital.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import base.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;

/**
 * Created by lenovo on 2016/3/16.
 */
public class Frag_Doctor extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.btn_date_up)
    Button btnDateUp;
    @Bind(R.id.btn_date_down)
    Button btnDateDown;
    @Bind(R.id.tv_date)
    TextView tvDate;



    Calendar cal ;
    SimpleDateFormat df ;
    private Date firstDay;
    private Date endDay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_doctor, null);
        init();
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        cal =Calendar.getInstance();
        df = new SimpleDateFormat("yyyy/MM/dd");
        initDate();
        initTitle();
        initListener();
    }

    private void initDate() {
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        firstDay= cal.getTime();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        //增加一个星期，才是我们中国人理解的本周日的日期
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        endDay=cal.getTime();
        Log.e("gnifeifeiing",df.format(firstDay));
        tvDate.setText(df.format(firstDay)+"-"+df.format(endDay));
    }

    private void initListener() {
        btnDateUp.setOnClickListener(this);
        btnDateDown.setOnClickListener(this);
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) view.findViewById(R.id.titleBar);
        titleBarUtils.setTitle("医生信息");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date_up:
                setDate(-7);
                break;
            case R.id.btn_date_down:
                setDate(7);
                break;
            default:
                break;
        }
    }

    /**
    * Describe:     计算七天的时间段
    * User:         LF
    * Date:         2016/3/22 16:26
    */
    private void setDate(int poorDay){
        cal.setTime(firstDay);
        cal.add(Calendar.DAY_OF_MONTH,poorDay);
        firstDay=cal.getTime();

        cal.setTime(endDay);
        cal.add(Calendar.DAY_OF_MONTH,poorDay);
        endDay=cal.getTime();
        tvDate.setText(df.format(firstDay)+"-"+df.format(endDay));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
