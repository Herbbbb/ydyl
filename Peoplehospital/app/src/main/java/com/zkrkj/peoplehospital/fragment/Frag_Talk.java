package com.zkrkj.peoplehospital.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.login.LoginActivity;
import com.zkrkj.peoplehospital.record.MedicalExpensesActivity;
import com.zkrkj.peoplehospital.record.OutpatientPrescriptionsActivity;
import com.zkrkj.peoplehospital.record.ReportQuery;
import com.zkrkj.peoplehospital.record.SeeDocDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import base.BaseFragment;
import base.OptsharepreInterface;
import butterknife.Bind;
import butterknife.ButterKnife;
import util.Constants;
import util.JsonUtils;
import util.SerializableMap;
import util.ToastUtil;
import widget.ProgressDialogStyle;
import widget.RefreshLayout;

/**
 * Describe:     就医记录
 * User:         LF
 * Date:         2016/3/28 15:54
 */
public class Frag_Talk extends BaseFragment implements View.OnClickListener {

    private LinearLayout ll_bgcx;
    Intent intent;

    private ListView listview;
    private RefreshLayout swipRefresh;
    private List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
    private Dialog pb;

    private OptsharepreInterface share;
    private MyAdapter adapter;
    private int totalCount = 0;
    private int startIndex = 1;

    ImageView ivPhoto;
    TextView tvName;
    LinearLayout tabMenzhenchufang;
    LinearLayout tabYiliaofeiyong;
    LinearLayout llBgcx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_talk, null);
        init();
        ButterKnife.bind(this, view);
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        share = new OptsharepreInterface(getActivity());
        initWidget();
        initData();
        initView();
    }

    private void initData() {
        pb = ProgressDialogStyle.createLoadingDialog(getActivity(), "请求中...");
        pb.show();
        startIndex=(totalCount/Constants.PAGE_SIZE)*Constants.PAGE_SIZE+1;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        Log.e(Constants.TAG, share.getPres("token"));
        String url =  Constants.SERVER_ADDRESS_BACKUP+"medicalRecords/patient-"+Constants.getPatientId(getActivity())+"?limit="+Constants.PAGE_SIZE+"&offset="+startIndex+"&token=" + share.getPres("token");
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
        listview = (ListView) view.findViewById(R.id.lv);
        swipRefresh = (RefreshLayout) view.findViewById(R.id.rl);

        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.frag_talk_head_vew, null);
        listview.addHeaderView(headView);

        ivPhoto= (ImageView) headView.findViewById(R.id.iv_photo);
        tvName= (TextView) headView.findViewById(R.id.tv_name);
        tabMenzhenchufang= (LinearLayout) headView.findViewById(R.id.tab_menzhenchufang);
        tabYiliaofeiyong= (LinearLayout) headView.findViewById(R.id.tab_yiliaofeiyong);
        llBgcx= (LinearLayout) headView.findViewById(R.id.ll_bgcx);

        // 设置下拉刷新时的颜色值,颜色值需要定义在xml中
        swipRefresh.setColorSchemeResources(R.color.switch_thumb_disabled_material_dark,
                R.color.switch_thumb_normal_material_dark, R.color.switch_thumb_normal_material_light,
                R.color.abc_secondary_text_material_light);

        // 设置下拉刷新监听器
        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                totalCount=0;
                startIndex = 0;
                lists.clear();
                initData();
                swipRefresh.setRefreshing(false);
            }
        });

        // 加载监听器
        swipRefresh.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                if(totalCount!=0&&totalCount%Constants.PAGE_SIZE!=0){
                    swipRefresh.setLoading(false);
                    ToastUtil.ToastShow(getActivity(),"没有更多",false);
                }else{
                    initData();
                    swipRefresh.setLoading(false);
                }
            }
        });

        llBgcx.setOnClickListener(this);
    }

    private void loadData(String json) {
        Map<String, Object> object = null;
        String success = "";
        try {
            object = JsonUtils.getMapObj(json);
            success = object.get("success").toString();
            if (success.equals("0")) {
                ToastUtil.ToastShow(getActivity(), object.get("msg").toString(), true);
            } else if (success.equals("1")) {
                if (lists.size() > 0) {
                    lists.addAll(JsonUtils.getListMap(object.get("data").toString()));
                    adapter.notifyDataSetChanged();
                } else {
                    lists.addAll(JsonUtils.getListMap(object.get("data").toString()));
                    adapter = new MyAdapter();
                    listview.setAdapter(adapter);
                }
                totalCount = lists.size();
            } else {
                ToastUtil.ToastShow(getActivity(), "登录过期", true);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        } catch (Exception e) {

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

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyViewHolder holder = null;
            if (convertView == null) {
                holder = new MyViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.my_record_rl_listitem, null);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_titles);
                holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
                holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                holder.tv_fyd = (TextView) convertView.findViewById(R.id.tv_fyd);
                holder.tv_cfd = (TextView) convertView.findViewById(R.id.tv_cfd);
                holder.tv_jyd = (TextView) convertView.findViewById(R.id.tv_jyd);
                holder.tv_jcd = (TextView) convertView.findViewById(R.id.tv_jcd);
                convertView.setTag(holder);
            } else {
                holder = (MyViewHolder) convertView.getTag();
            }
            holder.tv_title.setText(lists.get(position).get("diag").toString());
            String content = lists.get(position).get("hospitalName").toString() + "  " + lists.get(position).get("departmentName").toString();
            holder.tv_content.setText(content);
            holder.tv_date.setText(lists.get(position).get("diagDate").toString());
            //检查单
            if ((int) lists.get(position).get("ckReportCount") == 0) {
                holder.tv_jcd.setVisibility(View.GONE);
            } else {
                holder.tv_jcd.setText("检查单" + lists.get(position).get("ckReportCount").toString());
            }
            //检验单
            if ((int) lists.get(position).get("ttTestCount") == 0) {
                holder.tv_jyd.setVisibility(View.GONE);
            } else {
                holder.tv_jyd.setText("检验单" + lists.get(position).get("ttTestCount").toString());
            }
            //费用单
            if ((int) lists.get(position).get("feeCount") == 0) {
                holder.tv_fyd.setVisibility(View.GONE);
            } else {
                holder.tv_fyd.setText("费用单" + lists.get(position).get("feeCount").toString());
            }
            //处方单
            if ((int) lists.get(position).get("ttPCount") == 0) {
                holder.tv_cfd.setVisibility(View.GONE);
            } else {
                holder.tv_cfd.setText("处方单" + lists.get(position).get("ttPCount").toString());
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SeeDocDetail.class);
                    SerializableMap tmpmap = new SerializableMap();
                    tmpmap.setMap(lists.get(position));
                    intent.putExtra("index", tmpmap);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        private class MyViewHolder {
            TextView tv_title, tv_content, tv_date, tv_fyd, tv_cfd, tv_jyd, tv_jcd;

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
