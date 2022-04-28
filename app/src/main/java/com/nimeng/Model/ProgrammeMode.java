package com.nimeng.Model;

import android.content.Context;
import android.util.Log;

import com.nimeng.bean.ProgrammeBean;
import com.nimeng.contacts.EditProgrammeContacts;
import com.nimeng.dao.ProgrammeDao;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 9:43
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create ProgrammeMode.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ProgrammeMode  implements EditProgrammeContacts.EditProgrammemodel {
    private ProgrammeDao programmeDao;




    @Override
    public void addProgrammemodel(String ID, String name, int time, float tem_wave, float hum_wave, float T1, float T2, float T3, float H1, float H2, float H3) {
        ProgrammeBean programmeBean=new ProgrammeBean(ID,name,time,tem_wave,hum_wave,T1,T2,T3,H1,H2,H3);

        programmeDao.AddProgramme(programmeBean);
    }

    @Override
    public void deleteProgramme(String ID) {
        Log.d("1111111", "deleteProgramme: ");
        programmeDao.deleteProgrammeById(ID);
    }
}
