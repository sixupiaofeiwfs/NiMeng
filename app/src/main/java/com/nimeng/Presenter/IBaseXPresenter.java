package com.nimeng.Presenter;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 13:46
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create IBaseXPresenter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IBaseXPresenter {
    //判断presenter是否与view建立联系,防止内存泄露状况
    boolean isViewAttach();
    //断开presenter与view直接的联系
    void detachView();
}
