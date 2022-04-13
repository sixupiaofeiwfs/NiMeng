package com.nimeng.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nimeng.bean.ProgrammeBean;
import com.nimeng.util.ProgrammeDBHelper;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/12 16:04
 * <p>
 * Changes (from 2022/4/12)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/12 : Create ProgrammeDao.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ProgrammeDao implements IProgrammeDao{
    private SQLiteDatabase database;
    private ProgrammeDBHelper dbHelper;
    private ContentValues values;

    public ProgrammeDao(Context context){
        dbHelper=new ProgrammeDBHelper(context);
    }

    @Override
    public void AddProgramme(ProgrammeBean programmeBean) {
        database=dbHelper.getWritableDatabase();
        values=new ContentValues();
        values.put(ProgrammeDBHelper.COLNUMID, programmeBean.getId());
        values.put(ProgrammeDBHelper.COLNUMNAME, programmeBean.getName());
        values.put(ProgrammeDBHelper.COLNUMTIME, programmeBean.getTime());
        values.put(ProgrammeDBHelper.COLNUMWAVE, programmeBean.getWave());
        values.put(ProgrammeDBHelper.COLNUMH1, programmeBean.getHumidity1());
        values.put(ProgrammeDBHelper.COLNUMT1, programmeBean.getTemperature1());
        values.put(ProgrammeDBHelper.COLNUMH2, programmeBean.getHumidity2());
        values.put(ProgrammeDBHelper.COLNUMT2, programmeBean.getTemperature2());
        values.put(ProgrammeDBHelper.COLNUMH3, programmeBean.getHumidity3());
        values.put(ProgrammeDBHelper.COLNUMT3, programmeBean.getTemperature3());
        values.put(ProgrammeDBHelper.COLNUMH4, programmeBean.getHumidity4());
        values.put(ProgrammeDBHelper.COLNUMT4, programmeBean.getTemperature4());
        values.put(ProgrammeDBHelper.COLNUMH5, programmeBean.getHumidity5());
        values.put(ProgrammeDBHelper.COLNUMT5, programmeBean.getTemperature5());
        values.put(ProgrammeDBHelper.COLNUMH6, programmeBean.getHumidity6());
        values.put(ProgrammeDBHelper.COLNUMT6, programmeBean.getTemperature6());
        values.put(ProgrammeDBHelper.COLNUMH7, programmeBean.getHumidity7());
        values.put(ProgrammeDBHelper.COLNUMT7, programmeBean.getTemperature7());
        values.put(ProgrammeDBHelper.COLNUMH8, programmeBean.getHumidity8());
        values.put(ProgrammeDBHelper.COLNUMT8, programmeBean.getTemperature8());
        values.put(ProgrammeDBHelper.COLNUMH9, programmeBean.getHumidity9());
        values.put(ProgrammeDBHelper.COLNUMT9, programmeBean.getTemperature9());
        values.put(ProgrammeDBHelper.COLNUMH10, programmeBean.getHumidity10());
        values.put(ProgrammeDBHelper.COLNUMT10, programmeBean.getTemperature10());

        database.insert(ProgrammeDBHelper.TABLENAME,null,values);
        database.close();


    }

    @Override
    public void deleteProgrammeById(String id) {
        database =dbHelper.getWritableDatabase();
        database.delete(ProgrammeDBHelper.TABLENAME, ProgrammeDBHelper.COLNUMID+"=?",new String[]{id});
        database.close();

    }
}
