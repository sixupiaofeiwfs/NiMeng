package com.nimeng.View;

import static com.nimeng.View.MainActivity.DATABASE_NAME;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nimeng.Adapter.DataRecordAdapter;
import com.nimeng.bean.DataRecodeBean;
import com.nimeng.util.DataRecordDBHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/14 9:18
 * <p>
 * Changes (from 2022/6/14)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/14 : Create DataRecordActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DataRecordActivity extends BaseAvtivity {


    private ListView listView;
    private DataRecordAdapter adapter;
    private DataRecordDBHelper dataRecordDBHelper;
    private List<DataRecodeBean> list;
    private TextView textView1,textView2,textView3;
    private int year,month,day,hour,minute;
    private Button btn1;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datarecord);
        textView1=findViewById(R.id.datarecord_time1);
        textView2=findViewById(R.id.datarecord_time2);
        textView3=findViewById(R.id.datarecord_text1);
        listView=findViewById(R.id.datarecord_list);
        btn1=findViewById(R.id.datarecord_btn1);
        DatePicker datePicker1=(DatePicker) findViewById(R.id.datepick1);
        TimePicker timePicker1=(TimePicker) findViewById(R.id.timepicker1);


        DatePicker datePicker2=(DatePicker) findViewById(R.id.datepick2);
        TimePicker timePicker2=(TimePicker) findViewById(R.id.timepicker2);


        dataRecordDBHelper=new DataRecordDBHelper(DataRecordActivity.this,DATABASE_NAME,null,1);


        //获取当前日期和时间
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hour=calendar.get(Calendar.HOUR);
        minute=calendar.get(Calendar.MINUTE);
        //为DatePicker添加监听时间
        datePicker1.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                DataRecordActivity.this.year=i;
                DataRecordActivity.this.month=i1;
                DataRecordActivity.this.day=i2;
                showDate(textView1,i,i1,i2,hour,minute);

                datePicker1.setVisibility(View.GONE);
                timePicker1.setVisibility(View.GONE);


            }
        });

        //时间监听器
        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                DataRecordActivity.this.hour=i;
                DataRecordActivity.this.minute=i1;
            }
        });



        datePicker2.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                DataRecordActivity.this.year=i;
                DataRecordActivity.this.month=i1;
                DataRecordActivity.this.day=i2;
                showDate(textView2,i,i1,i2,hour,minute);

                datePicker2.setVisibility(View.GONE);
                timePicker2.setVisibility(View.GONE);


            }
        });

        //时间监听器
        timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                DataRecordActivity.this.hour=i;
                DataRecordActivity.this.minute=i1;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startTime=textView1.getText().toString();
                String endTime=textView2.getText().toString();



                String newTime=gettime();



                if(startTime.equals("点击选择开始时间") || endTime.equals("点击选择结束时间")){
                    Toast.makeText(DataRecordActivity.this,"请选择要查询的开始时间和结束时间",Toast.LENGTH_SHORT).show();
                }

                if(getTimeToDate(startTime).getTime()>getTimeToDate(endTime).getTime()){
                    Toast.makeText(DataRecordActivity.this,"开始时间不能晚于结束时间",Toast.LENGTH_SHORT).show();
                }
                if(getTimeToDate(startTime).getTime()>getTimeToDate(newTime).getTime() || getTimeToDate(endTime).getTime()>getTimeToDate(newTime ).getTime()){
                    Toast.makeText(DataRecordActivity.this,"开始时间或结束时间不能超过当前时间",Toast.LENGTH_SHORT).show();
                }



                else{
                  List<DataRecodeBean> list1=  dataRecordDBHelper.findDataRecordByTime(startTime,endTime);
                    DataRecordAdapter adapter1;
                    adapter1=new DataRecordAdapter(list1,DataRecordActivity.this);
                    listView.setAdapter(adapter1);
                }

                return;

            }
        });


        if(list!=null){
            list.clear();
        }

        //planModel=new PlanModel();
        updateListView();



        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker1.setVisibility(View.VISIBLE);
                timePicker1.setVisibility(View.VISIBLE);

            }
        });


        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker2.setVisibility(View.VISIBLE);
                timePicker2.setVisibility(View.VISIBLE);

            }
        });






        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(DataRecordActivity.this,ComprehensiveDataActivity.class);
                startActivity(intent);
            }
        });


    }







    public void updateListView(){
        list=dataRecordDBHelper.query();

        Log.d("查询到的数据——————————", "updateListView: "+list.size());

        adapter=new DataRecordAdapter(list,DataRecordActivity.this);
        listView.setAdapter(adapter);
    }

    public void showToast(String msg){
        Toast.makeText(DataRecordActivity.this,msg,Toast.LENGTH_SHORT).show();
    }


    private void showDate(TextView textView, int y,int mo,int d, int h,int m){
        textView.setText(y+"-"+(mo+1)+"-"+d+" "+h+":"+m+":00");
    }

}
