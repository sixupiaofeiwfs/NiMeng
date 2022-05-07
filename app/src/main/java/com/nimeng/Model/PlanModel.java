package com.nimeng.Model;

import android.content.Context;

import com.nimeng.bean.PlanBean;
import com.nimeng.dao.PlanDao;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/6 14:00
 * <p>
 * Changes (from 2022/5/6)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/6 : Create PlanModel.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class PlanModel  implements IPlanMode{
    private PlanDao planDao=new PlanDao();

    @Override
    public boolean add(Context context, PlanBean planBean) {

        return planDao.add(context,planBean);
    }

    @Override
    public boolean delete(Context context, String ID) {
        return planDao.delete(context ,ID);
    }

    @Override
    public boolean update(Context context, PlanBean planBean) {
        return planDao.update(context, planBean);
    }

    @Override
    public List<PlanBean> query(Context context) {
        return planDao.query(context);
    }
}
