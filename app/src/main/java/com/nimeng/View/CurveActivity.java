package com.nimeng.View;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nimeng.bean.BrokenLineDimensionBean;
import com.nimeng.bean.BrokenLineTrendDataBean;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.DataRecordDBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/21 9:40
 * <p>
 * Changes (from 2022/4/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/21 : Create CurveActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class CurveActivity extends CommonUtil implements IBaseView{

    private DataRecordDBHelper dataRecordDBHelper;
    private String[] defalultHorizontalText;
    private Double[] realTimeTem;
    private Double[] realTimeHum;
    private List<Double> realtimeTemList=new ArrayList<>();
    private List<Double> realtimeHumList=new ArrayList<>();
    private List<String> timeList=new ArrayList<>();


    //设置两条曲线(实时温度 实时湿度 )
   // private Double[] realTimeTem=new Double[]{15.00d,19.98d,25.22d,23.65d,19.98d,16.32,20.00d,19.98d,21.11,20.00d,19.98d,20.01d};
  //  private Double[] realTimeHum=new Double[]{30.00d,45.25d,50.36d,58.25d,59.66d,62.36d,58.02d,60.01d,60.02d,63.25d,59.00d,60.02d};




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve);


      dataRecordDBHelper =new DataRecordDBHelper(CurveActivity.this,"NIMENG.db",null,1);
      init();



        BrokenLineTrendView mBrokenLienTrendView=(BrokenLineTrendView) findViewById(R.id.scoreTrendView);
        List<Double>doubles=new ArrayList<>();
        doubles.add(0d);
        doubles.add(6d);
        doubles.add(12d);
        doubles.add(18d);
        doubles.add(24d);
        doubles.add(30d);
        doubles.add(36d);
        doubles.add(42d);
        doubles.add(48d);
        doubles.add(54d);
        doubles.add(60d);
        doubles.add(66d);
        doubles.add(72d);
        doubles.add(78d);
        doubles.add(84d);
        doubles.add(90d);
        doubles.add(96d);
        doubles.add(102d);

        BrokenLineTrendDataBean data=new BrokenLineTrendDataBean();

        //realtimeTemList= Arrays.asList(realTimeTem);
       // realtimeHumList=Arrays.asList(realTimeHum);

        List<BrokenLineDimensionBean> mDataList=new ArrayList<>();
        BrokenLineDimensionBean d1=new BrokenLineDimensionBean();
        d1.mDataList=realtimeTemList;
        d1.mBrokenLineColor=getResources().getColor(R.color.color_01_line);
        d1.mBrokenPointColor=getResources().getColor(R.color.color_01_line);
        d1.mBrokenPointIntColor=getResources().getColor(R.color.color_01_point_in);
        d1.mBrokenPointOutColor=getResources().getColor(R.color.color_01_point_out);
        d1.remark="温度";

        BrokenLineDimensionBean d2=new BrokenLineDimensionBean();
        d2.mDataList=realtimeHumList;
        d2.mBrokenLineColor=getResources().getColor(R.color.color_02_line);
        d2.mBrokenPointOutColor=getResources().getColor(R.color.color_02_point);
        d2.mBrokenPointIntColor=getResources().getColor(R.color.color_02_point_in);
        d2.mBrokenPointOutColor=getResources().getColor(R.color.color_02_point_out);
        d2.remark="湿度";


        mDataList.add(d1);
        mDataList.add(d2);

        data.mYLineDataList=doubles;
        //data.mXLineDataList=Arrays.asList(defalultHorizontalText);
        data.mXLineDataList=timeList;
        data.mDimensionList=mDataList;
        data.mSelectColor=getResources().getColor(R.color.colorAccent);



        mBrokenLienTrendView.setmBrokenLineTrendData(data);
        Log.d("b", "onCreate: ");
        mBrokenLienTrendView.setmOnItemClick(new BrokenLineTrendView.OnItemClick() {
            @Override
            public void onBrokenLinePointClick(int position, BrokenLineDimensionBean dimensionBean, List<String> xData) { Toast.makeText(CurveActivity.this, xData.get(position)+dimensionBean.remark+"."+dimensionBean.mDataList.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onXLinePointClick(int position, List<BrokenLineDimensionBean> data, List<String> xData) {
                String msg="";
                for(BrokenLineDimensionBean dimension:data){
                    String message=dimension.remark+":"+dimension.mDataList.get(position)+",";
                    msg+=message;
                }
                Toast.makeText(CurveActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void init() {

//        timeList = dataRecordDBHelper.queryColumn_String("time", true, "30");
//        realtimeTemList = dataRecordDBHelper.queryColumn_Double("realtimeTem", true, "30");
//        realtimeHumList = dataRecordDBHelper.queryColumn_Double("realtimeHum", true, "30");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//               // SystemClock.sleep(1000);
//                //获取到时间list
//                 timeList=dataRecordDBHelper.queryColumn_String("time",true,"30");
//               // Log.d(timeList.size()+"*****", "run: ");
//                //将list转化为数组
//               // String[] timeArray=  timeList.toArray(new String[timeList.size()]);
//
//                //横坐标 仅作演示使用,真实环境用现行时间
//                //private String[] defalultHorizontalText=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
//                //defalultHorizontalText=timeArray;
//
//
//
//                //获取实时温度和实时湿度
//                realtimeTemList=dataRecordDBHelper.queryColumn_Double("realtimeTem",true,"30");
////                //将list转化成数组
////                realTimeTem =realtimeTemList.toArray(new Double[realtimeTemList.size()]);
//
//
//
//                 realtimeHumList=dataRecordDBHelper.queryColumn_Double("realtimeHum",true,"30");
////                //将list转化成数组
////                realTimeHum= realtimeHumList.toArray(new Double[realtimeHumList.size()]);
//
//
//            }
//        }).start();
//    }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public <T extends Activity> T getSelfActivity() {
        return null;
    }
}
