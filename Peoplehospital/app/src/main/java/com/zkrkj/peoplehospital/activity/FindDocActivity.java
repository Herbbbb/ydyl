package com.zkrkj.peoplehospital.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.adapter.FindDocAdapter1;
import com.zkrkj.peoplehospital.findDoc.FindDocDetail;
import com.zkrkj.peoplehospital.hospital.adapter.PupAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.Constants;
import util.IStringRequest;
import util.JsonUtils;
import util.TitleBarUtils;
import util.ToastUtil;
import view.SearchView;

/**
 * Created by miao on 2016/3/16.
 * 找医生ctivity
 */
public class FindDocActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.finddoc)
    SearchView finddoc;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.l1)
    LinearLayout l1;
    private PopupWindow popupWindow;
    private List<Map<String, Object>> list1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_doc);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        initTitle();
        finddoc.setHint(this, "医生");
        hoslist();
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getBaseContext(), FindDocDetail.class);
                intent.putExtra("","");
                startActivity(intent);
            }
        });

    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("找医生");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAction() {

    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
    public void hoslist() {
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        IStringRequest requset = new IStringRequest(Request.Method.POST,
                Constants.SERVER_ADDRESS + "doctor",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parsedoc(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        ToastUtil.ToastShow(getBaseContext(), "服务器好像出错误了", true);

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("keyWord","");


                return map;
            }
        };


        queue.add(requset);

    }

    private void parsedoc(String response) {
        Map<String, Object> object = null;
        Map<String, Object> data = null;
        List<Map<String, Object>> doctors = null;
        try {
            object = JsonUtils.getMapObj(response);
            data = JsonUtils.getMapObj(object.get("data").toString());
            doctors = JsonUtils.getListMap(data.get("doctors").toString());
            list1 = doctors;
            listView.setAdapter(new FindDocAdapter1(list1, getBaseContext()
            ));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPopWindow(int x) {
        View popView = View.inflate(this, R.layout.pup_listview, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置popwindow出现和消失动画
        // popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
        ListView listview = (ListView) popView.findViewById(R.id.listView8);
        List<String> l1 = new ArrayList<>();
        final List<String> l2 = new ArrayList<>();
        final List<String> l3 = new ArrayList<>();
        l2.add("全部科室");
        l2.add("消化内科");
        l2.add("心血管科");
        l2.add("老年病科");
        l2.add("普通外科");

        l2.add("职业病科");
        l2.add("肿瘤放疗科");
        l2.add("神经内科");
        l2.add("骨科门诊");
        l2.add("中医科");


        l3.add("全部");

        l3.add("主任医师");
        l3.add("副主任医师");

        l3.add("主治医师");
        l3.add("医师");
        l3.add("医士");


        if (x == 1) {
            listview.setAdapter(new PupAdapter(this, l1));
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //tv2.setText(l2.get(i).toString());
                    popupWindow.dismiss();
                }
            });
        } else if (x == 2) {
            listview.setAdapter(new PupAdapter(this, l2));
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tv2.setText(l2.get(i).toString() + "▼");


                    popupWindow.dismiss();
                   // hoslist();

                }
            });
        } else {
            listview.setAdapter(new PupAdapter(this, l3));
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tv3.setText(l3.get(i).toString() + "▼");
                    popupWindow.dismiss();
                }
            });
        }


    }

    public void showPop(View parent, int x, int y, int postion) {
        //设置popwindow显示位置
        // popupWindow.showAtLocation(parent, 0, x, y);
        // popupWindow.showAtLocation(view,5,0,10);
        popupWindow.showAsDropDown(l1);
        //获取popwindow焦点
        popupWindow.setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        if (popupWindow.isShowing()) {

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                initPopWindow(1);
                showPop(view, 0, 0, 0);
                break;
            case R.id.tv2:
                ToastUtil.ToastShow(getBaseContext(),"科室",true);
                initPopWindow(2);

                showPop(view, 0, 0, 0);
                break;
            case R.id.tv3:
                initPopWindow(3);
                showPop(view, 0, 0, 0);
                break;
        }
    }
}
