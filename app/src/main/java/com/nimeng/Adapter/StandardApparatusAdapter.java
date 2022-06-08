package com.nimeng.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nimeng.View.R;
import com.nimeng.bean.StandardApparatusBean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/20 14:17
 * <p>
 * Changes (from 2022/6/20)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/20 : Create StandardApparatusAdapter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class StandardApparatusAdapter extends BaseAdapter {

    private List<StandardApparatusBean> list;
    private LayoutInflater layoutInflater;

    public StandardApparatusAdapter(List<StandardApparatusBean> list, Context context) {
        this.list = list;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        StandardApparatusViewHolder viewHolder;
        if(view==null){
            view=layoutInflater.inflate(R.layout.list_datarecord,null,false);
            viewHolder=new StandardApparatusViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder=(StandardApparatusViewHolder) view.getTag();
        }

        StandardApparatusBean standardApparatusBean=(StandardApparatusBean) getItem(i);



        viewHolder.name.setText(standardApparatusBean.getName());
        viewHolder.name.setTextSize(13);
        viewHolder.port.setText(String.valueOf(standardApparatusBean.getPort()));
        viewHolder.format.setText(standardApparatusBean.getFormat());
        viewHolder.rate.setText(String.valueOf(standardApparatusBean.getRate()));
        viewHolder.type.setText(String.valueOf(standardApparatusBean.getType()));
        viewHolder.model.setText(String.valueOf(standardApparatusBean.getModel()));
        viewHolder.agreement.setText(standardApparatusBean.getAgreement());
        viewHolder.number.setText(standardApparatusBean.getNumber());
        viewHolder.value.setText(standardApparatusBean.getValue());
        viewHolder.traceabilityUnit.setText(standardApparatusBean.getTraceabilityUnit());
        viewHolder.time.setText(standardApparatusBean.getTime());

        return view;


    }

    class StandardApparatusViewHolder{
        TextView name,port,format,rate,type,model,agreement,number,value,traceabilityUnit,time;
        public StandardApparatusViewHolder(View view){
            name=view.findViewById(R.id.list_s_name);
            port=view.findViewById(R.id.list_s_port);
            format=view.findViewById(R.id.list_s_format);
            rate=view.findViewById(R.id.list_s_rate);
            type=view.findViewById(R.id.list_s_type);
            model=view.findViewById(R.id.list_s_model);
            agreement=view.findViewById(R.id.list_s_agreement);
            number=view.findViewById(R.id.list_s_number);
            value=view.findViewById(R.id.list_s_value);
            traceabilityUnit=view.findViewById(R.id.list_s_traceabilityUnit);
            time=view.findViewById(R.id.list_s_time);

        }
    }

}
