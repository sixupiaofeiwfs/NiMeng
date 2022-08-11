package com.nimeng.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nimeng.bean.DataRecodeBean;
import com.nimeng.bean.GlobalVariable;
import com.nimeng.bean.LineChart;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.DataRecordDBHelper;

import org.achartengine.chart.PointStyle;

import java.util.Date;
import java.util.Random;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/11 14:35
 * <p>
 * Changes (from 2022/7/11)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/11 : Create LineChartActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class LineChartActivity extends Activity {
    private LineChart mLineChart;//直线图类
    private boolean addData_thread_run; // 控制添加折线图数据线程的退出
    private int x_index;// X轴的刻度值
    private Random random;// 用来获取随机数
    private DataRecordDBHelper dataRecordDBHelper;
    private GlobalVariable globalVariable;
    private Button btn1,btn2;

    private CommonUtil commonUtil=new CommonUtil();

    Thread thread1;

    private TextView textViewTem,textViewHum,textViewTemChange,textViewHumChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_linechart);//设置主界面

        textViewTem=findViewById(R.id.textview_realTem);
        textViewHum=findViewById(R.id.textview_realHum);
        textViewTemChange=findViewById(R.id.textview_temChange);
        textViewHumChange=findViewById(R.id.textview_humChange);


        globalVariable=(GlobalVariable)getApplicationContext();
        thread1=new Thread(addData_thread);
        thread1.start();















        System.out.println("onCreate------------------"+ thread1.isAlive());
        //创建折线图实例 （X轴标题，Y轴标题，X轴的最小值，X轴的最大值，Y轴的最小值,Y轴的最大值，坐标轴的颜色，刻度值的颜色）
        mLineChart = new LineChart("时间(s)", "温度（℃）/湿度（%）", 0, 600, -20, 100, Color.WHITE, Color.WHITE);




        //设置图表显示页面为本页面
        mLineChart.setChartViewActivity(this);


        //添加2条折线
        mLineChart.addLineToChart("温度", PointStyle.CIRCLE, Color.RED);//添加折线A
        mLineChart.addLineToChart("湿度", PointStyle.DIAMOND, Color.BLUE);//添加折线B


    };


    @Override
    protected void onStart() {



        btn1=findViewById(R.id.linechart_btn1);
        btn2=findViewById(R.id.linechart_btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LineChartActivity.this,MainActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LineChartActivity.this,DataRecordActivity.class));
            }
        });





        dataRecordDBHelper=new DataRecordDBHelper(LineChartActivity.this,"NIMENG.db",null,1);




        Date startTime= globalVariable.getStartTime();
        Date endTime=new Date();


        if(startTime==null){
            Toast.makeText(LineChartActivity.this,"请先选择方案或启动设备",Toast.LENGTH_SHORT).show();
            x_index=0;
        }else{
            x_index =(int) (endTime.getTime()-startTime.getTime())/1000;//初始化X轴的刻度值
        }


        random = new Random();
        super.onStart();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onResume() //在本页面onStart()之后设置为绘图所在的页面
    {
        super.onResume();




    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    // 消息句柄(线程里无法进行界面更新，所以要把消息从线程里发送出来在消息句柄里进行处理)
    public Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    //添加数据 (添加折点)

                    DataRecodeBean dataRecodeBean=dataRecordDBHelper.queryByTime(commonUtil.getDateTimeToString(new Date()));

                    textViewTem.setText(String.valueOf(dataRecodeBean.getRealtimeTem()));
                    textViewHum.setText(String.valueOf(dataRecodeBean.getRealtimeHum()));
                    textViewTemChange.setText("0.03/min");
                    textViewHumChange.setText("0.06/min");

                    mLineChart.addData("温度", x_index,  dataRecodeBean.getRealtimeTem() );
                    mLineChart.addData("湿度", x_index, dataRecodeBean.getRealtimeHum() );



                    x_index += 1; // X轴每次右移10个刻度
                    mLineChart.moveChart(1);// 图标右移10刻度值

                    //绘制折线图(更新UI)
                    mLineChart.mChartView.repaint();
                    break;
            }
        }
    };

    // "开始"按钮的点击响应动作
//    public void onButtonStartClicked(View v)
//    {
//        Button btn_start = (Button)v;// 拿到按钮句柄
//        if( btn_start.getText().toString().equals("开始") )
//        {// 点击的是"开始"
//            new Thread(addData_thread).start() ;// 开启子线程,开始动态的添加数据
//            btn_start.setText("停止");// 设置按钮文本为 "停止"
//        }
//        else // 点击的是"停止"
//        {
//            addData_thread_run = false;// 结束子线程,停止添加数据
//            btn_start.setText("开始");// 设置按钮文本为 "开始"
//        }
//
//    }

    //添加折线图数据的线程
   private  Runnable addData_thread = new Runnable()
    {
        @Override
        public void run()
        {

            addData_thread_run = true;
            while(addData_thread_run)
            {
                try {
                    Thread.sleep(1000);// 延时1秒
                    mHandler.sendEmptyMessage(0);// 发送0类型信息，通知主线程更新图表

                } catch (InterruptedException e)
                {
                    break;
                }
            }
        }
    };






}
