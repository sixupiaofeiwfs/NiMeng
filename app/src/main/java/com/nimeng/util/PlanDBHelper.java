package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nimeng.bean.PlanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/6 8:47
 * <p>
 * Changes (from 2022/5/6)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/6 : Create PlanDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class PlanDBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="plan";

    public PlanDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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
                "temWave float(5) not null,"+
                "humWave float(5) ,"+
                "tem1 float(5) ,"+
                "tem2 float(5) ,"+
                "tem3 float(5) ,"+
                "hum1 float(5) ,"+
                "hum2 float(5) ,"+
                "hum3 float(5)"+
                ")";

        sqLiteDatabase.execSQL(sql);

    }


    //添加
    public boolean add(PlanBean planBean){






        contentValues.put("name",planBean.getName());
        contentValues.put("unitTime",planBean.getUnitTime());
        contentValues.put("temWave",planBean.getTemWave());
        contentValues.put("humWave",planBean.getHumWave());
        contentValues.put("tem1",planBean.getTem1());
        contentValues.put("tem2",planBean.getTem2());
        contentValues.put("tem3",planBean.getTem3());
        contentValues.put("hum1",planBean.getHum1());
        contentValues.put("hum2",planBean.getHum2());
        contentValues.put("hum3",planBean.getHum3());





        long result= db.insert("plan",null,contentValues);

        return result>0? true:false;

    }

    //删除
    public boolean delete(String ID){
        int result =db.delete(TABLENAME,"id=?",new String[]{ID});
        return result>0? true:false;
    }


    //更新
    public boolean update(PlanBean planBean){
        contentValues.put("id",planBean.getID());
        contentValues.put("name",planBean.getName());
        contentValues.put("unitTime",planBean.getUnitTime());
        contentValues.put("temWave",planBean.getTemWave());
        contentValues.put("humWave",planBean.getHumWave());
        contentValues.put("tem1",planBean.getTem1());
        contentValues.put("tem2",planBean.getTem2());
        contentValues.put("tem3",planBean.getTem3());
        contentValues.put("hum1",planBean.getHum1());
        contentValues.put("hum2",planBean.getHum2());
        contentValues.put("hum3",planBean.getHum3());
        int result=db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf( planBean.getID())});

        return result>0 ? true:false;
    }



    //查询
    public List<PlanBean> query(){
        List<PlanBean> list =new ArrayList<>();
        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while (result.moveToNext()){
                PlanBean planBean=new PlanBean();
                planBean.setID(result.getInt(0));
                planBean.setName(result.getString(1));
                planBean.setUnitTime(result.getInt(2));
                planBean.setTemWave(result.getFloat(3));
                planBean.setHumWave(result.getFloat(4));
                planBean.setTem1(result.getString(5));
                planBean.setTem2(result.getString(6));
                planBean.setTem3(result.getString(7));
                planBean.setHum1(result.getString(8));
                planBean.setHum2(result.getString(9));
                planBean.setHum3(result.getString(10));

                list.add(planBean);

            }result.close();
        }

        return list;
    }





    //通过方案名称查询方案
    public PlanBean findPlanByName(String name){
        PlanBean planBean=new PlanBean();
        Cursor result =db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            planBean.setName(result.getString(1));
            planBean.setUnitTime(result.getInt(2));
            planBean.setTemWave(result.getFloat(3));
            planBean.setHumWave(result.getFloat(4));
            planBean.setTem1(result.getString(5));
            planBean.setTem2(result.getString(6));
            planBean.setTem3(result.getString(7));
            planBean.setHum1(result.getString(8));
            planBean.setHum2(result.getString(9));
            planBean.setHum3(result.getString(10));
            return planBean;
        }
        planBean.setName(null);
        planBean.setUnitTime(0);
        planBean.setTemWave(0);
        planBean.setHumWave(0);
        planBean.setTem1("");
        planBean.setTem2("");
        planBean.setTem3("");
        planBean.setHum1("");
        planBean.setHum2("");
        planBean.setHum3("");
        return planBean;

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
