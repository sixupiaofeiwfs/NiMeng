package com.nimeng.View;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 14:02
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create IBaseView.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IBaseView extends IBaseXView{
    //显示正在加载view
    void showLoading();

    //关闭正在加载 view
    void hideLoading ();

    //显示提示
    void showToast(String msg);
}
