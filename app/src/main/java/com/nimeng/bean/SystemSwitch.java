package com.nimeng.bean;

public class SystemSwitch {
    private int id;
    private boolean onOrOff;


    @Override
    public String toString() {
        return "SystemSwitch{" +
                "id=" + id +
                ", onOrOff=" + onOrOff +
                '}';
    }

    public boolean isOnOrOff() {
        return onOrOff;
    }

    public void setOnOrOff(boolean onOrOff) {
        this.onOrOff = onOrOff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SystemSwitch(int id, boolean onOrOff) {
        this.id = id;
        this.onOrOff = onOrOff;
    }

    public SystemSwitch() {
    }
}
