package com.nimeng.Model;

import android.content.Context;

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
    public void addProgrammemodel(String ID, String name, int time, float wave, float T1, float T2, float T3, float T4, float T5, float T6, float T7, float T8, float T9, float T10, float H1, float H2, float H3, float H4, float H5, float H6, float H7, float H8, float H9, float H10) {
        ProgrammeBean programmeBean=new ProgrammeBean(ID,name,time,wave,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,H1,H2,H3,H4,H5,H6,H7,H8,H9,H10);

        programmeDao.AddProgramme(programmeBean);
    }

    @Override
    public void deleteProgramme(String ID) {

    }
}
