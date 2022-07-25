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

import java.util.Date;

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
public class GlobalVariable extends Application {


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



}
