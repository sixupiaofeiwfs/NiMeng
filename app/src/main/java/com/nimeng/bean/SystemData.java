package com.nimeng.bean;

import java.util.Date;

public class SystemData {
    private int id;
    private int temUnitTime;
    private int humUnitTime;
    private float temWave;
    private float humWave;
    private boolean isStable;
    private int temPlanID;
    private int humPlanID;
    private Date startTime;
    private Date stableTime;
    private int executingTemID;
    private int executingHumID;
    private int temStandardID;
    private int humStandardID;
    private boolean haveJurisdiction;
    private Date dataRecordingTime;
    private Date lightStartTime;
    private int lightKeepSecond;
    private String select1;
    private String select2;
    private int numberOfStages;
    private boolean isInstallmentPayment;
    private String superPassword;
    private int settingTem;
    private int settingHum;
    private int temOnOrOff;
    private int humOnOrOff;
    private int temIsClick;//折线图中是否点击了温度坐标轴
    private int humIsClick;//折线图中是否点击了湿度坐标轴
    private int isFormal;//是否生产环境 1是0否

    private String temChange;//温度变化速率
    private String humChange;//湿度变化速率

    private String temPower;//温度功率
    private String humPower;//湿度功率





    @Override
    public String toString() {
        return "SystemData{" +
                "id=" + id +
                ", temUnitTime=" + temUnitTime +
                ", humUnitTime=" + humUnitTime +
                ", temWave=" + temWave +
                ", humWave=" + humWave +
                ", isStable=" + isStable +
                ", temPlanID=" + temPlanID +
                ", humPlanID=" + humPlanID +
                ", startTime=" + startTime +
                ", stableTime=" + stableTime +
                ", executingTemID=" + executingTemID +
                ", executingHumID=" + executingHumID +
                ", temStandardID=" + temStandardID +
                ", humStandardID=" + humStandardID +
                ", haveJurisdiction=" + haveJurisdiction +
                ", dataRecordingTime=" + dataRecordingTime +
                ", lightStartTime=" + lightStartTime +
                ", lightKeepSecond=" + lightKeepSecond +
                ", select1='" + select1 + '\'' +
                ", select2='" + select2 + '\'' +
                ", numberOfStages=" + numberOfStages +
                ", isInstallmentPayment=" + isInstallmentPayment +
                ", superPassword='" + superPassword + '\'' +
                ", settingTem=" + settingTem +
                ", settingHum=" + settingHum +
                ", temOnOrOff=" + temOnOrOff +
                ", humOnOrOff=" + humOnOrOff +
                ", temIsClick=" + temIsClick +
                ", humIsClick=" + humIsClick +
                ", isFormal=" + isFormal +
                ", temChange='" + temChange + '\'' +
                ", humChange='" + humChange + '\'' +
                '}';
    }

    public SystemData(int id, int temUnitTime, int humUnitTime, float temWave, float humWave, boolean isStable, int temPlanID, int humPlanID, Date startTime, Date stableTime, int executingTemID, int executingHumID, int temStandardID, int humStandardID, boolean haveJurisdiction, Date dataRecordingTime, Date lightStartTime, int lightKeepSecond, String select1, String select2, int numberOfStages, boolean isInstallmentPayment, String superPassword, int settingTem, int settingHum, int temOnOrOff, int humOnOrOff, int temIsClick, int humIsClick, int isFormal, String temChange, String humChange) {
        this.id = id;
        this.temUnitTime = temUnitTime;
        this.humUnitTime = humUnitTime;
        this.temWave = temWave;
        this.humWave = humWave;
        this.isStable = isStable;
        this.temPlanID = temPlanID;
        this.humPlanID = humPlanID;
        this.startTime = startTime;
        this.stableTime = stableTime;
        this.executingTemID = executingTemID;
        this.executingHumID = executingHumID;
        this.temStandardID = temStandardID;
        this.humStandardID = humStandardID;
        this.haveJurisdiction = haveJurisdiction;
        this.dataRecordingTime = dataRecordingTime;
        this.lightStartTime = lightStartTime;
        this.lightKeepSecond = lightKeepSecond;
        this.select1 = select1;
        this.select2 = select2;
        this.numberOfStages = numberOfStages;
        this.isInstallmentPayment = isInstallmentPayment;
        this.superPassword = superPassword;
        this.settingTem = settingTem;
        this.settingHum = settingHum;
        this.temOnOrOff = temOnOrOff;
        this.humOnOrOff = humOnOrOff;
        this.temIsClick = temIsClick;
        this.humIsClick = humIsClick;
        this.isFormal = isFormal;
        this.temChange = temChange;
        this.humChange = humChange;
    }

    public SystemData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isStable() {
        return isStable;
    }

    public void setStable(boolean stable) {
        isStable = stable;
    }

    public int getTemPlanID() {
        return temPlanID;
    }

    public void setTemPlanID(int temPlanID) {
        this.temPlanID = temPlanID;
    }

    public int getHumPlanID() {
        return humPlanID;
    }

    public void setHumPlanID(int humPlanID) {
        this.humPlanID = humPlanID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStableTime() {
        return stableTime;
    }

    public void setStableTime(Date stableTime) {
        this.stableTime = stableTime;
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

    public boolean isHaveJurisdiction() {
        return haveJurisdiction;
    }

    public void setHaveJurisdiction(boolean haveJurisdiction) {
        this.haveJurisdiction = haveJurisdiction;
    }

    public Date getDataRecordingTime() {
        return dataRecordingTime;
    }

    public void setDataRecordingTime(Date dataRecordingTime) {
        this.dataRecordingTime = dataRecordingTime;
    }

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

    public int getNumberOfStages() {
        return numberOfStages;
    }

    public void setNumberOfStages(int numberOfStages) {
        this.numberOfStages = numberOfStages;
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


    public int getSettingTem() {
        return settingTem;
    }

    public void setSettingTem(int settingTem) {
        this.settingTem = settingTem;
    }

    public int getSettingHum() {
        return settingHum;
    }

    public void setSettingHum(int settingHum) {
        this.settingHum = settingHum;
    }

    public int getTemOnOrOff() {
        return temOnOrOff;
    }

    public void setTemOnOrOff(int temOnOrOff) {
        this.temOnOrOff = temOnOrOff;
    }

    public int getHumOnOrOff() {
        return humOnOrOff;
    }

    public void setHumOnOrOff(int humOnOrOff) {
        this.humOnOrOff = humOnOrOff;
    }

    public int getTemIsClick() {
        return temIsClick;
    }

    public void setTemIsClick(int temIsClick) {
        this.temIsClick = temIsClick;
    }

    public int getHumIsClick() {
        return humIsClick;
    }

    public void setHumIsClick(int humIsClick) {
        this.humIsClick = humIsClick;
    }

    public int getIsFormal() {
        return isFormal;
    }

    public void setIsFormal(int isFormal) {
        this.isFormal = isFormal;
    }

    public String getTemChange() {
        return temChange;
    }

    public void setTemChange(String temChange) {
        this.temChange = temChange;
    }

    public String getHumChange() {
        return humChange;
    }

    public void setHumChange(String humChange) {
        this.humChange = humChange;
    }

    public String getTemPower() {
        return temPower;
    }

    public void setTemPower(String temPower) {
        this.temPower = temPower;
    }

    public String getHumPower() {
        return humPower;
    }

    public void setHumPower(String humPower) {
        this.humPower = humPower;
    }
}
