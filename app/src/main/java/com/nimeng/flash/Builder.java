package com.nimeng.flash;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/18 14:26
 * <p>
 * Changes (from 2022/4/18)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/18 : Create Bulider.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public  abstract class Builder<T extends Builder>{
    //颜色
    public int color;
    //path 系数
    public float lineSmoothness;

    public T setColor(int color){
        this.color=color;
        return (T)this;
    }

    public T setLineSmoothness(float lineSmoothness){
        this.lineSmoothness=lineSmoothness;
        return (T)this;
    }

    public abstract Object build();
}
