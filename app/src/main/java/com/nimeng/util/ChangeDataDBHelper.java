package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChangeDataDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    public static final String TABLENAME="changeData";

    public ChangeDataDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String sql="create table "
               +TABLENAME +
               "("+
               "id integer primary key AUTOINCREMENT,"+
               "temMax float,"+
               "temMin float,"+
               "humMax float,"+
               "humMin float,"+
               "time varchar"+
               ")";

       sqLiteDatabase.execSQL(sql);

       ContentValues contentValues=new ContentValues();
       contentValues.put("temMax",-100f);
       contentValues.put("temMin",200f);
       contentValues.put("humMax",-100f);
       contentValues.put("humMin",200f);
       contentValues.put("time",getDateTimeToString(new Date()));
       sqLiteDatabase.insert(TABLENAME,null,contentValues);


    }

    /**
     *
     * @param id   跟哪个id下的数据比较（系统已经运行了多少分钟）
     * @param data 需要比较的值（温湿度实时值）
     * @param code 0代表获取温度值 1代表获取湿度值
     */
    public void add(int id,float data ,int code){

        if(!tableIsExist(TABLENAME)){
            onCreate(db);
        }



        ContentValues contentValues=new ContentValues();
        //获取最大值和最小值
        List<Float> list= findMaxAndMinDataByID(id,code);

        //System.out.println("库中存储的最大值和最小值----"+list+"    "+id+"    "+code);

            if(data>list.get(0)){

                if(code==0){
                    contentValues.put("temMax",data);
                }else{
                    contentValues.put("humMax",data);
                }

            }
            if(data<list.get(1)){

                if(code==0){
                    contentValues.put("temMin",data);
                }else{
                    contentValues.put("humMin",data);
                }

            }
            if(data<=list.get(0) && data>=list.get(1)){
                contentValues.put("time",getDateTimeToString(new Date()));
                db.update(TABLENAME,contentValues,"id=?",new String[]{id+""});
                return;
            }


        contentValues.put("time",getDateTimeToString(new Date()));


        //System.out.println("待写入的信息..."+contentValues.toString());

       int i=  db.update(TABLENAME,contentValues,"id=?",new String[]{id+""});

       // System.out.println("更新结果..."+i+"  "+db.isOpen());



       if(i==0){
           contentValues.put("id",id);
         long l= db.insert(TABLENAME,null,contentValues);
          // System.out.println("写入结果..."+l);
       }

            return;


    }


    /**
     *
     * @param id    获取哪个id下的最大值和最小值（系统已经运行了多少分钟）
     * @param code  0代表获取温度值 1代表获取湿度值
     * @return
     */
    public List<Float> findMaxAndMinDataByID(int id, int code){
        List<Float>list =new ArrayList<>();
        if(!tableIsExist(TABLENAME)){
            list.add(-100f);//设置最大值
            list.add(200f);//设置最小值
            return list;
        }
        Cursor result=db.query(TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null);
        if(result!=null && result.getCount()>0){

            result.moveToFirst();
            if(code==0){
                list.add(result.getFloat(1));
                list.add(result.getFloat(2));
            }else{

             //   System.out.println("有数据----看一下数据"+result.getFloat(1)+"   "+result.getFloat(2)+"    "+result.getFloat(3)+"   "+result.getFloat(4));

                if(result.getFloat(1)!=0 && result.getFloat(2)!=0 && result.getFloat(3)==0 && result.getFloat(4)==0){
                    list.add(-100f);
                    list.add(200f);
                }else{
                    list.add(result.getFloat(3));
                    list.add(result.getFloat(4));
                }

            }

            result.close();
            return list;


        }else{
            result.close();
            list.add(-100f);
            list.add(200f);
            return list;
        }

    }


    //读最新的一条数据
    public List<Float> getNewChangeData(){
        if(!tableIsExist(TABLENAME)){
            return null;
        }

        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null && result.getCount()>=1){
            List<Float>list=new ArrayList<>();
            result.moveToLast();
            if(result.getFloat(1)==-100 || result.getFloat(2)==200 ||result.getFloat(3)==-100 ||result.getFloat(4)==200){
                result.close();
                return null;
            }

            list.add(result.getFloat(1));
            list.add(result.getFloat(2));
            list.add(result.getFloat(3));
            list.add(result.getFloat(4));
            result.close();
            return list;
        }else{
            result.close();
            return  null;
        }

    }



    //读所有数据中的最值

    /**
     *
     * @param s   字段名（temMax temMin humMax humMin）
     * @return
     */
    public float getAllChangeData(String s,int limit){
        float f;

        if(!tableIsExist(TABLENAME)){
            return 0;
        }
        Cursor result=db.query(TABLENAME,null,null,null,null,null,s+",time",String.valueOf(limit));

        if(result!=null && result.getCount()>0){
            if(s=="temMax"){
                result.moveToLast();
                f=result.getFloat(1);
                result.close();
                return f;
            }if(s=="temMin"){
                result.moveToFirst();
                f=result.getFloat(2);
                result.close();
                return f;
            }if(s=="humMax"){
                result.moveToLast();
                f= result.getFloat(3);
                result.close();
                return f;
            }if(s=="humMin"){
                result.moveToFirst();
               f=  result.getFloat(4);
               result.close();
               return f;
            }
            result.close();
            return 0;



        }else{
            result.close();
            return 0;
        }

    }


    /**
     * 删除30分钟之前的数据
     */
    public void delete30MinuteData(Date startTime){
        if(!tableIsExist(TABLENAME)){
            return;
        }

        String sql="delete  from "+TABLENAME+" where date('now','-30 minute')>= date(time)";
        db.execSQL(sql);
        return;
    }


}
