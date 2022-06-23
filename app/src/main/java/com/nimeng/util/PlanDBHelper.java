package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nimeng.View.PlanActivity;
import com.nimeng.bean.GlobalVariable;
import com.nimeng.bean.PlanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/6 8:47
 * <p>
 * Changes (from 2022/5/6)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/6 : Create PlanDBHelper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class PlanDBHelper extends BaseUtil {
    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    public static final String TABLENAME="plan";

    public PlanDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
       db=this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "
                +TABLENAME+
                " ("+
                "id integer primary key,"+
                "name varchar(20) not null,"+
                "unitTime  tinyint(2) not null,"+
                "temWave float(5) not null,"+
                "humWave float(5) ,"+
                "tem1 float(5) ,"+
                "tem2 float(5) ,"+
                "tem3 float(5) ,"+
                "hum1 float(5) ,"+
                "hum2 float(5) ,"+
                "hum3 float(5) ,"+
                "isCheck tinyint(2)"+
                ")";

        sqLiteDatabase.execSQL(sql);

    }


    //添加
    public boolean add(PlanBean planBean){






        contentValues.put("name",planBean.getName());
        contentValues.put("unitTime",planBean.getUnitTime());
        contentValues.put("temWave",planBean.getTemWave());
        contentValues.put("humWave",planBean.getHumWave());
        contentValues.put("tem1",planBean.getTem1());
        contentValues.put("tem2",planBean.getTem2());
        contentValues.put("tem3",planBean.getTem3());
        contentValues.put("hum1",planBean.getHum1());
        contentValues.put("hum2",planBean.getHum2());
        contentValues.put("hum3",planBean.getHum3());
        contentValues.put("isCheck",planBean.getIsCheck());




        long result= db.insert("plan",null,contentValues);

        return result>0? true:false;

    }

    //删除
    public boolean delete(String ID){



        int result =db.delete(TABLENAME,"id=?",new String[]{ID});
        return result>0? true:false;
    }


    //不再提供方案更新方法
    //更新
//    public boolean update(PlanBean planBean){
//        contentValues.put("id",planBean.getID());
//        contentValues.put("name",planBean.getName());
//        contentValues.put("unitTime",planBean.getUnitTime());
//        contentValues.put("temWave",planBean.getTemWave());
//        contentValues.put("humWave",planBean.getHumWave());
//        contentValues.put("tem1",planBean.getTem1());
//        contentValues.put("tem2",planBean.getTem2());
//        contentValues.put("tem3",planBean.getTem3());
//        contentValues.put("hum1",planBean.getHum1());
//        contentValues.put("hum2",planBean.getHum2());
//        contentValues.put("hum3",planBean.getHum3());
//        int result=db.update(TABLENAME,contentValues,"id=?",new String[]{String.valueOf( planBean.getID())});
//
//        return result>0 ? true:false;
//    }



    //查询
    public List<PlanBean> query(){
        List<PlanBean> list =new ArrayList<>();

        if(!tableIsExist(TABLENAME)){
            return list;
        }

        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            while (result.moveToNext()){
                PlanBean planBean=new PlanBean();
                planBean.setID(result.getInt(0));
                planBean.setName(result.getString(1));
                planBean.setUnitTime(result.getInt(2));
                planBean.setTemWave(result.getFloat(3));
                planBean.setHumWave(result.getFloat(4));
                planBean.setTem1(result.getString(5));
                planBean.setTem2(result.getString(6));
                planBean.setTem3(result.getString(7));
                planBean.setHum1(result.getString(8));
                planBean.setHum2(result.getString(9));
                planBean.setHum3(result.getString(10));
                planBean.setIsCheck(result.getInt(11));

                list.add(planBean);

            }result.close();
        }

        return list;
    }





    //通过方案名称查询方案
    public PlanBean findPlanByName(String name){
        PlanBean planBean=new PlanBean();
        Cursor result =db.query(TABLENAME,null,"name=?",new String[]{name},null,null,null,null);

        if(result.getCount()==1){
            result.moveToFirst();
            planBean.setName(result.getString(1));
            planBean.setUnitTime(result.getInt(2));
            planBean.setTemWave(result.getFloat(3));
            planBean.setHumWave(result.getFloat(4));
            planBean.setTem1(result.getString(5));
            planBean.setTem2(result.getString(6));
            planBean.setTem3(result.getString(7));
            planBean.setHum1(result.getString(8));
            planBean.setHum2(result.getString(9));
            planBean.setHum3(result.getString(10));
            planBean.setIsCheck(result.getInt(11));
            return planBean;
        }
        planBean.setName(null);
        planBean.setUnitTime(0);
        planBean.setTemWave(0);
        planBean.setHumWave(0);
        planBean.setTem1("");
        planBean.setTem2("");
        planBean.setTem3("");
        planBean.setHum1("");
        planBean.setHum2("");
        planBean.setHum3("");
        planBean.setIsCheck(0);
        return planBean;

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








    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




 //   AlertDialog.Builder builder=new AlertDialog.Builder(PlanActivity.this);
//        builder.setTitle("修改方案")
//                .setView(editView)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String name=editName.getText().toString();
//                        int unitTime=Integer.valueOf( editUnitTime.getText().toString());
//                        float temWave=Float.parseFloat( editTemWave.getText().toString());
//                        float humWave=Float.parseFloat(editHumWave.getText().toString());
//                        String tem1=editTem1.getText().toString();
//                        String tem2=editTem2.getText().toString();
//                        String tem3=editTem3.getText().toString();
//                        String hum1=editHum1.getText().toString();
//                        String hum2=editHum2.getText().toString();
//                        String hum3=editHum3.getText().toString();
//
//                        if(name==""){
//                            showToast("预设方案名称不能为空");
//                        }if(unitTime==0){
//                            showToast("单位时间不能为0");
//                        }if(temWave==0){
//                            showToast("温度波动为0");
//                        }if(humWave==0){
//                            showToast("湿度波动不能为0");
//                        }
//                        else {
//                            PlanBean planBean1=planDBHelper.findPlanByName(name);
//                            if(planBean1.getName()==null){
//                                PlanBean planBean2=new PlanBean();
//                                planBean2.setName(name);
//                                planBean2.setUnitTime(unitTime);
//                                planBean2.setTemWave(temWave);
//                                planBean2.setHumWave(humWave);
//                                planBean2.setTem1(tem1);
//                                planBean2.setTem2(tem2);
//                                planBean2.setTem3(tem3);
//                                planBean2.setHum1(hum1);
//                                planBean2.setHum2(hum2);
//                                planBean2.setHum3(hum3);
//                               if( planDBHelper.update(planBean2)){
//                                   showToast("预设方案修改成功");
//                               }else{
//                                   showToast("预设方案修改失败");
//                               }
//                            }else{
//                                showToast("该方案已经存在，不能修改");
//                            }
//                        }
//
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//        AlertDialog alertDialog=builder.create();
//        alertDialog.show();
}
