package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nimeng.bean.ComprehensiveDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/30 9:59
 * <p>
 * Changes (from 2022/6/30)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/30 : Create ComprehensiveDataDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ComprehensiveDataDBHelper  extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="comprehensivedata";

    public ComprehensiveDataDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "
                +TABLENAME+
                "("+
                "reading float(5) not null,"+
                "correctionValue float(5) not null,"+
                "actualValue float(5) not null,"+
                "readingOfTestedInstrument float(5) not null,"+
                "dewPointReading float(5) not null,"+
                "standardCorrectionValue float(5) not null,"+
                "standardActualValue float(5) not null,"+
                "actualRelativeHumidity float(5) not null,"+
                "wetBulbTemperature float(5) not null,"+
                "relativeHumidity float(5) not null,"+
                "indicationError float(5) not null"+
                ")";


        sqLiteDatabase.execSQL(sql);

    }


    public boolean add(ComprehensiveDataBean comprehensiveDataBean){
        contentValues.put("reading",comprehensiveDataBean.getReading());
        contentValues.put("correctionValue",comprehensiveDataBean.getCorrectionValue());
        contentValues.put("actualValue",comprehensiveDataBean.getActualValue());
        contentValues.put("readingOfTestedInstrument",comprehensiveDataBean.getReadingOfTestedInstrument());
        contentValues.put("dewPointReading",comprehensiveDataBean.getDewPointReading());
        contentValues.put("standardCorrectionValue",comprehensiveDataBean.getStandardCorrectionValue());
        contentValues.put("standardActualValue",comprehensiveDataBean.getStandardActualValue());
        contentValues.put("actualRelativeHumidity",comprehensiveDataBean.getActualRelativeHumidity());
        contentValues.put("wetBulbTemperature",comprehensiveDataBean.getWetBulbTemperature());
        contentValues.put("relativeHumidity",comprehensiveDataBean.getRelativeHumidity());
        contentValues.put("indicationError",comprehensiveDataBean.getIndicationError());

        long result=db.insert(TABLENAME,null,contentValues);
        return result>0?true:false;
    }



    public List<ComprehensiveDataBean> query(){
        List<ComprehensiveDataBean> list =new ArrayList<>();
        if(!tableIsExist(TABLENAME)){

            ComprehensiveDataBean comprehensiveDataBean=new ComprehensiveDataBean();
            comprehensiveDataBean.setReading(1.0f);
            comprehensiveDataBean.setCorrectionValue(2.0f);
            comprehensiveDataBean.setActualValue(3.0f);
            comprehensiveDataBean.setReadingOfTestedInstrument(4.0f);
            comprehensiveDataBean.setTemIndicationError(5.0f);
            comprehensiveDataBean.setDewPointReading(6.0f);
            comprehensiveDataBean.setStandardCorrectionValue(7.0f);
            comprehensiveDataBean.setStandardActualValue(8.0f);
            comprehensiveDataBean.setActualRelativeHumidity(9.0f);
            comprehensiveDataBean.setWetBulbTemperature(10.0f);
            comprehensiveDataBean.setRelativeHumidity(11.0f);
            comprehensiveDataBean.setIndicationError(12.0f);

            list.add(comprehensiveDataBean);


            return list;
        }

        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);


        Log.d("查询结果", "query: "+result.getCount());

        if(result!=null){
            while(result.moveToNext()){
                ComprehensiveDataBean comprehensiveDataBean=new ComprehensiveDataBean();
                comprehensiveDataBean.setReading(result.getFloat(0));
                comprehensiveDataBean.setCorrectionValue(result.getFloat(1));
                comprehensiveDataBean.setActualValue(result.getFloat(2));
                comprehensiveDataBean.setReadingOfTestedInstrument(result.getFloat(3));
                comprehensiveDataBean.setTemIndicationError(result.getFloat(4));
                comprehensiveDataBean.setDewPointReading(result.getFloat(5));
                comprehensiveDataBean.setStandardCorrectionValue(result.getFloat(6));
                comprehensiveDataBean.setStandardActualValue(result.getFloat(7));
                comprehensiveDataBean.setActualRelativeHumidity(result.getFloat(8));
                comprehensiveDataBean.setWetBulbTemperature(result.getFloat(9));
                comprehensiveDataBean.setRelativeHumidity(result.getFloat(10));
                comprehensiveDataBean.setIndicationError(result.getFloat(11));

                list.add(comprehensiveDataBean);
            }result.close();
        }
        ComprehensiveDataBean comprehensiveDataBean=new ComprehensiveDataBean();
        comprehensiveDataBean.setReading(1.0f);
        comprehensiveDataBean.setCorrectionValue(2.0f);
        comprehensiveDataBean.setActualValue(3.0f);
        comprehensiveDataBean.setReadingOfTestedInstrument(4.0f);
        comprehensiveDataBean.setTemIndicationError(5.0f);
        comprehensiveDataBean.setDewPointReading(6.0f);
        comprehensiveDataBean.setStandardCorrectionValue(7.0f);
        comprehensiveDataBean.setStandardActualValue(8.0f);
        comprehensiveDataBean.setActualRelativeHumidity(9.0f);
        comprehensiveDataBean.setWetBulbTemperature(10.0f);
        comprehensiveDataBean.setRelativeHumidity(11.0f);
        comprehensiveDataBean.setIndicationError(12.0f);

        list.add(comprehensiveDataBean);




        return list;
    }







    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
