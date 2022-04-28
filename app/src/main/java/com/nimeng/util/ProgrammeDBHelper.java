package com.nimeng.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 8:27
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create ProgrammeDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ProgrammeDBHelper extends SQLiteOpenHelper {
    //数据库名
    public static final String DBNAME="";

    //版本号
    public static final int VERSION=1;

    //表名
    public static final String TABLENAME="PROGRAMME";

    //字段名
    public static final String COLNUMID="programmeID";

    //字段名
    public static final String COLNUMNAME="programmeName";

    //字段名
    public static final String COLNUMTIME="programmeTime";

    //字段名
    public static final String COLNUMTEM_WAVE="programmeTemWave";

    //字段名
    public static final String COLNUMHUM_WAVE="programmeHumWave";


    //字段名
    public static final String COLNUMT1="programmeT1";

    //字段名
    public static final String COLNUMT2="programmeT2";

    //字段名
    public static final String COLNUMT3="programmeT3";

    //字段名
    public static final String COLNUMT4="programmeT4";

    //字段名
    public static final String COLNUMT5="programmeT5";

    //字段名
    public static final String COLNUMT6="programmeT6";

    //字段名
    public static final String COLNUMT7="programmeT7";

    //字段名
    public static final String COLNUMT8="programmeT8";

    //字段名
    public static final String COLNUMT9="programmeT9";

    //字段名
    public static final String COLNUMT10="programmeT10";

    //字段名
    public static final String COLNUMH1="programmeH1";

    //字段名
    public static final String COLNUMH2="programmeH2";

    //字段名
    public static final String COLNUMH3="programmeH3";

    //字段名
    public static final String COLNUMH4="programmeH4";

    //字段名
    public static final String COLNUMH5="programmeH5";

    //字段名
    public static final String COLNUMH6="programmeH6";

    //字段名
    public static final String COLNUMH7="programmeH7";

    //字段名
    public static final String COLNUMH8="programmeH8";

    //字段名
    public static final String COLNUMH9="programmeH9";

    //字段名
    public static final String COLNUMH10="programmeH10";

    public ProgrammeDBHelper(Context context){
        super(context,DBNAME,null,VERSION);
    }

    //创建表
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql="create table "
                +TABLENAME+
                "("+
                "programmeID varchar(10) primary key,"+
                "programmeName varchar(20) not null,"+
                "porgrammeTime  tinyint(2) not null,"+
                "programmeWave float(5) not null,"+
                "programmeT1 float(5) ,"+
                "programmeT2 float(5) ,"+
                "programmeT3 float(5) ,"+
                "programmeT4 float(5) ,"+
                "programmeT5 float(5) ,"+
                "programmeT6 float(5) ,"+
                "programmeT7 float(5) ,"+
                "programmeT8 float(5) ,"+
                "programmeT9 float(5) ,"+
                "programmeT10 float(5) ,"+
                "programmeH1 float(5) ,"+
                "programmeH2 float(5) ,"+
                "programmeH3 float(5) ,"+
                "programmeH4 float(5) ,"+
                "programmeH5 float(5) ,"+
                "programmeH6 float(5) ,"+
                "programmeH7 float(5) ,"+
                "programmeH8 float(5) ,"+
                "programmeH9 float(5) ,"+
                "programmeH10 float(5)"+
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
