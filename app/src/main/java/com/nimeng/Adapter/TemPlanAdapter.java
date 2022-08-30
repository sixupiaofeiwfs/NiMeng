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

        viewHolder.points.setText(String.valueOf(temPlanBean.getTemPoints()));
        if(temPlanBean.getTemPoints()==0){
            viewHolder.tem1.setText("");
            viewHolder.tem2.setText("");
            viewHolder.tem3.setText("");
            viewHolder.tem4.setText("");
            viewHolder.tem5.setText("");
            viewHolder.tem6.setText("");
            viewHolder.tem7.setText("");
            viewHolder.tem8.setText("");
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }
        if(temPlanBean.getTemPoints()==1){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText("");
            viewHolder.tem3.setText("");
            viewHolder.tem4.setText("");
            viewHolder.tem5.setText("");
            viewHolder.tem6.setText("");
            viewHolder.tem7.setText("");
            viewHolder.tem8.setText("");
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==2){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText("");
            viewHolder.tem4.setText("");
            viewHolder.tem5.setText("");
            viewHolder.tem6.setText("");
            viewHolder.tem7.setText("");
            viewHolder.tem8.setText("");
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==3){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
            viewHolder.tem4.setText("");
            viewHolder.tem5.setText("");
            viewHolder.tem6.setText("");
            viewHolder.tem7.setText("");
            viewHolder.tem8.setText("");
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==4){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
            viewHolder.tem4.setText(String.valueOf( temPlanBean.getTem4()));
            viewHolder.tem5.setText("");
            viewHolder.tem6.setText("");
            viewHolder.tem7.setText("");
            viewHolder.tem8.setText("");
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==5){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
            viewHolder.tem4.setText(String.valueOf( temPlanBean.getTem4()));
            viewHolder.tem5.setText(String.valueOf( temPlanBean.getTem5()));
            viewHolder.tem6.setText("");
            viewHolder.tem7.setText("");
            viewHolder.tem8.setText("");
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==6){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
            viewHolder.tem4.setText(String.valueOf( temPlanBean.getTem4()));
            viewHolder.tem5.setText(String.valueOf( temPlanBean.getTem5()));
            viewHolder.tem6.setText(String.valueOf( temPlanBean.getTem6()));
            viewHolder.tem7.setText("");
            viewHolder.tem8.setText("");
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==7){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
            viewHolder.tem4.setText(String.valueOf( temPlanBean.getTem4()));
            viewHolder.tem5.setText(String.valueOf( temPlanBean.getTem5()));
            viewHolder.tem6.setText(String.valueOf( temPlanBean.getTem6()));
            viewHolder.tem7.setText(String.valueOf( temPlanBean.getTem7()));
            viewHolder.tem8.setText("");
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==8){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
            viewHolder.tem4.setText(String.valueOf( temPlanBean.getTem4()));
            viewHolder.tem5.setText(String.valueOf( temPlanBean.getTem5()));
            viewHolder.tem6.setText(String.valueOf( temPlanBean.getTem6()));
            viewHolder.tem7.setText(String.valueOf( temPlanBean.getTem7()));
            viewHolder.tem8.setText(String.valueOf( temPlanBean.getTem8()));
            viewHolder.tem9.setText("");
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==9){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
            viewHolder.tem4.setText(String.valueOf( temPlanBean.getTem4()));
            viewHolder.tem5.setText(String.valueOf( temPlanBean.getTem5()));
            viewHolder.tem6.setText(String.valueOf( temPlanBean.getTem6()));
            viewHolder.tem7.setText(String.valueOf( temPlanBean.getTem7()));
            viewHolder.tem8.setText(String.valueOf( temPlanBean.getTem8()));
            viewHolder.tem9.setText(String.valueOf( temPlanBean.getTem9()));
            viewHolder.tem10.setText("");
        }else if(temPlanBean.getTemPoints()==10){
            viewHolder.tem1.setText(String.valueOf( temPlanBean.getTem1()));
            viewHolder.tem2.setText( String.valueOf( temPlanBean.getTem2()));
            viewHolder.tem3.setText(String.valueOf( temPlanBean.getTem3()));
            viewHolder.tem4.setText(String.valueOf( temPlanBean.getTem4()));
            viewHolder.tem5.setText(String.valueOf( temPlanBean.getTem5()));
            viewHolder.tem6.setText(String.valueOf( temPlanBean.getTem6()));
            viewHolder.tem7.setText(String.valueOf( temPlanBean.getTem7()));
            viewHolder.tem8.setText(String.valueOf( temPlanBean.getTem8()));
            viewHolder.tem9.setText(String.valueOf( temPlanBean.getTem9()));
            viewHolder.tem10.setText(String.valueOf( temPlanBean.getTem10()));
        }

        if(temPlanBean.getIsCheck()==1 ){
            viewHolder.isCheck.setText("是");
        }else{
            viewHolder.isCheck.setText("否");
        }

        return view;

    }




    class ViewHolder{
        TextView id, name,unitTime,temWave,points,tem1,tem2,tem3,tem4,tem5,tem6,tem7,tem8,tem9,tem10,isCheck;
        public ViewHolder(View view){
            //id=view.findViewById(R.id.list_id);
            name=view.findViewById(R.id.list_templan_name);
            unitTime=view.findViewById(R.id.list_templan_unitTime);
            temWave=view.findViewById(R.id.list_templan_temWave);
            points=view.findViewById(R.id.list_templan_points);
            tem1=view.findViewById(R.id.list_templan_tem1);
            tem2=view.findViewById(R.id.list_templan_tem2);
            tem3=view.findViewById(R.id.list_templan_tem3);
            tem4=view.findViewById(R.id.list_templan_tem4);
            tem5=view.findViewById(R.id.list_templan_tem5);
            tem6=view.findViewById(R.id.list_templan_tem6);
            tem7=view.findViewById(R.id.list_templan_tem7);
            tem8=view.findViewById(R.id.list_templan_tem8);
            tem9=view.findViewById(R.id.list_templan_tem9);
            tem10=view.findViewById(R.id.list_templan_tem10);
            isCheck=view.findViewById(R.id.list_templan_isCheck);

        }
    }


}
