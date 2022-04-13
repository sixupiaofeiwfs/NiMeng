package com.nimeng.Model;

import com.nimeng.bean.EquipmentBean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/8 15:02
 * <p>
 * Changes (from 2022/4/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/8 : Create OnAddListener.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface OnAddListener<T> {


    //表示添加标签
    String TAG="添加";


    public void AddSuccess(T t );
    public void AddError(T t);


    void AddSuccess(EquipmentBean equipmentBean);
}
