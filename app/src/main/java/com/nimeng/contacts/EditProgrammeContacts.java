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
        void addProgrammepresenter(String ID,String name,int time,float wave,float T1,float T2,float T3,float T4,float T5,float T6,float T7,float T8,float T9,float T10,float H1,float H2,float H3,float H4,float H5,float H6,float H7,float H8,float H9,float H10);

        void onSuccess(Object tag, ProgrammeBean t);

        void onError(Object tag, ProgrammeBean t);
    }

    //model层接口
    public interface EditProgrammemodel{
        //添加
        void addProgrammemodel(String ID, String name, int time, float wave, float T1, float T2, float T3, float T4, float T5, float T6, float T7, float T8, float T9, float T10, float H1, float H2, float H3, float H4, float H5, float H6, float H7, float H8, float H9, float H10);
        //删除
        void deleteProgramme(String ID);
    }
}
