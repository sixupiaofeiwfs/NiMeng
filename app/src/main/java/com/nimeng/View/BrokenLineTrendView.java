package com.nimeng.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.nimeng.bean.BrokenLineDimensionBean;
import com.nimeng.bean.BrokenLineTrendDataBean;
import com.nimeng.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/21 10:09
 * <p>
 * Changes (from 2022/4/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/21 : Create BrokenLineTrendView.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class BrokenLineTrendView extends View  {
    //控件的宽
    private float mWidth;
    //控件的高
    private float mHeight;
    //折线的颜色
    private int mBrokenLineColor;
    //虚线的颜色
    private int mDottedLineColor=getResources().getColor(R.color.color_dotted_line);
    //文字的颜色
    private int mTextColor=getResources().getColor(R.color.color_text);
    //折线的粗细
    private float mBrokenLineWidth=DensityUtil.dip2px(getContext(),1.5f);
    //字体大小
    private int mTextSize=DensityUtil.dip2px(getContext(),12);
    //最大的Y轴点
    private Double mMaxYPoint;
    //最小的Y轴点
    private Double mMinYPoint;
    //Y轴的虚线画笔
    private Paint mDottedPaint;
    //文字画笔
    private Paint mTextPaint;
    //折线画笔
    private Paint mBrokenLinePaint;
    //折线点画笔
    private Paint mScorePaint;
    private Paint mBrokenPointPaint;

    //顶部的描述画笔
    private Paint mRemarkPaint;

    //数据源
    private BrokenLineTrendDataBean mBrokenLineTrendData;

    //x轴的数据
    private List<String> mXLineDataList=new ArrayList<>();
    //y轴的坐标点数据
    private List<BrokenLineDimensionBean>mDataList=new ArrayList<>();

    //y轴坐标轴数据
    private List<Double> mYLineDataList=new ArrayList<>();

    //折线点的坐标list
    private List<List<Point>>mScorePointsList;

    //选中的index
    private int mSelectIndex=0;

    //padding值
    private int mLeftPadding=DensityUtil.dip2px(getContext(),15);
    private int mRightPadding=DensityUtil.dip2px(getContext(),15);
    private int mTopPadding=DensityUtil.dip2px(getContext(),15);
    private int mBottomPadding=DensityUtil.dip2px(getContext(),15);
    private int mTextBottomPadding=DensityUtil.dip2px(getContext(),8);


    //y轴区域部分的高度
    private float mYheight;

    private OnItemClick mOnItemClick;

    //y轴虚线的纵坐标的list
    private List<Float> dottedLineList=new ArrayList<>();


    public BrokenLineTrendView(Context context) {
        this(context,null);

    }

    public BrokenLineTrendView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public BrokenLineTrendView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig(context,attrs);
    }


    public void  initConfig(Context context,AttributeSet attrs){
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.BrokenLineTrendView);
        int count =array.getIndexCount();
        Log.d("count", "initConfig: "+count+"   attr:");
        for(int i=0;i<count ;i++){
            int attr=array.getIndex(i);
            Log.d("attr", "initConfig: "+attr);
            switch(attr){
                case R.styleable.BrokenLineTrendView_trend_view_color_dotted_line:
                    mDottedLineColor=array.getColor(attr,mBrokenLineColor);
                    break;
                case R.styleable.BrokenLineTrendView_trend_view_color_text:
                    mTextColor=array.getColor(attr,mTextColor);
                    break;
                case R.styleable.BrokenLineTrendView_trend_view_text_size:
                    mTextSize=array.getDimensionPixelSize(attr,mTextSize);
                    break;
            }
        }

        array.recycle();
        initPaint();
        initData();
    }

    private void initPaint(){
        mDottedPaint=new Paint();
        mDottedPaint.setAntiAlias(true);
        mDottedPaint.setStyle(Paint.Style.STROKE);
        mDottedPaint.setColor(mDottedLineColor);
        mDottedPaint.setStrokeWidth(mBrokenLineWidth);


        mTextPaint=new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mTextSize);


        mBrokenLinePaint=new Paint();
        mBrokenLinePaint.setAntiAlias(true);
        mBrokenLinePaint.setColor(Color.GREEN);
        mBrokenLinePaint.setStyle(Paint.Style.FILL);
        mBrokenLinePaint.setStrokeWidth(mBrokenLineWidth);
        mBrokenLinePaint.setStrokeCap(Paint.Cap.ROUND);



        mBrokenPointPaint=new Paint();
        mBrokenPointPaint.setAntiAlias(true);
        mBrokenPointPaint.setStyle(Paint.Style.STROKE);
        mBrokenPointPaint.setColor(Color.GREEN);
        mBrokenPointPaint.setStrokeWidth(mBrokenLineWidth);
        mBrokenPointPaint.setStrokeCap(Paint.Cap.ROUND);


        mScorePaint=new Paint();
        mScorePaint.setColor(Color.WHITE);
        mScorePaint.setTextSize(DensityUtil.dip2px(getContext(),12));
        mScorePaint.setAntiAlias(true);


        mRemarkPaint=new Paint();
        mRemarkPaint.setAntiAlias(true);
        mRemarkPaint.setStyle(Paint.Style.FILL);

    }

    private void initData(){
        mBrokenLineTrendData=new BrokenLineTrendDataBean();
        mDataList=mBrokenLineTrendData.mDimensionList;
        mXLineDataList=mBrokenLineTrendData.mXLineDataList;
        mYLineDataList=mBrokenLineTrendData.mYLineDataList;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXLineText(canvas);
        drawRemark(canvas);
        drawYLineText(canvas);
        drawDottedLine(canvas);
        drawBrokenLine(canvas);
        drawBrokenPoint(canvas);



    }
    //绘制虚线
    private void drawDottedLine(Canvas canvas){
        int count=mYLineDataList.size();
        if (count>0){
            canvas.save();//保存当前画布的状态
            mDottedPaint.setPathEffect(new DashPathEffect(new float[]{20,10},4));//设置折线的样式
            mDottedPaint.setStrokeWidth(1f);//画笔的宽度
            float height=mYheight-mBottomPadding;
            float one=1.00f/count;
            float startX=mWidth*0.1f;
            float stopX=mWidth-mRightPadding;
            float startY;
            float stopY;
            for(int i=0;i<count;i++){
                startY=stopY=(i+1)*one*height;
                Path path =new Path();
                path.reset();
                path.moveTo(startX,startY);//将画笔移动到目标位置
                path.lineTo(stopX,stopY);//用于进行直线绘制
                canvas.drawPath(path,mDottedPaint);
            }
            canvas.restore();//取出保存的画布状态
        }
    }

    //绘制Y轴
    private void drawYLineText(Canvas canvas){
        canvas.save();
        float height=mYheight-mBottomPadding;
        int count =mYLineDataList.size();
        if (count>0){
            float one =1.00f/count;
            float startY;
            float tempWidth=0;
            for (int i=0;i<count;i++){
                mTextPaint.setColor(mTextColor);
                startY=(i+1)*one*height;
                String text=String.valueOf(mYLineDataList.get(count-1-i));
                float textMaxHeight=getTextHeight(mTextPaint);
                float textWidth=mTextPaint.measureText(text);
                canvas.drawText(text,mLeftPadding,startY+textMaxHeight/4,mTextPaint);
                dottedLineList.add((count-i)*one*height);
                if (tempWidth<textWidth){
                    tempWidth=textWidth;
                }
            }
        }
        canvas.restore();
    }

    private int getTextHeight(Paint paint){
        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        return (int)(fontMetrics.descent-fontMetrics.ascent);
    }


    //绘制x轴的text
    private void drawXLineText(Canvas canvas){
        int count=mXLineDataList.size();
        if(count>0){
            canvas.save();
            float newWidth=mWidth-(mWidth*0.1f)*2;
            float startX;
            for(int i=0;i<count;i++){
                //在循环中计算响应的坐标点
                mTextPaint.setColor(mTextColor);
                String text=mXLineDataList.get(i);
                startX=((newWidth*i)/(count-1)+mWidth*0.1f);
                Rect rectText=new Rect();
                mTextPaint.getTextBounds(text,0,text.length(),rectText);
                float textSize=rectText.width();
                float textHeight=rectText.height();
                if (mSelectIndex==i){
                    //绘制选中后的text和框
                    mTextPaint.setStyle(Paint.Style.STROKE);
                    int XDataSelectColor=mBrokenLineTrendData.mSelectColor;
                    mTextPaint.setColor(XDataSelectColor);
                    RectF rectf=new RectF();
                    rectf.left=startX- DensityUtil.dip2px(getContext(),8);
                    rectf.right=startX+textSize+ DensityUtil.dip2px(getContext(),8);
                    rectf.bottom=mHeight-mBottomPadding;
                    rectf.top=rectf.bottom-mTextBottomPadding-textHeight-DensityUtil.dip2px(getContext(),4);
                    canvas.drawRoundRect(rectf,10,10,mTextPaint);
                    mYheight=rectf.top;
                }
                canvas.drawText(text,startX,mHeight-mBottomPadding-mTextBottomPadding,mTextPaint);
            }
            canvas.restore();
        }
    }

    //绘制折线
    private void  drawBrokenLine(Canvas canvas){
        int dataSize=mDataList.size();
        if(dataSize>0){
            mScorePointsList=new ArrayList<>();
            for(BrokenLineDimensionBean dimension:mDataList){
                List<Point> pointList=new ArrayList<>();
                float max=dottedLineList.get(dottedLineList.size()-1);
                float min=dottedLineList.get(0);
                float newWidth=mWidth-(mWidth*0.1f)*2;
                int coordinateX;
                List<Double> doubles=dimension.mDataList;
                int count=doubles.size();

                if(count>0){
                    for(int i=0;i<count;i++){
                        Point point =new Point();
                        coordinateX=(int)(((newWidth*i)/(count-1)+mWidth*0.1f)+DensityUtil.dip2px(getContext(),8));
                        point.x=coordinateX;
                        point.y=(int)((mMaxYPoint-doubles.get(i))*(min-max)/(mMaxYPoint-mMinYPoint)+max);
                        pointList.add(point);
                    }
                }
                int size=doubles.size();
                if(size>0){
                    canvas.save();
                    Path path=new Path();

                    path.reset();
                    mBrokenLinePaint.setStrokeWidth(mBrokenLineWidth);
                    mBrokenLineColor=dimension.mBrokenLineColor;
                    mBrokenLinePaint.setColor(mBrokenLineColor);
                    mBrokenLinePaint.setStyle(Paint.Style.STROKE);
                    for(int i=0;i<size;i++){
                        if(i==0){
                            path.moveTo(pointList.get(0).x,pointList.get(0).y);
                        }else {
                            path.lineTo(pointList.get(i).x,pointList.get(i).y);
                        }
                    }



                   canvas.drawPath(path,mBrokenLinePaint);
                }
                mScorePointsList.add(pointList);

            }
            canvas.restore();
        }
    }
//绘制折线点
    private void drawBrokenPoint(Canvas canvas){
        int dataSize=mDataList.size();
        if(dataSize>0){
            canvas.save();
            for(int j=0;j<dataSize;j++){
                BrokenLineDimensionBean dimensionBean=mDataList.get(j);
                List<Double>doubles=dimensionBean.mDataList;
                int count=doubles.size();
                for(int i=0;i<count;i++){
                    int brokenPointColor=dimensionBean.mBrokenPointColor;
                    int brokenPointInColor=dimensionBean.mBrokenPointIntColor;
                    int brokenPointOutColor=dimensionBean.mBrokenPointOutColor;

                    mBrokenPointPaint.setStrokeWidth(DensityUtil.dip2px(getContext(),2));
                    mBrokenPointPaint.setStyle(Paint.Style.STROKE);
                    mBrokenPointPaint.setColor(brokenPointColor);
                    canvas.drawCircle(mScorePointsList.get(j).get(i).x,mScorePointsList.get(j).get(i).y,DensityUtil.dip2px(getContext(),2),mBrokenLinePaint);

                    if(mSelectIndex==i){
                        mBrokenPointPaint.setColor(Color.WHITE);
                        mBrokenPointPaint.setStyle(Paint.Style.FILL);
                        mBrokenPointPaint.setColor(brokenPointOutColor);
                        canvas.drawCircle(mScorePointsList.get(j).get(i).x,mScorePointsList.get(j).get(i).y,DensityUtil.dip2px(getContext(),6),mBrokenLinePaint);
                        mBrokenPointPaint.setColor(brokenPointInColor);
                        canvas.drawCircle(mScorePointsList.get(j).get(i).x,mScorePointsList.get(j).get(i).y,DensityUtil.dip2px(getContext(),3),mBrokenLinePaint);


                    }

                    mBrokenPointPaint.setColor(0xffffffff);
                    canvas.drawCircle(mScorePointsList.get(j).get(i).x,mScorePointsList.get(j).get(i).y,DensityUtil.dip2px(getContext(),1.5f),mBrokenLinePaint);
                    mBrokenPointPaint.setStyle(Paint.Style.STROKE);
                    mBrokenPointPaint.setColor(brokenPointColor);
                    canvas.drawCircle(mScorePointsList.get(j).get(i).x,mScorePointsList.get(j).get(i).y,DensityUtil.dip2px(getContext(),2.5f),mBrokenLinePaint);
                    mBrokenPointPaint.setColor(brokenPointColor);

                }
            }
            canvas.restore();
        }
    }
    private void drawFloatTextBackground(Canvas canvas,int x,int y){
        Path path =new Path();
        path.reset();
        mBrokenLinePaint.setStyle(Paint.Style.FILL);
        mBrokenLinePaint.setColor(mBrokenLineColor);
        //p1
        Point point =new Point(x,y);
        path.moveTo(point.x,point.y);
        //p2
        point.x=point.x+DensityUtil.dip2px(getContext(),5);
        point.y= point.y-DensityUtil.dip2px(getContext(),5);
        path.lineTo(point.x, point.y);

        //p3
        point.x= point.x+DensityUtil.dip2px(getContext(),12);
        path.lineTo(point.x, point.y);

        //p4
        point.y= point.y-DensityUtil.dip2px(getContext(),17);
        path.lineTo(point.x, point.y);

        //p5
        point.x=point.x-DensityUtil.dip2px(getContext(),34);
        path.lineTo(point.x, point.y);


        //p6
        point.y=point.y+DensityUtil.dip2px(getContext(),17);
        path.lineTo(point.x, point.y);

        //p7
        point.x= point.x+DensityUtil.dip2px(getContext(),12);
        path.lineTo(point.x, point.y);

        //p8
        path.lineTo(x,y);
        path.close();
        canvas.drawPath(path,mBrokenLinePaint);



    }
    //绘制顶部介绍
    private void drawRemark(Canvas canvas){
        canvas.save();
        mRemarkPaint.setStrokeWidth(DensityUtil.dip2px(getContext(),2));
        int size=mDataList.size();
        int paddingRight=DensityUtil.dip2px(getContext(),40);
        int totalPadding=paddingRight*(size-1);
        int oneWidth=DensityUtil.dip2px(getContext(),90);
        int width=oneWidth*size+totalPadding;
        int x=(int)(mWidth-width)/2;
        int y=mTopPadding;

        for(int i=0;i<size;i++){
            BrokenLineDimensionBean dimensionBean=mDataList.get(i);
            mRemarkPaint.setColor(dimensionBean.mBrokenLineColor);
            int startX=x+(i*oneWidth)+paddingRight;
            int endx=startX+oneWidth-paddingRight;
            canvas.drawLine(startX,y,endx,y,mRemarkPaint);
            float textWidth=mTextPaint.measureText(dimensionBean.remark);
            int offset=(int) ((oneWidth-textWidth-paddingRight)/2);
            canvas.drawText(dimensionBean.remark,startX+offset,y*2,mTextPaint);
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.getParent().requestDisallowInterceptTouchEvent(true);
        int action=event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
                this.getParent().requestDisallowInterceptTouchEvent(false);
            case MotionEvent.ACTION_UP:
                onClick(event);
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return true;
    }

    private void onClick(MotionEvent event){
        boolean isValidTouch=validateTouch(event.getX(),event.getY());
        if (isValidTouch){
            invalidate();
        }
    }

    //判断点击区域
    private boolean validateTouch(float x,float y){
        for(int j=0;j<mScorePointsList.size();j++){
            List<Point>list=mScorePointsList.get(j);
            for(int i=0;i<list.size();i++){
                if(x>list.get(i).x-DensityUtil.dip2px(getContext(),8)*2 && x <list.get(i).x+DensityUtil.dip2px(getContext(),8)*2){
                    if(y>list.get(i).y-DensityUtil.dip2px(getContext(),8)*2 && y<list.get(i).y+DensityUtil.dip2px(getContext(),8)*2){
                        mSelectIndex=i;
                        if(mOnItemClick!=null){
                            mOnItemClick.onBrokenLinePointClick(i,mDataList.get(j),mXLineDataList);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public interface OnItemClick{
        void onBrokenLinePointClick(int position,BrokenLineDimensionBean dimensionBean,List<String> xData);
        void onXLinePointClick(int position,List<BrokenLineDimensionBean>data,List<String> xData);
    }

    public void setmOnItemClick(OnItemClick onItemClick){
        mOnItemClick=onItemClick;
    }

    public void setmBrokenLineTrendData(BrokenLineTrendDataBean brokenLineTrendData){
        Log.d("a1", "setmBrokenLineTrendData: ");
        this.mBrokenLineTrendData=brokenLineTrendData;
        mDataList=mBrokenLineTrendData.mDimensionList;
        mXLineDataList=mBrokenLineTrendData.mXLineDataList;
        mYLineDataList=mBrokenLineTrendData.mYLineDataList;
        mMaxYPoint=getMaxY();
        mMinYPoint=getMinY();
        Log.d("a2", "setmBrokenLineTrendData: ");
        invalidate();
        Log.d("a3", "setmBrokenLineTrendData: ");
    }



    private double getMaxY(){
        Double max=0d;
        for(Double aDouble:mYLineDataList){
            if(max<aDouble) {
                max = aDouble;
            }
        }
        return max;
    }

    private double getMinY(){
        Double min=0d;
        for(Double aDouble:mYLineDataList){
            if(min>aDouble){
                min=aDouble;
            }
        }
        return min;
    }


    //设置数据源
    public void setChangeData(List<BrokenLineDimensionBean> data){
        this.mDataList=data;
        invalidate();
    }

    //x轴的数据
    public void setChangeXData(List<String> xLineData){
        this.mXLineDataList=xLineData;
        invalidate();
    }

    //y轴的数据坐标轴
    public void setChangeYData(List<Double> yLIneData){
        this.mYLineDataList=yLIneData;
        mMaxYPoint=getMaxY();
        mMinYPoint=getMinY();
        invalidate();
    }




    public int getmDottedLineColor() {
        return mDottedLineColor;
    }

    public void setmDottedLineColor(int dottedLineColor) {
        mDottedLineColor=dottedLineColor;
        invalidate();
    }

    public int getmTextColor() {
        return mTextColor;
    }

    public void setmTextColor(int textColor) {
        mTextColor=textColor;
        invalidate();
    }

    public float getmBrokenLineWidth() {
        return mBrokenLineWidth;
    }

    public void setmBrokenLineWidth(float brokenLineWidth) {
        mBrokenLineWidth=brokenLineWidth;
        invalidate();
    }

    public int getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }
}
