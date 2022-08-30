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
    private int humPoints;
    private int hum1;
    private int hum2;
    private int hum3;
    private int hum4;
    private int hum5;
    private int hum6;
    private int hum7;
    private int hum8;
    private int hum9;
    private int hum10;
    private int isCheck;

    public HumPlanBean() {
    }

    public HumPlanBean(int ID, String name, int unitTime, float humWave, int humPoints, int hum1, int hum2, int hum3, int hum4, int hum5, int hum6, int hum7, int hum8, int hum9, int hum10, int isCheck) {
        this.ID = ID;
        this.name = name;
        this.unitTime = unitTime;
        this.humWave = humWave;
        this.humPoints = humPoints;
        this.hum1 = hum1;
        this.hum2 = hum2;
        this.hum3 = hum3;
        this.hum4 = hum4;
        this.hum5 = hum5;
        this.hum6 = hum6;
        this.hum7 = hum7;
        this.hum8 = hum8;
        this.hum9 = hum9;
        this.hum10 = hum10;
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

    public int getHumPoints() {
        return humPoints;
    }

    public void setHumPoints(int humPoints) {
        this.humPoints = humPoints;
    }

    public int getHum1() {
        return hum1;
    }

    public void setHum1(int hum1) {
        this.hum1 = hum1;
    }

    public int getHum2() {
        return hum2;
    }

    public void setHum2(int hum2) {
        this.hum2 = hum2;
    }

    public int getHum3() {
        return hum3;
    }

    public void setHum3(int hum3) {
        this.hum3 = hum3;
    }

    public int getHum4() {
        return hum4;
    }

    public void setHum4(int hum4) {
        this.hum4 = hum4;
    }

    public int getHum5() {
        return hum5;
    }

    public void setHum5(int hum5) {
        this.hum5 = hum5;
    }

    public int getHum6() {
        return hum6;
    }

    public void setHum6(int hum6) {
        this.hum6 = hum6;
    }

    public int getHum7() {
        return hum7;
    }

    public void setHum7(int hum7) {
        this.hum7 = hum7;
    }

    public int getHum8() {
        return hum8;
    }

    public void setHum8(int hum8) {
        this.hum8 = hum8;
    }

    public int getHum9() {
        return hum9;
    }

    public void setHum9(int hum9) {
        this.hum9 = hum9;
    }

    public int getHum10() {
        return hum10;
    }

    public void setHum10(int hum10) {
        this.hum10 = hum10;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "HumPlanBean{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", unitTime=" + unitTime +
                ", humWave=" + humWave +
                ", humPoints=" + humPoints +
                ", hum1=" + hum1 +
                ", hum2=" + hum2 +
                ", hum3=" + hum3 +
                ", hum4=" + hum4 +
                ", hum5=" + hum5 +
                ", hum6=" + hum6 +
                ", hum7=" + hum7 +
                ", hum8=" + hum8 +
                ", hum9=" + hum9 +
                ", hum10=" + hum10 +
                ", isCheck=" + isCheck +
                '}';
    }
}
