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
    private int tem1;
    private int tem2;
    private int tem3;
    private int tem4;
    private int tem5;
    private int tem6;
    private int tem7;
    private int tem8;
    private int tem9;
    private int tem10;
    private int isCheck;

    public TemPlanBean() {
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

    public TemPlanBean(int ID, String name, int unitTime, float temWave, int temPoints, int tem1, int tem2, int tem3, int tem4, int tem5, int tem6, int tem7, int tem8, int tem9, int tem10, int isCheck) {
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

    public int getTemPoints() {
        return temPoints;
    }

    public void setTemPoints(int temPoints) {
        this.temPoints = temPoints;
    }

    public void setTem1(int tem1) {
        this.tem1 = tem1;
    }

    public int getTem2() {
        return tem2;
    }

    public void setTem2(int tem2) {
        this.tem2 = tem2;
    }

    public int getTem3() {
        return tem3;
    }

    public void setTem3(int tem3) {
        this.tem3 = tem3;
    }

    public int getTem4() {
        return tem4;
    }

    public void setTem4(int tem4) {
        this.tem4 = tem4;
    }

    public int getTem5() {
        return tem5;
    }

    public void setTem5(int tem5) {
        this.tem5 = tem5;
    }

    public int getTem6() {
        return tem6;
    }

    public void setTem6(int tem6) {
        this.tem6 = tem6;
    }

    public int getTem7() {
        return tem7;
    }

    public void setTem7(int tem7) {
        this.tem7 = tem7;
    }

    public int getTem8() {
        return tem8;
    }

    public void setTem8(int tem8) {
        this.tem8 = tem8;
    }

    public int getTem9() {
        return tem9;
    }

    public void setTem9(int tem9) {
        this.tem9 = tem9;
    }

    public int getTem10() {
        return tem10;
    }

    public void setTem10(int tem10) {
        this.tem10 = tem10;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
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
