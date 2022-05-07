package com.nimeng.Model;

import android.content.Context;

import com.nimeng.bean.PlanBean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/6 13:58
 * <p>
 * Changes (from 2022/5/6)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/6 : Create IPlanMode.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IPlanMode {
    public boolean add(Context context, PlanBean planBean);

    public boolean delete(Context context,String ID);

    public boolean update(Context context ,PlanBean planBean);

    public List<PlanBean> query(Context context);
}
