package com.nimeng.bean;

import java.util.List;

public class StandardApparatus {

    private int ID;
    private String name;
    private int port;
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
    private List<Integer> list1;
    private List<Float> list2;


    @Override
    public String toString() {
        return "StandardApparatus{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", port=" + port +
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
                '}';
    }

    public StandardApparatus(int ID, String name, int port, String format, int rate, String type, String model, String agreement, String number, String traceabilityUnit, String time, int isCheck, int quantity, List<Integer> list1, List<Float> list2) {
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
}
