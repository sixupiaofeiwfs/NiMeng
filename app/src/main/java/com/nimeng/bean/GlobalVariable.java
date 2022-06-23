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
    private int ID;//当前系统执行的预设方案
    private Date startTime;//方案开始的时间

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }








}
