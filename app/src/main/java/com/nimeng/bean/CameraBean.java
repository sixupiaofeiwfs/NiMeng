package com.nimeng.bean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/21 10:53
 * <p>
 * Changes (from 2022/6/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/21 : Create CameraBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class CameraBean {
    private int ID;
    private String name;
    private String port;

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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
