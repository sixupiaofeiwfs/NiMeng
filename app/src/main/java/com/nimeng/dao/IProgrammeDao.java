package com.nimeng.dao;

import com.nimeng.bean.ProgrammeBean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/12 16:01
 * <p>
 * Changes (from 2022/4/12)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/12 : Create IProgrammeDao.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IProgrammeDao {

    //添加预设方案
    void AddProgramme(ProgrammeBean programmeBean);

    //通过预设方案ID删除预设方案
    void deleteProgrammeById(String id);

}
