package com.nimeng.Model;

import java.util.Date;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/7 9:59
 * <p>
 * Changes (from 2022/4/7)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/7 : Create EquipmentDao.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IEquipmentMode {

    //添加
    void addEquipment(String equipment_id, String equipment_name, String  equipment_type, String equipment_IP, Date equipment_time, boolean switch1, boolean switch2, boolean switch3, OnAddListener listener);

}
