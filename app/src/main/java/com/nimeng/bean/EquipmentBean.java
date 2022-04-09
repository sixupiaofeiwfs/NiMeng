package com.nimeng.bean;

import java.sql.Time;
import java.util.Date;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/7 9:50
 * <p>
 * Changes (from 2022/4/7)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/7 : Create EquipmentBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EquipmentBean {
    private String id;                      //设备编号
    private String name;                    //设备名称
    private String type;                    //设备类型
    private Date time;                      //出厂时间
    private String IP;
    private boolean switch1;
    private boolean switch2;
    private boolean switch3;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }





    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public boolean isSwitch1() {
        return switch1;
    }

    public void setSwitch1(boolean switch1) {
        this.switch1 = switch1;
    }

    public boolean isSwitch2() {
        return switch2;
    }

    public void setSwitch2(boolean switch2) {
        this.switch2 = switch2;
    }

    public boolean isSwitch3() {
        return switch3;
    }

    public void setSwitch3(boolean switch3) {
        this.switch3 = switch3;
    }

    public EquipmentBean() {
    }

    public EquipmentBean(String id, String name, String type, Date time, String IP, boolean switch1, boolean switch2, boolean switch3) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.time = time;
        this.IP = IP;
        this.switch1 = switch1;
        this.switch2 = switch2;
        this.switch3 = switch3;
    }
}
