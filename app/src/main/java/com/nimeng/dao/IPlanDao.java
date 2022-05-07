package com.nimeng.dao;

import android.content.Context;

import com.nimeng.bean.PlanBean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/5 16:42
 * <p>
 * Changes (from 2022/5/5)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/5 : Create IPlanDao.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IPlanDao {
    public boolean add(Context context, PlanBean planBean);

    public boolean delete( Context context,String ID);

    public boolean update(Context context,PlanBean planBean);

    public List<PlanBean> query(Context context);

}
