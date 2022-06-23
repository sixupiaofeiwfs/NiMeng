package com.nimeng.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nimeng.View.R;
import com.nimeng.bean.QueryBean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/30 15:49
 * <p>
 * Changes (from 2022/6/30)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/30 : Create QueryAdapter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class QueryAdapter extends BaseAdapter {
    private List<QueryBean> list;
     private LayoutInflater layoutInflater;

    public QueryAdapter(List<QueryBean> list, Context context) {
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

        QueryView queryView;
        if(view==null){
            view=layoutInflater.inflate(R.layout.list_query,null,false);
            queryView=new QueryView(view);
            view.setTag(queryView);
        }else{
            queryView=(QueryView) view.getTag();
        }

        QueryBean queryBean=(QueryBean) getItem(i);
        queryView.name.setText(queryBean.getName());
        queryView.time.setText(String.valueOf(queryBean.getTime()));
        queryView.PVTem.setText(String.valueOf(queryBean.getPVTem()));
        queryView.PVHum.setText(String.valueOf(queryBean.getPVHum()));
        queryView.state.setText(queryBean.getState());
        queryView.standardTem.setText(String.valueOf(queryBean.getStandardTem()));
        queryView.standardHum.setText(String.valueOf(queryBean.getStandardHum()));
        queryView.dewPointTem.setText(String.valueOf(queryBean.getDewPointTem()));
        queryView.tem.setText(String.valueOf(queryBean.getTem()));


        return view;
    }





    class QueryView{
        TextView name,time,PVTem,PVHum,state,standardTem,standardHum,dewPointTem,tem;


        public QueryView(View view){
            name=view.findViewById(R.id.list_query_name);
            time=view.findViewById(R.id.list_query_time);
            PVTem=view.findViewById(R.id.list_query_PVTem);
            PVHum=view.findViewById(R.id.list_query_PVHum);
            state=view.findViewById(R.id.list_query_state);
            standardTem=view.findViewById(R.id.list_query_standardTem);
            standardHum=view.findViewById(R.id.list_query_standardHum);
            dewPointTem=view.findViewById(R.id.list_query_dewPointTem);
            tem=view.findViewById(R.id.list_query_tem);

        }
    }

}
