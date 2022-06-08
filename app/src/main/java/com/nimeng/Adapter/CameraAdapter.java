package com.nimeng.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nimeng.View.R;
import com.nimeng.bean.CameraBean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/21 14:42
 * <p>
 * Changes (from 2022/6/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/21 : Create CameraAdapter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class CameraAdapter extends BaseAdapter {

    private List<CameraBean> list;
    private LayoutInflater layoutInflater;

    public CameraAdapter(List<CameraBean> list, Context context) {
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

        ViewHolder viewHolder;
        if(view==null){
            view=layoutInflater.inflate(R.layout.list_camera,null,false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }

        CameraBean cameraBean=(CameraBean) getItem(i);

        viewHolder.name.setText(cameraBean.getName());
        viewHolder.name.setTextSize(13);
        viewHolder.port.setText(cameraBean.getPort());
        return view;

    }


    class ViewHolder{

        TextView name,port;
        public ViewHolder(View view){
            name=view.findViewById(R.id.list_c_name);
            port=view.findViewById(R.id.list_c_port);
        }
    }

}
