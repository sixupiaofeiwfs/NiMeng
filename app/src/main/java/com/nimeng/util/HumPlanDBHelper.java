package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nimeng.bean.HumPlanBean;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/21 15:54
 * <p>
 * Changes (from 2022/7/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/21 : Create HumPlanDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class HumPlanDBHelper extends BaseUtil{
    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="humplan";

    public HumPlanDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "
                +TABLENAME+
                " ("+
                "id integer primary key  AUTOINCREMENT,"+
                "name varchar(20) not null,"+
                "unitTime  tinyint(2) not null,"+
                "humWave float(5) not null,"+
                "points tinyint(2) not null,"+
                "hum1 float(5) ,"+
                "hum2 float(5) ,"+
                "hum3 float(5) ,"+
                "hum4 float(5) ,"+
                "hum5 float(5) ,"+
                "hum6 float(5) ,"+
                "hum7 float(5) ,"+
                "hum8 float(5) ,"+
                "hum9 float(5) ,"+
                "hum10 float(5) ,"+
                "isCheck tinyint(2)"+
                ")";

        sqLiteDatabase.execSQL(sql);

    }


    public boolean add(HumPlanBean humPlanBean){

        if(!tableIsExist(TABLENAME)){
            onCreate(db);
        }


        contentValues.put("name",humPlanBean.getName());
        contentValues.put("unitTime",humPlanBean.getUnitTime());
        contentValues.put("humWave",humPlanBean.getHumWave());
        contentValues.put("points",humPlanBean.getHumPoints());
        if(humPlanBean.getHumPoints()==1){
            contentValues.put("hum1",humPlanBean.getHum1());
        }else if(humPlanBean.getHumPoints()==2){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
        }else if(humPlanBean.getHumPoints()==3){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
            contentValues.put("hum3",humPlanBean.getHum3());
        }else if(humPlanBean.getHumPoints()==4){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
            contentValues.put("hum3",humPlanBean.getHum3());
            contentValues.put("hum4",humPlanBean.getHum4());
        }else if(humPlanBean.getHumPoints()==5){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
            contentValues.put("hum3",humPlanBean.getHum3());
            contentValues.put("hum4",humPlanBean.getHum4());
            contentValues.put("hum5",humPlanBean.getHum5());
        }else if(humPlanBean.getHumPoints()==6){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
            contentValues.put("hum3",humPlanBean.getHum3());
            contentValues.put("hum4",humPlanBean.getHum4());
            contentValues.put("hum5",humPlanBean.getHum5());
            contentValues.put("hum6",humPlanBean.getHum6());
        }else if(humPlanBean.getHumPoints()==7){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
            contentValues.put("hum3",humPlanBean.getHum3());
            contentValues.put("hum4",humPlanBean.getHum4());
            contentValues.put("hum5",humPlanBean.getHum5());
            contentValues.put("hum6",humPlanBean.getHum6());
            contentValues.put("hum7",humPlanBean.getHum7());
        }else if(humPlanBean.getHumPoints()==8){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
            contentValues.put("hum3",humPlanBean.getHum3());
            contentValues.put("hum4",humPlanBean.getHum4());
            contentValues.put("hum5",humPlanBean.getHum5());
            contentValues.put("hum6",humPlanBean.getHum6());
            contentValues.put("hum7",humPlanBean.getHum7());
            contentValues.put("hum8",humPlanBean.getHum8());
        }else if(humPlanBean.getHumPoints()==9){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
            contentValues.put("hum3",humPlanBean.getHum3());
            contentValues.put("hum4",humPlanBean.getHum4());
            contentValues.put("hum5",humPlanBean.getHum5());
            contentValues.put("hum6",humPlanBean.getHum6());
            contentValues.put("hum7",humPlanBean.getHum7());
            contentValues.put("hum8",humPlanBean.getHum8());
            contentValues.put("hum9",humPlanBean.getHum9());
        }else if(humPlanBean.getHumPoints()==10){
            contentValues.put("hum1",humPlanBean.getHum1());
            contentValues.put("hum2",humPlanBean.getHum2());
            contentValues.put("hum3",humPlanBean.getHum3());
            contentValues.put("hum4",humPlanBean.getHum4());
            contentValues.put("hum5",humPlanBean.getHum5());
            contentValues.put("hum6",humPlanBean.getHum6());
            contentValues.put("hum7",humPlanBean.getHum7());
            contentValues.put("hum8",humPlanBean.getHum8());
            contentValues.put("hum9",humPlanBean.getHum9());
            contentValues.put("hum10",humPlanBean.getHum10());
        }
        contentValues.put("isCheck",humPlanBean.getIsCheck());
        long result=db.insert(TABLENAME,null,contentValues);
        return result>0?true:false;

    }


    //删除
    public boolean delete(String ID){
        int result =db.delete(TABLENAME,"id=?",new String[]{ID});
        return result>0?true:false;
    }


    //查询
    public List<HumPlanBean> query(){
        List<HumPlanBean> list =new ArrayList<HumPlanBean>();

        if(!tableIsExist(TABLENAME)){
            return list;
        }

        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while (result.moveToNext()){
                HumPlanBean humPlanBean=new HumPlanBean();
                humPlanBean.setID(result.getInt(0));
                humPlanBean.setName(result.getString(1));
                humPlanBean.setUnitTime(result.getInt(2));
                humPlanBean.setHumWave(result.getInt(3));
                humPlanBean.setHumPoints(result.getInt(4));


                if(result.getInt(4)==1){
                    humPlanBean.setHum1(result.getInt(5));
                }else if(result.getInt(4)==2){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                }else if(result.getInt(4)==3){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                    humPlanBean.setHum3(result.getInt(7));
                }else if(result.getInt(4)==4){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                    humPlanBean.setHum3(result.getInt(7));
                    humPlanBean.setHum4(result.getInt(8));
                }else if(result.getInt(4)==5){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                    humPlanBean.setHum3(result.getInt(7));
                    humPlanBean.setHum4(result.getInt(8));
                    humPlanBean.setHum5(result.getInt(9));
                }else if(result.getInt(4)==6){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                    humPlanBean.setHum3(result.getInt(7));
                    humPlanBean.setHum4(result.getInt(8));
                    humPlanBean.setHum5(result.getInt(9));
                    humPlanBean.setHum6(result.getInt(10));
                }else if(result.getInt(4)==7){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                    humPlanBean.setHum3(result.getInt(7));
                    humPlanBean.setHum4(result.getInt(8));
                    humPlanBean.setHum5(result.getInt(9));
                    humPlanBean.setHum6(result.getInt(10));
                    humPlanBean.setHum7(result.getInt(11));
                }else if(result.getInt(4)==8){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                    humPlanBean.setHum3(result.getInt(7));
                    humPlanBean.setHum4(result.getInt(8));
                    humPlanBean.setHum5(result.getInt(9));
                    humPlanBean.setHum6(result.getInt(10));
                    humPlanBean.setHum7(result.getInt(11));
                    humPlanBean.setHum8(result.getInt(12));
                }else if(result.getInt(4)==9){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                    humPlanBean.setHum3(result.getInt(7));
                    humPlanBean.setHum4(result.getInt(8));
                    humPlanBean.setHum5(result.getInt(9));
                    humPlanBean.setHum6(result.getInt(10));
                    humPlanBean.setHum7(result.getInt(11));
                    humPlanBean.setHum8(result.getInt(12));
                    humPlanBean.setHum9(result.getInt(13));
                }else if(result.getInt(4)==10){
                    humPlanBean.setHum1(result.getInt(5));
                    humPlanBean.setHum2(result.getInt(6));
                    humPlanBean.setHum3(result.getInt(7));
                    humPlanBean.setHum4(result.getInt(8));
                    humPlanBean.setHum5(result.getInt(9));
                    humPlanBean.setHum6(result.getInt(10));
                    humPlanBean.setHum7(result.getInt(11));
                    humPlanBean.setHum8(result.getInt(12));
                    humPlanBean.setHum9(result.getInt(13));
                    humPlanBean.setHum10(result.getInt(14));
                }



                humPlanBean.setIsCheck(result.getInt(15));

                System.out.println("展示时："+humPlanBean);
                list.add(humPlanBean);

            }result.close();
        }

        return list;
    }



    //通过方案名称查询方案
    public int findHumPlanByName(String name){
        if(!tableIsExist(TABLENAME)){
            return 0;
        }


        Cursor result =db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        int number=result.getCount();
        result.close();

        return number;


    }



    //更新被选中的信息
    public boolean updateCheck(int id,int isCheckID){

        //删除之前的被选中
        Log.d("之前选中的ID", "updateCheck: "+isCheckID);
        if(isCheckID!=0){
            // contentValues.put("isCheck",0);
            // int result1=db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf(isCheckID)});
            String sql="update "+TABLENAME+" set isCheck=0 where id="+isCheckID;
            db.execSQL(sql);

        }

        //contentValues.put("isCheck",1);
        // int result=db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf(id)});
        Log.d("需要设置的ID", "updateCheck: "+id);
        String sql="update "+TABLENAME+" set isCheck=1 where id="+id;
        db.execSQL(sql);
        return true;
    }



    //通过HumID和hum？查询
    public int queryByID(int id,int humID){
        if(!tableIsExist(TABLENAME)){
            return 0;
        }

        if(humID==0){
            return 0;
        }
        Cursor result=db.query(TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(result.getCount()==0){
            result.close();
            return 0;
        }
        result.moveToFirst();
        int resultNumber=result.getInt(humID+4);
        result.close();
        return resultNumber;
    }


    public HumPlanBean queryByID(int id){
        if(!tableIsExist(TABLENAME)){
            return null;
        }

        Cursor result=db.query(TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(result!=null){
            result.moveToFirst();

            HumPlanBean humPlanBean =new HumPlanBean();
            humPlanBean.setID(result.getInt(0));
            humPlanBean.setName(result.getString(1));
            humPlanBean.setUnitTime(result.getInt(2));
            humPlanBean.setHumWave(result.getFloat(3));
            humPlanBean.setHumPoints(result.getInt(4));


            if(result.getInt(4)==1){
                humPlanBean.setHum1(result.getInt(5));
            }else if(result.getInt(4)==2){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
            }else if(result.getInt(4)==3){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
                humPlanBean.setHum3(result.getInt(7));
            }else if(result.getInt(4)==4){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
                humPlanBean.setHum3(result.getInt(7));
                humPlanBean.setHum4(result.getInt(8));
            }else if(result.getInt(4)==5){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
                humPlanBean.setHum3(result.getInt(7));
                humPlanBean.setHum4(result.getInt(8));
                humPlanBean.setHum5(result.getInt(9));
            }else if(result.getInt(4)==6){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
                humPlanBean.setHum3(result.getInt(7));
                humPlanBean.setHum4(result.getInt(8));
                humPlanBean.setHum5(result.getInt(9));
                humPlanBean.setHum6(result.getInt(10));
            }else if(result.getInt(4)==7){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
                humPlanBean.setHum3(result.getInt(7));
                humPlanBean.setHum4(result.getInt(8));
                humPlanBean.setHum5(result.getInt(9));
                humPlanBean.setHum6(result.getInt(10));
                humPlanBean.setHum7(result.getInt(11));
            }else if(result.getInt(4)==8){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
                humPlanBean.setHum3(result.getInt(7));
                humPlanBean.setHum4(result.getInt(8));
                humPlanBean.setHum5(result.getInt(9));
                humPlanBean.setHum6(result.getInt(10));
                humPlanBean.setHum7(result.getInt(11));
                humPlanBean.setHum8(result.getInt(12));
            }else if(result.getInt(4)==9){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
                humPlanBean.setHum3(result.getInt(7));
                humPlanBean.setHum4(result.getInt(8));
                humPlanBean.setHum5(result.getInt(9));
                humPlanBean.setHum6(result.getInt(10));
                humPlanBean.setHum7(result.getInt(11));
                humPlanBean.setHum8(result.getInt(12));
                humPlanBean.setHum9(result.getInt(13));
            }else if(result.getInt(4)==10){
                humPlanBean.setHum1(result.getInt(5));
                humPlanBean.setHum2(result.getInt(6));
                humPlanBean.setHum3(result.getInt(7));
                humPlanBean.setHum4(result.getInt(8));
                humPlanBean.setHum5(result.getInt(9));
                humPlanBean.setHum6(result.getInt(10));
                humPlanBean.setHum7(result.getInt(11));
                humPlanBean.setHum8(result.getInt(12));
                humPlanBean.setHum9(result.getInt(13));
                humPlanBean.setHum10(result.getInt(14));
            }



            humPlanBean.setIsCheck(result.getInt(15));

            result.close();
            return humPlanBean;
        }
        result.close();
        return null;
    }
}
