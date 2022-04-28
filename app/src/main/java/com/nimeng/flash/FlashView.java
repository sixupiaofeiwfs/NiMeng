package com.nimeng.flash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.RequiresApi;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Author: wfs
 * <p>
 * Create: 2022/4/18 14:01
 * <p>
 * Changes (from 2022/4/18)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/18 : Create HwChargingView.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class FlashView extends View {
    private String TAG="HwChargingView";
    /*
    1.发射器
    2.气泡
    从发射器的底部随机生成,并向上运动
    以一定的速率变小,甚至消失
    气泡与发射器之间的关系:离开时黏性
    气泡与气泡之间的关系"有黏性
    气泡与圆环之间的关系:有黏性

    3.圆环
    围绕自身旋转
    圆环四周有多个小月亮,也在旋转,并且速度不一样
    气泡与圆环间的关系:进入时有黏性,等完全进入时消失



     */
    //线程池
    private Executor executor;
    //标记是否运行
    private boolean isStop=false;
    //视图背景色
    private int backGroundColor= Color.parseColor("#ffffff");
    //进度值
    private int progress=0;

    private Rect rect=new Rect();

    private Paint paint=new Paint();

    private TextPaint textPaint=new TextPaint();

    private TextPaint HumTextPaint=new TextPaint();


    private int[]colorArr={
            Color.parseColor("#f83149"),
            Color.parseColor("#f83149"),
            Color.parseColor("#f83149")
    };

    private Random random=new Random();

    //圆环
    private CircularSegments circularSegments;
    //最小的角度
    private int minAngle=50;
    //偏移增大率
    private float offsetRadio=0.045f;
    //旋转速率的差值
    private float radio_dVal=0.2f;
    //正常的旋转速率
    private float rotateRadio=1.5f;
    //总的旋转角度差 最大值
    private float maxDValueAngle=15;
    //偏移的最大值
    private float maxOffset=12;
    //偏移量的最小值
    private float minOffset=8;
    //圆环片段数量
    private int segmentCount=6;
    //圆环的半径
    private float circularRadius=100;


    //发射器
    private BubbleEmitter bubbleEmitter;
    //颜色
    private int color;
    //path 系数
    private float lineSmoothness=0.3f;
    //发射器半径
    private float emitterRadius=40;
    //发射器的圆弧占周长的1/4
    private float emitterPercent=1.0f/4;
    //发射器的中心x坐标
    private float emitterCenterX;
    //发射器的中心y坐标
    private float emitterCentery;

    //气泡
    private BubbleController bubbleController;
    //气泡的最小半径
    private float minBubbleRadius=5;
    //气泡的最大半径
    private float maxBubbleRadius=15;
    //速率百分比 当气泡与发射器之间有粘合贝塞尔曲线时,运动速度减慢
    private float speedRadio=0.5f;
    //半径变化率
    private float radiusRadio=0.1f;
    //气泡的最小速度
    private float minBubbleSpeedY=2;
    //气泡的最大速度
    private float maxBubbleSpeedY=5;
    //气泡最多有几个
    private int maxBubbleCount=10;

    private Handler handle=new Handler();

    private FlashView(Context context){
        this(context,null);
    }
    public FlashView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init(context);
    }

    private void init(final Context context){
        //设置高亮
        setKeepScreenOn(true);
        //设置半径
        emitterRadius= TypeValueUtil.dp2px(context,emitterRadius);
        //圆形半径
        circularRadius=TypeValueUtil.dp2px(context,circularRadius);
        //圆环片段的偏移量
        minOffset=TypeValueUtil.dp2px(context,minOffset);
        maxOffset=TypeValueUtil.dp2px(context,maxOffset);

        //设置气泡的最大半径和最小半径
        maxBubbleRadius=TypeValueUtil.dp2px(context,maxBubbleRadius);
        minBubbleRadius=TypeValueUtil.dp2px(context,minBubbleRadius);

        final float sweepAngle=360*emitterPercent;
        final float startAngle=-sweepAngle/2-90;

        final double midRad=DegreeUtil.toRadians(sweepAngle/2);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                //发射器
                emitterCenterX = getWidth() / 2;

                emitterCentery = (float) (getHeight() + DegreeUtil.getCosSideLength(emitterRadius, midRad)); // todo有问题
                bubbleEmitter = new BubbleEmitter.EmitterBuilder()
                        .setColor(color)
                        .setLineSmoothness(lineSmoothness)
                        .build();
                bubbleEmitter.generatePoints(emitterCenterX, emitterCentery, emitterRadius, startAngle, sweepAngle);

                //分片的圆环
                circularSegments = new CircularSegments.CircularSegmentsBuilder()
                        .setColor(color)
                        .setLineSmoothness(lineSmoothness)
                        .setRd(random)
                        .setMinAngle(minAngle)
                        .setOffsetRadio(offsetRadio)
                        .setRadio_dVal(radio_dVal)
                        .setRotateRadio(rotateRadio)
                        .setMaxDValueAngle(maxDValueAngle)
                        .setMaxOffset(maxOffset)
                        .setMinOffset(minOffset)
                        .setSegmentCount(segmentCount)
                        .setCircularCenterX(getWidth() / 2)
                        .setCircularCenterY(getHeight() / 2)
                        .setCircularRadius(circularRadius)
                        .build();
                circularSegments.generateCircular();

                //气泡
                bubbleController = new BubbleController.BubbleControllerBuilder()
                        .setColor(color)
                        .setRd(random)
                        .setLineSmoothness(lineSmoothness)
                        .setMinBubbleRadius(minBubbleRadius)
                        .setMaxBubbleRadius(maxBubbleRadius)
                        .setSpeedRadio(speedRadio)
                        .setRadiusRadio(radiusRadio)
                        .setMinBubbleSpeedY(minBubbleSpeedY)
                        .setMaxBubbleSpeedY(maxBubbleSpeedY)
                        .setMaxBubbleCount(maxBubbleCount)
                        .setCircularCenterX(getWidth() / 2)
                        .setCircularCenterY(getHeight() / 2)
                        .setCircularRadius(circularRadius)
                        .setEmitterCenterX(emitterCenterX)
                        .setEmitterCenterY(emitterCentery)
                        .setEmitterRadius(emitterRadius)
                        .build();

                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);

        textPaint.setColor(Color.RED);
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(TypeValueUtil.sp2px(context, 32));
        textPaint.setStrokeWidth(3);
        textPaint.getTextBounds(progress + "%", 0, (progress + "%").length(), rect);

        executor = new ThreadPoolExecutor(3, 5, 15,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(8));
        executor.execute(mBubbleRunnable);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        //设置背景色
        setBackgroundColor(backGroundColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制进度
        drawProgress(canvas);
        //绘制圆环
        if (circularSegments != null) {
            circularSegments.drawSegments(canvas, paint);
        }
        //绘制气泡
        if (bubbleController != null) {
            bubbleController.drawBubble(canvas, paint);
        }
        //绘制发射器
        if (bubbleEmitter != null) {
            bubbleEmitter.drawEmitter(canvas, paint);
        }
    }

    /**
     * 绘制进度
     *
     * @param canvas
     */
    private void drawProgress(Canvas canvas) {


        float x = getWidth() / 2 - rect.width() / 2;
        float y = getHeight() / 2 + rect.height() / 2;

        canvas.drawText(progress + "%", x, y, textPaint);
    }

    private Runnable mBubbleRunnable = new Runnable() {
        @Override
        public void run() {

            if (!isStop) {

                if (bubbleController != null) {
                    //生成气泡
                    bubbleController.generateBubble();
                    //遍历气泡
                    bubbleController.performTraversals();
                }

                if (circularSegments != null) {
                    circularSegments.changeCircular();
                }

                postInvalidate();
                handle.postDelayed(this, 16);
            }
        }
    };

    /**
     * 设置进度
     *
     * @param percent
     */
    public void setProgress(int percent,String type) {
        this.progress = percent;
        if(type=="tem"){
            textPaint.getTextBounds( String.valueOf(progress), 0, String.valueOf(progress).length(), rect);
        }

        HumTextPaint.getTextBounds(progress+"%",0,(progress+"%").length(),rect);


        if (percent <= 10) {
            changeColor(colorArr[0]);
        } else if (percent <= 20) {
            changeColor(colorArr[1]);
        } else {
            changeColor(colorArr[2]);
        }
    }

    /**
     * 改变颜色
     *
     * @param color
     */
    private void changeColor(int color) {
        //颜色如果一样,则不用改变颜色
        if (color == this.color) return;
        this.color = color;
        if (circularSegments != null) {
            circularSegments.setColor(color);
        }
        if (bubbleController != null) {
            bubbleController.setColor(color);
        }
        if (bubbleEmitter != null) {
            bubbleEmitter.setColor(color);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isStop = false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isStop = true;
    }
}
