package com.nimeng.Model;

import android.content.Context;

import com.nimeng.bean.EquipmentBean;
import com.nimeng.dao.EquipmentDao;

import java.util.Date;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/7 13:04
 * <p>
 * Changes (from 2022/4/7)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/7 : Create EquipmentDaoImpl.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EquipmentMode implements IEquipmentMode {
    private EquipmentDao equipmentDao;

    public EquipmentMode(Context context) {
        equipmentDao = new EquipmentDao(context);
    }


    @Override
    public void addEquipment(String equipment_id, String equipment_name, String equipment_type, String equipment_IP, Date equipment_time, boolean switch1, boolean switch2, boolean switch3, OnAddEquipmentListener listener) {
        EquipmentBean equipmentBean=new EquipmentBean(equipment_id,equipment_name,equipment_type,equipment_time,equipment_IP,switch1,switch2,switch3);
        if(equipmentDao.isExistsEquipment(equipmentBean)){
            listener.AddEquipmentError(equipmentBean);
        }
        equipmentDao.addEquipment(equipmentBean);
        listener.AddEquipmentSuccess(equipmentBean);
    }
}

