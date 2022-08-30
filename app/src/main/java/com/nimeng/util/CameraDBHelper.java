package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nimeng.bean.CameraBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/21 15:29
 * <p>
 * Changes (from 2022/6/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/21 : Create CameraDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class CameraDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="camera";

    public CameraDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql="create table "
                + TABLENAME+
                "("+
                "id integer primary key,"+
                "name varchar(30) not null,"+
                "port varchar(5) not null"+
                ")";

        sqLiteDatabase.execSQL(sql);


    }



    //增
    public boolean add(CameraBean cameraBean){
        contentValues.put("name",cameraBean.getName());
        contentValues.put("port",cameraBean.getPort());

        long result=db.insert(TABLENAME,null,contentValues);
        return result>0?true:false;

    }

    //删
    public boolean delete(String ID){
        int result=db.delete(TABLENAME,"id=?",new String[]{ID});
        return result>0?true:false;
    }

    //更新
    public boolean update(CameraBean cameraBean){
        contentValues.put("id",cameraBean.getID());
        contentValues.put("name",cameraBean.getName());
        contentValues.put("port",cameraBean.getPort());
        int result =db.update(TABLENAME,contentValues,"id=?", new String[]{String.valueOf(cameraBean.getID())});

        return result>0?true:false;
    }


    //查询

    public List<CameraBean> query(){
        List<CameraBean> list =new ArrayList<>();

        if(!tableIsExist(TABLENAME)){
            return list;
        }



        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while(result.moveToNext()){
                CameraBean cameraBean=new CameraBean();
                cameraBean.setID(result.getInt(0));
                cameraBean.setName(result.getString(1));
                cameraBean.setPort(result.getString(2));
                list.add(cameraBean);
            }result.close();
        }
        return list;
    }


    //按名称查询
    public CameraBean findByName(String name){
        CameraBean cameraBean=new CameraBean();
        Cursor result=db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            cameraBean.setName(result.getString(1));
            cameraBean.setPort(result.getString(2));
            result.close();
            return cameraBean;
        }

        cameraBean.setName("");
        cameraBean.setPort("");
        result.close();
        return cameraBean;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }





}
