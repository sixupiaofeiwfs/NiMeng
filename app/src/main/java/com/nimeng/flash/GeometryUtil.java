package com.nimeng.flash;

import android.graphics.PointF;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/19 8:31
 * <p>
 * Changes (from 2022/4/19)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/19 : Create GeometryUtil.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class GeometryUtil {//几何圆形工具

    //获得两点之间的距离
    public static float getDistanceBetween2Points(PointF p0,PointF p1){

        float distance =(float) Math.sqrt(Math.pow(p0.y-p1.y,2)+Math.pow(p0.x-p1.x,2));
        return distance;
    }



    //获得两点连线的中点
    public static PointF getMinddlePoint(PointF p1,PointF p2){
        return new PointF((p1.x+p2.x)/2.0f,(p1.y+p2.y)/2.0f);
    }

    //根据分度值,计算从start到end中,fraction位置的值.fraction范围为0到1
    public static  float evaluateValue(float fraction,Number start,Number end){
        return start.floatValue()+(end.floatValue()-start.floatValue())*fraction;
    }

    //获取 通过指定圆心,斜率为linek的直线与圆的交点
    public static PointF[] getIntersectionPoints(PointF pMiddle,float radius,Double linek){
        PointF[] points=new PointF[2];
        float radian,xOffset=0,yOffset=0;
        if (linek==null){
            radian=(float)Math.atan(linek);
            xOffset=(float)(Math.sin(radian)*radius);
            yOffset=(float)(Math.cos(radian)*radius);
        }else{
            xOffset=radius;
            yOffset=0;
        }

        points[0]=new PointF(pMiddle.x+xOffset,pMiddle.y-yOffset);
        points[1]=new PointF(pMiddle.x-xOffset,pMiddle.y+yOffset);
        return points;
    }


}
