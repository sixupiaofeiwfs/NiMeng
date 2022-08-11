package com.nimeng.bean;





import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/6 15:19
 * <p>
 * Changes (from 2022/7/6)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/6 : Create GlobalVariable.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public  class GlobalVariable extends Application {


    private static final int REQUEST_CODE = 1024;
    private int temUnitTime;//当前温度方案判断时间
    private int humUnitTime;//当前湿度方案判断时间
    private float temWave;//当前方案是否稳定的温度判断
    private float humWave;//当前方案是否稳定的湿度判断
    private boolean isStable;//是否达到稳定状态
    private String temPlanName;//当前温度方案的名称
    private String humPlanName;//当前湿度方案的名称
    private Date startTime;//方案开始的时间
    private Date stableTime;//达到稳定的时间
    private int temID;//当前系统执行的温度预设方案（在系统预设方案时，温度991-999   自定义时temID=ID）
    private int humID;//当前系统执行的湿度预设方案（在系统预设方案是，湿度1001-1099 自定义时humID=ID）
    private int executingTemID;//当前正在执行的温度编号
    private int executingHumID;//当前正在执行的湿度编号
    private boolean temIsSystem;//当前温度方案是否系统预设
    private boolean humIsSystem;//当前湿度方式是否系统预设

    private int temStandardID;//温度标准器id
    private int humStandardID;//湿度标准器id

    public int getTemStandardID() {
        return temStandardID;
    }

    public void setTemStandardID(int temStandardID) {
        this.temStandardID = temStandardID;
    }

    public int getHumStandardID() {
        return humStandardID;
    }

    public void setHumStandardID(int humStandardID) {
        this.humStandardID = humStandardID;
    }

    public  boolean switch_1=false;//露点仪开关
    public  boolean switch_2=false;//数字式温度计开关
    public  boolean switch_3=false;//报警开关
    public  boolean switch_4=false;//状态指示开关
    public  boolean switch_5=false;//语音播报开关
    public  boolean switch_6=false;//自动拍摄开关
    public  boolean switch_7=false;//灯控开关
    public  boolean switch_8=false;//转盘显示开关
    public  boolean switch_9=false;//相机显示开关
    public  boolean switch_10=false;//自动拍摄显示开关


    public boolean haveJurisdiction=false;//是否获取文件读写权限





    public int numberOfStages;//分期数
    public boolean isInstallmentPayment=false;//是否需要分期付款
    public String superPassword="";//超级密码
    public List<String> passwords;//密码
    public List<Integer> errorNumbers;//错误次数
    public List<Boolean> matchs;//是否匹配
    public List<String> times;//分期结束时间


    public Date dataRecordingTime=null;


    public Date lightStartTime=null;
    public int lightKeepSecond=0;



    //实时温湿度
    private float realTem;
    private float realHum;



    public String select1;//温度pid取值范围设置
    private String select2;//湿度pid取值范围设置


    //折线图的线程是否已开启，避免重复开启线程，出现一秒钟跳两次的情况
    public boolean threadIsStart=false;

    public List<String> temPlanList=new ArrayList<String>(Arrays.asList("方案一（不设置）","方案二（20℃-40℃-60-80℃）","方案三（15℃-20℃-40℃-60℃-80℃）","方案四（15℃-20℃-40-℃-60℃-80℃-90℃）"));
    public List<String> humPlanList=new ArrayList<String>(Arrays.asList("方案一（不设置）","方案二（40%）","方案三（20%-40%-60%-80%）","方案四（20%-40%-60%-80%-90%）"));


    public Date getLightStartTime() {
        return lightStartTime;
    }

    public void setLightStartTime(Date lightStartTime) {
        this.lightStartTime = lightStartTime;
    }

    public int getLightKeepSecond() {
        return lightKeepSecond;
    }

    public void setLightKeepSecond(int lightKeepSecond) {
        this.lightKeepSecond = lightKeepSecond;
    }

    public List<String> getTemPlanList() {
        return temPlanList;
    }

    public void setTemPlanList(List<String> temPlanList) {
        this.temPlanList = temPlanList;
    }

    public List<String> getHumPlanList() {
        return humPlanList;
    }

    public void setHumPlanList(List<String> humPlanList) {
        this.humPlanList = humPlanList;
    }

    public boolean isThreadIsStart() {
        return threadIsStart;
    }

    public void setThreadIsStart(boolean threadIsStart) {
        this.threadIsStart = threadIsStart;
    }

    public String getTemPlanName() {
        return temPlanName;
    }

    public void setTemPlanName(String temPlanName) {
        this.temPlanName = temPlanName;
    }

    public String getHumPlanName() {
        return humPlanName;
    }

    public void setHumPlanName(String humPlanName) {
        this.humPlanName = humPlanName;
    }
    public int getTemID() {
        return temID;
    }

    public void setTemID(int temID) {
        this.temID = temID;
    }

    public int getHumID() {
        return humID;
    }

    public void setHumID(int humID) {
        this.humID = humID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getTemUnitTime() {
        return temUnitTime;
    }

    public void setTemUnitTime(int temUnitTime) {
        this.temUnitTime = temUnitTime;
    }

    public int getHumUnitTime() {
        return humUnitTime;
    }

    public void setHumUnitTime(int humUnitTime) {
        this.humUnitTime = humUnitTime;
    }

    public boolean isStable() {
        return isStable;
    }

    public void setStable(boolean stable) {
        isStable = stable;
    }

    public Date getStableTime() {
        return stableTime;
    }

    public void setStableTime(Date stableTime) {
        this.stableTime = stableTime;
    }

    public float getTemWave() {
        return temWave;
    }

    public void setTemWave(float temWave) {
        this.temWave = temWave;
    }

    public float getHumWave() {
        return humWave;
    }

    public void setHumWave(float humWave) {
        this.humWave = humWave;
    }

    public int getExecutingTemID() {
        return executingTemID;
    }

    public void setExecutingTemID(int executingTemID) {
        this.executingTemID = executingTemID;
    }

    public int getExecutingHumID() {
        return executingHumID;
    }

    public void setExecutingHumID(int executingHumID) {
        this.executingHumID = executingHumID;
    }

    public boolean isTemIsSystem() {
        return temIsSystem;
    }

    public void setTemIsSystem(boolean temIsSystem) {
        this.temIsSystem = temIsSystem;
    }

    public boolean isHumIsSystem() {
        return humIsSystem;
    }

    public void setHumIsSystem(boolean humIsSystem) {
        this.humIsSystem = humIsSystem;
    }


    public boolean isSwitch_1() {
        return switch_1;
    }

    public void setSwitch_1(boolean switch_1) {
        this.switch_1 = switch_1;
    }

    public boolean isSwitch_2() {
        return switch_2;
    }

    public void setSwitch_2(boolean switch_2) {
        this.switch_2 = switch_2;
    }

    public boolean isSwitch_3() {
        return switch_3;
    }

    public void setSwitch_3(boolean switch_3) {
        this.switch_3 = switch_3;
    }

    public boolean isSwitch_4() {
        return switch_4;
    }

    public void setSwitch_4(boolean switch_4) {
        this.switch_4 = switch_4;
    }

    public boolean isSwitch_5() {
        return switch_5;
    }

    public void setSwitch_5(boolean switch_5) {
        this.switch_5 = switch_5;
    }

    public boolean isSwitch_6() {
        return switch_6;
    }

    public void setSwitch_6(boolean switch_6) {
        this.switch_6 = switch_6;
    }


    public boolean isSwitch_7() {
        return switch_7;
    }

    public void setSwitch_7(boolean switch_7) {
        this.switch_7 = switch_7;
    }


    public boolean isSwitch_8() {
        return switch_8;
    }

    public void setSwitch_8(boolean switch_8) {
        this.switch_8 = switch_8;
    }

    public boolean isSwitch_9() {
        return switch_9;
    }

    public void setSwitch_9(boolean switch_9) {
        this.switch_9 = switch_9;
    }

    public boolean isSwitch_10() {
        return switch_10;
    }

    public void setSwitch_10(boolean switch_10) {
        this.switch_10 = switch_10;
    }

    public boolean isHaveJurisdiction() {
        return haveJurisdiction;
    }

    public void setHaveJurisdiction(boolean haveJurisdiction) {
        this.haveJurisdiction = haveJurisdiction;
    }

    public boolean isInstallmentPayment() {
        return isInstallmentPayment;
    }

    public void setInstallmentPayment(boolean installmentPayment) {
        isInstallmentPayment = installmentPayment;
    }

    public String getSuperPassword() {
        return superPassword;
    }

    public void setSuperPassword(String superPassword) {
        this.superPassword = superPassword;
    }



    public int getNumberOfStages() {
        return numberOfStages;
    }

    public void setNumberOfStages(int numberOfStages) {
        this.numberOfStages = numberOfStages;
    }


    public List<String> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<String> passwords) {
        this.passwords = passwords;
    }

    public List<Integer> getErrorNumbers() {
        return errorNumbers;
    }

    public void setErrorNumbers(List<Integer> errorNumbers) {
        this.errorNumbers = errorNumbers;
    }

    public List<Boolean> getMatchs() {
        return matchs;
    }

    public void setMatchs(List<Boolean> matchs) {
        this.matchs = matchs;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public Date getDataRecordingTime() {
        return dataRecordingTime;
    }

    public void setDataRecordingTime(Date dataRecordingTime) {
        this.dataRecordingTime = dataRecordingTime;
    }

    public float getRealTem() {
        return realTem;
    }

    public void setRealTem(float realTem) {
        this.realTem = realTem;
    }

    public float getRealHum() {
        return realHum;
    }

    public void setRealHum(float realHum) {
        this.realHum = realHum;
    }

    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public String getSelect2() {
        return select2;
    }

    public void setSelect2(String select2) {
        this.select2 = select2;
    }
}
