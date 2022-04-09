package com.nimeng.dao;

import com.nimeng.bean.EquipmentBean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/8 10:41
 * <p>
 * Changes (from 2022/4/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/8 : Create IEquipmentDao.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IEquipmentDao {
    //添加新设备
    void addEquipment(EquipmentBean equipmentBean);
    //删除设备
    void deleteEquipmentByName(String equipment_name);
    //修改设备信息
    void updateEquipment(EquipmentBean equipmentBean);
    //通过设备名称查找设备
    EquipmentBean queryEquipmentByID(String equipment_id);
    //查找所有设备
    List<EquipmentBean> queryAllEquipment();

    //判断是否存在重复
    boolean isExistsEquipment(EquipmentBean equipmentBean);
}
