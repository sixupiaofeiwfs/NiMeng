package com.nimeng.View;

import android.app.Activity;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 13:42
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create IBaseXView.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface IBaseXView {
    //获取Activity对象  主要用于在Presenter中需要使用Context对象时调用,不直接在Presenter中创建Context对象,最大程度防止内存泄漏
    <T extends Activity> T getSelfActivity();
}
