package com.nimeng.util;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mysql.cj.xdevapi.Table;
import com.nimeng.bean.DataRecodeBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private SQLiteDatabase writeDB;
    private SQLiteDatabase readDB;
    public static DataRecordDBHelper mInstance;
    public static final String TABLENAME="data";

    CommonUtil commonUtil=new CommonUtil();
    ContentValues contentValues=new ContentValues();


    public DataRecordDBHelper(Context context) {
        super(context,"NIMENG.db",null,1);
    }

    public synchronized static DataRecordDBHelper getInstance(Context context){
        if (mInstance==null){
            mInstance=new DataRecordDBHelper(context);
        }
        return mInstance;
    }

    @Override
    public synchronized void close() {
        writeDB.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql="create table if not exists "
                +TABLENAME+
                " ("+
                "id integer primary key AUTOINCREMENT,"+
                "time varchar(30) not null,"+
                "settingTem float(5) not null,"+
                "realtimeTem float(5) not null,"+
                "settingHum float(5) not null,"+
                "realtimeHum float(5) not null"+
                ")";

        if(!sqLiteDatabase.isOpen()){
            sqLiteDatabase=getWritableDatabase();
        }

        sqLiteDatabase.execSQL(sql);





    }



    //添加
    public  boolean add(DataRecodeBean dataRecodeBean){
        writeDB=getWritableDatabase();

        if(!tableIsExist(TABLENAME)){
           onCreate(writeDB);
        }


        String time;

      //  System.out.println("------DataRecordDBHelper---104---"+dataRecodeBean.getTime()+"---"+dataRecodeBean);
        if(dataRecodeBean.getTime()==null){
             time=getDateTimeToString(new Date());
        }else{
            time=dataRecodeBean.getTime();
        }

        DataRecodeBean dataRecodeBean1= queryByTime(time);
       // System.out.println("------DataRecordDBHelper---112---"+dataRecodeBean1);
        //查询上一秒是否有数据,没有数据的添加一条与当前数据相同的数据
        String prefixTime=commonUtil.getNextTime(new Date(),-1);//上一秒
        DataRecodeBean dataRecodeBean2=queryByTime(prefixTime);
        if(dataRecodeBean1!=null){



            if(dataRecodeBean1.getRealtimeHum()==0){
                contentValues.put("realtimeHum",dataRecodeBean.getRealtimeHum());
            }
            if(dataRecodeBean1.getRealtimeTem()==0){
                contentValues.put("realtimeTem",dataRecodeBean.getRealtimeTem());
            }if(dataRecodeBean1.getSettingHum()==0){
                contentValues.put("settingHum",dataRecodeBean.getSettingHum());
            }if(dataRecodeBean1.getSettingTem()==0){
                contentValues.put("settingTem",dataRecodeBean.getSettingTem());
            }

            writeDB.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf(dataRecodeBean1.getId())});

         //   writeDB.close();
            return true;

        }

        contentValues.put("time",time);
        contentValues.put("realtimeHum",dataRecodeBean.getRealtimeHum());
        contentValues.put("settingTem",dataRecodeBean.getSettingTem());
        contentValues.put("realtimeTem",dataRecodeBean.getRealtimeTem());
        contentValues.put("settingHum",dataRecodeBean.getSettingHum());

        long result=writeDB.insert(TABLENAME,null,contentValues);





        if(dataRecodeBean2==null ){
            contentValues.put("time",prefixTime);
            contentValues.put("realtimeHum",dataRecodeBean.getRealtimeHum());
            contentValues.put("settingTem",dataRecodeBean.getSettingTem());
            contentValues.put("realtimeTem",dataRecodeBean.getRealtimeTem());
            contentValues.put("settingHum",dataRecodeBean.getSettingHum());
            writeDB.insert(TABLENAME,null,contentValues);
        }




       // writeDB.close();

        return result>0?true:false;
    }


    //查询
    public List<DataRecodeBean> query(){

        List<DataRecodeBean> list =new ArrayList<>();

        readDB=getReadableDatabase();
        Cursor result=null;
        try{
            result=readDB.query(TABLENAME,null,null,null,null,null,null);

        }
        catch (Exception e){
            e.printStackTrace();
         //   readDB.close();
            result.close();
            return list;
        }



        if(result!=null){
            while(result.moveToNext()){
                DataRecodeBean dataRecodeBean=new DataRecodeBean();
                dataRecodeBean.setTime(result.getString(1));
                dataRecodeBean.setSettingTem(result.getFloat(2));
                dataRecodeBean.setRealtimeTem(result.getFloat(3));
                dataRecodeBean.setSettingHum(result.getFloat(4));
                dataRecodeBean.setRealtimeHum(result.getFloat(5));

                list.add(dataRecodeBean);
            }//readDB.close();
        }
        result.close();
        return  list;
    }


    //根据日期查找
    public List<DataRecodeBean> findDataRecordByTime(String startTime, String endTime){
        List<DataRecodeBean> list=new ArrayList<DataRecodeBean>();

        readDB=getReadableDatabase();

        Cursor result=null;
        try{
            result=  readDB.query(TABLENAME,null,"time between  ? and ?",new String[]{startTime,endTime},null,null,null,null);

        }
        catch (Exception e){
            e.printStackTrace();
         //   readDB.close();
            result.close();
            return null;
        }



        if(result!=null){
            while (result.moveToNext()){
                DataRecodeBean dataRecodeBean=new DataRecodeBean();
                dataRecodeBean.setTime(result.getString(1));
                dataRecodeBean.setSettingTem(result.getFloat(2));
                dataRecodeBean.setRealtimeTem(result.getFloat(3));
                dataRecodeBean.setSettingHum(result.getFloat(4));
                dataRecodeBean.setRealtimeHum(result.getFloat(5));
                list.add(dataRecodeBean);
            }
          //  readDB.close();;
        }

        result.close();
        return list;



    }


    //根据时间查询实时温度与实时湿度
    public DataRecodeBean queryByTime(String time){


        readDB=getReadableDatabase();

        Cursor result=null;
        try{
            result= readDB.query(TABLENAME,null,"time=?",new String[]{time},null,null,null,null);

        }
        catch (Exception e){
            e.printStackTrace();
            if(result!=null){
                result.close();
            }

          //  readDB.close();
            return null;
        }


        DataRecodeBean dataRecodeBean=new DataRecodeBean();
//

            result= readDB.query(TABLENAME,null,"time=?",new String[]{time},null,null,null,null);



            if(result.getCount()==1){
                result.moveToFirst();
                dataRecodeBean.setId(result.getInt(0));
                dataRecodeBean.setRealtimeTem(result.getFloat(3));
                dataRecodeBean.setRealtimeHum(result.getFloat(5));
                dataRecodeBean.setTime(result.getString(1));

              //  readDB.close();
                result.close();
                return dataRecodeBean;
            }
          //  readDB.close();
            result.close();
            return  null;



    }


    //根据id查询温度湿度
    public DataRecodeBean queryByID(int id){
        readDB=getReadableDatabase();
        DataRecodeBean dataRecodeBean=new DataRecodeBean();


        Cursor result=null;
        try{
            result= readDB.query(TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null,null);

        }
        catch (Exception e){
            e.printStackTrace();
           // readDB.close();
            return null;
        }


        if(result.getCount()==1){
            result.moveToFirst();
            dataRecodeBean.setId(result.getInt(0));
            dataRecodeBean.setRealtimeTem(result.getFloat(3));
            dataRecodeBean.setRealtimeHum(result.getFloat(5));
            dataRecodeBean.setTime(result.getString(1));
          //  readDB.close();
            return dataRecodeBean;

        } else {
          //  readDB.close();
           return null;
        }


    }





    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    //查询时间、实时温度、实时湿度
    public List<String> queryColumn_String(String columnName,@Nullable boolean isReverseOrder,@Nullable String limit){


         List<String> list=new ArrayList();

        Cursor result;

        readDB=getReadableDatabase();
         if(isReverseOrder){
             result=readDB.query(TABLENAME,new String[]{columnName},null,null,null,null,columnName+" DESC",limit);
         }else{
             result =readDB.query(TABLENAME, new String[]{columnName},null,null,null,null,null);
         }


        if(result!=null){
            while(result.moveToNext()){

                if(columnName=="time"){
                    String time=result.getString(0).substring(14,19);


                    list.add(time);
                }

            }
        }

     //   readDB.close();
        return  list;

    }




    //在某段时间内按照大小排序
    public List<Double> queryColumn_Double(String columnName,@Nullable  String limit){

        List<Double> list=new ArrayList<Double>();
        readDB=getReadableDatabase();


        Cursor result=null;
        try{

            result=  readDB.query(TABLENAME, new String[]{columnName},null,null,null,null,columnName+" DESC",limit);
        }
        catch (Exception e){
            e.printStackTrace();
         //   readDB.close();
            return null;
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

            }
        }
      //  readDB.close();
        return  list;

    }


//删除7天前的数据
    public void delete7DaysData(){

        writeDB=getWritableDatabase();
        String sql="delete from "+TABLENAME+" where date('now','-7 day')>= date(time)";
        try{
            writeDB.execSQL(sql);
        }
        catch (Exception e){
            e.printStackTrace();
        }



      //  writeDB.close();
        return;

    }
    public String getDateTimeToString(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time=simpleDateFormat.format(date);
        return time;
    }

    public List<DataRecodeBean> query20DataRecodeBean(String limit){

        readDB=getReadableDatabase();
        List<DataRecodeBean> list=new ArrayList<>();



        Cursor result=null;
        try{
            result= readDB.query(TABLENAME,null,null,null,null,null,"time DESC",limit);

        }
        catch (Exception e){
            e.printStackTrace();
            result.close();
           // readDB.close();
            return null;
        }


            result= readDB.query(TABLENAME,null,null,null,null,null,"time DESC",limit);


        if(result!=null){
            while(result.moveToNext()){
                DataRecodeBean dataRecodeBean=new DataRecodeBean();
                dataRecodeBean.setId(result.getInt(0));
                dataRecodeBean.setTime(result.getString(1));
                dataRecodeBean.setSettingTem(result.getFloat(2));
                dataRecodeBean.setRealtimeTem(result.getFloat(3));
                dataRecodeBean.setSettingHum(result.getFloat(4));
                dataRecodeBean.setRealtimeHum(result.getFloat(5));
                list.add(dataRecodeBean);
            }
        }
      //  readDB.close();
        result.close();
        return list;

    }




}
