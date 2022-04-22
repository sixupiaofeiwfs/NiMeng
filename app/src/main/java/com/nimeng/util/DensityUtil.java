package com.nimeng.util;

import android.content.Context;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/21 15:38
 * <p>
 * Changes (from 2022/4/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/21 : Create DensityUtil.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DensityUtil {
    public  static int dip2px(Context context,float dipValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dipValue*scale+0.5f);
    }

    public static int px2dp(Context context,float pxValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
}
