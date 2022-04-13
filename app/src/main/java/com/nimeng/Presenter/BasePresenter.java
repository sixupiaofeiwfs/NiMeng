package com.nimeng.Presenter;

import androidx.annotation.NonNull;

import com.nimeng.View.IBaseView;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 14:14
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create BasePresenter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public  abstract class BasePresenter <V extends IBaseView,T >extends BaseXPresenter<V> implements IBasePresenter{
    public BasePresenter(@NonNull V view){
        super(view);
    }



    @Override
    public void cancel(@NonNull Object tag){

    }

    @Override
    public void cancelAll(){

    }

//    //来自于HttpResponseListener
//    @Override
//    public void onSuccess(Object tag,T t){
//
//    }


}
