package com.zkrkj.peoplehospital.User;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.User.adapter.MyUserAdapter;

import java.util.List;
import java.util.Map;

import base.BaseActivity;
import base.OptsharepreInterface;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.IStringRequest;
import util.JsonUtils;
import util.TitleBarUtils;
import widget.RefreshLayout;

public class MyUserActivity extends BaseActivity {


    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.listView7)
    ListView listView7;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.slistview)
    RefreshLayout slistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_my_user);
        ButterKnife.bind(this);
        initTitle();
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        View v = View.inflate(this, R.layout.tianjiajiuzhenren, null);
        listView7.addFooterView(v);

        OptsharepreInterface o = new OptsharepreInterface(this);
        String token = o.getPres("token");
        RequestQueue queue = Volley.newRequestQueue(this);
        IStringRequest requset = new IStringRequest(Request.Method.GET,
                "http://192.168.1.252:9401/AppointMentServer/api/patients?limit=20&offset=0&token=" + token,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("aaa", response);
                        parseuser(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("err", error.toString());

                    }
                }
        );
        queue.add(requset);


    }

    private void parseuser(String response) {
        Map<String, Object> object = null;
        List<Map<String, Object>> data = null;
        try {
            object = JsonUtils.getMapObj(response);
            data = JsonUtils.getListMap(object.get("data").toString());
            o = new OptsharepreInterface(this);
            o.putPres("total", data.size() + "");

            MyUserAdapter adapter = new MyUserAdapter(data, this);
            listView7.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initAction() {
        slistview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
            }
        });
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("我的就诊人");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
