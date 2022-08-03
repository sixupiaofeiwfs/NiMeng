package com.nimeng.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nimeng.View.R;
import com.nimeng.bean.TemPlanBean;


import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/6 14:04
 * <p>
 * Changes (from 2022/5/6)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/6 : Create PlanAdapter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class TemPlanAdapter extends BaseAdapter {
    private List<TemPlanBean> list;

    private LayoutInflater layoutInflater;




    public TemPlanAdapter(List<TemPlanBean> list, Context context) {
        this.list = list;
        layoutInflater=LayoutInflater.from(context);

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
        ViewHolder viewHolder;

        Log.d("view是否为空", "getView: "+view);
        if(view==null){
            view=layoutInflater.inflate(R.layout.list_templan,null,false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }

        TemPlanBean temPlanBean=(TemPlanBean) getItem(i);




        //viewHolder.id.setText(temPlanBean.getID());
        viewHolder.name.setText(temPlanBean.getName());
        viewHolder.name.setTextSize(13);
        viewHolder.unitTime.setText(String.valueOf( temPlanBean.getUnitTime()));
        viewHolder.temWave.setText(String.valueOf( temPlanBean.getTemWave()));
        viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
        viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
        viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
        viewHolder.tem4.setText( String.valueOf( temPlanBean.getTem4()));
        viewHolder.tem5.setText(String.valueOf( temPlanBean.getTem5()));
        if(temPlanBean.getIsCheck()==1 ){
            viewHolder.isCheck.setText("是");
        }else{
            viewHolder.isCheck.setText("否");
        }

        return view;

    }




    class ViewHolder{
        TextView id, name,unitTime,temWave,tem1,tem2,tem3,tem4,tem5,isCheck;
        public ViewHolder(View view){
            //id=view.findViewById(R.id.list_id);
            name=view.findViewById(R.id.list_templan_name);
            unitTime=view.findViewById(R.id.list_templan_unitTime);
            temWave=view.findViewById(R.id.list_templan_temWave);
            tem1=view.findViewById(R.id.list_templan_tem1);
            tem2=view.findViewById(R.id.list_templan_tem2);
            tem3=view.findViewById(R.id.list_templan_tem3);
            tem4=view.findViewById(R.id.list_templan_tem4);
            tem5=view.findViewById(R.id.list_templan_tem5);
            isCheck=view.findViewById(R.id.list_templan_isCheck);

        }
    }


}
