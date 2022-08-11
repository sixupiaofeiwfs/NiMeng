//package com.nimeng.Adapter;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.nimeng.View.R;
//import com.nimeng.bean.HumStandardApparatus;
//
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
//public class HumStandardApparatusAdapter extends BaseAdapter {
//
//    private List<HumStandardApparatus> list;
//    private LayoutInflater layoutInflater;
//
//    public HumStandardApparatusAdapter(List<HumStandardApparatus> list, Context context) {
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
//        HumHolder viewHolder;
//
//        Log.d("view是否为空", "getView: "+view);
//
//        if(view==null){
//            view=layoutInflater.inflate(R.layout.list_standardapparatus,null,false);
//            Log.d("第二次判断", "getView: "+view);
//            viewHolder=new HumHolder(view);
//            view.setTag(viewHolder);
//        }else{
//            viewHolder=(HumHolder) view.getTag();
//        }
//
//        HumStandardApparatus humStandardApparatus=(HumStandardApparatus) getItem(i);
//
//
//        System.out.println(humStandardApparatus.getID()+"---->"+humStandardApparatus.getName());
//
//        viewHolder.name.setText(humStandardApparatus.getName());
//        viewHolder.name.setTextSize(13);
//        viewHolder.port.setText(String.valueOf(humStandardApparatus.getPort()));
//        viewHolder.format.setText(humStandardApparatus.getFormat());
//        viewHolder.rate.setText(String.valueOf(humStandardApparatus.getRate()));
//        viewHolder.type.setText(String.valueOf(humStandardApparatus.getType()));
//        viewHolder.model.setText(String.valueOf(humStandardApparatus.getModel()));
//        viewHolder.agreement.setText(humStandardApparatus.getAgreement());
//        viewHolder.number.setText(humStandardApparatus.getNumber());
//        viewHolder.value.setText(humStandardApparatus.getValue());
//        viewHolder.traceabilityUnit.setText(humStandardApparatus.getTraceabilityUnit());
//        viewHolder.time.setText(humStandardApparatus.getTime());
//        if(humStandardApparatus.getIsCheck()==1){
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
//    class HumHolder{
//        TextView name,port,format,rate,type,model,agreement,number,value,traceabilityUnit,time,isCheck;
//        public HumHolder(View view){
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
