package com.nimeng.Presenter;

import androidx.annotation.NonNull;

import com.nimeng.Model.ProgrammeMode;
import com.nimeng.bean.ProgrammeBean;
import com.nimeng.contacts.EditProgrammeContacts;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 15:32
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create EditProgrammePresenter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EditProgrammePresenter extends BasePresenter<EditProgrammeContacts.EditProgrammeUI, ProgrammeBean> implements EditProgrammeContacts.EditPresenter {
   private EditProgrammeContacts.EditProgrammemodel mEditProgrammermodel;

   private EditProgrammePresenter(@NonNull EditProgrammeContacts.EditProgrammeUI view){
       super(view);

       //实例化Model层
       mEditProgrammermodel=new ProgrammeMode();

   }


    @Override
    public void addProgrammepresenter(String ID,String name,int time,float wave,float T1,float T2,float T3,float T4,float T5,float T6,float T7,float T8,float T9,float T10,float H1,float H2,float H3,float H4,float H5,float H6,float H7,float H8,float H9,float H10) {
        mEditProgrammermodel.addProgrammemodel(ID,name,time,wave,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,H1,H2,H3,H4,H5,H6,H7,H8,H9,H10);

    }
    
    @Override
    public void onSuccess(Object tag, ProgrammeBean t){
       //先判断是否已经与View建立联系
        if(isViewAttach()){
            getView().addSuccess();
        }
    }

   @Override
    public void onError(Object tag, ProgrammeBean t){
       getView().addError();
   }

}
