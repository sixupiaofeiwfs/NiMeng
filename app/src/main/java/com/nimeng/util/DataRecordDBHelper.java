package com.nimeng.util;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nimeng.bean.DataRecodeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/15 10:27
 * <p>
 * Changes (from 2022/6/15)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/15 : Create DataRecordDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DataRecordDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="data";

    public DataRecordDBHelper(@NonNull Context context, @Nullable String name,@Nullable SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        db=this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "
                +TABLENAME+
                "("+
                "time varchar(30) not null,"+
                "settingTem float(5) not null,"+
                "realtimeTem float(5) not null,"+
                "settingHum float(5) not null,"+
                "realtimeHum float(5) not null"+
                ")";

        sqLiteDatabase.execSQL(sql);

    }



    //添加
    public boolean add(DataRecodeBean dataRecodeBean){
        contentValues.put("time",dataRecodeBean.getTime());
        contentValues.put("settingTem",dataRecodeBean.getSettingTem());
        contentValues.put("realtimeTem",dataRecodeBean.getRealtimeTem());
        contentValues.put("settingHum",dataRecodeBean.getSettingHum());
        contentValues.put("realtimeHum",dataRecodeBean.getRealtimeHum());

        long result=db.insert("data",null,contentValues);
        return result>0?true:false;
    }


    //查询
    public List<DataRecodeBean> query(){

        List<DataRecodeBean> list =new ArrayList<>();
        if(!tableIsExist(TABLENAME)){
            return list;
        }





        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while(result.moveToNext()){
                DataRecodeBean dataRecodeBean=new DataRecodeBean();
                dataRecodeBean.setTime(result.getString(0));
                dataRecodeBean.setSettingTem(result.getFloat(1));
                dataRecodeBean.setRealtimeTem(result.getFloat(2));
                dataRecodeBean.setSettingHum(result.getFloat(3));
                dataRecodeBean.setRealtimeHum(result.getFloat(4));

                list.add(dataRecodeBean);
            }result.close();
        }
        return  list;
    }



    //根据日期查找
    public DataRecodeBean findDataRecordByTime(String startTime, String endTime){
        DataRecodeBean dataRecodeBean=new DataRecodeBean();
        Cursor result=db.query(TABLENAME,null,"time between=? and=?",new String[]{startTime,endTime},null,null,null,null);
        if(result.getCount()==1){
            result.moveToFirst();
            dataRecodeBean.setTime(result.getString(0));
            dataRecodeBean.setSettingTem(result.getFloat(1));
            dataRecodeBean.setRealtimeTem(result.getFloat(2));
            dataRecodeBean.setSettingHum(result.getFloat(3));
            dataRecodeBean.setRealtimeHum(result.getFloat(4));
            return dataRecodeBean;
        }
        dataRecodeBean.setTime("");
        dataRecodeBean.setSettingTem(0);
        dataRecodeBean.setRealtimeTem(0);
        dataRecodeBean.setSettingHum(0);
        dataRecodeBean.setRealtimeHum(0);
        return dataRecodeBean;


    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }






}
