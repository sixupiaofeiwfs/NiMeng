package com.nimeng.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nimeng.bean.BrokenLineDimensionBean;
import com.nimeng.bean.BrokenLineTrendDataBean;

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
public class CurveActivity extends BaseAvtivity implements IBaseView{

    //横坐标 仅作演示使用,真实环境用现行时间
    private String[] defalultHorizontalText=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};

    //设置两条曲线(实时温度 实时湿度 )
    private Double[] realTimeTem=new Double[]{15.00d,19.98d,25.22d,23.65d,19.98d,16.32,20.00d,19.98d,21.11,20.00d,19.98d,20.01d};
    private Double[] realTimeHum=new Double[]{30.00d,45.25d,50.36d,58.25d,59.66d,62.36d,58.02d,60.01d,60.02d,63.25d,59.00d,60.02d};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve);
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
        List<Double> doubles1= Arrays.asList(realTimeTem);
        List<Double> doubles2=Arrays.asList(realTimeHum);

        List<BrokenLineDimensionBean> mDataList=new ArrayList<>();
        BrokenLineDimensionBean d1=new BrokenLineDimensionBean();
        d1.mDataList=doubles1;
        d1.mBrokenLineColor=getResources().getColor(R.color.color_01_line);
        d1.mBrokenPointColor=getResources().getColor(R.color.color_01_line);
        d1.mBrokenPointIntColor=getResources().getColor(R.color.color_01_point_in);
        d1.mBrokenPointOutColor=getResources().getColor(R.color.color_01_point_out);
        d1.remark="温度";

        BrokenLineDimensionBean d2=new BrokenLineDimensionBean();
        d2.mDataList=doubles2;
        d2.mBrokenLineColor=getResources().getColor(R.color.color_02_line);
        d2.mBrokenPointOutColor=getResources().getColor(R.color.color_02_point);
        d2.mBrokenPointIntColor=getResources().getColor(R.color.color_02_point_in);
        d2.mBrokenPointOutColor=getResources().getColor(R.color.color_02_point_out);
        d2.remark="湿度";


        mDataList.add(d1);
        mDataList.add(d2);

        data.mYLineDataList=doubles;
        data.mXLineDataList=Arrays.asList(defalultHorizontalText);
        data.mDimensionList=mDataList;
        data.mSelectColor=getResources().getColor(R.color.colorAccent);

        Log.d("a", "onCreate: ");

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
}
