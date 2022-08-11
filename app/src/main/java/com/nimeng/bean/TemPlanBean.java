package com.nimeng.bean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/21 15:08
 * <p>
 * Changes (from 2022/7/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/21 : Create TemPlanBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class TemPlanBean {
    private int ID;
    private String name;
    private int unitTime;
    private float temWave;
    private int temPoints;
    private float tem1;
    private float tem2;
    private float tem3;
    private float tem4;
    private float tem5;
    private float tem6;
    private float tem7;
    private float tem8;
    private float tem9;
    private float tem10;
    private int isCheck;

    public TemPlanBean() {
    }

    public TemPlanBean(int ID, String name, int unitTime, float temWave, int temPoints, float tem1, float tem2, float tem3, float tem4, float tem5, float tem6, float tem7, float tem8, float tem9, float tem10, int isCheck) {
        this.ID = ID;
        this.name = name;
        this.unitTime = unitTime;
        this.temWave = temWave;
        this.temPoints = temPoints;
        this.tem1 = tem1;
        this.tem2 = tem2;
        this.tem3 = tem3;
        this.tem4 = tem4;
        this.tem5 = tem5;
        this.tem6 = tem6;
        this.tem7 = tem7;
        this.tem8 = tem8;
        this.tem9 = tem9;
        this.tem10 = tem10;
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

    public float getTemWave() {
        return temWave;
    }

    public void setTemWave(float temWave) {
        this.temWave = temWave;
    }

    public float getTem1() {
        return tem1;
    }

    public void setTem1(float tem1) {
        this.tem1 = tem1;
    }

    public float getTem2() {
        return tem2;
    }

    public void setTem2(float tem2) {
        this.tem2 = tem2;
    }

    public float getTem3() {
        return tem3;
    }

    public void setTem3(float tem3) {
        this.tem3 = tem3;
    }

    public float getTem4() {
        return tem4;
    }

    public void setTem4(float tem4) {
        this.tem4 = tem4;
    }

    public float getTem5() {
        return tem5;
    }

    public void setTem5(float tem5) {
        this.tem5 = tem5;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public int getTemPoints() {
        return temPoints;
    }

    public void setTemPoints(int temPoints) {
        this.temPoints = temPoints;
    }

    public float getTem6() {
        return tem6;
    }

    public void setTem6(float tem6) {
        this.tem6 = tem6;
    }

    public float getTem7() {
        return tem7;
    }

    public void setTem7(float tem7) {
        this.tem7 = tem7;
    }

    public float getTem8() {
        return tem8;
    }

    public void setTem8(float tem8) {
        this.tem8 = tem8;
    }

    public float getTem9() {
        return tem9;
    }

    public void setTem9(float tem9) {
        this.tem9 = tem9;
    }

    public float getTem10() {
        return tem10;
    }

    public void setTem10(float tem10) {
        this.tem10 = tem10;
    }

    @Override
    public String toString() {
        return "TemPlanBean{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", unitTime=" + unitTime +
                ", temWave=" + temWave +
                ", temPoints=" + temPoints +
                ", tem1=" + tem1 +
                ", tem2=" + tem2 +
                ", tem3=" + tem3 +
                ", tem4=" + tem4 +
                ", tem5=" + tem5 +
                ", tem6=" + tem6 +
                ", tem7=" + tem7 +
                ", tem8=" + tem8 +
                ", tem9=" + tem9 +
                ", tem10=" + tem10 +
                ", isCheck=" + isCheck +
                '}';
    }
}
