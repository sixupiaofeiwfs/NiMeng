package com.nimeng.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nimeng.View.R;
import com.nimeng.bean.ComprehensiveDataBean;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/30 9:24
 * <p>
 * Changes (from 2022/6/30)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/30 : Create ComprehensiveDataAdapter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ComprehensiveDataAdapter extends BaseAdapter {
    private List<ComprehensiveDataBean> list;

    private LayoutInflater layoutInflater;

    public ComprehensiveDataAdapter(List<ComprehensiveDataBean> list, Context context){
        this.list=list;
        layoutInflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ComprehensiveData comprehensiveData;
        if(view==null){
            view=layoutInflater.inflate(R.layout.list_comprehensivedata,null,false);
            comprehensiveData=new ComprehensiveData(view);
            view.setTag(comprehensiveData);
        }else{
            comprehensiveData=(ComprehensiveData) view.getTag();
        }

        ComprehensiveDataBean comprehensiveDataBean=(ComprehensiveDataBean) getItem(i);
        comprehensiveData.reading.setText(String.valueOf( comprehensiveDataBean.getReading()));
        comprehensiveData.reading.setTextSize(10);
        comprehensiveData.correctionValue.setText(String.valueOf( comprehensiveDataBean.getCorrectionValue()));
        comprehensiveData.actualValue.setText(String.valueOf( comprehensiveDataBean.getActualValue()));
        comprehensiveData.readingOfTestedInstrument.setText(String.valueOf( comprehensiveDataBean.getReadingOfTestedInstrument()));
        comprehensiveData.temIndicationError.setText(String.valueOf(comprehensiveDataBean.getTemIndicationError()));
        comprehensiveData.dewPointReading.setText(String.valueOf( comprehensiveDataBean.getDewPointReading()));
        comprehensiveData.standardCorrectionValue.setText(String.valueOf( comprehensiveDataBean.getStandardCorrectionValue()));
        comprehensiveData.standardActualValue.setText(String.valueOf(comprehensiveDataBean.getStandardActualValue()));
        comprehensiveData.actualRelativeHumidity.setText(String.valueOf( comprehensiveDataBean.getActualRelativeHumidity()));
        comprehensiveData.wetBulbTemperature.setText(String.valueOf( comprehensiveDataBean.getWetBulbTemperature()));
        comprehensiveData.relativeHumidity.setText(String.valueOf( comprehensiveDataBean.getRelativeHumidity()));
        comprehensiveData.indicationError.setText(String.valueOf( comprehensiveDataBean.getIndicationError()));

        return view;

    }




class ComprehensiveData{
        TextView reading, correctionValue,actualValue,readingOfTestedInstrument,temIndicationError,dewPointReading,standardCorrectionValue,standardActualValue,actualRelativeHumidity,wetBulbTemperature,relativeHumidity,indicationError;

        public ComprehensiveData(View view){
            reading=view.findViewById(R.id.list_reading);
            correctionValue=view.findViewById(R.id.list_correctionValue);
            actualValue=view.findViewById(R.id.list_actualValue);
            readingOfTestedInstrument=view.findViewById(R.id.list_readingOfTestedInstrument);
            temIndicationError=view.findViewById(R.id.list_temIndicationError);
            dewPointReading=view.findViewById(R.id.list_dewPointReading);
            standardCorrectionValue=view.findViewById(R.id.list_standardCorrectionValue);
            standardActualValue=view.findViewById(R.id.list_standardActualValue);
            actualRelativeHumidity=view.findViewById(R.id.list_actualRelativeHumidity);
            wetBulbTemperature=view.findViewById(R.id.list_wetBulbTemperature);
            relativeHumidity=view.findViewById(R.id.list_relativeHumidity);
            indicationError=view.findViewById(R.id.list_indicationError);
        }
    }

}
