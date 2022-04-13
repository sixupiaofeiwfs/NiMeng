package com.nimeng.bean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/12 15:50
 * <p>
 * Changes (from 2022/4/12)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/12 : Create Programme.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ProgrammeBean {

    private String id;//方案编号
    private String name;//方案名称
    private int time;//波动时间段
    private float wave;//波动值

    private float temperature1;
    private float temperature2;
    private float temperature3;
    private float temperature4;
    private float temperature5;
    private float temperature6;
    private float temperature7;
    private float temperature8;
    private float temperature9;
    private float temperature10;

    private float humidity1;
    private float humidity2;
    private float humidity3;
    private float humidity4;
    private float humidity5;
    private float humidity6;
    private float humidity7;
    private float humidity8;
    private float humidity9;
    private float humidity10;

    public ProgrammeBean() {
    }

    public ProgrammeBean(String id, String name, Integer time, float wave, float temperature1, float temperature2, float temperature3, float temperature4, float temperature5, float temperature6, float temperature7, float temperature8, float temperature9, float temperature10, float humidity1, float humidity2, float humidity3, float humidity4, float humidity5, float humidity6, float humidity7, float humidity8, float humidity9, float humidity10) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.wave = wave;
        this.temperature1 = temperature1;
        this.temperature2 = temperature2;
        this.temperature3 = temperature3;
        this.temperature4 = temperature4;
        this.temperature5 = temperature5;
        this.temperature6 = temperature6;
        this.temperature7 = temperature7;
        this.temperature8 = temperature8;
        this.temperature9 = temperature9;
        this.temperature10 = temperature10;
        this.humidity1 = humidity1;
        this.humidity2 = humidity2;
        this.humidity3 = humidity3;
        this.humidity4 = humidity4;
        this.humidity5 = humidity5;
        this.humidity6 = humidity6;
        this.humidity7 = humidity7;
        this.humidity8 = humidity8;
        this.humidity9 = humidity9;
        this.humidity10 = humidity10;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getWave() {
        return wave;
    }

    public void setWave(float wave) {
        this.wave = wave;
    }

    public float getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(float temperature1) {
        this.temperature1 = temperature1;
    }

    public float getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(float temperature2) {
        this.temperature2 = temperature2;
    }

    public float getTemperature3() {
        return temperature3;
    }

    public void setTemperature3(float temperature3) {
        this.temperature3 = temperature3;
    }

    public float getTemperature4() {
        return temperature4;
    }

    public void setTemperature4(float temperature4) {
        this.temperature4 = temperature4;
    }

    public float getTemperature5() {
        return temperature5;
    }

    public void setTemperature5(float temperature5) {
        this.temperature5 = temperature5;
    }

    public float getTemperature6() {
        return temperature6;
    }

    public void setTemperature6(float temperature6) {
        this.temperature6 = temperature6;
    }

    public float getTemperature7() {
        return temperature7;
    }

    public void setTemperature7(float temperature7) {
        this.temperature7 = temperature7;
    }

    public float getTemperature8() {
        return temperature8;
    }

    public void setTemperature8(float temperature8) {
        this.temperature8 = temperature8;
    }

    public float getTemperature9() {
        return temperature9;
    }

    public void setTemperature9(float temperature9) {
        this.temperature9 = temperature9;
    }

    public float getTemperature10() {
        return temperature10;
    }

    public void setTemperature10(float temperature10) {
        this.temperature10 = temperature10;
    }

    public float getHumidity1() {
        return humidity1;
    }

    public void setHumidity1(float humidity1) {
        this.humidity1 = humidity1;
    }

    public float getHumidity2() {
        return humidity2;
    }

    public void setHumidity2(float humidity2) {
        this.humidity2 = humidity2;
    }

    public float getHumidity3() {
        return humidity3;
    }

    public void setHumidity3(float humidity3) {
        this.humidity3 = humidity3;
    }

    public float getHumidity4() {
        return humidity4;
    }

    public void setHumidity4(float humidity4) {
        this.humidity4 = humidity4;
    }

    public float getHumidity5() {
        return humidity5;
    }

    public void setHumidity5(float humidity5) {
        this.humidity5 = humidity5;
    }

    public float getHumidity6() {
        return humidity6;
    }

    public void setHumidity6(float humidity6) {
        this.humidity6 = humidity6;
    }

    public float getHumidity7() {
        return humidity7;
    }

    public void setHumidity7(float humidity7) {
        this.humidity7 = humidity7;
    }

    public float getHumidity8() {
        return humidity8;
    }

    public void setHumidity8(float humidity8) {
        this.humidity8 = humidity8;
    }

    public float getHumidity9() {
        return humidity9;
    }

    public void setHumidity9(float humidity9) {
        this.humidity9 = humidity9;
    }

    public float getHumidity10() {
        return humidity10;
    }

    public void setHumidity10(float humidity10) {
        this.humidity10 = humidity10;
    }
}
