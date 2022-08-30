package com.nimeng.bean;

import java.util.Date;

public class Password {
    private int id;
    private String password;
    private int errorNumbers;
    private boolean matchs;
    private Date times;


    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", errorNumbers=" + errorNumbers +
                ", matchs=" + matchs +
                ", times=" + times +
                '}';
    }

    public Password(int id, String password, int errorNumbers, boolean matchs, Date times) {
        this.id = id;
        this.password = password;
        this.errorNumbers = errorNumbers;
        this.matchs = matchs;
        this.times = times;
    }

    public Password() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getErrorNumbers() {
        return errorNumbers;
    }

    public void setErrorNumbers(int errorNumbers) {
        this.errorNumbers = errorNumbers;
    }

    public boolean isMatchs() {
        return matchs;
    }

    public void setMatchs(boolean matchs) {
        this.matchs = matchs;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }
}
