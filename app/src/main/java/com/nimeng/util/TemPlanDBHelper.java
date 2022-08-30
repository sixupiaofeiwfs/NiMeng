package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;


import com.nimeng.bean.TemPlanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/21 15:19
 * <p>
 * Changes (from 2022/7/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/21 : Create TemPlanDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class TemPlanDBHelper extends BaseUtil {

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="templan";

    public TemPlanDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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
                "temWave float(5) not null,"+
                "points tinyint(2) not null,"+
                "tem1 float(5) ,"+
                "tem2 float(5) ,"+
                "tem3 float(5) ,"+
                "tem4 float(5) ,"+
                "tem5 float(5) ,"+
                "tem6 float(5) ,"+
                "tem7 float(5) ,"+
                "tem8 float(5) ,"+
                "tem9 float(5) ,"+
                "tem10 float(5) ,"+
                "isCheck tinyint(2)"+
                ")";

        sqLiteDatabase.execSQL(sql);

    }


    public boolean add(TemPlanBean temPlanBean){


        System.out.println("添加时："+temPlanBean);

        if(!tableIsExist(TABLENAME)){
            onCreate(db);
        }
        contentValues.put("name",temPlanBean.getName());
        contentValues.put("unitTime",temPlanBean.getUnitTime());
        contentValues.put("temWave",temPlanBean.getTemWave());
        contentValues.put("points",temPlanBean.getTemPoints());
        if(temPlanBean.getTemPoints()==1){
            contentValues.put("tem1",temPlanBean.getTem1());
        }else if(temPlanBean.getTemPoints()==2){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
        }else if(temPlanBean.getTemPoints()==3){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
            contentValues.put("tem3",temPlanBean.getTem3());
        }else if(temPlanBean.getTemPoints()==4){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
            contentValues.put("tem3",temPlanBean.getTem3());
            contentValues.put("tem4",temPlanBean.getTem4());
        }else if(temPlanBean.getTemPoints()==5){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
            contentValues.put("tem3",temPlanBean.getTem3());
            contentValues.put("tem4",temPlanBean.getTem4());
            contentValues.put("tem5",temPlanBean.getTem5());
        }else if(temPlanBean.getTemPoints()==6){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
            contentValues.put("tem3",temPlanBean.getTem3());
            contentValues.put("tem4",temPlanBean.getTem4());
            contentValues.put("tem5",temPlanBean.getTem5());
            contentValues.put("tem6",temPlanBean.getTem6());
        }else if(temPlanBean.getTemPoints()==7){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
            contentValues.put("tem3",temPlanBean.getTem3());
            contentValues.put("tem4",temPlanBean.getTem4());
            contentValues.put("tem5",temPlanBean.getTem5());
            contentValues.put("tem6",temPlanBean.getTem6());
            contentValues.put("tem7",temPlanBean.getTem7());
        }else if(temPlanBean.getTemPoints()==8){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
            contentValues.put("tem3",temPlanBean.getTem3());
            contentValues.put("tem4",temPlanBean.getTem4());
            contentValues.put("tem5",temPlanBean.getTem5());
            contentValues.put("tem6",temPlanBean.getTem6());
            contentValues.put("tem7",temPlanBean.getTem7());
            contentValues.put("tem8",temPlanBean.getTem8());
        }else if(temPlanBean.getTemPoints()==9){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
            contentValues.put("tem3",temPlanBean.getTem3());
            contentValues.put("tem4",temPlanBean.getTem4());
            contentValues.put("tem5",temPlanBean.getTem5());
            contentValues.put("tem6",temPlanBean.getTem6());
            contentValues.put("tem7",temPlanBean.getTem7());
            contentValues.put("tem8",temPlanBean.getTem8());
            contentValues.put("tem9",temPlanBean.getTem9());
        }else if(temPlanBean.getTemPoints()==10){
            contentValues.put("tem1",temPlanBean.getTem1());
            contentValues.put("tem2",temPlanBean.getTem2());
            contentValues.put("tem3",temPlanBean.getTem3());
            contentValues.put("tem4",temPlanBean.getTem4());
            contentValues.put("tem5",temPlanBean.getTem5());
            contentValues.put("tem6",temPlanBean.getTem6());
            contentValues.put("tem7",temPlanBean.getTem7());
            contentValues.put("tem8",temPlanBean.getTem8());
            contentValues.put("tem9",temPlanBean.getTem9());
            contentValues.put("tem10",temPlanBean.getTem10());
        }
        contentValues.put("isCheck",temPlanBean.getIsCheck());
        long result=db.insert(TABLENAME,null,contentValues);
        return result>0?true:false;

    }


    //删除
    public boolean delete(String ID){
        int result =db.delete(TABLENAME,"id=?",new String[]{ID});
        return result>0?true:false;
    }


    //查询
    public List<TemPlanBean> query(){
        List<TemPlanBean> list =new ArrayList<TemPlanBean>();

        if(!tableIsExist(TABLENAME)){
            return null;
        }

        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while (result.moveToNext()){
                TemPlanBean temPlanBean=new TemPlanBean();
                temPlanBean.setID(result.getInt(0));
                temPlanBean.setName(result.getString(1));
                temPlanBean.setUnitTime(result.getInt(2));
                temPlanBean.setTemWave(result.getInt(3));
                temPlanBean.setTemPoints(result.getInt(4));


                if(result.getInt(4)==1){
                    temPlanBean.setTem1(result.getInt(5));
                }else if(result.getInt(4)==2){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                }else if(result.getInt(4)==3){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                    temPlanBean.setTem3(result.getInt(7));
                }else if(result.getInt(4)==4){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                    temPlanBean.setTem3(result.getInt(7));
                    temPlanBean.setTem4(result.getInt(8));
                }else if(result.getInt(4)==5){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                    temPlanBean.setTem3(result.getInt(7));
                    temPlanBean.setTem4(result.getInt(8));
                    temPlanBean.setTem5(result.getInt(9));
                }else if(result.getInt(4)==6){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                    temPlanBean.setTem3(result.getInt(7));
                    temPlanBean.setTem4(result.getInt(8));
                    temPlanBean.setTem5(result.getInt(9));
                    temPlanBean.setTem6(result.getInt(10));
                }else if(result.getInt(4)==7){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                    temPlanBean.setTem3(result.getInt(7));
                    temPlanBean.setTem4(result.getInt(8));
                    temPlanBean.setTem5(result.getInt(9));
                    temPlanBean.setTem6(result.getInt(10));
                    temPlanBean.setTem7(result.getInt(11));
                }else if(result.getInt(4)==8){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                    temPlanBean.setTem3(result.getInt(7));
                    temPlanBean.setTem4(result.getInt(8));
                    temPlanBean.setTem5(result.getInt(9));
                    temPlanBean.setTem6(result.getInt(10));
                    temPlanBean.setTem7(result.getInt(11));
                    temPlanBean.setTem8(result.getInt(12));
                }else if(result.getInt(4)==9){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                    temPlanBean.setTem3(result.getInt(7));
                    temPlanBean.setTem4(result.getInt(8));
                    temPlanBean.setTem5(result.getInt(9));
                    temPlanBean.setTem6(result.getInt(10));
                    temPlanBean.setTem7(result.getInt(11));
                    temPlanBean.setTem8(result.getInt(12));
                    temPlanBean.setTem9(result.getInt(13));
                }else if(result.getInt(4)==10){
                    temPlanBean.setTem1(result.getInt(5));
                    temPlanBean.setTem2(result.getInt(6));
                    temPlanBean.setTem3(result.getInt(7));
                    temPlanBean.setTem4(result.getInt(8));
                    temPlanBean.setTem5(result.getInt(9));
                    temPlanBean.setTem6(result.getInt(10));
                    temPlanBean.setTem7(result.getInt(11));
                    temPlanBean.setTem8(result.getInt(12));
                    temPlanBean.setTem9(result.getInt(13));
                    temPlanBean.setTem10(result.getInt(14));
                }



                temPlanBean.setIsCheck(result.getInt(15));

                System.out.println("展示时："+temPlanBean);
                list.add(temPlanBean);

            }result.close();
            return list;
        }

       return null;
    }



    //通过方案名称查询方案
    public int findTemPlanByName(String name){
        if(!tableIsExist(TABLENAME)){
           return 0;
        }


        Cursor result =db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        int number=result.getCount();
        result.close();
        return number;


    }


    //更新被选中的信息

    /**
     *
     * @param id 现在选中的temPlanID
     * @param isCheckID 之前选中的temPlanID
     * @return
     */
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

    //通过TemID和tem？查询
    public int queryByID(int id,int temId){
        if(!tableIsExist(TABLENAME)){
            return 0;
        }

        if(temId==0){
            return 0;
        }

        Cursor result=db.query(TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(result.getCount()==0){
            result.close();
            return 0;
        }
        result.moveToFirst();
        int numberResult= result.getInt(temId+4);
        result.close();
        return numberResult;
    }


    public TemPlanBean queryByID(int id){
        if(!tableIsExist(TABLENAME)){
            return null;
        }

        Cursor result=db.query(TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(result!=null){
            result.moveToFirst();

            TemPlanBean temPlanBean=new TemPlanBean();
            temPlanBean.setID(result.getInt(0));
            temPlanBean.setName(result.getString(1));
            temPlanBean.setUnitTime(result.getInt(2));
            temPlanBean.setTemWave(result.getFloat(3));
            temPlanBean.setTemPoints(result.getInt(4));


            if(result.getInt(4)==1){
                temPlanBean.setTem1(result.getInt(5));
            }else if(result.getInt(4)==2){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
            }else if(result.getInt(4)==3){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
                temPlanBean.setTem3(result.getInt(7));
            }else if(result.getInt(4)==4){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
                temPlanBean.setTem3(result.getInt(7));
                temPlanBean.setTem4(result.getInt(8));
            }else if(result.getInt(4)==5){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
                temPlanBean.setTem3(result.getInt(7));
                temPlanBean.setTem4(result.getInt(8));
                temPlanBean.setTem5(result.getInt(9));
            }else if(result.getInt(4)==6){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
                temPlanBean.setTem3(result.getInt(7));
                temPlanBean.setTem4(result.getInt(8));
                temPlanBean.setTem5(result.getInt(9));
                temPlanBean.setTem6(result.getInt(10));
            }else if(result.getInt(4)==7){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
                temPlanBean.setTem3(result.getInt(7));
                temPlanBean.setTem4(result.getInt(8));
                temPlanBean.setTem5(result.getInt(9));
                temPlanBean.setTem6(result.getInt(10));
                temPlanBean.setTem7(result.getInt(11));
            }else if(result.getInt(4)==8){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
                temPlanBean.setTem3(result.getInt(7));
                temPlanBean.setTem4(result.getInt(8));
                temPlanBean.setTem5(result.getInt(9));
                temPlanBean.setTem6(result.getInt(10));
                temPlanBean.setTem7(result.getInt(11));
                temPlanBean.setTem8(result.getInt(12));
            }else if(result.getInt(4)==9){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
                temPlanBean.setTem3(result.getInt(7));
                temPlanBean.setTem4(result.getInt(8));
                temPlanBean.setTem5(result.getInt(9));
                temPlanBean.setTem6(result.getInt(10));
                temPlanBean.setTem7(result.getInt(11));
                temPlanBean.setTem8(result.getInt(12));
                temPlanBean.setTem9(result.getInt(13));
            }else if(result.getInt(4)==10){
                temPlanBean.setTem1(result.getInt(5));
                temPlanBean.setTem2(result.getInt(6));
                temPlanBean.setTem3(result.getInt(7));
                temPlanBean.setTem4(result.getInt(8));
                temPlanBean.setTem5(result.getInt(9));
                temPlanBean.setTem6(result.getInt(10));
                temPlanBean.setTem7(result.getInt(11));
                temPlanBean.setTem8(result.getInt(12));
                temPlanBean.setTem9(result.getInt(13));
                temPlanBean.setTem10(result.getInt(14));
            }



            temPlanBean.setIsCheck(result.getInt(15));

            result.close();
            return temPlanBean;
        }
        result.close();
        return null;
    }

}
