package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nimeng.bean.HumStandardApparatus;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/20 15:13
 * <p>
 * Changes (from 2022/6/20)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/20 : Create StandardApparatusDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class HumStandardApparatusDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="humstandardapparatus";


    public HumStandardApparatusDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "
                +TABLENAME+
                "("+
                "id integer primary key  AUTOINCREMENT,"+
                "name varchar(30) not null,"+
                "port int not null,"+
                "format varchar not null,"+
                "rate int not null,"+
                "type varchar not null,"+
                "model varchar not null,"+
                "agreement varchar not null,"+
                "number varchar not null,"+
                "value varchar not null,"+
                "traceabilityUnit varchar not null,"+
                "time varchar not null,"+
                 "isCheck tinyint(2)"+
                ")";
        sqLiteDatabase.execSQL(sql);


    }



    //添加
    public boolean add(HumStandardApparatus humStandarApparatus){

        if(!tableIsExist(TABLENAME)){
            onCreate(db);
        }

        contentValues.put("name",humStandarApparatus.getName());
        contentValues.put("port",humStandarApparatus.getPort());
        contentValues.put("format",humStandarApparatus.getFormat());
        contentValues.put("rate",humStandarApparatus.getRate());
        contentValues.put("type",humStandarApparatus.getType());
        contentValues.put("model",humStandarApparatus.getModel());
        contentValues.put("agreement",humStandarApparatus.getAgreement());
        contentValues.put("number",humStandarApparatus.getNumber());
        contentValues.put("value",humStandarApparatus.getValue());
        contentValues.put("traceabilityUnit",humStandarApparatus.getTraceabilityUnit());
        contentValues.put("time",humStandarApparatus.getTime());
        contentValues.put("isCheck",humStandarApparatus.getIsCheck());


        long result =db.insert(TABLENAME,null,contentValues);
     //   db.close();
        return result >0? true:false;
    }


    //删除
    public boolean delete(int ID){
        int result=db.delete(TABLENAME,"id=?",new String[]{String.valueOf(ID)});
      //  db.close();
        return result>0?true:false;
    }




    //查询
    public List<HumStandardApparatus> query(){
        List<HumStandardApparatus> list =new ArrayList<HumStandardApparatus>();

        Cursor result=null;
        try{

            result=  db.query(TABLENAME,null,null,null,null,null,null);

        }
        catch (Exception e){
            e.printStackTrace();
        //    db.close();
            result.close();
            return null;
        }



        if(result!=null){
            while (result.moveToNext()){
                HumStandardApparatus humStandarApparatus=new HumStandardApparatus();
                humStandarApparatus.setID(result.getInt(0));
                humStandarApparatus.setName(result.getString(1));
                humStandarApparatus.setPort(result.getInt(2));
                humStandarApparatus.setFormat(result.getString(3));
                humStandarApparatus.setRate(result.getInt(4));
                humStandarApparatus.setType(result.getString(5));
                humStandarApparatus.setModel(result.getString(6));
                humStandarApparatus.setAgreement(result.getString(7));
                humStandarApparatus.setNumber(result.getString(8));
                humStandarApparatus.setValue(result.getString(9));
                humStandarApparatus.setTraceabilityUnit(result.getString(10));
                humStandarApparatus.setTime(result.getString(11));
                humStandarApparatus.setIsCheck(result.getInt(12));
                list.add(humStandarApparatus);

            }
        }
      //  db.close();
        result.close();
        return list;
    }





    //通过方案名称查询方案
    public HumStandardApparatus findByName(String name){
        HumStandardApparatus humStandarApparatus=new HumStandardApparatus();

        Cursor result=null;
        try{

            result= db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        }
        catch (Exception e){
            humStandarApparatus.setName(null);
            humStandarApparatus.setPort(0);
            humStandarApparatus.setFormat("");
            humStandarApparatus.setRate(0);
            humStandarApparatus.setType("");
            humStandarApparatus.setModel("");
            humStandarApparatus.setAgreement("");
            humStandarApparatus.setNumber("");
            humStandarApparatus.setValue("");
            humStandarApparatus.setTraceabilityUnit("");
            humStandarApparatus.setTime("");
            humStandarApparatus.setIsCheck(0);
         //   db.close();
            result.close();
            return humStandarApparatus;

        }
        if(result.getCount()==1){
            result.moveToFirst();
            humStandarApparatus.setName(result.getString(1));
            humStandarApparatus.setPort(result.getInt(2));
            humStandarApparatus.setFormat(result.getString(3));
            humStandarApparatus.setRate(result.getInt(4));
            humStandarApparatus.setType(result.getString(5));
            humStandarApparatus.setModel(result.getString(6));
            humStandarApparatus.setAgreement(result.getString(7));
            humStandarApparatus.setNumber(result.getString(8));
            humStandarApparatus.setValue(result.getString(9));
            humStandarApparatus.setTraceabilityUnit(result.getString(10));
            humStandarApparatus.setTime(result.getString(11));
            humStandarApparatus.setIsCheck(result.getInt(12));
          //  db.close();
            result.close();
            return humStandarApparatus;
        }
        humStandarApparatus.setName(null);
        humStandarApparatus.setPort(0);
        humStandarApparatus.setFormat("");
        humStandarApparatus.setRate(0);
        humStandarApparatus.setType("");
        humStandarApparatus.setModel("");
        humStandarApparatus.setAgreement("");
        humStandarApparatus.setNumber("");
        humStandarApparatus.setValue("");
        humStandarApparatus.setTraceabilityUnit("");
        humStandarApparatus.setTime("");
        humStandarApparatus.setIsCheck(0);
       // db.close();
        result.close();
        return humStandarApparatus;

    }






    public boolean updateCheck(int id,int isCheckID){
        if(isCheckID!=0){
            String sql="update "+TABLENAME+" set isCheck=0 where id="+isCheckID;
            db.execSQL(sql);

        }

        String sql="update "+TABLENAME+" set isCheck=1 where id="+id;
        db.execSQL(sql);
      //  db.close();
        return true;
    }





    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
