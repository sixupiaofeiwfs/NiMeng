package com.nimeng.bean;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nimeng.View.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;
import android.view.ViewGroup.LayoutParams;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/11 14:22
 * <p>
 * Changes (from 2022/7/11)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/11 : Create LineChart.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
//折线图 类
public class LineChart
{
    // 渲染数据集 （图表的数据，折线的折点坐标信息等）
    private XYMultipleSeriesDataset mDataset;

    // 多重图层渲染器 （可以看做是总渲染器(含多个子渲染器)，绘制背景网格，折线图等）
    private XYMultipleSeriesRenderer mMltRenderer;

    // 折线集合
    private List<XYSeries> mSeriesList;


    // 标记折线图是否可以拖动
    public boolean mIsCanMove = true;

    // 图表视图（就是最后绘制出来的整个折线图）
    public GraphicalView mChartView;

    //无参构造函数 (如果有 有参数的构造函数，就不会自动添加无参构造函数，最好自己加上无参构造函数，)
    public LineChart(){}


    /* 有参构造函数
     *   设置图表属性
     *   xTitle：X轴标题
     *   yTitle：Y轴标题
     *   xMin：X轴的最小值
     *   xMax：X轴的最大值
     *   yMin：Y轴的最小值
     *   yMax：Y轴的最大值
     *   axisColor：坐标轴的颜色
     *   labelsColor：标签的颜色（标签：也就是坐标轴上的刻度值10，20...80）
     */
    public LineChart(String xTitle, String yTitle, double xMin,
                     double xMax, double yMin, double yMax, int axisColor, int labelsColor)
    {
        mDataset = new XYMultipleSeriesDataset();// 实例化数据集对象
        mMltRenderer = new XYMultipleSeriesRenderer();// 实例化多层渲染器对象
        mSeriesList = new ArrayList<XYSeries>(); // 初始化折线集合

        mMltRenderer.setXTitle(xTitle);// 设置X轴标题
        mMltRenderer.setYTitle(yTitle);// 设置Y轴标题
        mMltRenderer.setXAxisMin(xMin);// 设置X轴的最小值
        mMltRenderer.setXAxisMax(xMax);// 设置X轴的最大值
        mMltRenderer.setYAxisMin(yMin);// 设置Y轴的最小值
        mMltRenderer.setYAxisMax(yMax);// 设置Y轴的最大值
        mMltRenderer.setAxesColor(axisColor);// 设置坐标轴颜色
        mMltRenderer.setLabelsColor(labelsColor);// 设置标签(刻度值）颜色
        mMltRenderer.setShowGrid(true);// 显示网格
        mMltRenderer.setGridColor(Color.LTGRAY);// 设置网格颜色
        mMltRenderer.setXLabels(10);// 设置X轴的标签数（有几个刻度）
        mMltRenderer.setXLabelsPadding(1);//设置X轴标签的间距
        mMltRenderer.setYLabels(18);// 设置Y轴的标签数
        mMltRenderer.setYLabelsAlign(Paint.Align.LEFT);// 设置Y轴标签的方向
        mMltRenderer.setPointSize((float) 3);//设置折线点的大小
        mMltRenderer.setShowLegend(true);// 下面的 图例标注，如圆点的蓝色的折线是X轴...
        mMltRenderer.setZoomButtonsVisible(false);// 隐藏放大缩小按钮
        mMltRenderer.setZoomEnabled(true, false);// 设置缩放,这边是横向可以缩放,竖向不能缩放
        mMltRenderer.setPanEnabled(false, false);// 设置滑动,这边是横向可以滑动,竖向不可滑动



        mMltRenderer.setAxisTitleTextSize(20);//设置坐标轴标题的大小
        mMltRenderer.setLabelsTextSize(20);//设置坐标轴数字的大小



    }

    //添加一条折线到图表
    public void addLineToChart(String lineTitle, PointStyle pointStyle, int lineColor)
    {
        XYSeriesRenderer serRender = new XYSeriesRenderer();//创建1个子渲染器
        XYSeries series = new XYSeries(lineTitle);//创建1条折线

        mMltRenderer.addSeriesRenderer(serRender);// 将子渲染器添加到总渲染器
        mDataset.addSeries(series);// 将折线添加到数据集
        mSeriesList.add(series);// 将折线添加到折线集合

        // 设置折线渲染属性
        serRender.setPointStyle(pointStyle);// 设置折点的样式
        serRender.setColor(lineColor);// 设置折线的颜色
        serRender.setFillPoints(true);// 设置折点是实心还是空心
        serRender.setDisplayChartValues(false);// 不显示折点的Y值
        serRender.setDisplayChartValuesDistance(10);// 设置数值与折点的距离

    }

    //设置图表的显示页面 (activity：图表显示所在的页面)
    public void setChartViewActivity(final Activity activity)
    {
        if (mChartView == null)
        {
            //获取一个layout,用来显示图表
            LinearLayout layout = (LinearLayout) activity.findViewById(R.id.chart);
            //生成图表
            mChartView = ChartFactory.getLineChartView(activity, mDataset,mMltRenderer);

            mMltRenderer.setClickEnabled(true);// 可以响应点击
            mMltRenderer.setSelectableBuffer(10);// 设置点的缓冲半径值(在某点附近点击时,在点的半径范围内都算点击这个点)

            //折线的点击响应
            mChartView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // 拿到点击的折线对象、折点
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                    if (seriesSelection != null)
                    {
                        // 将所点击的点的信息通过Toast展示(点击的是那一条折线，第几个折点，坐标值)

                        if(seriesSelection.getSeriesIndex()==0){
                            Toast.makeText(activity,"运行时间："+seriesSelection.getXValue()+", 温度："+seriesSelection.getValue()+"℃",Toast.LENGTH_SHORT).show();
                        }else if(seriesSelection.getSeriesIndex()==1){
                            Toast.makeText(activity,"运行时间："+seriesSelection.getXValue()+", 湿度："+seriesSelection.getValue()+"%",Toast.LENGTH_SHORT).show();
                        }

//                        Toast.makeText(activity,
//                                "折线:"+ seriesSelection.getSeriesIndex()
//                                        + "\n点: "+ seriesSelection.getPointIndex()
//                                        + "\nX="+ seriesSelection.getXValue() + ", Y="+ seriesSelection.getValue(),
//                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // 将图表添加到layout中
            layout.addView(mChartView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
        else
        {
            mChartView.repaint();//绘制折线图(重绘，更新)
        }
    }

    // 向某条折线添加折点(x,y) （添加数据）lineTitle：折线的标题
    public void addData(String lineTitle, double x, double y)

    {

        if(mSeriesList.size() > 0 )//有折线
        {
            for(XYSeries ser : mSeriesList)//遍历折线集合
            {
                if(ser.getTitle().equals(lineTitle))// 找到指定的折线
                {
                    ser.add(x, y);
                    break;
                }
            }
        }
    }

    // 拖动图表  设置X轴的当前显示向右移val
    public void moveChart(int val)
    {
        // 计算当前X轴可视长度，也就是当前看到的X轴上的右刻度与左刻度之差(缩放按钮能够影响看到的刻度值范围)
        double dis = mMltRenderer.getXAxisMax() - mMltRenderer.getXAxisMin();

        double max = val + mMltRenderer.getXAxisMax();
        double min = max - dis;
        mMltRenderer.setXAxisMin(min);// 设置X轴显示的左刻度值
        mMltRenderer.setXAxisMax(max);// 设置X轴显示的右刻度值

    }
}
