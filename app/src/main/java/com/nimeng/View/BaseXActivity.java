package com.nimeng.View;

import android.app.Activity;

import com.nimeng.Presenter.IBaseXPresenter;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 13:48
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create BaseXActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public  abstract class BaseXActivity<P extends IBaseXPresenter> extends Activity implements IBaseXView {
    private P mPresenter;
    //创建Presenter
    public abstract P onBindPresenter();

    //获取Presenter对象,在需要获取时才创建Presenter 起到懒加载的作用

    public P getmPresenter(){
        if (mPresenter==null){
            mPresenter=onBindPresenter();
        }
        return mPresenter;
    }

    @Override
    public Activity getSelfActivity(){
        return this;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        //在生命周期结束时,将presenter 与 view之间的联系断开,防止出现内存泄漏
        if (mPresenter!=null){
            mPresenter.detachView();;
        }
    }
}
