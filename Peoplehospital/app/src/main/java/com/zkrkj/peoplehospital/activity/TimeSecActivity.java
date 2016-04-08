package com.zkrkj.peoplehospital.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.adapter.MyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
import util.ToastUtil;


/**
 * Describe:预约挂号时间选择
 * Created by ${苗}
 * on 2016/4/6.
 */

public class TimeSecActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;
    @Bind(R.id.text3)
    TextView text3;
    @Bind(R.id.text4)
    TextView text4;
    @Bind(R.id.text5)
    TextView text5;
    @Bind(R.id.text6)
    TextView text6;
    @Bind(R.id.text7)
    TextView text7;
    @Bind(R.id.listView10)
    ListView listView10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.yuyuetime);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        text1.setSelected(true);


    }


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        initTitle();

        long time = System.currentTimeMillis();
        long time1 = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        long time2 = System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000;
        long time3 = System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000;
        long time4 = System.currentTimeMillis() + 4 * 24 * 60 * 60 * 1000;
        long time5 = System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000;
        long time6 = System.currentTimeMillis() + 6 * 24 * 60 * 60 * 1000;
        Date date = new Date(time);
        Date date1 = new Date(time1);
        Date date2 = new Date(time2);
        Date date3 = new Date(time3);
        Date date4 = new Date(time4);
        Date date5 = new Date(time5);
        Date date6 = new Date(time6);
        text1.setText(riqi(date));
        text2.setText(riqi(date1));
        text3.setText(riqi(date2));
        text4.setText(riqi(date3));
        text5.setText(riqi(date4));
        text6.setText(riqi(date5));
        text7.setText(riqi(date6));
        List<String> list=new ArrayList<>();
        list.add("上午");
        list.add("下午");
        listView10.setAdapter(new MyAdapter(this,list));

        text2.setOnClickListener(this);
        text1.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        text5.setOnClickListener(this);
        text6.setOnClickListener(this);
        text7.setOnClickListener(this);

    }


    private String riqi(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("E");
        String xingqi = format.format(date);
        format = new SimpleDateFormat("MM-dd");
        String riqi = format.format(date);

        return xingqi + "\n" + riqi;
    }

    @Override
    public void initAction() {

    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("预约挂号");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void changeStatus(int position) {
        text1.setBackgroundColor(Color.parseColor("#ffffff"));
        text2.setBackgroundColor(Color.parseColor("#ffffff"));
        text3.setBackgroundColor(Color.parseColor("#ffffff"));
        text4.setBackgroundColor(Color.parseColor("#ffffff"));
        text5.setBackgroundColor(Color.parseColor("#ffffff"));
        text6.setBackgroundColor(Color.parseColor("#ffffff"));
        text7.setBackgroundColor(Color.parseColor("#ffffff"));
        switch (position) {
           case 1:
                text1.setBackgroundColor(Color.parseColor("#C2E8D4"));
                break;
            case 2:
                text2.setBackgroundColor(Color.parseColor("#C2E8D4"));

                break;
            case 3:
                text3.setBackgroundColor(Color.parseColor("#C2E8D4"));

                break;
            case 4:
                text4.setBackgroundColor(Color.parseColor("#C2E8D4"));
                break;
            case 5:
                text5.setBackgroundColor(Color.parseColor("#C2E8D4"));
                break;
            case 6:
                text6.setBackgroundColor(Color.parseColor("#C2E8D4"));
                break;
            case 7:
                text7.setBackgroundColor(Color.parseColor("#C2E8D4"));
                break;
            default:
                break;
        }

    }


    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.text1:
          changeStatus(1);
             break;
         case R.id.text2:

             changeStatus(2);
             break;
         case R.id.text3:
             changeStatus(3);

             break;
         case R.id.text4:
             changeStatus(4);

             break;
         case R.id.text5:
             changeStatus(5);

             break;
         case R.id.text6:
             changeStatus(6);

             break;
         case R.id.text7:
             changeStatus(7);
             break;


     }
    }


}
