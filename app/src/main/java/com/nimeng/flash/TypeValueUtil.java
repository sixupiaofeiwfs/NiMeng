package com.nimeng.flash;

import android.content.Context;
import android.util.TypedValue;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/19 8:50
 * <p>
 * Changes (from 2022/4/19)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/19 : Create TypeValueUtil.java (wfs);
 * //dp  px 的相互转换
 * <p>
 * -----------------------------------------------------------------
 */
public class TypeValueUtil {

    //dp转px
    public static float dp2px(Context context,float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }

    //sp转px
    public static float sp2px(Context context,float sp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }
}
