//package com.nimeng.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.nimeng.View.R;
//import com.nimeng.bean.TemStandarApparatus;
//
//import java.util.List;
//
///**
// * Author: wfs
// * <p>
// * Create: 2022/6/20 14:17
// * <p>
// * Changes (from 2022/6/20)
// * <p>
// * -----------------------------------------------------------------
// * <p>
// * 2022/6/20 : Create StandardApparatusAdapter.java (wfs);
// * <p>
// * -----------------------------------------------------------------
// */
//public class TemStandardApparatusAdapter extends BaseAdapter {
//
//    private List<TemStandarApparatus> list;
//    private LayoutInflater layoutInflater;
//
//    public TemStandardApparatusAdapter(List<TemStandarApparatus> list, Context context) {
//        this.list = list;
//        layoutInflater=LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return list.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        System.out.println("adapter开始调佣----->");
//
//        StandardApparatusViewHolder viewHolder;
//        if(view==null){
//            view=layoutInflater.inflate(R.layout.list_standardapparatus,null,false);
//            viewHolder=new StandardApparatusViewHolder(view);
//            view.setTag(viewHolder);
//        }else{
//            viewHolder=(StandardApparatusViewHolder) view.getTag();
//        }
//
//        TemStandarApparatus temStandarApparatus=(TemStandarApparatus) getItem(i);
//
//
//
//        viewHolder.name.setText(temStandarApparatus.getName());
//        viewHolder.name.setTextSize(13);
//        viewHolder.port.setText(String.valueOf(temStandarApparatus.getPort()));
//        viewHolder.format.setText(temStandarApparatus.getFormat());
//        viewHolder.rate.setText(String.valueOf(temStandarApparatus.getRate()));
//        viewHolder.type.setText(String.valueOf(temStandarApparatus.getType()));
//        viewHolder.model.setText(String.valueOf(temStandarApparatus.getModel()));
//        viewHolder.agreement.setText(temStandarApparatus.getAgreement());
//        viewHolder.number.setText(temStandarApparatus.getNumber());
//        viewHolder.value.setText(temStandarApparatus.getValue());
//        viewHolder.traceabilityUnit.setText(temStandarApparatus.getTraceabilityUnit());
//        viewHolder.time.setText(temStandarApparatus.getTime());
//
//        System.out.println("查询到的结果---->"+temStandarApparatus.getID()+"------------>"+temStandarApparatus.getIsCheck());
//
//        if(temStandarApparatus.getIsCheck()==1){
//            viewHolder.isCheck.setText("是");
//        }else{
//            viewHolder.isCheck.setText("否");
//        }
//
//        return view;
//
//
//    }
//
//    class StandardApparatusViewHolder{
//        TextView name,port,format,rate,type,model,agreement,number,value,traceabilityUnit,time,isCheck;
//        public StandardApparatusViewHolder(View view){
//            name=view.findViewById(R.id.list_s_name);
//            port=view.findViewById(R.id.list_s_port);
//            format=view.findViewById(R.id.list_s_format);
//            rate=view.findViewById(R.id.list_s_rate);
//            type=view.findViewById(R.id.list_s_type);
//            model=view.findViewById(R.id.list_s_model);
//            agreement=view.findViewById(R.id.list_s_agreement);
//            number=view.findViewById(R.id.list_s_number);
//            value=view.findViewById(R.id.list_s_value);
//            traceabilityUnit=view.findViewById(R.id.list_s_traceabilityUnit);
//            time=view.findViewById(R.id.list_s_time);
//            isCheck=view.findViewById(R.id.list_s_isCheck);
//
//        }
//    }
//
//}
