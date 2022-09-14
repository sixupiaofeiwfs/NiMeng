package com.nimeng.bean;

import java.util.List;

public class StandardApparatus {

    private int ID;
    private String name;
    private String port;
    private String format;
    private int rate;
    private String type;
    private String model;
    private String agreement;
    private String number;
    private String traceabilityUnit;
    private String time;
    private int isCheck;
    private int quantity;
    private List<Integer> list1;//温度校准点
    private List<Float> list2;//温度修正值
    private List<Integer> list3;//湿度校准点
    private List<Float> list4;//湿度修正值
    private int slave;//从机地址
    private int state;//0单一标准器1单一标准器(可将温度转化为湿度进行检测)2复杂标准器(单一标准器仅可以检测温度或湿度,复杂标准器可以检测温度和湿度)
    private String temStartAddress;//温度读取的起始地址
    private String humStartAddress;//湿度读取的起始地址
    private int count;//读取的字节数


    public StandardApparatus(int ID, String name, String port, String format, int rate, String type, String model, String agreement, String number, String traceabilityUnit, String time, int isCheck, int quantity, List<Integer> list1, List<Float> list2, List<Integer> list3, List<Float> list4, int slave) {
        this.ID = ID;
        this.name = name;
        this.port = port;
        this.format = format;
        this.rate = rate;
        this.type = type;
        this.model = model;
        this.agreement = agreement;
        this.number = number;
        this.traceabilityUnit = traceabilityUnit;
        this.time = time;
        this.isCheck = isCheck;
        this.quantity = quantity;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.slave = slave;
    }

    @Override
    public String toString() {
        return "StandardApparatus{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", port='" + port + '\'' +
                ", format='" + format + '\'' +
                ", rate=" + rate +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", agreement='" + agreement + '\'' +
                ", number='" + number + '\'' +
                ", traceabilityUnit='" + traceabilityUnit + '\'' +
                ", time='" + time + '\'' +
                ", isCheck=" + isCheck +
                ", quantity=" + quantity +
                ", list1=" + list1 +
                ", list2=" + list2 +
                ", list3=" + list3 +
                ", list4=" + list4 +
                ", slave=" + slave +
                '}';
    }

    public StandardApparatus() {
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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
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

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Integer> getList1() {
        return list1;
    }

    public void setList1(List<Integer> list1) {
        this.list1 = list1;
    }

    public List<Float> getList2() {
        return list2;
    }

    public void setList2(List<Float> list2) {
        this.list2 = list2;
    }


    public List<Integer> getList3() {
        return list3;
    }

    public void setList3(List<Integer> list3) {
        this.list3 = list3;
    }

    public List<Float> getList4() {
        return list4;
    }

    public void setList4(List<Float> list4) {
        this.list4 = list4;
    }

    public int getSlave() {
        return slave;
    }

    public void setSlave(int slave) {
        this.slave = slave;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTemStartAddress() {
        return temStartAddress;
    }

    public void setTemStartAddress(String temStartAddress) {
        this.temStartAddress = temStartAddress;
    }

    public String getHumStartAddress() {
        return humStartAddress;
    }

    public void setHumStartAddress(String humStartAddress) {
        this.humStartAddress = humStartAddress;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
