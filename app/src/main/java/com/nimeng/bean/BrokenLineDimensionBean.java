package com.nimeng.bean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/21 14:09
 * <p>
 * Changes (from 2022/4/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/21 : Create BrokenLineDimensionBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class BrokenLineDimensionBean {
    public int mBrokenLineColor; //线的颜色
    public int mBrokenPointColor; //点的颜色
    public int mBrokenPointOutColor;//选择的点的外部颜色
    public int mBrokenPointIntColor;//选择的点的内部颜色
    public List<Double> mDataList;//数据源
    public String remark;//备注
}
