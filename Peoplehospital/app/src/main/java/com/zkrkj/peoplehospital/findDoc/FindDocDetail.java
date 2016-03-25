package com.zkrkj.peoplehospital.findDoc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
/**
* Describe:     医生信息
* User:         LF
* Date:         2016/3/25 10:12
*/
public class FindDocDetail extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.btn_date_up)
    Button btnDateUp;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.btn_date_down)
    Button btnDateDown;

    Calendar cal ;
    SimpleDateFormat df ;
    private Date firstDay;
    private Date endDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doc_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
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
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("医生信息");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
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
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initAction() {

    }
}
