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

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="data";
    Random random=new Random();

    CommonUtil commonUtil=new CommonUtil();


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
                "id integer primary key AUTOINCREMENT,"+
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
                    "id integer primary key  AUTOINCREMENT,"+
                    "time varchar(30) ,"+
                    "settingTem float(5) not null,"+
                    "realtimeTem float(5) not null,"+
                    "settingHum float(5) not null,"+
                    "realtimeHum float(5) not null"+
                    ")";

            db.execSQL(sql);

        }


        String time;


        if(dataRecodeBean.getTime()==null){
             time=getDateTimeToString(new Date());
        }else{
            time=dataRecodeBean.getTime();
        }

        DataRecodeBean dataRecodeBean1= queryByTime(time);
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

            db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf(dataRecodeBean1.getId())});

            return true;

        }

        contentValues.put("time",time);
        contentValues.put("realtimeHum",dataRecodeBean.getRealtimeHum());
        contentValues.put("settingTem",dataRecodeBean.getSettingTem());
        contentValues.put("realtimeTem",dataRecodeBean.getRealtimeTem());
        contentValues.put("settingHum",dataRecodeBean.getSettingHum());


        long result=db.insert(TABLENAME,null,contentValues);





        if(dataRecodeBean2==null ){
            contentValues.put("time",prefixTime);
            contentValues.put("realtimeHum",dataRecodeBean.getRealtimeHum());
            contentValues.put("settingTem",dataRecodeBean.getSettingTem());
            contentValues.put("realtimeTem",dataRecodeBean.getRealtimeTem());
            contentValues.put("settingHum",dataRecodeBean.getSettingHum());
            db.insert(TABLENAME,null,contentValues);
        }






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
    public List<DataRecodeBean> findDataRecordByTime(String startTime, String endTime){
        List<DataRecodeBean> list=new ArrayList<DataRecodeBean>();


        if(!tableIsExist(TABLENAME)){

//            System.out.println("没有数据表，自动创建假数据");
//            DataRecodeBean dataRecodeBean=new DataRecodeBean();
//            dataRecodeBean.setTime("2022-08-08 08:00:00");
//            dataRecodeBean.setSettingTem(20.0f);
//            dataRecodeBean.setRealtimeTem(19.98f);
//            dataRecodeBean.setSettingHum(40.0f);
//            dataRecodeBean.setRealtimeHum(39.99f);
//            list.add(dataRecodeBean);

            return list;
        }

        Cursor result=db.query(TABLENAME,null,"time between  ? and ?",new String[]{startTime,endTime},null,null,null,null);
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
            result.close();;
        }

//        DataRecodeBean dataRecodeBean=new DataRecodeBean();
//        dataRecodeBean.setTime("2022-08-08 08:00:00");
//        dataRecodeBean.setSettingTem(20.0f);
//        dataRecodeBean.setRealtimeTem(19.98f);
//        dataRecodeBean.setSettingHum(40.0f);
//        dataRecodeBean.setRealtimeHum(39.99f);
//        list.add(dataRecodeBean);


        return list;



    }


    //根据时间查询实时温度与实时湿度
    public DataRecodeBean queryByTime(String time){

        DataRecodeBean dataRecodeBean=new DataRecodeBean();
        if(!tableIsExist(TABLENAME)){
               return null;
        }else{
            Cursor result=db.query(TABLENAME,null,"time=?",new String[]{time},null,null,null,null);



            if(result.getCount()==1){
                result.moveToFirst();
                dataRecodeBean.setId(result.getInt(0));
                dataRecodeBean.setRealtimeTem(result.getFloat(3));
                dataRecodeBean.setRealtimeHum(result.getFloat(5));
                dataRecodeBean.setTime(result.getString(1));

                result.close();
                return dataRecodeBean;
            }
            result.close();
            return  null;
        }


    }


    //根据id查询温度湿度
    public DataRecodeBean queryByID(int id){

        DataRecodeBean dataRecodeBean=new DataRecodeBean();

        Cursor result=db.query(TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            dataRecodeBean.setId(result.getInt(0));
            dataRecodeBean.setRealtimeTem(result.getFloat(3));
            dataRecodeBean.setRealtimeHum(result.getFloat(5));
            dataRecodeBean.setTime(result.getString(1));
            result.close();
            return dataRecodeBean;

        } else {
            result.close();
           return null;
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

            } result.close();
        }
        return  list;

    }




    //在某段时间内按照大小排序
    public List<Double> queryColumn_Double(String columnName,@Nullable  String limit){

        if(!tableIsExist(TABLENAME)){
            return null;
        }

        List<Double> list=new ArrayList<Double>();
        Cursor result=db.query(TABLENAME, new String[]{columnName},null,null,null,null,columnName+" DESC",limit);



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


//删除7天前的数据
    public void delete7DaysData(){
        if(!tableIsExist(TABLENAME)){
            return;
        }

        String sql="delete from "+TABLENAME+" where date('now','-7 day')>= date(time)";

        db.execSQL(sql);
        return;

    }
    public String getDateTimeToString(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time=simpleDateFormat.format(date);
        return time;
    }

    public List<DataRecodeBean> query20DataRecodeBean(String limit){
        if(!tableIsExist(TABLENAME)){
            return null;
        }
        List<DataRecodeBean> list=new ArrayList<>();
        Cursor result=db.query(TABLENAME,null,null,null,null,null,"time DESC",limit);
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
            }result.close();
        }
        result.close();
        return list;

    }




}
