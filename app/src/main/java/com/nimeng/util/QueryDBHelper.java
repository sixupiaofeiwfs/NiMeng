package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import androidx.annotation.Nullable;

import com.nimeng.bean.QueryBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/1 10:33
 * <p>
 * Changes (from 2022/7/1)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/1 : Create QueryDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class QueryDBHelper extends BaseUtil {
    private SQLiteDatabase db;
    ContentValues contentValues = new ContentValues();
    public static final String TABLENAME = "query";

    public QueryDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " +
                TABLENAME +
                "(" +
                "name varchar not null," +
                "time Date not null," +
                "PVTem float not null," +
                "PVHum float not null," +
                "state varchar not null," +
                "standardTem float not null," +
                "standardHum float not null," +
                "dewPointTem float not null," +
                "tem float not null" +
                ")";

        sqLiteDatabase.execSQL(sql);
    }


    public boolean add(QueryBean queryBean) {
        contentValues.put("name", queryBean.getName());
        contentValues.put("time", String.valueOf(queryBean.getTime()));
        contentValues.put("PVTem", queryBean.getPVTem());
        contentValues.put("PVHum", queryBean.getPVHum());
        contentValues.put("state", queryBean.getState());
        contentValues.put("standardTem", queryBean.getStandardTem());
        contentValues.put("standardHum", queryBean.getStandardHum());
        contentValues.put("dewPointTem", queryBean.getDewPointTem());
        contentValues.put("tem", queryBean.getTem());

        long result = db.insert(TABLENAME, null, contentValues);
      //  db.close();
        return result > 0 ? true : false;
    }


    public List<QueryBean> query() {

        List<QueryBean> list = new ArrayList<>();

        Cursor result =null;

        try{

           result= db.query(TABLENAME, null, null, null, null, null, null);

        }
        catch (Exception e){
            e.printStackTrace();
            QueryBean queryBean = new QueryBean();
            queryBean.setName("name");
            queryBean.setTime(new Date());
            queryBean.setPVTem(3.0f);
            queryBean.setPVHum(4.0f);
            queryBean.setState("完成");
            queryBean.setStandardTem(5.0f);
            queryBean.setStandardHum(6.0f);
            queryBean.setTem(7.0f);

            list.add(queryBean);
         //   db.close();
            result.close();
            return list;
        }

        if (result != null) {
            while (result.moveToNext()) {
                QueryBean queryBean = new QueryBean();
                queryBean.setName(result.getString(1));
                String dateString = result.getString(2);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = null;
                sdf.setLenient(false);
                try {
                    date = sdf.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                    result.close();
                }

                queryBean.setTime(date);
                queryBean.setPVTem(result.getFloat(3));
                queryBean.setPVHum(result.getFloat(4));
                queryBean.setState(result.getString(5));
                queryBean.setStandardTem(result.getFloat(6));
                queryBean.setStandardHum(result.getFloat(7));
                queryBean.setDewPointTem(result.getFloat(8));
                queryBean.setTem(result.getFloat(9));

                list.add(queryBean);

            }
         //   db.close();

            result.close();
        }

        QueryBean queryBean = new QueryBean();
        queryBean.setName("name");
        queryBean.setTime(new Date());
        queryBean.setPVTem(3.0f);
        queryBean.setPVHum(4.0f);
        queryBean.setState("完成");
        queryBean.setStandardTem(5.0f);
        queryBean.setStandardHum(6.0f);
        queryBean.setTem(7.0f);

        list.add(queryBean);
        result.close();
        return list;


    }


    public QueryBean findQueryByName(String name) {
        QueryBean queryBean = new QueryBean();

        Cursor result = null;
        try {

            result= db.query(TABLENAME, null, "name=?", new String[]{name}, null, null, null, null);

        } catch (Exception e) {
            e.printStackTrace();
            queryBean.setName("");
            queryBean.setTime(new Date());
            queryBean.setPVTem(0);
            queryBean.setPVHum(0);
            queryBean.setState("");
            queryBean.setStandardHum(0);
            queryBean.setStandardTem(0);
            queryBean.setDewPointTem(0);
            queryBean.setTem(0);

          //  db.close();
            result.close();
            return queryBean;

        }

            result.moveToFirst();
            queryBean.setName(result.getString(1));
            String dateString = result.getString(2);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            sdf.setLenient(false);
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            queryBean.setTime(date);
            queryBean.setPVTem(result.getFloat(3));
            queryBean.setPVHum(result.getFloat(4));
            queryBean.setState(result.getString(5));
            queryBean.setStandardTem(result.getFloat(6));
            queryBean.setStandardHum(result.getFloat(7));
            queryBean.setDewPointTem(result.getFloat(8));
            queryBean.setTem(result.getFloat(9));

          //  db.close();
            result.close();
            return queryBean;
        }

}