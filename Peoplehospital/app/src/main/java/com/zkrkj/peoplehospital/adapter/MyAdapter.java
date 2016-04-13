package com.zkrkj.peoplehospital.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.zkrkj.peoplehospital.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.ToastUtil;

/**
 * Describe:
 * Created by ${苗}
 * on 2016/4/7.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    List<String> list=new ArrayList<>();
    List<Map<String,Object>> list1=new ArrayList<>();
    private int j;

    public MyAdapter(Context context, List<String> list, List<Map<String, Object>> list1) {
        this.context = context;
        this.list = list;
        this.list1 = list1;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item_yuyuedoctor,null);
            holder.textView1= (TextView) view.findViewById(R.id.text1);
            holder.gridView= (GridView) view.findViewById(R.id.gridView1);

            final List<Map<String,Object>> list2=new ArrayList<>();
            final List<Map<String,Object>> list3=new ArrayList<>();
            final List<Map<String,Object>> list4=new ArrayList<>();
            for (int l=0;l<list1.size();l++){
                String time=list1.get(l).get("regisrationPeriodCd").toString();
                switch (time){
                    case "1":
                        list2.add(list1.get(l));
                        break;
                    case "2":
                        list3.add(list1.get(l));
                        break;
                    case "3":
                        list4.add(list1.get(l));
                        break;
                    case "":
                        list2.add(list1.get(l));
                        break;
                }
            }
            if (i==0) {
                holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent();



                        intent.putExtra("docId",list2.get(i).get("docId").toString());



                        ToastUtil.ToastShow(context,"预约跳转界面",false);
                    }
                });

                holder.gridView.setAdapter(new MyAdapter1(context, list2));
                view.setTag(holder);
            }else if (i==1){
                holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent();
                        intent.putExtra("docId",list3.get(i).get("docId").toString());


                        ToastUtil.ToastShow(context,"预约跳转界面",false);


                    }
                });
                holder.gridView.setAdapter(new MyAdapter1(context, list3));
                view.setTag(holder);
            }else if (i==2){
                holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent();
                        intent.putExtra("docId",list4.get(i).get("docId").toString());


                        ToastUtil.ToastShow(context,"预约跳转界面",false);



                    }
                });
                holder.gridView.setAdapter(new MyAdapter1(context, list4));
                view.setTag(holder);
            }

        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView1.setText(list.get(i).toString());
        return view;
    }
    class ViewHolder{

        TextView textView1;
        GridView gridView;
    }
    class MyAdapter1 extends BaseAdapter{
        Context context;
        List<Map<String,Object>> list1=new ArrayList<>();

        public MyAdapter1(Context context, List<Map<String, Object>> list1) {
            this.context = context;
            this.list1 = list1;
        }

        @Override
        public int getCount() {

            return list1.size();
        }

        @Override
        public Object getItem(int k) {
            return list1.get(k);
        }

        @Override
        public long getItemId(int k) {
            return k;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewH hold;
            if (view==null){
                hold=new ViewH();
              view=View.inflate(context,R.layout.item_haoyuan,null);
                hold.textView1= (TextView) view.findViewById(R.id.textView58);
                hold.textView2= (TextView) view.findViewById(R.id.textView59);
                hold.textView3= (TextView) view.findViewById(R.id.textView60);
                view.setTag(hold);
            }else {
                hold= (ViewH) view.getTag();
            }
           switch (list1.get(i).get("clinicCode").toString()){
               case "1":
                   hold.textView1.setText("普通门诊");
                   hold.textView2.setText("");
                   j=Integer.parseInt(list1.get(i).get("allowReservationNum").toString());
                   if (j>0){


                       hold.textView3.setText("预约>");
                   }else {
                       hold.textView3.setText("约满");
                       viewGroup.setEnabled(false);
                       view.setBackgroundColor(Color.parseColor("#E0E6E5"));
                   }
                   break;
               case "2":
                   hold.textView1.setText("副主任医师");

                   hold.textView2.setText(list1.get(i).get("expName").toString());
                   j=Integer.parseInt(list1.get(i).get("allowReservationNum").toString());
                   if (j>0){
                       hold.textView3.setText("预约>");
                   }else {
                       hold.textView3.setText("约满");
                       viewGroup.setEnabled(false);
                       view.setBackgroundColor(Color.parseColor("#E0E6E5"));

                   }
                   break;
               case "3":
                   hold.textView1.setText("主任医师");
                   hold.textView2.setText(list1.get(i).get("expName").toString());
                   int j=Integer.parseInt(list1.get(i).get("allowReservationNum").toString());
                   if (j>0){
                       hold.textView3.setText("预约>");
                   }else {
                       viewGroup.setEnabled(false);
                       view.setBackgroundColor(Color.parseColor("#E0E6E5"));
                       hold.textView3.setText("约满");
                   }
                   break;
               case "4":
                   hold.textView1.setText("特约专家");
                   hold.textView2.setText(list1.get(i).get("expName").toString());
                   j=Integer.parseInt(list1.get(i).get("clinicCode").toString());
                   if (j>0){
                       hold.textView3.setText("预约>");
                   }else {
                       viewGroup.setEnabled(false);
                       hold.textView3.setText("约满");
                   }
                   break;
           }

            return view;
        }
        class ViewH{
           TextView textView1,textView2,textView3;
        }
    }

}