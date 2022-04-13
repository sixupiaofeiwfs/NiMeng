package com.nimeng.Presenter;

import androidx.annotation.NonNull;

import com.nimeng.View.IBaseXView;

import java.lang.ref.WeakReference;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 13:55
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create BaseXPresenter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class BaseXPresenter <V extends IBaseXView> implements  IBaseXPresenter{
    //防止Activity 不走 onDestory()方法,所以采用弱引用来防止内存泄漏
    private WeakReference<V> mViewRef;

    public BaseXPresenter(@NonNull V view){
        attachView(view);
    }
    private void  attachView(V view){
        mViewRef=new WeakReference<V>(view);
    }

    public V getView(){
        return mViewRef.get();
    }

    @Override
    public boolean isViewAttach(){
        return mViewRef!=null && mViewRef.get()!=null;
    }

    @Override
    public void detachView(){
        if(mViewRef !=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
