package com.nimeng.bean;

import java.util.Date;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/30 15:40
 * <p>
 * Changes (from 2022/6/30)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/30 : Create QueryBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class QueryBean {
    private String name;
    private Date time;
    private float PVTem;
    private float PVHum;
    private String state;
    private float standardTem;
    private float standardHum;
    private float dewPointTem;
    private float tem;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getPVTem() {
        return PVTem;
    }

    public void setPVTem(float PVTem) {
        this.PVTem = PVTem;
    }

    public float getPVHum() {
        return PVHum;
    }

    public void setPVHum(float PVHum) {
        this.PVHum = PVHum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getStandardTem() {
        return standardTem;
    }

    public void setStandardTem(float standardTem) {
        this.standardTem = standardTem;
    }

    public float getStandardHum() {
        return standardHum;
    }

    public void setStandardHum(float standardHum) {
        this.standardHum = standardHum;
    }

    public float getDewPointTem() {
        return dewPointTem;
    }

    public void setDewPointTem(float dewPointTem) {
        this.dewPointTem = dewPointTem;
    }

    public float getTem() {
        return tem;
    }

    public void setTem(float tem) {
        this.tem = tem;
    }

    public QueryBean() {
    }

    public QueryBean(String name, Date time, float PVTem, float PVHum, String state, float standardTem, float standardHum, float dewPointTem, float tem) {
        this.name = name;
        this.time = time;
        this.PVTem = PVTem;
        this.PVHum = PVHum;
        this.state = state;
        this.standardTem = standardTem;
        this.standardHum = standardHum;
        this.dewPointTem = dewPointTem;
        this.tem = tem;
    }
}
