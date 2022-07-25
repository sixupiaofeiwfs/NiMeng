package com.nimeng.bean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/21 15:52
 * <p>
 * Changes (from 2022/7/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/21 : Create HumPlanBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class HumPlanBean {
    private int ID;
    private String name;
    private int unitTime;
    private float humWave;
    private float hum1;
    private float hum2;
    private float hum3;
    private float hum4;
    private float hum5;
    private int isCheck;

    public HumPlanBean() {
    }

    public HumPlanBean(int ID, String name, int unitTime, float humWave, float hum1, float hum2, float hum3, float hum4, float hum5, int isCheck) {
        this.ID = ID;
        this.name = name;
        this.unitTime = unitTime;
        this.humWave = humWave;
        this.hum1 = hum1;
        this.hum2 = hum2;
        this.hum3 = hum3;
        this.hum4 = hum4;
        this.hum5 = hum5;
        this.isCheck = isCheck;
    }

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

    public float getHumWave() {
        return humWave;
    }

    public void setHumWave(float humWave) {
        this.humWave = humWave;
    }

    public float getHum1() {
        return hum1;
    }

    public void setHum1(float hum1) {
        this.hum1 = hum1;
    }

    public float getHum2() {
        return hum2;
    }

    public void setHum2(float hum2) {
        this.hum2 = hum2;
    }

    public float getHum3() {
        return hum3;
    }

    public void setHum3(float hum3) {
        this.hum3 = hum3;
    }

    public float getHum4() {
        return hum4;
    }

    public void setHum4(float hum4) {
        this.hum4 = hum4;
    }

    public float getHum5() {
        return hum5;
    }

    public void setHum5(float hum5) {
        this.hum5 = hum5;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }
}
