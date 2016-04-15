package com.zkrkj.peoplehospital.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.adapter.DrugPriceAdapter;
import com.zkrkj.peoplehospital.adapter.ServicePriceAdapter;

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

public class ServicePriceActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.textView36)
    TextView textView36;
    @Bind(R.id.textView37)
    TextView textView37;
    @Bind(R.id.listView2)
    ListView listView2;
    @Bind(R.id.search)
    SearchView search;
    List<Map<String,Object>> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_service_price);
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
        search.setHint(this,"医疗服务项目");
        initData();
    }

    @Override
    public void initAction() {

    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("价格查询");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void  initData(){
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        IStringRequest requset = new IStringRequest(Request.Method.POST,
                Constants.SERVER_ADDRESS + "hospital/servicePrice",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("yaopin",response);
                        Map<String, Object> object = null;
                        Map<String, Object> data = null;

                        try {
                            object = JsonUtils.getMapObj(response);
                            data = JsonUtils.getMapObj(object.get("data").toString());
                            list=JsonUtils.getListMap(data.get("hosServices").toString());
                            ServicePriceAdapter adapter=new ServicePriceAdapter(list,getBaseContext());
                            listView2.setAdapter(adapter);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        ToastUtil.ToastShow(getBaseContext(),"服务器好像出错误了",true);

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("hosId","1");
                //map.put("token", token);

                return map;
            }
        };
        queue.add(requset);
    }

}
