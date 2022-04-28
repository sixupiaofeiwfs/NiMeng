package com.nimeng.contacts;

import com.nimeng.Model.OnAddListener;
import com.nimeng.Presenter.IBasePresenter;
import com.nimeng.View.IBaseView;
import com.nimeng.bean.ProgrammeBean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 14:35
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create EditProgrammeContacts.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public final class EditProgrammeContacts {
    //编辑方案的所有接口列表 便于查找
    //view层接口
    public interface EditProgrammeUI extends IBaseView{
        //添加成功
        void addSuccess();
        //添加失败
        void addError();

    }

    //presenter层接口
     public interface EditPresenter extends IBasePresenter{
        void addProgrammePresenter(String ID,String name,int time,float tem_wave,float hum_wave, float T1,float T2,float T3,float H1,float H2,float H3);

        void onSuccess(Object tag, ProgrammeBean t);

        void onError(Object tag, ProgrammeBean t);


        void deleteProgrammePrsenter(String ID);

    }

    //model层接口
    public interface EditProgrammemodel{
        //添加
        void addProgrammemodel(String ID, String name, int time, float tem_wave, float hum_wave, float T1, float T2, float T3, float H1, float H2, float H3);
        //删除
        void deleteProgramme(String ID);
    }
}
