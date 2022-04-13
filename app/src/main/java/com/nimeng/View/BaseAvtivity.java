package com.nimeng.View;

import android.app.ProgressDialog;

import com.nimeng.Presenter.IBasePresenter;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 14:04
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create BaseAvtivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class BaseAvtivity <P extends IBasePresenter> extends BaseXActivity<P> implements IBaseView{

//加载进度框
    private ProgressDialog mProgressDialog;

    @Override
    public void showLoading(){

    }

    @Override
    public void hideLoading(){

    }

    @Override
    public void showToast(String msg){

    }

    @Override
    public P onBindPresenter() {
        return null;
    }

    @Override
    public void onDestroy(){
        hideLoading();
        super.onDestroy();
    }

}
