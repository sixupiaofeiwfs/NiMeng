package com.nimeng.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/21 15:55
 * <p>
 * Changes (from 2022/6/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/21 : Create BaseUtil.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class BaseUtil extends SQLiteOpenHelper {

    private  SQLiteDatabase db ;

    public  boolean tableIsExist(String tableName){
        boolean result=false;
        if(tableName==null){
            return  result;
        }
        Cursor cursor=null;
        try{
            String sql="select count(*) as c from sqlite_master where type='table' and name='"+tableName.trim()+"'";
            cursor=db.rawQuery(sql,null);
            if(cursor.moveToNext()){
                int count=cursor.getInt(0);
                if(count>0){
                    result=true;
                }
            }
        }catch (Exception e){

        }
        return result;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public BaseUtil(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }
}
