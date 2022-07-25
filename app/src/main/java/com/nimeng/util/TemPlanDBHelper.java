package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;



import com.nimeng.bean.TemPlanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/21 15:19
 * <p>
 * Changes (from 2022/7/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/21 : Create TemPlanDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class TemPlanDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="templan";

    public TemPlanDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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
                "tem1 float(5) ,"+
                "tem2 float(5) ,"+
                "tem3 float(5) ,"+
                "tem4 float(5) ,"+
                "tem5 float(5) ,"+
                "isCheck tinyint(2)"+
                ")";

        sqLiteDatabase.execSQL(sql);

    }


    public boolean add(TemPlanBean temPlanBean){

        if(!tableIsExist(TABLENAME)){
            onCreate(db);
        }
        contentValues.put("name",temPlanBean.getName());
        contentValues.put("unitTime",temPlanBean.getUnitTime());
        contentValues.put("temWave",temPlanBean.getTemWave());
        contentValues.put("tem1",temPlanBean.getTem1());
        contentValues.put("tem2",temPlanBean.getTem2());
        contentValues.put("tem3",temPlanBean.getTem3());
        contentValues.put("tem4",temPlanBean.getTem4());
        contentValues.put("tem5",temPlanBean.getTem5());
        contentValues.put("isCheck",temPlanBean.getIsCheck());

        long result=db.insert(TABLENAME,null,contentValues);
        return result>0?true:false;

    }


    //删除
    public boolean delete(String ID){
        int result =db.delete(TABLENAME,"id=?",new String[]{ID});
        return result>0?true:false;
    }


    //查询
    public List<TemPlanBean> query(){
        List<TemPlanBean> list =new ArrayList<TemPlanBean>();

        if(!tableIsExist(TABLENAME)){
            return list;
        }

        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while (result.moveToNext()){
                TemPlanBean temPlanBean=new TemPlanBean();
                temPlanBean.setID(result.getInt(0));
                temPlanBean.setName(result.getString(1));
                temPlanBean.setUnitTime(result.getInt(2));
                temPlanBean.setTemWave(result.getFloat(3));
                temPlanBean.setTem1(result.getFloat(4));
                temPlanBean.setTem2(result.getFloat(5));
                temPlanBean.setTem3(result.getFloat(6));
                temPlanBean.setTem4(result.getFloat(7));
                temPlanBean.setTem5(result.getFloat(8));
                temPlanBean.setIsCheck(result.getInt(9));
                list.add(temPlanBean);

            }result.close();
        }

        return list;
    }



    //通过方案名称查询方案
    public TemPlanBean findTemPlanByName(String name){
        TemPlanBean temPlanBean=new TemPlanBean();

        if(!tableIsExist(TABLENAME)){
            temPlanBean.setName(null);
            temPlanBean.setUnitTime(0);
            temPlanBean.setTemWave(0);
            temPlanBean.setTem1(0);
            temPlanBean.setTem2(0);
            temPlanBean.setTem3(0);
            temPlanBean.setTem4(0);
            temPlanBean.setTem5(0);
            temPlanBean.setIsCheck(0);
            return temPlanBean;
        }






        Cursor result =db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            temPlanBean.setName(result.getString(1));
            temPlanBean.setUnitTime(result.getInt(2));
            temPlanBean.setTemWave(result.getFloat(3));
            temPlanBean.setTem1(result.getFloat(4));
            temPlanBean.setTem2(result.getFloat(5));
            temPlanBean.setTem3(result.getFloat(6));
            temPlanBean.setTem4(result.getFloat(7));
            temPlanBean.setTem5(result.getFloat(8));
            temPlanBean.setIsCheck(result.getInt(9));
            return temPlanBean;
        }
        temPlanBean.setName(null);
        temPlanBean.setUnitTime(0);
        temPlanBean.setTemWave(0);
        temPlanBean.setTem1(0);
        temPlanBean.setTem2(0);
        temPlanBean.setTem3(0);
        temPlanBean.setTem4(0);
        temPlanBean.setTem5(0);
        temPlanBean.setIsCheck(0);
        return temPlanBean;

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
