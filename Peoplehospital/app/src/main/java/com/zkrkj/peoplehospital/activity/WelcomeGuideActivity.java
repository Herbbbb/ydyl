package com.zkrkj.peoplehospital.activity;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zkrkj.peoplehospital.R;

import base.BaseActivity;
import base.OptsharepreInterface;

public class WelcomeGuideActivity extends BaseActivity{

	private ImageView btn;

	private ViewPager pager;
	private OptsharepreInterface share;

	private List<View> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_guide);
		share=new OptsharepreInterface(this);
		if(share.getPres("isFirstLogin").equals("0")){
			pager= (ViewPager) findViewById(R.id.welcome_guide_viewpager);
			btn= (ImageView) findViewById(R.id.welcome_guide_btn);
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					click(view);
				}
			});
			initViewPager();
		}else{
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}

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

	public void click(View view)
	{
		share.putPres("isFirstLogin","1");
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	public void initViewPager()
	{
		list=new ArrayList<View>();
		ImageView iv=new ImageView(this);
		iv.setImageResource(R.drawable.guide_01);
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		list.add(iv);
		ImageView iv1=new ImageView(this);
		iv1.setImageResource(R.drawable.guide_02);
		iv1.setScaleType(ImageView.ScaleType.FIT_XY);
		list.add(iv1);
		ImageView iv2=new ImageView(this);
		iv2.setScaleType(ImageView.ScaleType.FIT_XY);
		iv2.setImageResource(R.drawable.guide_03);
		list.add(iv2);
		pager.setAdapter(new MyViewPagerAdapter());


		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if(arg0==2)
				{

					btn.setVisibility(View.VISIBLE);

				}else
				{


					btn.setVisibility(View.GONE);

				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	class MyViewPagerAdapter extends PagerAdapter{



		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {


			//super.destroyItem(container, position, object);
			container.removeView(list.get(position));
		}

	}

}
