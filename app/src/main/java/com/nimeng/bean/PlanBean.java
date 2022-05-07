package com.nimeng.bean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/5 16:02
 * <p>
 * Changes (from 2022/5/5)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/5 : Create PlanBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class PlanBean {
    private int ID;
    private String name;
    private int unitTime;
    private float temWave;
    private float humWave;
    private String tem1;
    private String tem2;
    private String tem3;
    private String hum1;
    private String hum2;
    private String hum3;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(int unitTime) {
        this.unitTime = unitTime;
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

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String getTem3() {
        return tem3;
    }

    public void setTem3(String tem3) {
        this.tem3 = tem3;
    }

    public String getHum1() {
        return hum1;
    }

    public void setHum1(String hum1) {
        this.hum1 = hum1;
    }

    public String getHum2() {
        return hum2;
    }

    public void setHum2(String hum2) {
        this.hum2 = hum2;
    }

    public String getHum3() {
        return hum3;
    }

    public void setHum3(String hum3) {
        this.hum3 = hum3;
    }


    public PlanBean() {
    }

    public PlanBean(int ID, String name, int unitTime, float temWave, float humWave, String tem1, String tem2, String tem3, String hum1, String hum2, String hum3) {
        this.ID = ID;
        this.name = name;
        this.unitTime = unitTime;
        this.temWave = temWave;
        this.humWave = humWave;
        this.tem1 = tem1;
        this.tem2 = tem2;
        this.tem3 = tem3;
        this.hum1 = hum1;
        this.hum2 = hum2;
        this.hum3 = hum3;
    }
}
