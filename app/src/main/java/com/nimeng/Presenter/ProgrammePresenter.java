package com.nimeng.Presenter;

import android.view.View;

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
public class ProgrammePresenter extends BasePresenter<EditProgrammeContacts.EditProgrammeUI, ProgrammeBean> implements EditProgrammeContacts.EditPresenter {
   private EditProgrammeContacts.EditProgrammemodel mEditProgrammermodel;

   public ProgrammePresenter(@NonNull EditProgrammeContacts.EditProgrammeUI view){
       super(view);

       //实例化Model层
       mEditProgrammermodel=new ProgrammeMode();

   }



    @Override
    public void addProgrammePresenter(String ID,String name,int time,float tem_wave, float hum_wave, float T1,float T2,float T3,float H1,float H2,float H3) {
        mEditProgrammermodel.addProgrammemodel(ID,name,time,tem_wave,hum_wave,T1,T2,T3,H1,H2,H3);

    }
    @Override
    public void deleteProgrammePrsenter(String ID) {
        mEditProgrammermodel.deleteProgramme(ID);
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
