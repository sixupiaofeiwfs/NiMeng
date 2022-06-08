package com.nimeng.bean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/20 14:07
 * <p>
 * Changes (from 2022/6/20)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/20 : Create StandardApparatusBean.java (wfs);
 * 标准器
 * <p>
 * -----------------------------------------------------------------
 */
public class StandardApparatusBean {

    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private String name;
    private int port;
    private String format;
    private int rate;//速率
    private String type;//类型
    private String model;//型号
    private String agreement;//协议
    private String number;//证书编号
    private String value;//证书值
    private String traceabilityUnit;//溯源单位
    private String time;//有效期

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTraceabilityUnit() {
        return traceabilityUnit;
    }

    public void setTraceabilityUnit(String traceabilityUnit) {
        this.traceabilityUnit = traceabilityUnit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
