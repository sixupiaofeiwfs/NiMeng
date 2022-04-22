package com.nimeng.flash;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/19 9:02
 * <p>
 * Changes (from 2022/4/19)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/19 : Create BubbleEmitter.java (wfs);
 * <p>发射器
 * -----------------------------------------------------------------
 */
public class BubbleEmitter {
    private float lineSmoothness;

    //颜色
    private int color;
    //三个点,用来连接成弧线
    private PointF[] points=new PointF[3];
    //发射器路径
    private Path path=new Path();

    public BubbleEmitter(EmitterBuilder builder){
        if(builder.lineSmoothness<=0) throw new IllegalArgumentException("lineSmoothness<=0");
        this.lineSmoothness=builder.lineSmoothness;
        this.color=builder.color;

    }

    static class EmitterBuilder extends Builder<EmitterBuilder>{

        @Override
        public BubbleEmitter build() {
            return new BubbleEmitter(this);
        }
    }

    //生成点集合 并连接在一起
    public void generatePoints(float emitterCenterX,float emitterCenterY,float emitterRadius,float startAngle,float sweepAngle){

        points=ChargingHelper.generatePoints(emitterCenterX,emitterCenterY,emitterRadius,startAngle,sweepAngle);

        //连接路径
        ChargingHelper.connectToPath(path,points,lineSmoothness);
    }
    //设置颜色
    public void setColor(int color){
        this.color=color;
    }

    //绘制发射器
    public void drawEmitter(Canvas canvas, Paint paint){
        paint.setColor(color);
        canvas.drawPath(path,paint);
    }
}
