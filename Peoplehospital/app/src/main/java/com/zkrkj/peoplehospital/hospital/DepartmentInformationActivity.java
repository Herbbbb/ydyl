package com.zkrkj.peoplehospital.hospital;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.Constants;
import util.IStringRequest;
import util.JsonUtils;
import util.TitleBarUtils;

public class DepartmentInformationActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;

    TextView hosname;

    TextView keshi;

    TextView keshixiangxi;


    @Bind(R.id.listView9)
    ListView listView9;
    private String hosname1="",hosId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_department_information);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        hosname1 = getIntent().getStringExtra("hosname");
        String keshi1=getIntent().getStringExtra("keshi");
        hosId = getIntent().getStringExtra("hosId");
          initTitle();
        View v= LayoutInflater.from(this).inflate(R.layout.it_keshixinxi,null);
        hosname= (TextView) v.findViewById(R.id.hosname);
        keshi= (TextView) v.findViewById(R.id.keshi);
        keshixiangxi= (TextView) v.findViewById(R.id.keshixiangxi);
        hosname.setText(hosname1);
        keshi.setText(keshi1);
        listView9.addHeaderView(v);
        List<String> l=new ArrayList<>();
        l.add("2");
        l.add("2");
        l.add("2");
        l.add("2");
        l.add("2");
        l.add("2");
        l.add("2");
        l.add("2");
        l.add("2");
        l.add("2");
        listView9.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,l));


    }

    @Override
    public void initAction() {

    }
    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("科室信息");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void network() {
        RequestQueue queue = Volley.newRequestQueue(this);
        IStringRequest requset = new IStringRequest(Request.Method.GET,
                Constants.SERVER_ADDRESS_BACKUP + "department/?hosId=" + hosId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Map<String, Object> object = null;
                        Map<String, Object> data = null;

                        try {
                            object = JsonUtils.getMapObj(response);
                            data = JsonUtils.getMapObj(object.get("data").toString());



                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("aaa", error.toString());

                    }
                }
        );
        queue.add(requset);
    }
}
