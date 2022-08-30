package com.nimeng.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nimeng.View.R;
import com.nimeng.bean.DataRecodeBean;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/15 9:38
 * <p>
 * Changes (from 2022/6/15)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/15 : Create DataRecordAdapter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DataRecordAdapter  extends BaseAdapter {

    private List<DataRecodeBean> list;

    private LayoutInflater layoutInflater;

    public DataRecordAdapter(List<DataRecodeBean> list, Context context){
        this.list=list;
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


        DataRecordViewHolder viewHolder;
        if(view==null){
            view=layoutInflater.inflate(R.layout.list_datarecord,null,false);
            viewHolder=new DataRecordViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder=(DataRecordViewHolder) view.getTag();
        }

        DataRecodeBean dataRecodeBean=(DataRecodeBean) getItem(i);



        viewHolder.time.setText(dataRecodeBean.getTime());
        viewHolder.time.setTextSize(13);
        viewHolder.settingTem.setText(String.valueOf(dataRecodeBean.getSettingTem()));
        viewHolder.realtimeTem.setText(String.valueOf(dataRecodeBean.getRealtimeTem()));
        viewHolder.settingHum.setText(String.valueOf(dataRecodeBean.getSettingHum()));
        viewHolder.realtimeHum.setText(String.valueOf(dataRecodeBean.getRealtimeHum()));
        return view;
    }


    class DataRecordViewHolder{
        TextView time,settingTem,realtimeTem,settingHum,realtimeHum;
        public DataRecordViewHolder(View view){
            time=view.findViewById(R.id.list_time);
            settingTem=view.findViewById(R.id.list_setting_tem);
            realtimeTem=view.findViewById(R.id.list_realtime_tem);
            settingHum=view.findViewById(R.id.list_setting_hum);
            realtimeHum=view.findViewById(R.id.list_realtime_hum);


        }
    }

}
