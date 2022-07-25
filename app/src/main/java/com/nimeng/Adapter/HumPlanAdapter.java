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
import com.nimeng.bean.HumPlanBean;


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
public class HumPlanAdapter extends BaseAdapter {
    private List<HumPlanBean> list;

    private LayoutInflater layoutInflater;




    public HumPlanAdapter(List<HumPlanBean> list, Context context) {
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
            view=layoutInflater.inflate(R.layout.list_humplan,null,false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }

        HumPlanBean humPlanBean=(HumPlanBean) getItem(i);




        //viewHolder.id.setText(humPlanBean.getID());
        viewHolder.name.setText(humPlanBean.getName());
        viewHolder.name.setTextSize(13);
        viewHolder.unitTime.setText(String.valueOf( humPlanBean.getUnitTime()));
        viewHolder.humWave.setText(String.valueOf( humPlanBean.getHumWave()));
        viewHolder.hum1.setText(String.valueOf( humPlanBean.getHum1()));
        viewHolder.hum2.setText( String.valueOf( humPlanBean.getHum2()));
        viewHolder.hum3.setText(String.valueOf( humPlanBean.getHum3()));
        viewHolder.hum4.setText( String.valueOf( humPlanBean.getHum4()));
        viewHolder.hum5.setText(String.valueOf( humPlanBean.getHum5()));
        if(humPlanBean.getIsCheck()==1 ){
            viewHolder.isCheck.setText("是");
        }else{
            viewHolder.isCheck.setText("否");
        }

        return view;

    }




    class ViewHolder{
        TextView id, name,unitTime,humWave,hum1,hum2,hum3,hum4,hum5,isCheck;
        public ViewHolder(View view){
            //id=view.findViewById(R.id.list_id);
            name=view.findViewById(R.id.list_humplan_name);
            unitTime=view.findViewById(R.id.list_humplan_unitTime);
            humWave=view.findViewById(R.id.list_humplan_humWave);
            hum1=view.findViewById(R.id.list_humplan_hum1);
            hum2=view.findViewById(R.id.list_humplan_hum2);
            hum3=view.findViewById(R.id.list_humplan_hum3);
            hum4=view.findViewById(R.id.list_humplan_hum4);
            hum5=view.findViewById(R.id.list_humplan_hum5);
            isCheck=view.findViewById(R.id.list_humplan_isCheck);

        }
    }


}
