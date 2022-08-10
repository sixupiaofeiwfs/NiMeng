package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;


import com.nimeng.bean.TemStandarApparatus;

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
public class TemStandardApparatusDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="temstandardapparatus";


    public TemStandardApparatusDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "
                +TABLENAME+
                "("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
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
    public boolean add(TemStandarApparatus temStandarApparatus){

        if(!tableIsExist(TABLENAME)){
            onCreate(db);
        }



        contentValues.put("name",temStandarApparatus.getName());
        contentValues.put("port",temStandarApparatus.getPort());
        contentValues.put("format",temStandarApparatus.getFormat());
        contentValues.put("rate",temStandarApparatus.getRate());
        contentValues.put("type",temStandarApparatus.getType());
        contentValues.put("model",temStandarApparatus.getModel());
        contentValues.put("agreement",temStandarApparatus.getAgreement());
        contentValues.put("number",temStandarApparatus.getNumber());
        contentValues.put("value",temStandarApparatus.getValue());
        contentValues.put("traceabilityUnit",temStandarApparatus.getTraceabilityUnit());
        contentValues.put("time",temStandarApparatus.getTime());
        contentValues.put("isCheck",temStandarApparatus.getIsCheck());


        long result =db.insert(TABLENAME,null,contentValues);
        return result >0? true:false;
    }


    //删除
    public boolean delete(int ID){
        int result=db.delete(TABLENAME,"id=?",new String[]{String.valueOf(ID)});
        return result>0?true:false;
    }




    //查询
    public List<TemStandarApparatus> query(){
        List<TemStandarApparatus> list =new ArrayList<>();

        if(!tableIsExist(TABLENAME)){
            return list;
        }

        Log.d("判断数据表是否已打开", "query: "+db.isOpen());


        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);

        System.out.println("result--->"+result.getCount());

        if(result!=null){
            while (result.moveToNext()){
                TemStandarApparatus temStandarApparatus=new TemStandarApparatus();
                temStandarApparatus.setID(result.getInt(0));
                temStandarApparatus.setName(result.getString(1));
                temStandarApparatus.setPort(result.getInt(2));
                temStandarApparatus.setFormat(result.getString(3));
                temStandarApparatus.setRate(result.getInt(4));
                temStandarApparatus.setType(result.getString(5));
                temStandarApparatus.setModel(result.getString(6));
                temStandarApparatus.setAgreement(result.getString(7));
                temStandarApparatus.setNumber(result.getString(8));
                temStandarApparatus.setValue(result.getString(9));
                temStandarApparatus.setTraceabilityUnit(result.getString(10));
                temStandarApparatus.setTime(result.getString(11));
                temStandarApparatus.setIsCheck(result.getInt(12));

                list.add(temStandarApparatus);

            }result.close();
        }

        return list;
    }





    //通过方案名称查询方案
    public TemStandarApparatus findByName(String name){
        TemStandarApparatus temStandarApparatus=new TemStandarApparatus();

        if(!tableIsExist(TABLENAME)){
            temStandarApparatus.setName(null);
            temStandarApparatus.setPort(0);
            temStandarApparatus.setFormat("");
            temStandarApparatus.setRate(0);
            temStandarApparatus.setType("");
            temStandarApparatus.setModel("");
            temStandarApparatus.setAgreement("");
            temStandarApparatus.setNumber("");
            temStandarApparatus.setValue("");
            temStandarApparatus.setTraceabilityUnit("");
            temStandarApparatus.setTime("");
            temStandarApparatus.setIsCheck(0);
            return temStandarApparatus;
        }


        Cursor result =db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            temStandarApparatus.setName(result.getString(1));
            temStandarApparatus.setPort(result.getInt(2));
            temStandarApparatus.setFormat(result.getString(3));
            temStandarApparatus.setRate(result.getInt(4));
            temStandarApparatus.setType(result.getString(5));
            temStandarApparatus.setModel(result.getString(6));
            temStandarApparatus.setAgreement(result.getString(7));
            temStandarApparatus.setNumber(result.getString(8));
            temStandarApparatus.setValue(result.getString(9));
            temStandarApparatus.setTraceabilityUnit(result.getString(10));
            temStandarApparatus.setTime(result.getString(11));
            temStandarApparatus.setIsCheck(result.getInt(12));
            return temStandarApparatus;
        }
        temStandarApparatus.setName(null);
        temStandarApparatus.setPort(0);
        temStandarApparatus.setFormat("");
        temStandarApparatus.setRate(0);
        temStandarApparatus.setType("");
        temStandarApparatus.setModel("");
        temStandarApparatus.setAgreement("");
        temStandarApparatus.setNumber("");
        temStandarApparatus.setValue("");
        temStandarApparatus.setTraceabilityUnit("");
        temStandarApparatus.setTime("");
        temStandarApparatus.setIsCheck(0);
        return temStandarApparatus;

    }


//    //更新
//    public boolean update(TemStandarApparatus temStandarApparatus){
//        contentValues.put("name",temStandarApparatus.getName());
//        contentValues.put("port",temStandarApparatus.getPort());
//        contentValues.put("format",temStandarApparatus.getFormat());
//        contentValues.put("rate",temStandarApparatus.getRate());
//        contentValues.put("type",temStandarApparatus.getType());
//        contentValues.put("model",temStandarApparatus.getModel());
//        contentValues.put("agreement",temStandarApparatus.getAgreement());
//        contentValues.put("number",temStandarApparatus.getNumber());
//        contentValues.put("value",temStandarApparatus.getValue());
//        contentValues.put("traceabilityUnit",temStandarApparatus.getTraceabilityUnit());
//        contentValues.put("time",temStandarApparatus.getTime());
//        int result=db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf( temStandarApparatus.getID())});
//
//        return result>0 ? true:false;
//    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean updateCheck(int id,int isCheckID){
        System.out.println("选中时----》》》"+id+"------>>>"+isCheckID);
        if(isCheckID!=0){
            String sql="update "+TABLENAME+" set isCheck=0 where id="+isCheckID;
            db.execSQL(sql);
        }

        String sql="update "+TABLENAME+" set isCheck=1 where id="+id;
        db.execSQL(sql);
        return true;
    }
}
