package com.zkrkj.peoplehospital.activity;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.fragment.Frag_Doctor;
import com.zkrkj.peoplehospital.fragment.Frag_Home;
import com.zkrkj.peoplehospital.fragment.Frag_Hospitals;
import com.zkrkj.peoplehospital.fragment.Frag_Talk;
import com.zkrkj.peoplehospital.fragment.Frag_User;

import base.BaseActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Fragment mHomeFragment;
    private Fragment mHospitalsFragment;
    private Fragment mDoctorFragment;
    private Fragment mTalkFragment;
    private Fragment mUserFragment;
    private ImageView image1,image2,image3,image4,docterimage;
    private TextView textView1,textView2,textView3,textView4,doctertextView;
     public ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_main);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home;
    }

    @Override
    public void initView() {
        image1= (ImageView) findViewById(R.id.tab_home_image);
        image2= (ImageView) findViewById(R.id.tab_hospital_image);
        image3= (ImageView) findViewById(R.id.tab_find_image);
        image4= (ImageView) findViewById(R.id.tab_user_image);
        docterimage=(ImageView)findViewById(R.id.tab_docter);
        textView1= (TextView) findViewById(R.id.tab_home_text);
        textView2= (TextView) findViewById(R.id.tab_hospital_text);
        textView3= (TextView) findViewById(R.id.tab_find_text);
        textView4= (TextView) findViewById(R.id.tab_user_text);
        doctertextView= (TextView) findViewById(R.id.tab_docter_text);
        LinearLayout l1= (LinearLayout) findViewById(R.id.tab_home);
        LinearLayout l2= (LinearLayout) findViewById(R.id.tab_hospitals);
        LinearLayout l3= (LinearLayout) findViewById(R.id.tab_talk);
        LinearLayout l4= (LinearLayout) findViewById(R.id.tab_user);
        LinearLayout l5= (LinearLayout) findViewById(R.id.tab_docters);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        select(0);



    }

    @Override
    public void initAction() {

    }
    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mDoctorFragment != null) {
            transaction.hide(mDoctorFragment);
        }
        if (mHospitalsFragment != null) {
            transaction.hide(mHospitalsFragment);
        }
        if (mTalkFragment != null) {
            transaction.hide(mTalkFragment);
        }
        if (mUserFragment != null) {
            transaction.hide(mUserFragment);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_home:

                Toast.makeText(this,"home",Toast.LENGTH_SHORT).show();
                select(0);

                break;
            case R.id.tab_hospitals:
                Toast.makeText(this,"tab_hospitals",Toast.LENGTH_SHORT).show();
                select(1);

                break;
            case R.id.tab_docters:
                select(2);
                Toast.makeText(this,"doctor",Toast.LENGTH_SHORT).show();

                break;
            case R.id.tab_talk:
                select(3);
                Toast.makeText(this,"talk",Toast.LENGTH_SHORT).show();

                break;
            case R.id.tab_user:
                select(4);
                Toast.makeText(this,"user",Toast.LENGTH_SHORT).show();

                break;

            default:
                break;
        }
    }
    long time=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (System.currentTimeMillis() - time > 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
        return true;
    }
    private void select(int position) {
        // 获取FragmentManager对象
       // FragmentManager fm=getFragmentManager();
        FragmentManager fm = getSupportFragmentManager();
        // 获取事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏事务
        hideFragment(transaction);
        // 根据传递过来的参数 选择显示对应的Fragment
        switch (position) {
            case 0:// HomeFragment
                // 如果此Fragment为空 就新建一个
                if (mHomeFragment == null) {
                    mHomeFragment = new Frag_Home();
                    transaction.add(R.id.cccc, mHomeFragment);
                } else {// 此Fragment已经存在 直接显示
                    transaction.show(mHomeFragment);
                }
                // 改变按下后图片和文字的状态
                changeStatus(0);
                break;
            case 1:
                if (mHospitalsFragment == null) {
                    mHospitalsFragment = new Frag_Hospitals();

                    transaction.add(R.id.cccc, mHospitalsFragment);
                } else {
                    transaction.show(mHospitalsFragment);
                }
                changeStatus(1);
                break;
            case 2:
                if (mDoctorFragment == null) {
                    mDoctorFragment = new Frag_Doctor();
                    transaction.add(R.id.cccc, mDoctorFragment);
                } else {
                    transaction.show(mDoctorFragment);
                }
                changeStatus(2);
                break;
            case 3:
                if (mTalkFragment == null) {
                    mTalkFragment = new Frag_Talk();
                    transaction.add(R.id.cccc, mTalkFragment);
                } else {
                    transaction.show(mTalkFragment);
                }
                changeStatus(3);
                break;
            case 4:
                if (mUserFragment == null) {
                    mUserFragment = new Frag_User();
                    transaction.add(R.id.cccc, mUserFragment);
                } else {
                    transaction.show(mUserFragment);
                }
                changeStatus(4);
                break;

            default:
                break;
        }
        // 提交事务
        transaction.commit();
    }
    private void changeStatus(int position) {

        // 重置所有图片
        image1.setImageResource(R.drawable.ic_tab_home_normal);
        image2.setImageResource(R.drawable.ic_tab_friends_a);
        image3.setImageResource(R.drawable.ic_tab_find_a);
        image4.setImageResource(R.drawable.ic_tab_user_normal);
        docterimage.setImageResource(R.drawable.ic_tab_friends_a);
        // 重置所有文本的颜色
        textView1.setTextColor(Color.WHITE);
        textView2.setTextColor(Color.WHITE);
        textView3.setTextColor(Color.WHITE);
        textView4.setTextColor(Color.WHITE);
        doctertextView.setTextColor(Color.WHITE);
        // 改变对应图片和文本颜色

        switch (position) {
            case 0:
                image1.setImageResource(R.drawable.ic_tab_home_press);
                textView1.setTextColor(Color.parseColor("#FF1493"));
                break;
            case 1:
                image2.setImageResource(R.drawable.ic_tab_friends_b);
                textView2.setTextColor(Color.parseColor("#FF1493"));
                break;
            case 2:
                docterimage.setImageResource(R.drawable.ic_tab_friends_b);
                doctertextView.setTextColor(Color.parseColor("#FF1493"));

                break;
            case 3:
                image3.setImageResource(R.drawable.ic_tab_find_b);
                textView3.setTextColor(Color.parseColor("#FF1493"));

                break;
            case 4:
                image4.setImageResource(R.drawable.ic_tab_user_press);
                textView4.setTextColor(Color.parseColor("#FF1493"));
                break;

            default:
                break;
        }
    }
}