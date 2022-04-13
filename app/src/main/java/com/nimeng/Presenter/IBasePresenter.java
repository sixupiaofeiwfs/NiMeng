package com.nimeng.Presenter;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 14:05
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create IBasePresenter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IBasePresenter extends IBaseXPresenter{
    //取消网络请求
    void cancel(Object tag);

    //取消所有网络请求
    void cancelAll();
}
