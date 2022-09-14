package com.nimeng.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nimeng.View.R;
import com.nimeng.bean.StandardApparatus;
import com.nimeng.util.StandardApparatusDBHelper;

import java.util.List;

public class StandardApparatusAdapter extends BaseAdapter {


    private List<StandardApparatus> list;
    private LayoutInflater layoutInflater;

    public StandardApparatusAdapter(List<StandardApparatus> list, Context context) {
        this.list = list;
        this.layoutInflater = layoutInflater.from(context);

        System.out.println("list----->"+list);

    }


    public List<StandardApparatus> getList() {
        return list;
    }

    public void setList(List<StandardApparatus> list) {
        this.list = list;
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

        StandardApparatusView standardApparatusView;
        if(view==null){
            view=layoutInflater.inflate(R.layout.list_standardapparatus,null,false);
            standardApparatusView=new StandardApparatusView(view);
            view.setTag(standardApparatusView);
        }else{
            standardApparatusView=(StandardApparatusView) view.getTag();
        }

        StandardApparatus standardApparatus=(StandardApparatus) getItem(i);

        System.out.println("-------------------");
        System.out.println(standardApparatus+"------------"+standardApparatus.getQuantity());
        System.out.println("-------------------");

        standardApparatusView.slave.setText(String.valueOf(standardApparatus.getSlave()));
        standardApparatusView.name.setText(standardApparatus.getName());
        standardApparatusView.name.setTextSize(13);
        standardApparatusView.port.setText(String.valueOf(standardApparatus.getPort()));
        standardApparatusView.format.setText(standardApparatus.getFormat());
        standardApparatusView.rate.setText(String.valueOf(standardApparatus.getRate()));
        standardApparatusView.type.setText(String.valueOf(standardApparatus.getType()));
        standardApparatusView.model.setText(String.valueOf(standardApparatus.getModel()));
        standardApparatusView.agreement.setText(standardApparatus.getAgreement());
        standardApparatusView.number.setText(standardApparatus.getNumber());
        standardApparatusView.quantity.setText(String.valueOf(standardApparatus.getQuantity()));
        standardApparatusView.traceabilityUnit.setText(standardApparatus.getTraceabilityUnit());
        standardApparatusView.time.setText(standardApparatus.getTime());
        if(standardApparatus.getState()==1){
            standardApparatusView.state.setText("单一标准器");
        }if(standardApparatus.getState()==2){
            standardApparatusView.state.setText("可转换单一标准器");
        }if(standardApparatus.getState()==3){
            standardApparatusView.state.setText("复杂标准器");
        }
        standardApparatusView.temAddress.setText(standardApparatus.getTemStartAddress());
        standardApparatusView.humAddress.setText(standardApparatus.getHumStartAddress());
        standardApparatusView.count.setText(String.valueOf(standardApparatus.getCount()));


        if(standardApparatus.getIsCheck()==1){
            standardApparatusView.isCheck.setText("是");
        }else{
            standardApparatusView.isCheck.setText("否");
        }
        return view;
    }



    class StandardApparatusView{

        TextView name,slave,port,format,rate,type,model,agreement,number,quantity,traceabilityUnit,time,isCheck,state,temAddress,humAddress,count;
        public StandardApparatusView(View view){
            name=view.findViewById(R.id.list_s_name);
            slave=view.findViewById(R.id.list_s_slave);
            port=view.findViewById(R.id.list_s_port);
            format=view.findViewById(R.id.list_s_format);
            rate=view.findViewById(R.id.list_s_rate);
            type=view.findViewById(R.id.list_s_type);
            model=view.findViewById(R.id.list_s_model);
            agreement=view.findViewById(R.id.list_s_agreement);
            number=view.findViewById(R.id.list_s_number);
            quantity=view.findViewById(R.id.list_s_quantity);
            traceabilityUnit=view.findViewById(R.id.list_s_traceabilityUnit);
            time=view.findViewById(R.id.list_s_time);
            isCheck=view.findViewById(R.id.list_s_isCheck);
            state=view.findViewById(R.id.list_s_state);
            temAddress=view.findViewById(R.id.list_s_temAddress);
            humAddress=view.findViewById(R.id.list_s_humAddress);
            count=view.findViewById(R.id.list_s_count);



        }
    }

}
