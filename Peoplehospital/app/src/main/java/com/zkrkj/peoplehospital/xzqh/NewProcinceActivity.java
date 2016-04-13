package com.zkrkj.peoplehospital.xzqh;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;
import com.zkrkj.peoplehospital.activity.FindHospitalActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import base.BaseActivity;
import bean.CityNewBean;
import util.CharacterParser;
import util.Constants;
import util.PinyinComparator;
import util.TitleBarUtils;
import util.ToastUtil;

public class NewProcinceActivity extends BaseActivity {
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private RecyclerView xzqh_rl;
    List<CityNewBean> lists = new ArrayList<CityNewBean>();
    private XZQHDBHelper dbHelper;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==0){
                xzqh_rl.setLayoutManager(new LinearLayoutManager(NewProcinceActivity.this));
                xzqh_rl.setAdapter(new MyAdapter());
            }else if(msg.what==1){
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_procince);
        dbHelper=new XZQHDBHelper(this);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        init();
    }

    private void init() {
        initTitle();
        initWidget();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                lists=dbHelper.queryAllProcince();
                listSort();
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void initWidget() {
        xzqh_rl= (RecyclerView) findViewById(R.id.xzqh_rl);
    }

    private void initTitle() {
        TitleBarUtils titleBarUtils = (TitleBarUtils) findViewById(R.id.titleBar);
        titleBarUtils.setTitle("选择地区");
        titleBarUtils.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void listSort(){
        lists = filledData(lists);
        // 根据a-z进行排序源数据
        Collections.sort(lists, pinyinComparator);
    }

    /**
     * @Description: 为ListView填充数据
     * @param: @param date
     * @param: @return
     */
    private List<CityNewBean> filledData(List<CityNewBean> data) {
        List<CityNewBean> mSortList = new ArrayList<CityNewBean>();

        for (int i = 0; i < data.size(); i++) {
            CityNewBean sortModel = new CityNewBean();
            sortModel.setZone_code(data.get(i).getZone_code());
            sortModel.setZone_desc(data.get(i).getZone_desc());
            sortModel.setZone_code_par(data.get(i).getZone_code_par());
            sortModel.setZone_level(data.get(i).getZone_level());
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(data.get(i).getZone_desc());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        String level;
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder=new MyHolder(LayoutInflater.from(NewProcinceActivity.this).inflate(R.layout.activity_group_member_item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyHolder){
                // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
                if (position == getPositionForSection(position)) {
                    ((MyHolder) holder).catalog.setVisibility(View.VISIBLE);
                    ((MyHolder) holder).catalog.setText(lists.get(position).getSortLetters());
                } else {
                    ((MyHolder) holder).catalog.setVisibility(View.GONE);
                }

                ((MyHolder) holder).title.setText(lists.get(position).getZone_desc());
            }
        }

        /**
         * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
         */
        public int getPositionForSection(int position) {
            for (int i = 0; i < getItemCount(); i++) {
                String sortStr = lists.get(i).getSortLetters();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == lists.get(position).getSortLetters().charAt(0)) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{

            TextView catalog,title;
            public MyHolder(View itemView) {
                super(itemView);
                catalog= (TextView) itemView.findViewById(R.id.catalog);
                title= (TextView) itemView.findViewById(R.id.title);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        level=lists.get(getPosition()).getZone_level();
                        if(level.equals("1")){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e(Constants.TAG,lists.get(getPosition()).getZone_code());
                                    lists=dbHelper.queryCityByCode(lists.get(getPosition()).getZone_code());
                                    listSort();
                                    handler.sendEmptyMessage(0);
                                }
                            }).start();
                        }else if(level.equals("2")){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    lists=dbHelper.queryCountyByCode(lists.get(getPosition()).getZone_code());
                                    listSort();
                                    handler.sendEmptyMessage(0);
                                }
                            }).start();
                        }else{
                            Intent intent =new Intent(NewProcinceActivity.this,FindHospitalActivity.class);
                            intent.putExtra("cityNewBean",lists.get(getPosition()));
                            startActivity(intent);
                        }
                    }
                });
            }
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
}
