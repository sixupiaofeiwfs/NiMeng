package com.nimeng.dao;

import android.content.Context;

import com.nimeng.bean.PlanBean;
import com.nimeng.util.PlanDBHelper;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/6 8:25
 * <p>
 * Changes (from 2022/5/6)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/6 : Create PlanDao.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class PlanDao implements IPlanDao{

    PlanDBHelper planDBHelper;
    @Override
    public boolean add(Context context, PlanBean planBean) {

        planDBHelper  =new PlanDBHelper(context,"NIMENG.db",null,1);

       return planDBHelper.add(planBean);

    }

    @Override
    public boolean delete(Context context,String ID) {
        planDBHelper  =new PlanDBHelper(context,"NIMENG.db",null,1);
        return planDBHelper.delete(ID);

    }

    @Override
    public boolean update(Context context,PlanBean planBean) {
        planDBHelper  =new PlanDBHelper(context,"NIMENG.db",null,1);
        return planDBHelper.update(planBean);
    }

    @Override
    public List<PlanBean> query(Context context) {
        planDBHelper  =new PlanDBHelper(context,"NIMENG.db",null,1);
        return planDBHelper.query();
    }


    public PlanDao() {
    }
}
