package com.nimeng.Presenter;

import android.content.Context;
import android.os.Handler;

import com.nimeng.Model.EquipmentMode;
import com.nimeng.Model.IEquipmentMode;
import com.nimeng.Model.OnAddListener;
import com.nimeng.View.IEquipmentView;
import com.nimeng.bean.EquipmentBean;


/**
 * Author: wfs
 * <p>
 * Create: 2022/4/7 14:46
 * <p>
 * Changes (from 2022/4/7)
 * 绑定View层和Model层
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/7 : Create EquipmentPresenter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EquipmentPresenter implements IPresenter{

    //model层控件,对model层进行操作
    IEquipmentMode iEquipmentMode;

    //view层控件,对view层进行操作
    IEquipmentView iEquipmentView;


    public EquipmentPresenter(IEquipmentMode iEquipmentMode, Context context) {
        iEquipmentMode = new EquipmentMode(context);
        this.iEquipmentView = iEquipmentView;
    }



    //添加
    public void add() {
        //展示进度条
        iEquipmentView.showLoading();
        //给M层发送指令,执行添加操作.
        iEquipmentMode.addEquipment(iEquipmentView.getID(), iEquipmentView.getName(), iEquipmentView.getType(), iEquipmentView.getIP(), iEquipmentView.getTime(), iEquipmentView.getSwitch1(), iEquipmentView.getSwitch2(), iEquipmentView.getSwitch3(), new OnAddListener<EquipmentBean>() {
            @Override
            public void AddSuccess(EquipmentBean equipmentBean) {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //成功隐藏进度条 显示成功
                        iEquipmentView.hideLoading();
                        iEquipmentView.success(equipmentBean,OnAddListener.TAG);
                    }
                },3000);
            }

            @Override
            public void AddError(EquipmentBean equipmentBean) {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //添加失败隐藏进度条 显示失败
                        iEquipmentView.hideLoading();
                        iEquipmentView.error(equipmentBean,OnAddListener.TAG);
                    }
                },3000);
            }
        } );
    }


    }

