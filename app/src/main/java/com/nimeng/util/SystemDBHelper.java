package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.nimeng.bean.Password;
import com.nimeng.bean.SystemData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemDBHelper extends BaseUtil{



    private SQLiteDatabase db;

    public static final String TABLENAME="systemData";

    public SystemDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql1="create table "
                + TABLENAME +
                " ("+
                "id integer primary key,"+
                "temUnitTime int ,"+                             //当前温度方案判断是否稳定的时间
                "humUnitTime int ,"+                            //当前湿度方案判断是否稳定的时间
                "temWave float ,"+                              //当前方案是否稳定的温度判断
                "humWave float ,"+                              //当前方案是否稳定的湿度判断
                "isStable boolean ,"+                           //当前方案是否已达到稳定状态
                "temPlanID int ,"+                              //正在执行的温度方案ID
                "humPlanID int ,"+                              //正在执行的湿度方案ID
                "startTime Date ,"+                             //方案开始的时间
                "stableTime Date ,"+                            //达到稳定的时间
                "executingTemID int ,"+                         //正在执行的温度编号
                "executingHumID int ,"+                         //正在执行的湿度编号
                "temStandardID int ,"+                          //温度标准器ID
                "humStandardID int ,"+                          //湿度标准器ID
                "haveJurisdiction boolean ,"+                   //是否获取文件读写权限
                "dataRecordingTime Date ,"+                     //数据记录时间
                "lightStartTime Date ,"+                        //灯控开关的开始时间
                "lightKeepSecond int ,"+                        //灯控开关的维持时间
                "select1 varchar ,"+                            //温度pid参数显示几行
                "select2 varchar  , "+                          //湿度pid参数显示几行
                "numberOfStages int ,"+                         //分期付款的期数
                "isInstallmentPayment boolean ,"+               //是否需要分期付款
                "superPassword varchar,"+                        //超级密码
                "settingTem int ,"+                             //设定温度
                "settingHum int ,"+                            //设定湿度
                "temOnOrOff int,"+                              //温度开关状态
                "humOnOrOff int,"+                               //湿度开关状态
                "temIsClick int,"+                              //温度坐标轴是否点击
                "humIsClick int,"+                               //湿度坐标轴是否点击
                "isFormal int,"+                                 //是否生产环境
                "temChange varchar,"+                           // 温度变化速率
                "humChange varchar,"+                            //湿度变化速率
                "temPower varchar,"+                            //温度功率
                "humPower varchar"+                             //湿度功率
                ")";







            sqLiteDatabase.execSQL(sql1);







    }



    public boolean addSystemData(SystemData systemData){
         CommonUtil commonUtil=new CommonUtil();
        if(!tableIsExist(TABLENAME)){
            onCreate(db);
        }

        ContentValues contentValues=new ContentValues();
        contentValues.put("temUnitTime",systemData.getTemUnitTime());
        contentValues.put("humUnitTime",systemData.getHumUnitTime());
        contentValues.put("temWave",systemData.getTemWave());
        contentValues.put("humWave",systemData.getHumWave());
        contentValues.put("isStable",systemData.isStable());
        contentValues.put("temPlanID",systemData.getTemPlanID());
        contentValues.put("humPlanID",systemData.getHumPlanID());
        if(systemData.getStartTime()!=null){
            contentValues.put("startTime",commonUtil.getDateTimeToString(systemData.getStartTime()));
        }
        if(systemData.getStableTime()!=null){
            contentValues.put("stableTime",commonUtil.getDateTimeToString(systemData.getStableTime()));
        }


        contentValues.put("executingTemID",systemData.getExecutingTemID());
        contentValues.put("executingHumID",systemData.getExecutingHumID());
        contentValues.put("temStandardID",systemData.getTemStandardID());
        contentValues.put("humStandardID",systemData.getHumStandardID());
        contentValues.put("haveJurisdiction",systemData.isHaveJurisdiction());
        if(systemData.getDataRecordingTime()!=null){
            contentValues.put("dataRecordingTime",commonUtil.getDateTimeToString(systemData.getDataRecordingTime()));
        }
       if(systemData.getLightStartTime()!=null){
           contentValues.put("lightStartTime",commonUtil.getDateTimeToString(systemData.getLightStartTime()));
       }

        contentValues.put("lightKeepSecond",systemData.getLightKeepSecond());
        contentValues.put("select1",systemData.getSelect1());
        contentValues.put("select2",systemData.getSelect2());
        contentValues.put("numberOfStages",systemData.getNumberOfStages());
        contentValues.put("isInstallmentPayment",systemData.isInstallmentPayment());
        contentValues.put("superPassword",systemData.getSuperPassword());
        contentValues.put("settingTem",systemData.getSettingTem());
        contentValues.put("settingHum",systemData.getSettingHum());
        contentValues.put("temOnOrOff",systemData.getTemOnOrOff());
        contentValues.put("humOnOrOff",systemData.getHumOnOrOff());
        contentValues.put("temIsClick",systemData.getTemIsClick());
        contentValues.put("humIsClick",systemData.getHumIsClick());
        contentValues.put("isFormal",systemData.getIsFormal());
        contentValues.put("temChange",systemData.getTemChange());
        contentValues.put("humChange",systemData.getHumChange());
        contentValues.put("temPower",systemData.getTemPower());
        contentValues.put("humPower",systemData.getHumPower());


          long result= db.insert(TABLENAME,null,contentValues);
           return result>0?true:false;


    }

    public void updateSystemData(SystemData systemData){
        ContentValues contentValues=new ContentValues();
        contentValues.put("temUnitTime",systemData.getTemUnitTime());
        contentValues.put("humUnitTime",systemData.getHumUnitTime());
        contentValues.put("temWave",systemData.getTemWave());
        contentValues.put("humWave",systemData.getHumWave());
        contentValues.put("isStable",systemData.isStable());
        contentValues.put("temPlanID",systemData.getTemPlanID());
        contentValues.put("humPlanID",systemData.getHumPlanID());
        if(systemData.getStartTime()!=null){
            contentValues.put("startTime",getDateTimeToString(systemData.getStartTime()));
        }
        if(systemData.getStableTime()!=null){
            contentValues.put("stableTime",getDateTimeToString(systemData.getStableTime()));
        }

        contentValues.put("executingTemID",systemData.getExecutingTemID());
        contentValues.put("executingHumID",systemData.getExecutingHumID());
        contentValues.put("temStandardID",systemData.getTemStandardID());
        contentValues.put("humStandardID",systemData.getHumStandardID());
        contentValues.put("haveJurisdiction",systemData.isHaveJurisdiction());
        if(systemData.getDataRecordingTime()!=null){
            contentValues.put("dataRecordingTime",getDateTimeToString(systemData.getDataRecordingTime()));
        }
        if(systemData.getLightStartTime()!=null){
            contentValues.put("lightStartTime",getDateTimeToString(systemData.getLightStartTime()));
        }

        contentValues.put("lightKeepSecond",systemData.getLightKeepSecond());
        contentValues.put("select1",systemData.getSelect1());
        contentValues.put("select2",systemData.getSelect2());
        contentValues.put("numberOfStages",systemData.getNumberOfStages());
        contentValues.put("isInstallmentPayment",systemData.isInstallmentPayment());
        contentValues.put("superPassword",systemData.getSuperPassword());
        contentValues.put("settingTem",systemData.getSettingTem());
        contentValues.put("settingHum",systemData.getSettingHum());
        contentValues.put("temOnOrOff",systemData.getTemOnOrOff());
        contentValues.put("humOnOrOff",systemData.getHumOnOrOff());
        contentValues.put("temIsClick",systemData.getTemIsClick());
        contentValues.put("humIsClick",systemData.getHumIsClick());
        contentValues.put("isFormal",systemData.getIsFormal());
        contentValues.put("temChange",systemData.getTemChange());
        contentValues.put("humChange",systemData.getHumChange());
        contentValues.put("temPower",systemData.getTemPower());
        contentValues.put("humPower",systemData.getHumPower());
        long result=db.update(TABLENAME,contentValues,"id=?",new String[]{"1"});
        return ;

    }

    public SystemData getSystemData(){

        if(!tableIsExist(TABLENAME)){
            return null;
        }

        Cursor result=db.query(TABLENAME,null,null,null,null,null,null);
        if(result!=null){
            result.moveToLast();
            SystemData systemData=new SystemData();
            systemData.setTemUnitTime(result.getInt(1));
            systemData.setHumUnitTime(result.getInt(2));
            systemData.setTemWave(result.getFloat(3));
            systemData.setHumWave(result.getFloat(4));
            if(result.getInt(5)>0){
                systemData.setStable(true);
            }else{
                systemData.setStable(false);
            }


            systemData.setTemPlanID(result.getInt(6));
            systemData.setHumPlanID(result.getInt(7));

            System.out.println("-----"+result.getString(8)+"---");

            if(result.getString(8)!=null){
                systemData.setStartTime(transferStringToDate(result.getString(8)));
            }
            if(result.getString(9)!=null){
                systemData.setStableTime(transferStringToDate(result.getString(9)));
            }


            systemData.setExecutingTemID(result.getInt(10));
            systemData.setExecutingHumID(result.getInt(11));
            systemData.setTemStandardID(result.getInt(12));
            systemData.setHumStandardID(result.getInt(13));
            if(result.getInt(14)>0){
                systemData.setHaveJurisdiction(true);
            }else{
                systemData.setHaveJurisdiction(false);
            }

            if(result.getString(15)!=null){
                systemData.setDataRecordingTime(transferStringToDate(result.getString(15)));
            }
            if(result.getString(16)!=null){
                systemData.setLightStartTime(transferStringToDate(result.getString(16)));
            }


            systemData.setLightKeepSecond(result.getInt(17));
            systemData.setSelect1(result.getString(18));
            systemData.setSelect2(result.getString(19));
            systemData.setNumberOfStages(result.getInt(20));
            if(result.getInt(21)>0){
                systemData.setInstallmentPayment(true);
            }else{
                systemData.setInstallmentPayment(false);
            }

            systemData.setSuperPassword(result.getString(22));
            systemData.setSettingTem(result.getInt(23));
            systemData.setSettingHum(result.getInt(24));
            systemData.setTemOnOrOff(result.getInt(25));
            systemData.setHumOnOrOff(result.getInt(26));
            systemData.setTemIsClick(result.getInt(27));
            systemData.setHumIsClick(result.getInt(28));
            systemData.setIsFormal(result.getInt(29));
            systemData.setTemChange(result.getString(30));
            systemData.setHumChange(result.getString(31));
            systemData.setTemPower(result.getString(32));
            systemData.setHumPower(result.getString(33));

            result.close();
            return systemData;

        }
        return null;
    }







    public boolean addSwitch(String switchID, boolean isSwitch){
        if(!tableIsExist("systemSwitch")){
            createSwitch();
        }

        ContentValues contentValues=new ContentValues();
        contentValues.put("onOrOff",isSwitch);
        long result1=db.update("systemSwitch",contentValues,"id=?",new String[]{switchID});
        if(result1<=0){
            contentValues.put("id",switchID);
            long result=db.insert("systemSwitch",null,contentValues);
            return result>0?true:false;
        }

       return true;



    }
    public boolean getSwitch(String switchID){
        if(!tableIsExist("systemSwitch")){
            return false;
        }

            Cursor result=db.query("systemSwitch",null,"id=?",new String[]{switchID},null,null,null);
            if(result==null){
                return false;
            }else{
                result.moveToFirst();

                int number=result.getInt(1);


                result.close();
                return number>0?true:false;




            }

    }
    public boolean addPassword(Password password){
        CommonUtil commonUtil=new CommonUtil();
        if(!tableIsExist("password")){
            createPassword();
        }
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",password.getPassword());
        contentValues.put("errorNumbers",password.getErrorNumbers());
        contentValues.put("matchs",password.isMatchs());
        contentValues.put("times",commonUtil.getDateTimeToString(password.getTimes()));
        long result=db.insert("password",null,contentValues);
        return result>0?true:false;
    }
    public void updatePassword(Password password){

        CommonUtil commonUtil=new CommonUtil();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",password.getPassword());
        contentValues.put("errorNumbers",password.getErrorNumbers());
        contentValues.put("matchs",password.isMatchs());
        contentValues.put("times",commonUtil.getDateTimeToString(password.getTimes()));

        db.update("password",contentValues,"id=?",new String[]{String.valueOf(password.getId())});
        return;

    }

    public List<Password> getPassword(){
        CommonUtil commonUtil=new CommonUtil();
        if(!tableIsExist("password")){
            return null;
        }
        List<Password> list=new ArrayList<>();

        Cursor result=db.query("password",null,null,null,null,null,null);
        if(result!=null){
            Password password=new Password();
            while(result.moveToNext()){
                password.setId(result.getInt(0));
                password.setPassword(result.getString(1));
                password.setErrorNumbers(result.getInt(2));
                password.setMatchs(Boolean.valueOf(String.valueOf(result.getInt(3))));
                password.setTimes(commonUtil.transferStringToDate(result.getString(4)));
                list.add(password);
            }result.close();
        }
        return list;
    }


    public void createSwitch(){

        /**
         * 1露点仪开关
         * 2.数字式温度计开关
         * 3.报警开关
         * 4.状态指示开关
         * 5.语音播报开关
         * 6.自动拍摄开关
         * 7.灯控开关
         * 8.转盘显示开关
         * 9.相机显示开关
         * 10.自动拍摄显示开关
         */
        String sql2="create table systemSwitch "
                +"("+
                "id integer primary key ,"+
                "onOrOff boolean"+
                ")";

        db.execSQL(sql2);
    }
    public void createPassword(){

        /**
         * 密码
         * 错误次数
         * 是否匹配
         * 结束时间
         */
        String sql3="create table password "
                +"("+
                "id integer primary key AUTOINCREMENT,"+
                "password varchar ,"+
                "errorNumbers int ,"+
                "matchs boolean ,"+
                "times Date "+
                ")";

        db.execSQL(sql3);
    }




    public Date transferStringToDate(String s){
        Date date=new Date();
        try{
            date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        }catch(ParseException e){

        }
        return date;
    }


}
