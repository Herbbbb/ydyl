package com.zkrkj.peoplehospital.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zkrkj.peoplehospital.MyApplication;
import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.User.AboutUs;
import com.zkrkj.peoplehospital.User.ChangePasswordActivity;
import com.zkrkj.peoplehospital.User.FeedBackActivity;
import com.zkrkj.peoplehospital.User.MyDocCard;
import com.zkrkj.peoplehospital.User.MyUserActivity;
import com.zkrkj.peoplehospital.User.PersonalDetail;
import com.zkrkj.peoplehospital.User.UnreadMessagesActivity;
import com.zkrkj.peoplehospital.activity.MainActivity;
import com.zkrkj.peoplehospital.registered.RegisteredHistory;

import java.util.List;
import java.util.Map;

import base.BaseFragment;
import base.OptsharepreInterface;
import bean.MessageBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import db.DataBaseManager;
import util.Constants;
import util.IStringRequest;
import util.JsonUtils;
import util.TitleBarUtils;
import util.ToastUtil;

/**
 * Created by lenovo on 2016/3/16.
 */

public class Frag_User extends BaseFragment implements View.OnClickListener {

    MessageBean message=new MessageBean();
    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.username_text)
    TextView usernameText;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.textView9)
    TextView textView9;
    @Bind(R.id.dangan)
    LinearLayout dangan;
    @Bind(R.id.yuyue)
    LinearLayout yuyue;
    @Bind(R.id.func)
    LinearLayout func;
    @Bind(R.id.yijian)
    LinearLayout yijian;
    @Bind(R.id.about)
    LinearLayout about;
    @Bind(R.id.update)
    LinearLayout update;
    @Bind(R.id.xiugaimima)
    LinearLayout xiugaimima;
    @Bind(R.id.resiglogin)
    LinearLayout resiglogin;
    @Bind(R.id.jiuyika)
    LinearLayout jiuyika;
    @Bind(R.id.tab_message)
    RelativeLayout tabMessage;
    @Bind(R.id.wodejiuzhenren)
    RelativeLayout wodejiuzhenren;
    @Bind(R.id.id_sum)
    TextView idSum;
    private Intent intent;
    private String name;
    private String idNo, gender;
    private RequestQueue queue;
    private String token;
    boolean first=true;
    private DataBaseManager db;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x123:
                    Log.i("hand","消息是"+msg.arg1);
                    idSum.setText(msg.arg1+"");
                    break;

            }
            super.handleMessage(msg);
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_user, null);

        ButterKnife.bind(this, view);
        initView();
        initTitle();
        return view;
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) view.findViewById(R.id.titleBar);
        titleBarUtils.setTitle("个人中心");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (!MyApplication.loginFlag) {
            usernameText.setText("未登录");
        }

        initView();
        initAction();
    }

    @Override
    protected void initView() {


        xiugaimima.setOnClickListener(this);

        jiuyika.setOnClickListener(this);
        about.setOnClickListener(this);
        usernameText.setOnClickListener(this);
        resiglogin.setOnClickListener(this);
        dangan.setOnClickListener(this);
        tabMessage.setOnClickListener(this);
        yijian.setOnClickListener(this);
        wodejiuzhenren.setOnClickListener(this);
        o = new OptsharepreInterface(getActivity());
        token = o.getPres("token");
        idSum.setText(o.getPres("unmsg"));

        if (MyApplication.loginFlag) {


            queue = Volley.newRequestQueue(getActivity());
            IStringRequest requset = new IStringRequest(Request.Method.GET,
                    Constants.SERVER_ADDRESS+"userinfo/summary?token=" + token,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("aaa", response);
                            parseUser(response);


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


    }

    private void insertdb(String response) {
        Map<String, Object> object = null;
        List< Map<String, Object>> data = null;
        try {
            object=JsonUtils.getMapObj(response);
            data = JsonUtils.getListMap(object.get("data").toString());
            Message msg=Message.obtain();
            msg.arg1=data.size();
            msg.what=0x123;
            Log.i("sizem",msg.arg1+"");
            handler.sendMessage(msg);

            Log.i("size",data.size()+"");
            o.putPres("unmsg",data.size()+"");

            for (int i=0;i<data.size();i++){
                message.setContext1(data.get(i).get("msg").toString());
                message.setContext(data.get(i).get("msg").toString());
                message.setMesid(data.get(i).get("id").toString());
                message.setMessagetype(data.get(i).get("msgtype").toString());
                message.setUpdate1(data.get(i).get("createtime").toString());
                  db=DataBaseManager.getInstance(getActivity());
                db.insertData1("m"+ MyApplication.phone,message);
                db.getDataCounts("m"+ MyApplication.phone);



            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseUser(String response) {
        Map<String, Object> object = null;
        Map<String, Object> data = null;
        Map<String, Object> user = null;

        try {
            object = JsonUtils.getMapObj(response);

                data = JsonUtils.getMapObj(object.get("data").toString());
            if (data.size()==0){

            }else {
                String sum = data.get("myPatientCount").toString();
                user = JsonUtils.getMapObj(data.get("user").toString());
                name = user.get("name").toString();
                Log.i("bbb", name);

                idNo = user.get("idNo").toString();
                gender = user.get("gender").toString();
                textView9.setText(sum);
                if (name.length()==0) {
                    usernameText.setText("请设置用户名");
                } else {
                    usernameText.setText(name);
                }
            }



        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @Override
    protected void initAction() {

        if (first&&MyApplication.loginFlag) {
            IStringRequest requset1 = new IStringRequest(Request.Method.GET,
                    Constants.SERVER_ADDRESS+"usermessage/unread?token=" + token,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("aaa", response);
                           // insertdb(response);


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("err", error.toString());

                        }
                    }
            );

            //queue.add(requset1);
            first=false;
        }
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


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.username_text:                           //个人信息
                if (MyApplication.loginFlag) {
                    intent = new Intent(getActivity(), PersonalDetail.class);
                    intent.putExtra("name", name);
                    intent.putExtra("idNo", idNo);
                    intent.putExtra("gender", gender);
                    startActivity(intent);
                } else {
                    ToastUtil.ToastShow(getActivity(), "您还没有登录，登录账号后再来吧", true);
                }
                break;
            case R.id.dangan:
                ToastUtil.ToastShow(getActivity(), "点击了健康档案", true);
                // Toast.makeText(getActivity(),"点击了健康档案",Toast.LENGTH_SHORT).show();
                break;
            case R.id.yuyue:
                intent = new Intent(getActivity(), RegisteredHistory.class);
                startActivity(intent);
                break;
            case R.id.setaccount:
                Toast.makeText(getActivity(), "点击了退出登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wodejiuzhenren:                         //我的就诊人
                if (MyApplication.loginFlag == false) {
                    ToastUtil.ToastShow(getActivity(), "您还没有登录，登录账号后再来吧", true);
                } else {
                    intent = new Intent(getActivity(), MyUserActivity.class);
                    intent.putExtra("type",0);
                    startActivity(intent);
                }
                break;
            case R.id.func:
                Toast.makeText(getActivity(), "点击了退出登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.yijian:
                if (MyApplication.loginFlag == false) {
                    ToastUtil.ToastShow(getActivity(), "您还没有登录，登录账号后再来吧", true);
                } else {
                    intent = new Intent(getActivity(), FeedBackActivity.class);//意见反馈
                    startActivity(intent);
                }
                break;
            case R.id.about:
                intent = new Intent(getActivity(), AboutUs.class);//意见反馈
                startActivity(intent);
                break;
            case R.id.update:
                Toast.makeText(getActivity(), "点击了退出登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.resiglogin:
                Toast.makeText(getActivity(), "点击了退出登录", Toast.LENGTH_SHORT).show();
                MyApplication.loginFlag = false;
                onResume();
                break;
            case R.id.jiuyika://我的就医卡
                intent = new Intent(getActivity(), MyDocCard.class);
                startActivity(intent);
                break;
            case R.id.tab_message:                         //未读消息
                if (MyApplication.loginFlag == false) {
                    ToastUtil.ToastShow(getActivity(), "您还没有登录，登录账号后再来吧", true);
                } else {
                    intent = new Intent(getActivity(), UnreadMessagesActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.xiugaimima:
                intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

}
