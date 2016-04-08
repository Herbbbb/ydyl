package com.zkrkj.peoplehospital.Search;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.MainActivity;
import com.zkrkj.peoplehospital.hospital.adapter.PupAdapter1;

import util.TitleBarUtils;

public class TFSearchActivity extends Activity implements OnClickListener {

	public static final String SEARCH_HISTORY = "search_history";
	private EditText ed1;
	private ListView mAutoListView;
	private TextView mSearchButtoon;
	private TextView mAutoEdit;
	private ImageView ivDeleteText;
	private SearchAutoAdapter mSearchAutoAdapter;
	private PopupWindow popupWindow;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		init();
	}

	private void init() {
		initTitle();

		mSearchAutoAdapter = new SearchAutoAdapter(this, 5);
		mAutoListView = (ListView) findViewById(R.id.auto_listview);
		//ed1= (EditText) findViewById(R.id.auto_edit);
		mAutoListView.setAdapter(mSearchAutoAdapter);
		mAutoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				String data = (String) mSearchAutoAdapter.getItem(position);
				mAutoEdit.setText(data);
				mSearchButtoon.performClick();

			}
		});

		mSearchButtoon = (TextView) findViewById(R.id.search_button);
		mSearchButtoon.setOnClickListener(this);

		mAutoEdit = (TextView) findViewById(R.id.auto_edit);
		mAutoEdit.setOnClickListener(this);
		mAutoEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mSearchAutoAdapter.performFiltering(s);
			//	showPop();

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					ivDeleteText.setVisibility(View.GONE);
				} else {
					ivDeleteText.setVisibility(View.VISIBLE);
				}
			}
		});

		ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
		ivDeleteText.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mAutoEdit.setText("");
			}
		});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.search_button) {//搜索按钮
			saveSearchHistory();

			mSearchAutoAdapter.initSearchHistory();
			Toast.makeText(this, "点击搜索成功", Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * 保存搜索记录
	 */
	private void saveSearchHistory() {
		String text = mAutoEdit.getText().toString().trim();
		if (text.length() < 1) 
		{
			return;
		}
		SharedPreferences sp = getSharedPreferences(SEARCH_HISTORY, 0);
		String longhistory = sp.getString(SEARCH_HISTORY, "");
		String[] tmpHistory  = longhistory.split(",");
		ArrayList<String> history = new ArrayList<String>(
				Arrays.asList(tmpHistory));
		//检查历史记录是否已经存在当前输入的text，如果存在则删除
		if (history.size() > 0) 
		{
			int i;
			for (i = 0; i < history.size(); i++) 
			{
				if (text.equals(history.get(i))) 
				{
					history.remove(i);
					break;
				}
			}
			//如果记录大于4个，则移除最后一个数据再在最前面增加一个数据
			if (history.size() > 4) {
				history.remove(history.size()-1);
			}
			history.add(0, text);
		}

		//重新加，提交
		if (history.size() > 0) //history.size()>1和history.size()>0一样
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < history.size(); i++) 
			{
				sb.append(history.get(i) + ",");//这句一开始添加一个数据时加了两个,	why？ 用String也是 因为""
			}
			sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
		} 
		else 
		{
			sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
		}
	}
	private void initTitle() {
		TitleBarUtils titleBarUtils = (TitleBarUtils)findViewById(R.id.titleBar);
		titleBarUtils.setTitle("搜索");
		titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	private void initPopWindow() {
		View popView = View.inflate(this, R.layout.pup_listview, null);
		popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		//设置popwindow出现和消失动画
		// popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
		ListView listview = (ListView) popView.findViewById(R.id.listView8);

		listview.setAdapter(mSearchAutoAdapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				//hosname=hospitalSimples.get(i).get("hosOrgName").toString();
				//hosId=hospitalSimples.get(i).get("hosId").toString();
				//textView11.setText(hosname);
				popupWindow.dismiss();

			}
		});


	}

	public void showPop() {
		//设置popwindow显示位置
		// popupWindow.showAtLocation(parent, 0, x, y);
		// popupWindow.showAtLocation(view,5,0,10);
		popupWindow.showAsDropDown(mAutoEdit);
		//获取popwindow焦点
		popupWindow.setFocusable(true);
		//设置popwindow如果点击外面区域，便关闭。
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		mAutoEdit.setFocusable(true);
		mAutoEdit.setFocusableInTouchMode(true);
		mAutoEdit.requestFocus();
		mAutoEdit.requestFocusFromTouch();
		if (popupWindow.isShowing()) {

		}
	}


	}
