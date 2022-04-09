package com.nimeng.View;



import com.nimeng.bean.EquipmentBean;

import java.util.Date;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/7 14:48
 * <p>
 * Changes (from 2022/4/7)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/7 : Create IEquipmentView.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IEquipmentView {

public void success(EquipmentBean equipmentBean,String tag);

public void error(EquipmentBean equipmentBean,String tag);

String getName();

String getID();

String getIP();

String getType();

Date getTime();

boolean getSwitch1();

boolean getSwitch2();

boolean getSwitch3();

//显示进度条
void showLoading();

void hideLoading();
}
