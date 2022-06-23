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
import com.nimeng.bean.GlobalVariable;
import com.nimeng.bean.PlanBean;

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
public class PlanAdapter extends BaseAdapter {
    private List<PlanBean> list;

    private LayoutInflater layoutInflater;




    public PlanAdapter(List<PlanBean> list, Context context) {
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
            view=layoutInflater.inflate(R.layout.list_plan,null,false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }

        PlanBean planBean=(PlanBean) getItem(i);

        Log.d("列表数据", "getView: "+planBean.getID()+"    "+"------"+planBean.getIsCheck()+"    "+planBean.getName()+"   "+planBean.getUnitTime()+"   "+planBean.getTemWave()+"      "+planBean.getHumWave());


        //viewHolder.id.setText(planBean.getID());
        viewHolder.name.setText(planBean.getName());
        viewHolder.name.setTextSize(13);
        viewHolder.unitTime.setText(String.valueOf( planBean.getUnitTime()));
        viewHolder.temWave.setText(String.valueOf( planBean.getTemWave()));
        viewHolder.humWave.setText(String.valueOf( planBean.getHumWave()));
        viewHolder.tem1.setText(planBean.getTem1());
        viewHolder.tem2.setText( planBean.getTem2());
        viewHolder.tem3.setText(planBean.getTem3());
        viewHolder.hum1.setText( planBean.getHum1());
        viewHolder.hum2.setText(planBean.getHum2());
        viewHolder.hum3.setText(planBean.getHum3());

        if(planBean.getIsCheck()==1 ){
           viewHolder.isCheck.setText("是");
        }else{
            viewHolder.isCheck.setText("否");
        }

        return view;

    }




    class ViewHolder{
        TextView id, name,unitTime,temWave,humWave,tem1,tem2,tem3,hum1,hum2,hum3,isCheck;
        public ViewHolder(View view){
            //id=view.findViewById(R.id.list_id);
            name=view.findViewById(R.id.list_name);
            unitTime=view.findViewById(R.id.list_unitTime);
            temWave=view.findViewById(R.id.list_temWave);
            humWave=view.findViewById(R.id.list_humWave);
            tem1=view.findViewById(R.id.list_tem1);
            tem2=view.findViewById(R.id.list_tem2);
            tem3=view.findViewById(R.id.list_tem3);
            hum1=view.findViewById(R.id.list_hum1);
            hum2=view.findViewById(R.id.list_hum2);
            hum3=view.findViewById(R.id.list_hum3);
            isCheck=view.findViewById(R.id.list_isCheck);

        }
    }


}
