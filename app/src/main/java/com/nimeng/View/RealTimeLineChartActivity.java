package com.nimeng.View;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

public class RealTimeLineChartActivity extends Activity {

    private LineChartView mLineChart; // 折线图对象

    // 横轴
    private String[] data = {"9s前", "8s前", "7s前", "6s前", "5s前", "4s前", "3s前", "2s前", "1s前", "现在"};

    // 每个点对应的y轴数据
    private float[] score = {541, 429, 865, 901, 503, 787, 515, 326, 892, 455};

    // 转换为LineChartView可以录入的集合对象
    private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();
    private List<PointValue> mPointValues = new ArrayList<PointValue>();

    // 单条线的对象
    private Line mLine;
    // 线的集合
    private List<Line> mLines = new ArrayList<Line>();
    // 折线图数据对象
    private LineChartData mData = new LineChartData();

    // 坐标轴对象
    private Axis mAxis = new Axis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart);

        // 找控件
        mLineChart = findViewById(R.id.line_chart);
        // 把数组中的数据录入集合中
        for (int i = 0; i < 10; i++) {
            mAxisValues.add(new AxisValue(i).setLabel(data[i])); // 设置x轴坐标显示
            mPointValues.add(new PointValue(i, score[i]));
        }

        // 创建线对象
        mLine = new Line(mPointValues);
        // 设置线的颜色
        mLine.setColor(Color.parseColor("#451F66"));
        // 设置点的形状(圆、菱形、方形)
        mLine.setShape(ValueShape.DIAMOND);
        // 设置平滑效果
//        mLine.setCubic(true);
        // 设置填充效果
        mLine.setFilled(true);
        // 是否显示点数据
        mLine.setHasLabels(true);
        // 是否显示点
        mLine.setHasPoints(true);
//        //设计点击坐标提醒
//        mLine.setHasLabelsOnlyForSelected(true);
//        //设置是否用线显示
//        mLine.setHasLines(false);

        // 把所有的线添加到集合中
        mLines.add(mLine);

        // 把x轴数据设置给坐标轴对象
        mAxis.setValues(mAxisValues);
        // 设置坐标轴是否显示
        mAxis.setHasTiltedLabels(true);
        // 设置x轴颜色
        mAxis.setTextColor(Color.parseColor("#99338D"));
        // 设置x轴标题
        mAxis.setName("山东农业大学南校区光照强度");
        // 设置字体大小
        mAxis.setTextSize(20);
        // 设置x轴是否有分割线
        mAxis.setHasLines(true);

        // 把所有的线设置到数据对象中
        mData.setLines(mLines);
        // 设置坐标轴x到数据对象中
        mData.setAxisXBottom(mAxis);
        // 把数据设置到折线控件上
        mLineChart.setLineChartData(mData);

        // 设置图表是否支持缩放滑动和平移
//        mLineChart.setInteractive(false);
        // 设置缩放的轴
        mLineChart.setZoomType(ZoomType.HORIZONTAL);
        // 设置最高放大倍数
        mLineChart.setMaxZoom(2);

        new Thread() {

            public void run() {

                while (true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 把随机生成的数更新到折线图上
                    final double randomPoint = Math.random() * 1000;

                    // 切换到主线程中执行
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            addNewPoint((float) randomPoint);
                        }
                    });


                }
            }

        }.start();
    }

    /**
     * 在调用后，在折线图中增加一个新的点，删除一个旧的点，更新折线图显示
     *
     * @param newData 新的点数据
     */
    private void addNewPoint(float newData) {
        // 先创建一个长度为11的数组
        float[] temp = new float[11];
        // 复制之前10个数组的数据到temp数组中
        for (int i = 0; i < 10; i++) {
            temp[i] = score[i];
        }
        // 补充最后一个元素的数据
        temp[10] = newData;

        // 把新数组的后十个元素覆盖到原数组中
        for (int i = 0; i < 10; i++) {
            score[i] = temp[i + 1];
        }

        // 之前集合中的数据清空
        mPointValues.clear();
        // 再次存入数组中新的数据
        for (int i = 0; i < 10; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
        // 重新设置数据
        mLineChart.setLineChartData(mData);
    }

}

