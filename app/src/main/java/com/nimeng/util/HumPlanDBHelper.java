package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nimeng.bean.HumPlanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/21 15:54
 * <p>
 * Changes (from 2022/7/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/21 : Create HumPlanDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class HumPlanDBHelper extends BaseUtil{
    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="humplan";

    public HumPlanDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "
                +TABLENAME+
                " ("+
                "id integer primary key,"+
                "name varchar(20) not null,"+
                "unitTime  tinyint(2) not null,"+
                "humWave float(5) not null,"+
                "hum1 float(5) ,"+
                "hum2 float(5) ,"+
                "hum3 float(5) ,"+
                "hum4 float(5) ,"+
                "hum5 float(5) ,"+
                "isCheck tinyint(2)"+
                ")";

        sqLiteDatabase.execSQL(sql);

    }


    public boolean add(HumPlanBean humPlanBean){

        if(!tableIsExist(TABLENAME)){
            onCreate(db);
        }


        contentValues.put("name",humPlanBean.getName());
        contentValues.put("unitTime",humPlanBean.getUnitTime());
        contentValues.put("humWave",humPlanBean.getHumWave());
        contentValues.put("hum1",humPlanBean.getHum1());
        contentValues.put("hum2",humPlanBean.getHum2());
        contentValues.put("hum3",humPlanBean.getHum3());
        contentValues.put("hum4",humPlanBean.getHum4());
        contentValues.put("hum5",humPlanBean.getHum5());
        contentValues.put("isCheck",humPlanBean.getIsCheck());

        long result=db.insert(TABLENAME,null,contentValues);
        return result>0?true:false;

    }


    //删除
    public boolean delete(String ID){
        int result =db.delete(TABLENAME,"id=?",new String[]{ID});
        return result>0?true:false;
    }


    //查询
    public List<HumPlanBean> query(){
        List<HumPlanBean> list =new ArrayList<HumPlanBean>();

        if(!tableIsExist(TABLENAME)){
            return list;
        }

        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while (result.moveToNext()){
                HumPlanBean humPlanBean=new HumPlanBean();
                humPlanBean.setID(result.getInt(0));
                humPlanBean.setName(result.getString(1));
                humPlanBean.setUnitTime(result.getInt(2));
                humPlanBean.setHumWave(result.getFloat(3));
                humPlanBean.setHum1(result.getFloat(4));
                humPlanBean.setHum2(result.getFloat(5));
                humPlanBean.setHum3(result.getFloat(6));
                humPlanBean.setHum4(result.getFloat(7));
                humPlanBean.setHum5(result.getFloat(8));
                humPlanBean.setIsCheck(result.getInt(9));
                list.add(humPlanBean);

            }result.close();
        }

        return list;
    }



    //通过方案名称查询方案
    public HumPlanBean findHumPlanByName(String name){
        HumPlanBean humPlanBean=new HumPlanBean();
        if(!tableIsExist(TABLENAME)){
            Log.d("数据表不存在", "findHumPlanByName: ");
            humPlanBean.setName(null);
            humPlanBean.setUnitTime(0);
            humPlanBean.setHumWave(0);
            humPlanBean.setHum1(0);
            humPlanBean.setHum2(0);
            humPlanBean.setHum3(0);
            humPlanBean.setHum4(0);
            humPlanBean.setHum5(0);
            humPlanBean.setIsCheck(0);
            return humPlanBean;
        }




        Cursor result =db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            humPlanBean.setName(result.getString(1));
            humPlanBean.setUnitTime(result.getInt(2));
            humPlanBean.setHumWave(result.getFloat(3));
            humPlanBean.setHum1(result.getFloat(4));
            humPlanBean.setHum2(result.getFloat(5));
            humPlanBean.setHum3(result.getFloat(6));
            humPlanBean.setHum4(result.getFloat(7));
            humPlanBean.setHum5(result.getFloat(8));
            humPlanBean.setIsCheck(result.getInt(9));
            return humPlanBean;
        }
        humPlanBean.setName(null);
        humPlanBean.setUnitTime(0);
        humPlanBean.setHumWave(0);
        humPlanBean.setHum1(0);
        humPlanBean.setHum2(0);
        humPlanBean.setHum3(0);
        humPlanBean.setHum4(0);
        humPlanBean.setHum5(0);
        humPlanBean.setIsCheck(0);
        return humPlanBean;

    }



    //更新被选中的信息
    public boolean updateCheck(int id,int isCheckID){

        //删除之前的被选中
        Log.d("之前选中的ID", "updateCheck: "+isCheckID);
        if(isCheckID!=0){
            // contentValues.put("isCheck",0);
            // int result1=db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf(isCheckID)});
            String sql="update "+TABLENAME+" set isCheck=0 where id="+isCheckID;
            db.execSQL(sql);

        }

        //contentValues.put("isCheck",1);
        // int result=db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf(id)});
        Log.d("需要设置的ID", "updateCheck: "+id);
        String sql="update "+TABLENAME+" set isCheck=1 where id="+id;
        db.execSQL(sql);
        return true;
    }
}
