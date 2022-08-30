package com.nimeng.bean;

import java.util.Date;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/15 9:39
 * <p>
 * Changes (from 2022/6/15)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/15 : Create DataRecodeBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DataRecodeBean {
    private int id;
    private String time;
    private float settingTem;
    private float realtimeTem;
    private float settingHum;
    private float realtimeHum;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getSettingTem() {
        return settingTem;
    }

    public void setSettingTem(float settingTem) {
        this.settingTem = settingTem;
    }

    public float getRealtimeTem() {
        return realtimeTem;
    }

    public void setRealtimeTem(float realtimeTem) {
        this.realtimeTem = realtimeTem;
    }

    public float getSettingHum() {
        return settingHum;
    }

    public void setSettingHum(float settingHum) {
        this.settingHum = settingHum;
    }

    public float getRealtimeHum() {
        return realtimeHum;
    }

    public void setRealtimeHum(float realtimeHum) {
        this.realtimeHum = realtimeHum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "DataRecodeBean{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", settingTem=" + settingTem +
                ", realtimeTem=" + realtimeTem +
                ", settingHum=" + settingHum +
                ", realtimeHum=" + realtimeHum +
                '}';
    }
}
