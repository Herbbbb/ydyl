package com.zkrkj.peoplehospital.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.MainActivity;
import com.zkrkj.peoplehospital.adapter.RecyclerBaseAdapter;
import com.zkrkj.peoplehospital.record.ReportQuery;
import com.zkrkj.peoplehospital.record.SeeDocDetail;
import com.zkrkj.peoplehospital.record.MedicalExpensesActivity;
import com.zkrkj.peoplehospital.record.OutpatientPrescriptionsActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import base.BaseFragment;
import base.OptsharepreInterface;
import bean.MyRecord;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.Constants;
import util.JsonUtils;
import util.ToastUtil;
import widget.FullyLinearLayoutManager;
import widget.ProgressDialogStyle;

/**
 * Describe:     就医记录
 * User:         LF
 * Date:         2016/3/28 15:54
 */
public class Frag_Talk extends BaseFragment implements View.OnClickListener {

    private LinearLayout ll_bgcx;
    Intent intent;

    @Bind(R.id.iv_photo)
    ImageView ivPhoto;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.rl_listview)
    RecyclerView rlListview;
    @Bind(R.id.tab_menzhenchufang)
    LinearLayout tabMenzhenchufang;
    @Bind(R.id.tab_yiliaofeiyong)
    LinearLayout tabYiliaofeiyong;
    private RecyclerView rl_listview;
    private SwipeRefreshLayout swipRefresh;
    private List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
    private Dialog pb;

    private OptsharepreInterface share;
    private MyRecyclerView adapter=new MyRecyclerView();
    private FullyLinearLayoutManager llManager=new FullyLinearLayoutManager(getActivity());
    private int totalCount=0;
    private int lastVisibleItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_talk, null);
        init();
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        share=new OptsharepreInterface(getActivity());
        initData();
        initWidget();
        initView();
    }

    private void initData() {
        pb = ProgressDialogStyle.createLoadingDialog(getActivity(), "请求中...");
        pb.show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        Log.e(Constants.TAG,share.getPres("token"));
        String url="http://192.168.1.252:9401/AppointMentServer/api/medicalRecords/patient-1?token="+share.getPres("token");
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadData(response);
                pb.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.ToastShow(getActivity(), Constants.VOLLEY_ERROR, true);
                pb.dismiss();
            }
        });
        queue.add(request);
    }

    private void initWidget() {
        ll_bgcx = (LinearLayout) view.findViewById(R.id.ll_bgcx);
        swipRefresh= (SwipeRefreshLayout) view.findViewById(R.id.swipRefresh);
        rl_listview = (RecyclerView) view.findViewById(R.id.rl_listview);

        swipRefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                totalCount = 0;
                lists.clear();
                initData();
            }
        });

        rl_listview.setLayoutManager(llManager);
        rl_listview.setFocusable(false);
        rl_listview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    if(totalCount!=0&&totalCount%Constants.PAGE_SIZE==0){
                        initData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = llManager.findLastVisibleItemPosition();
            }
        });

        ll_bgcx.setOnClickListener(this);
    }

    private void loadData(String json){
        Map<String,Object> object=null;
        String token="";
        String msg="";
        String success="";
        try{
            object= JsonUtils.getMapObj(json);
            success=object.get("success").toString();
            if (success.equals("0")){
                ToastUtil.ToastShow(getActivity(),  object.get("msg").toString(), true);
            }else{
                if(lists.size()>0){
                    lists.addAll(JsonUtils.getListMap(object.get("data").toString()));
                    adapter.notifyDataSetChanged();
                }else{
                    lists.addAll(JsonUtils.getListMap(object.get("data").toString()));
                    rl_listview.setAdapter(adapter);
                }
                totalCount=lists.size();
            }
        }catch (Exception e){

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bgcx:
                Intent intent = new Intent(getActivity(), ReportQuery.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyRecyclerView extends RecyclerBaseAdapter {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.my_record_rl_listitem, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).tv_title.setText(lists.get(position).get("diag").toString());
                String content=lists.get(position).get("hospitalName").toString()+"  "+lists.get(position).get("departmentName").toString();
                ((MyViewHolder) holder).tv_content.setText(content);
                ((MyViewHolder) holder).tv_date.setText(lists.get(position).get("diagDate").toString());
                //检查单
                if((int)lists.get(position).get("ckReportCount")==0){
                    ((MyViewHolder) holder).tv_jcd.setVisibility(View.GONE);
                }else{
                    ((MyViewHolder) holder).tv_jcd.setText("检查单"+lists.get(position).get("ckReportCount").toString());
                }
                //检验单
                if((int)lists.get(position).get("ttTestCount")==0){
                    ((MyViewHolder) holder).tv_jyd.setVisibility(View.GONE);
                }else{
                    ((MyViewHolder) holder).tv_jyd.setText("检验单"+lists.get(position).get("ttTestCount").toString());
                }
                //费用单
                if((int)lists.get(position).get("feeCount")==0){
                    ((MyViewHolder) holder).tv_fyd.setVisibility(View.GONE);
                }else{
                    ((MyViewHolder) holder).tv_fyd.setText("费用单"+lists.get(position).get("feeCount").toString());
                }
                //处方单
                if((int)lists.get(position).get("ttPCount")==0){
                    ((MyViewHolder) holder).tv_cfd.setVisibility(View.GONE);
                }else{
                    ((MyViewHolder) holder).tv_cfd.setText("处方单"+lists.get(position).get("ttPCount").toString());
                }
            }
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_title, tv_content,tv_date,tv_fyd,tv_cfd,tv_jyd,tv_jcd;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_content = (TextView) itemView.findViewById(R.id.tv_content);
                tv_date = (TextView) itemView.findViewById(R.id.tv_date);
                tv_fyd = (TextView) itemView.findViewById(R.id.tv_fyd);
                tv_cfd = (TextView) itemView.findViewById(R.id.tv_cfd);
                tv_jyd = (TextView) itemView.findViewById(R.id.tv_jyd);
                tv_jcd = (TextView) itemView.findViewById(R.id.tv_jcd);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), SeeDocDetail.class);
                        startActivity(intent);
                    }
                });

            }
        }
    }

    @Override
    protected void initView() {
        tabMenzhenchufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), OutpatientPrescriptionsActivity.class);
                startActivity(intent);
            }
        });
        tabYiliaofeiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), MedicalExpensesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initAction() {

    }

    @Override
    public int getViewId() {
        return 0;
    }
}
