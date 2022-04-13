package com.nimeng.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nimeng.bean.EquipmentBean;
import com.nimeng.util.EquipmentDBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/8 10:48
 * <p>
 * Changes (from 2022/4/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/8 : Create EquipmentDao.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EquipmentDao implements IEquipmentDao{
    private EquipmentDBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues values;

    public EquipmentDao(Context context){
        dbHelper=new EquipmentDBHelper(context);
    }

    //添加新设备
    @Override
    public void addEquipment(EquipmentBean equipmentBean) {
        database=dbHelper.getWritableDatabase();
        values=new ContentValues();
        values.put(EquipmentDBHelper.COLNUMID,equipmentBean.getId());
        values.put(EquipmentDBHelper.COLNUMTYPE,equipmentBean.getType());
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
        values.put(EquipmentDBHelper.COLNUMTIME, sdf1.format(equipmentBean.getTime()));
        values.put(EquipmentDBHelper.COLNUMIP,equipmentBean.getIP());
        values.put(EquipmentDBHelper.COLNUMSWITCH1, equipmentBean.isSwitch1());
        values.put(EquipmentDBHelper.COLNUMSWITCH2,equipmentBean.isSwitch2());
        values.put(EquipmentDBHelper.COLNUMSWITCH3,equipmentBean.isSwitch3());
        values.put(EquipmentDBHelper.COLNUMNAME,equipmentBean.getName());
        database.insert(EquipmentDBHelper.TABLENAME,null,values);
        database.close();
    }



    //删除设备
    @Override
    public void deleteEquipmentByName(String equipment_name) {
        database =dbHelper.getWritableDatabase();
        database.delete(EquipmentDBHelper.TABLENAME, EquipmentDBHelper.COLNUMNAME+"=?",new String[]{equipment_name});
        database.close();

    }


    //更新设备
    @Override
    public void updateEquipment(EquipmentBean equipmentBean) {
        database=dbHelper.getWritableDatabase();
        values=new ContentValues();
        values.put(EquipmentDBHelper.COLNUMTYPE,equipmentBean.getType());
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
        values.put(EquipmentDBHelper.COLNUMTIME, sdf1.format(equipmentBean.getTime()));
        values.put(EquipmentDBHelper.COLNUMIP,equipmentBean.getIP());
        values.put(EquipmentDBHelper.COLNUMSWITCH1, equipmentBean.isSwitch1());
        values.put(EquipmentDBHelper.COLNUMSWITCH2,equipmentBean.isSwitch2());
        values.put(EquipmentDBHelper.COLNUMSWITCH3,equipmentBean.isSwitch3());
        values.put(EquipmentDBHelper.COLNUMNAME,equipmentBean.getName());
        database.update(EquipmentDBHelper.TABLENAME,values, EquipmentDBHelper.COLNUMID+"=?",new String[]{equipmentBean.getId()});
    }

    @SuppressLint("Range")
    @Override
    public EquipmentBean queryEquipmentByID(String equipment_id) {
        database=dbHelper.getReadableDatabase();
        Cursor cursor=database.query(EquipmentDBHelper.TABLENAME,null, EquipmentDBHelper.COLNUMID+"=?",null,null,null,null);
        EquipmentBean equipmentBean=null;
        while(cursor.moveToNext()){
            equipmentBean=new EquipmentBean();
            equipmentBean.setName(cursor.getString(cursor.getColumnIndex(EquipmentDBHelper.COLNUMNAME)));
            SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

            try {
                equipmentBean.setTime(sdf1.parse( cursor.getString(cursor.getColumnIndex(EquipmentDBHelper.COLNUMTIME))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            equipmentBean.setType(cursor.getString(cursor.getColumnIndex(EquipmentDBHelper.COLNUMTYPE)));
            equipmentBean.setIP(cursor.getString(cursor.getColumnIndex(EquipmentDBHelper.COLNUMIP)));
            equipmentBean.setSwitch1(Boolean.valueOf( cursor.getString(cursor.getColumnIndex(EquipmentDBHelper.COLNUMSWITCH1))));
            equipmentBean.setSwitch2(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(EquipmentDBHelper.COLNUMSWITCH2)))) ;
            equipmentBean.setSwitch3(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(EquipmentDBHelper.COLNUMSWITCH3))));
        }
        cursor.close();
        return equipmentBean;
    }

    @Override
    public List<EquipmentBean> queryAllEquipment() {

        return null;


    }

    @Override
    public boolean isExistsEquipment(EquipmentBean equipmentBean) {
        EquipmentBean isExit=queryEquipmentByID(equipmentBean.getId());
        return isExit==null?false:true;
    }
}
