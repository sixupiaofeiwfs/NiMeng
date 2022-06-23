package com.nimeng.util;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nimeng.View.CurveActivity;
import com.nimeng.bean.DataRecodeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/15 10:27
 * <p>
 * Changes (from 2022/6/15)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/15 : Create DataRecordDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DataRecordDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="data";


    public DataRecordDBHelper(@NonNull Context context, @Nullable String name,@Nullable SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        db=this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("是否创建表了", "onCreate: ");


        String sql="create table "
                +TABLENAME+
                " ("+
                "id integer primary key,"+
                "time varchar(30) not null,"+
                "settingTem float(5) not null,"+
                "realtimeTem float(5) not null,"+
                "settingHum float(5) not null,"+
                "realtimeHum float(5) not null"+
                ")";

        sqLiteDatabase.execSQL(sql);





    }



    //添加
    public boolean add(DataRecodeBean dataRecodeBean){

        if(!tableIsExist(TABLENAME)){
            Log.d("表不存在，创建表", "add: ");
            String sql="create table "
                    +TABLENAME+
                    " ("+
                    "id integer primary key,"+
                    "time varchar(30) not null,"+
                    "settingTem float(5) not null,"+
                    "realtimeTem float(5) not null,"+
                    "settingHum float(5) not null,"+
                    "realtimeHum float(5) not null"+
                    ")";

            db.execSQL(sql);

        }

        contentValues.put("time",dataRecodeBean.getTime());
        contentValues.put("settingTem",dataRecodeBean.getSettingTem());
        contentValues.put("realtimeTem",dataRecodeBean.getRealtimeTem());
        contentValues.put("settingHum",dataRecodeBean.getSettingHum());
        contentValues.put("realtimeHum",dataRecodeBean.getRealtimeHum());

        long result=db.insert(TABLENAME,null,contentValues);
        Log.d("添加成功了吗",result+"");





        //将数据写入到文件中
        String str=dataRecodeBean.getTime()+"    "+dataRecodeBean.getSettingTem()+"    "+dataRecodeBean.getRealtimeTem()+"    "+dataRecodeBean.getSettingHum()+"     "+dataRecodeBean.getRealtimeHum()+"\r\n";
        String fileName=dataRecodeBean.getTime().substring(0,10);

        write6("datarecord",str,fileName);




        return result>0?true:false;
    }


    //查询
    public List<DataRecodeBean> query(){

        List<DataRecodeBean> list =new ArrayList<>();
        if(!tableIsExist(TABLENAME)){
            return list;
        }





        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);

        Log.d("查询时", "query: "+result.getCount());

        if(result!=null){
            while(result.moveToNext()){
                DataRecodeBean dataRecodeBean=new DataRecodeBean();
                dataRecodeBean.setTime(result.getString(1));
                dataRecodeBean.setSettingTem(result.getFloat(2));
                dataRecodeBean.setRealtimeTem(result.getFloat(3));
                dataRecodeBean.setSettingHum(result.getFloat(4));
                dataRecodeBean.setRealtimeHum(result.getFloat(5));

                list.add(dataRecodeBean);
            }result.close();
        }
        return  list;
    }



    //根据日期查找
    public DataRecodeBean findDataRecordByTime(String startTime, String endTime){
        DataRecodeBean dataRecodeBean=new DataRecodeBean();
        Cursor result=db.query(TABLENAME,null,"time between=? and=?",new String[]{startTime,endTime},null,null,null,null);
        if(result.getCount()==1){
            result.moveToFirst();
            dataRecodeBean.setTime(result.getString(1));
            dataRecodeBean.setSettingTem(result.getFloat(2));
            dataRecodeBean.setRealtimeTem(result.getFloat(3));
            dataRecodeBean.setSettingHum(result.getFloat(4));
            dataRecodeBean.setRealtimeHum(result.getFloat(5));
            return dataRecodeBean;
        }
        dataRecodeBean.setTime("");
        dataRecodeBean.setSettingTem(0);
        dataRecodeBean.setRealtimeTem(0);
        dataRecodeBean.setSettingHum(0);
        dataRecodeBean.setRealtimeHum(0);
        return dataRecodeBean;


    }



    //根据id查询温度湿度
    public float queryByID(int id,String columnName){
        DataRecodeBean dataRecodeBean=new DataRecodeBean();
        Random random=new Random();

        if(!tableIsExist(TABLENAME)){
            return random.nextInt(100);
        }

        Cursor result=db.query(TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            if(columnName=="tem"){
                return result.getFloat(3);
            }else if(columnName=="hum"){
                return result.getFloat(5);
            } else{
                return 0;
            }
        }else {
            return random.nextInt(100);
        }


    }





    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    //查询时间、实时温度、实时湿度
    public List<String> queryColumn_String(String columnName,@Nullable boolean isReverseOrder,@Nullable String limit){

        if(!tableIsExist(TABLENAME)){
            return null;
        }




         List<String> list=new ArrayList();

        Cursor result;

         if(isReverseOrder){
             result=db.query(TABLENAME,new String[]{columnName},null,null,null,null,columnName+" DESC",limit);
         }else{
             result =db.query(TABLENAME, new String[]{columnName},null,null,null,null,null);
         }


        if(result!=null){
            while(result.moveToNext()){

                if(columnName=="time"){
                    String time=result.getString(0).substring(14,19);


                    list.add(time);
                }

            }result.close();
        }
        return  list;

    }


    public List<Double> queryColumn_Double(String columnName,@Nullable boolean isReverseOrder,@Nullable  String limit){

        if(!tableIsExist(TABLENAME)){
            return null;
        }

        List<Double> list=new ArrayList<Double>();
        Cursor result;
         if(isReverseOrder){
             result=db.query(TABLENAME, new String[]{columnName},null,null,null,null,columnName+" DESC",limit);
         }else{
             result=db.query(TABLENAME, new String[]{columnName},null,null,null,null,null);
         }


        if(result!=null){
            while(result.moveToNext()){

               if(columnName=="realtimeTem"){
                    Double realtimeTem=Double.valueOf( result.getFloat(0));
                    list.add(realtimeTem);
                }else if(columnName=="realtimeHum"){
                    Double realtimeHum=Double.valueOf(result.getFloat(0));
                    list.add(realtimeHum);
                }

            }result.close();
        }
        return  list;

    }









}
