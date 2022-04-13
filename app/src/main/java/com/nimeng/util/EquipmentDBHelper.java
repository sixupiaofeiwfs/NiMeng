package com.nimeng.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.Date;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/8 10:49
 * <p>
 * Changes (from 2022/4/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/8 : Create DBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EquipmentDBHelper extends SQLiteOpenHelper {
    //数据库名
    public static final String DBNAME="";

    //版本号
    public static final int VERSION=1;

    //表名
    public static final String TABLENAME="EQUIPMENT";

    //字段名
    public static final String COLNUMID="equipmentID";

    //字段名
    public static final String COLNUMTYPE="equipmentType";


    //字段名
    public static final String COLNUMTIME="equipmentTime";

    //字段名
    public static final String COLNUMIP="equipmentIP";

    //字段名
    public static  final String COLNUMSWITCH1="switch1";

    //字段名
    public static final String COLNUMSWITCH2="switch2";

    //字段名
    public static final String COLNUMSWITCH3="switch3";

    //字段名
    public static final String COLNUMNAME="equipmentName";

    public EquipmentDBHelper(Context context){
        super(context,DBNAME,null,VERSION);

    }



    //首次执行创建表 仅执行一次
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql="create table "
                +TABLENAME+
                "("+
                "equipmentID varchar(10) primary key," +
                "equipmentType varchar(10) not null," +
                "equipmentTime datetime not null," +
                "equipmentIP varchar(20) not null," +
                "switch1 tinyint(1) not null," +
                "switch2 tinyint(1) not null," +
                "switch3 tinyint(1) not null" +
                ")";


        db.execSQL(sql);
    }
    //数据库版本更新
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
    //还原数据库版本
    @Override
    public void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion){
        super.onDowngrade(db,oldVersion,newVersion);
    }

}
