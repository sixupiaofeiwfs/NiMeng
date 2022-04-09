package com.nimeng.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nimeng.bean.EquipmentBean;
import java.util.List;
/**
 * Author: wfs
 * <p>
 * Create: 2022/4/7 14:02
 * <p>
 * Changes (from 2022/4/7)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/7 : Create EquipmentAdapter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EquipmentAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<EquipmentBean> list;

    public EquipmentAdapter(Context context,List<EquipmentBean> list){
        inflater=LayoutInflater.from(context);
        this.list=list;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        return null;
    }


}
