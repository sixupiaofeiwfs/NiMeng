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
public interface OnAddEquipmentListener {
    //表示添加标签
    String TAG="添加";


    void AddEquipmentSuccess(EquipmentBean equipmentBean);


    void AddEquipmentError(EquipmentBean equipmentBean);
}
