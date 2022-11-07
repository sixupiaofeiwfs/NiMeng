package com.nimeng.View;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mysql.cj.protocol.InternalDate;
import com.nimeng.View.R;
import com.nimeng.bean.DataRecodeBean;
import com.nimeng.bean.DemoBase;
import com.nimeng.bean.SystemData;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.DataRecordDBHelper;
import com.nimeng.util.DialogThridUtils;
import com.nimeng.util.SystemDBHelper;
import com.nimeng.util.WeiboDialogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class RealTimeLineChartActivity extends CommonUtil {

    private LineChart chart;


    DataRecordDBHelper dataRecordDBHelper;

    LineData data;

    public static LineDataSet temLineDataSet,humLineDataSet;

    private TextView btn1,btn2;


    private TextView textView1,textView2;
    SystemDBHelper systemDBHelper;

    private Thread thread;

    //AlertDialog alertDialog=null;

    private Dialog mWeiboDialog;

    private boolean b=true;//控制温度曲线上的点数据读取

    private boolean c=true;//控制湿度曲线上的点数据读取
    private boolean d=true;//控制每秒展示点数据


    List<DataRecodeBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);



    //  AlertDialog.Builder builder=new AlertDialog.Builder(RealTimeLineChartActivity.this);

        systemDBHelper=new SystemDBHelper(RealTimeLineChartActivity.this);
        dataRecordDBHelper=DataRecordDBHelper.getInstance(RealTimeLineChartActivity.this);

        SystemData systemData=systemDBHelper.getSystemData();
        if(systemData.getStartTime()!=null){
            list = dataRecordDBHelper.query();
        }



        temLineDataSet=createSet("tem");
        humLineDataSet= createSet("hum");
        if(!temThread.isAlive()){
            temThread.start();
        }
//        if(!humThread.isAlive()){
//            humThread.start();
//        }



        setContentView(R.layout.activity_realtime_linechart);
        LinearLayout linearLayout1=findViewById(R.id.LinearLayout1);
        linearLayout1.setBackgroundColor(Color.LTGRAY);

        LinearLayout linearLayout2=findViewById(R.id.LinearLayout2);
        linearLayout2.setBackgroundColor(Color.LTGRAY);





        textView1=findViewById(R.id.text1);
       // textView1.setBackgroundColor(Color.LTGRAY);
        textView2=findViewById(R.id.text2);
       // textView2.setBackgroundColor(Color.LTGRAY);


       // systemDBHelper=new SystemDBHelper(RealTimeLineChartActivity.this,"NIMENG.db",null,1);



        setTitle("温湿度运行曲线图");

        chart = findViewById(R.id.chart1);



        //不设置描述信息
        chart.getDescription().setEnabled(false);



        // 设置可以触摸
        chart.setTouchEnabled(true);



        chart.setDragEnabled(true);//启动拖动
        chart.setScaleEnabled(true);//启动缩放
        chart.setDrawGridBackground(false);



        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

        // set an alternative background color
        chart.setBackgroundColor(Color.LTGRAY);

        data= new LineData();
        data.setValueTextColor(Color.BLACK);




        // add empty data
        chart.setData(data);


        chart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

                int l=list.size();
                if(l>300){

                    //此界面中显示的最大的x值
                    float highestVisibleX = chart.getHighestVisibleX();
                    //整个曲线中最大的x值
                    float mAxisMaximum = chart.getXAxis().mAxisMaximum;

                    //最新的点的X值
                    Date date =new Date();
                    int t=(int) (date.getTime()- getTimeToDate(systemData.getStartTime()).getTime())/1000;


                    if(mAxisMaximum-highestVisibleX>60){
                        d=false;
                        thread.interrupt();
                        chart.moveViewToX(highestVisibleX);
                    }
                    if(t-highestVisibleX<10) {
                        if(!thread.isAlive()){
                            d=true;
                            thread.start();
                            chart.moveViewToX(t);
                        }
                    }
                    if(mAxisMaximum-highestVisibleX<=10){
                        if(!thread.isAlive()){
                            d=true;
                            thread.start();
                            chart.moveViewToX(t);
                            //chart.moveViewToX(chart.getXAxis().mAxisMaximum);
                            //chart.moveViewToX(data.getEntryCount());
                        }

                    }
                }




            }

            @Override
            public void onChartLongPressed(MotionEvent motionEvent) {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent motionEvent) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent motionEvent) {

            }

            @Override
            public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

            }

            @Override
            public void onChartScale(MotionEvent motionEvent, float v, float v1) {

            }

            @Override
            public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {

            }
        });



        // get the legend (only possible after setting data)
        Legend temLegend = chart.getLegend();


        // modify the legend ...
        temLegend.setForm(LegendForm.LINE);
        temLegend.setTextColor(Color.WHITE);


        XAxis temXL = chart.getXAxis();
       // xl.setTypeface(tfLight);
        temXL.setTextColor(Color.BLACK);
        temXL.setDrawGridLines(true);//绘制网格线
        temXL.setAvoidFirstLastClipping(true);
        temXL.setEnabled(true);
        temXL.setPosition(XAxis.XAxisPosition.BOTTOM);
        temXL.setValueFormatter(new ValueFormatter() {
         @Override
         public String getFormattedValue(float value) {
             Date date=getTimeToDate(systemData.getStartTime());
             if(date==null){
                 return "";
             }



             Calendar c=Calendar.getInstance();
             c.setTime(date);
            c.add(Calendar.SECOND,Math.round(value));
            // c.add(Calendar.SECOND,Math.round(limit));


             SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss" );
             String strTime = sdf.format(c.getTime());
            // System.out.println("时间坐标轴..."+date.toString()+"   "+value+"   "+Math.round(value)+"   "+strTime);
             return strTime;
         }
     });



        YAxis leftAxis = chart.getAxisLeft();
      //  leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(Color.RED);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(-20f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = chart.getAxisRight();
      //  rightAxis.setTypeface(tfRegular);
        rightAxis.setTextColor(Color.BLUE);
        rightAxis.setAxisMaximum(100f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setEnabled(true);




        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {


                Date date=getTimeToDate(systemData.getStartTime());
                if(date==null){
                    return;
                }
                Calendar c=Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.SECOND,Math.round(entry.getX()));
                SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss" );
                String time = sdf.format(c.getTime());


                Toast.makeText(RealTimeLineChartActivity.this,"时间："+time+" 值："+entry.getY(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });



        btn1=findViewById(R.id.linechart_btn1);
        btn2=findViewById(R.id.linechart_btn2);


        feedMultiple();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RealTimeLineChartActivity.this,MainActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RealTimeLineChartActivity.this,DataRecordActivity.class));
            }
        });



        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SystemData systemData=systemDBHelper.getSystemData();
                System.out.println("开始前："+systemData.getTemIsClick());
                if(systemData.getTemIsClick()==0){
                    data.removeDataSet(humLineDataSet);
//                    float ratio = (float) temLineDataSet.getEntryCount()/(float) 60;
//                    chart.zoom(ratio,2f,0,0);
                    systemData.setTemIsClick(1);
                }else{
                    data.addDataSet(humLineDataSet);
                    systemData.setTemIsClick(0);

                }


                systemDBHelper.updateSystemData(systemData);



                onStart();


            }
        });



        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemData systemData=systemDBHelper.getSystemData();

                if(systemData.getHumIsClick()==0){
                    data.removeDataSet(temLineDataSet);
//                    float ratio = (float) humLineDataSet.getEntryCount()/(float) 60;
//                    chart.zoom(ratio,2f,0,0);
                    systemData.setHumIsClick(1);
                }else{
                    data.addDataSet(temLineDataSet);
                    systemData.setHumIsClick(0);

                }


                systemDBHelper.updateSystemData(systemData);

                onStart();
            }
        });

    }

    public Thread temThread=new Thread(new Runnable() {

        @Override
        public void run() {
           while(b){
               System.out.println("执行method方法...");
               temMethod();
           }

        }
    });




     public void temMethod(){
         Date date=new Date();
         SystemData systemData=systemDBHelper.getSystemData();
         if(systemData!=null && systemData.getStartTime()!=null) {



             int total=0;
             int m = 0;
             int t=0;
             if (list != null) {
              int n= total=list.size();

//                 if(n>30){
//                     n=30;
//                 }
                 for (int i = 0; i < n; i++) {

                     //从数据库中获取到的数据的时间距离启动时间已经过去的秒数
                     t = (int) (getTimeToDate(list.get(total-n+i).getTime()).getTime() - getTimeToDate(systemData.getStartTime()).getTime()) / 1000;
                     float fTem = list.get(total-n+i).getRealtimeTem();
                     float fHum = list.get(total-n+i).getRealtimeHum();

                     //System.out.println("数据..."+t+"   "+fTem+"   "+fHum);
                     if(t>=1){
                         if (m != 0 && t - m > 1) {
                             int c = t - m - 1;
                             for (int j = 1; j <= c; j++) {
                                 //System.out.println("循环内写入:"+(j+m));
                                 temLineDataSet.addEntry(new Entry((j + m), 0));
                                 humLineDataSet.addEntry(new Entry((j + m), 0));
                             }
                         }
                         //System.out.println("循环外写入:"+t);
                         temLineDataSet.addEntry(new Entry(t, fTem));
                         humLineDataSet.addEntry(new Entry(t, fHum));

                         m = t;
                     }


                 }



            }
        }
        b=false;
        temThread.interrupt();

    }





    @Override
    protected void onStart() {
        SystemData systemData= systemDBHelper.getSystemData();



        data.removeDataSet(temLineDataSet);
        data.removeDataSet(humLineDataSet);


            if(systemData.getTemIsClick()==0){
                data.addDataSet(humLineDataSet);
            }if(systemData.getHumIsClick()==0){
                data.addDataSet(temLineDataSet);
            }



        super.onStart();




    }



    private void addEntry(String code) {
       data=chart.getData();

       SystemData systemData=systemDBHelper.getSystemData();




    if(code=="tem"){

            Date date=new Date();
            Calendar c=Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.SECOND,-1);
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            String strTime = sdf.format(c.getTime());

            DataRecodeBean dataRecodeBean=dataRecordDBHelper.queryByTime(strTime);



            if(dataRecodeBean!=null){
              // temLineDataSet.addEntry(new Entry(temLineDataSet.getEntryCount(),dataRecodeBean.getRealtimeTem()));

               // System.out.println("开始时间:"+systemData.getStartTime()+"现在时间:"+strTime+"时间差:"+(getTimeToDate( strTime).getTime()-getTimeToDate(systemData.getStartTime()).getTime())+"可以:"+temLineDataSet.getEntryCount()+"    不可以:"+(getTimeToDate( strTime).getTime()-getTimeToDate(systemData.getStartTime()).getTime())/1000+"   温度:"+dataRecodeBean.getRealtimeTem());

                if(systemData.getStartTime()==null){
                    return;
                }
                 temLineDataSet.addEntry(new Entry((getTimeToDate( strTime).getTime()-getTimeToDate(systemData.getStartTime()).getTime())/1000,dataRecodeBean.getRealtimeTem()));

            }






            data.notifyDataChanged();

            // let the chart know it's data has changed
            chart.notifyDataSetChanged();


            // 可以显示的最大x轴范围
            chart.setVisibleXRangeMaximum(300);
            // chart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
          //  chart.moveViewToX(data.getEntryCount());

            chart.moveViewToX(chart.getXAxis().mAxisMaximum);

        }
        else{


            Date date=new Date();
            Calendar c=Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.SECOND,-1);
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            String strTime = sdf.format(c.getTime());



            DataRecodeBean dataRecodeBean=dataRecordDBHelper.queryByTime(strTime);
            if(dataRecodeBean!=null){

               // humLineDataSet.addEntry(new Entry(humLineDataSet.getEntryCount(),dataRecodeBean.getRealtimeHum()));

                if(systemData.getStartTime()==null){
                    return;
                }
                humLineDataSet.addEntry(new Entry((getTimeToDate( dataRecodeBean.getTime()).getTime()-getTimeToDate(systemData.getStartTime()).getTime())/1000,dataRecodeBean.getRealtimeHum()));
            }




            data.notifyDataChanged();

            // let the chart know it's data has changed
            chart.notifyDataSetChanged();


            // 可以显示的最大x轴范围
            chart.setVisibleXRangeMaximum(300);
            // chart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
           // chart.moveViewToX(data.getEntryCount());
            chart.moveViewToX(chart.getXAxis().mAxisMaximum);

        }
    }










    private LineDataSet createSet(String code) {

        LineDataSet set=new LineDataSet(null,"");

        System.out.println("调用设置方法.....");

        if(code=="tem"){
             set.setLabel("温度");
            //控制该数据使用哪个Y轴（左侧还是右侧）
            set.setAxisDependency(AxisDependency.LEFT);
            set.setColor(Color.RED);
        }else{
            set.setLabel("湿度");
            set.setAxisDependency(AxisDependency.RIGHT);
            set.setColor(Color.BLUE);
        }




        set.setCircleColor(Color.WHITE);
        set.setLineWidth(1f);
        set.setCircleRadius(2f);



        set.setFillAlpha(65);//透明度
        set.setFillColor(ColorTemplate.getHoloBlue());


        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);


        return set;
    }







    private void feedMultiple() {



        if(thread!=null){
            if(thread.isAlive()){
                return;
            }
        }


        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                addEntry("tem");
                addEntry("hum");
            }
        };

        thread = new Thread(new Runnable() {

            @Override
            public void run() {

              while(d){

                    // Don't generate garbage runnables inside the loop.
                    runOnUiThread(runnable);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }




            }
        });

        thread.start();
    }





    @Override
    protected void onStop() {
        d=false;
        if (thread != null) {
            thread.interrupt();
        }
        super.onStop();
    }

    class MyMarkers extends MarkerView{

        public MyMarkers(Context context, int layoutResource) {
            super(context, layoutResource);
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            super.refreshContent(e, highlight);
            Toast.makeText(RealTimeLineChartActivity.this,"时间："+e.getX()+" 值："+e.getY(),Toast.LENGTH_SHORT).show();
        }
    }



//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        createVelocityTracker(ev);
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                xDown = ev.getRawX();
//                yDown = ev.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                xMove = ev.getRawX();
//                yMove = ev.getRawY();
//                //滑动的距离
//                int distanceX = (int) (xMove - xDown);
//                int distanceY = (int) (yMove - yDown);
//                //获取瞬时速度
//                int ySpeed = getScrollVelocity();
//
//                if (distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN && distanceX > 0) {
//                    startActivity(new Intent(this, MainActivity.class));
//
//                } else if (-distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN && -distanceX > 0) {
//                    startActivity(new Intent(this, DataRecordActivity.class));
//
//                }
//        }
//
//        return super.dispatchTouchEvent(ev);
//    }



}



