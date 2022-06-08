package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;



import com.nimeng.bean.StandardApparatusBean;

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
public class StandardApparatusDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="standardapparatus";


    public StandardApparatusDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "
                +TABLENAME+
                "("+
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
                "time varchar not null"+
                ")";
        sqLiteDatabase.execSQL(sql);


    }



    //添加
    public boolean add(StandardApparatusBean standardApparatusBean){
        contentValues.put("name",standardApparatusBean.getName());
        contentValues.put("port",standardApparatusBean.getPort());
        contentValues.put("format",standardApparatusBean.getFormat());
        contentValues.put("rate",standardApparatusBean.getRate());
        contentValues.put("type",standardApparatusBean.getType());
        contentValues.put("model",standardApparatusBean.getModel());
        contentValues.put("agreement",standardApparatusBean.getAgreement());
        contentValues.put("number",standardApparatusBean.getNumber());
        contentValues.put("value",standardApparatusBean.getValue());
        contentValues.put("traceabilityUnit",standardApparatusBean.getTraceabilityUnit());
        contentValues.put("time",standardApparatusBean.getTime());


        long result =db.insert("standardApparatus",null,contentValues);
        return result >0? true:false;
    }


    //删除
    public boolean delete(String ID){
        int result=db.delete(TABLENAME,"id=?"+ID,new String[]{ID});
        return result>0?true:false;
    }




    //查询
    public List<StandardApparatusBean> query(){
        List<StandardApparatusBean> list =new ArrayList<>();

        if(!tableIsExist(TABLENAME)){
            return list;
        }

        Log.d("判断数据表是否已打开", "query: "+db.isOpen());


        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while (result.moveToNext()){
                StandardApparatusBean standardApparatusBean=new StandardApparatusBean();
                //  planBean.setID(result.getInt(0));
                standardApparatusBean.setName(result.getString(1));
                standardApparatusBean.setPort(result.getInt(2));
                standardApparatusBean.setFormat(result.getString(3));
                standardApparatusBean.setRate(result.getInt(4));
                standardApparatusBean.setType(result.getString(5));
                standardApparatusBean.setModel(result.getString(6));
                standardApparatusBean.setAgreement(result.getString(7));
                standardApparatusBean.setNumber(result.getString(8));
                standardApparatusBean.setValue(result.getString(9));
                standardApparatusBean.setTraceabilityUnit(result.getString(10));
                standardApparatusBean.setTime(result.getString(11));

                list.add(standardApparatusBean);

            }result.close();
        }

        return list;
    }





    //通过方案名称查询方案
    public StandardApparatusBean findByName(String name){
        StandardApparatusBean standardApparatusBean=new StandardApparatusBean();
        Cursor result =db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            standardApparatusBean.setName(result.getString(1));
            standardApparatusBean.setPort(result.getInt(2));
            standardApparatusBean.setFormat(result.getString(3));
            standardApparatusBean.setRate(result.getInt(4));
            standardApparatusBean.setType(result.getString(5));
            standardApparatusBean.setModel(result.getString(6));
            standardApparatusBean.setAgreement(result.getString(7));
            standardApparatusBean.setNumber(result.getString(8));
            standardApparatusBean.setValue(result.getString(9));
            standardApparatusBean.setTraceabilityUnit(result.getString(10));
            standardApparatusBean.setTime(result.getString(11));
            return standardApparatusBean;
        }
        standardApparatusBean.setName(null);
        standardApparatusBean.setPort(0);
        standardApparatusBean.setFormat("");
        standardApparatusBean.setRate(0);
        standardApparatusBean.setType("");
        standardApparatusBean.setModel("");
        standardApparatusBean.setAgreement("");
        standardApparatusBean.setNumber("");
        standardApparatusBean.setValue("");
        standardApparatusBean.setTraceabilityUnit("");
        standardApparatusBean.setTime("");
        return standardApparatusBean;

    }


    //更新
    public boolean update(StandardApparatusBean standardApparatusBean){
        contentValues.put("name",standardApparatusBean.getName());
        contentValues.put("port",standardApparatusBean.getPort());
        contentValues.put("format",standardApparatusBean.getFormat());
        contentValues.put("rate",standardApparatusBean.getRate());
        contentValues.put("type",standardApparatusBean.getType());
        contentValues.put("model",standardApparatusBean.getModel());
        contentValues.put("agreement",standardApparatusBean.getAgreement());
        contentValues.put("number",standardApparatusBean.getNumber());
        contentValues.put("value",standardApparatusBean.getValue());
        contentValues.put("traceabilityUnit",standardApparatusBean.getTraceabilityUnit());
        contentValues.put("time",standardApparatusBean.getTime());
        int result=db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf( standardApparatusBean.getID())});

        return result>0 ? true:false;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
