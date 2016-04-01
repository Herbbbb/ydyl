package com.zkrkj.peoplehospital.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.zkrkj.peoplehospital.News.NewsFragment;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.MainActivity;
import java.util.ArrayList;
import java.util.List;
import base.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.TitleBarUtils;
public class Frag_News extends BaseFragment implements ViewPager.OnPageChangeListener {


    @Bind(R.id.rg)
    RadioGroup rg;
    @Bind(R.id.vp)
    ViewPager vp;

    private List<NewsFragment> listFragment =new ArrayList<NewsFragment>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag__news, container,false);
        init();
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        initTitle();
        initFragment();
        initViewPager();
    }

    private void initViewPager() {
        vp.setAdapter(new MyViewPagerAdapter(getFragmentManager()));
        vp.setOnPageChangeListener(this);
    }

    private void initFragment() {
        for(int i=0;i<4;i++){
            NewsFragment news=new NewsFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("index",i);
            news.setArguments(bundle);
            listFragment.add(news);
        }
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) view.findViewById(R.id.titleBar);
        titleBarUtils.setTitle("健康资讯");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        View view=null;
        switch (position){
            case 0:
                view= rg.getChildAt(0);
                break;
            case 1:
                view= rg.getChildAt(2);
                break;
            case 2:
                view=  rg.getChildAt(4);
                break;
            case 3:
                view= rg.getChildAt(6);
                break;
        }
        if(view instanceof RadioButton){
            for(int i=0;i<rg.getChildCount();i++){
                if(rg.getChildAt(i) instanceof RadioButton){
                    if(view.getId()==rg.getChildAt(i).getId()){
                        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f,
                                1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0.5f);
                        animation.setDuration(500);
                        animation.setFillAfter(true);
                        view.clearAnimation();
                        view.setAnimation(animation);
                        animation.start();
                    }else{
                        rg.getChildAt(i).clearAnimation();
                    }
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    /**
    * Describe:     viewpager适配器
    * User:         LF
    * Date:         2016/3/28 15:20
    */
    class MyViewPagerAdapter extends FragmentPagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }
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
