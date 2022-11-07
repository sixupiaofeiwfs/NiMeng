package com.nimeng.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;


import androidx.annotation.Nullable;

import com.nimeng.Adapter.DataRecordAdapter;
import com.nimeng.bean.DataRecodeBean;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.DataRecordDBHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class QueryDataRecordActivity extends CommonUtil {


    TextView startDateTextView,startTimeTextView,endDateTextView,endTimeTextView;
    ImageView imageView;
    DateFormat format= DateFormat.getDateTimeInstance();
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    DataRecordDBHelper dataRecordDBHelper;

    List<DataRecodeBean> list;
    private ListView listView;
    private DataRecordAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_querydatarecord);

        startDateTextView=findViewById(R.id.textview1);
        startTimeTextView=findViewById(R.id.textview2);


        endDateTextView=findViewById(R.id.textview3);
        endTimeTextView=findViewById(R.id.textview4);

        imageView=findViewById(R.id.search);


        dataRecordDBHelper=DataRecordDBHelper.getInstance(QueryDataRecordActivity.this);
       list=new ArrayList<DataRecodeBean>();

       listView=findViewById(R.id.datarecord_list);

    }


    @Override
    protected void onStart() {

        startDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDatePickerDialog(QueryDataRecordActivity.this,2,startDateTextView,calendar);

            }
        });


        startTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(QueryDataRecordActivity.this,3,startTimeTextView,calendar);
            }
        });


        endDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(QueryDataRecordActivity.this,2,endDateTextView,calendar);
            }
        });


        endTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(QueryDataRecordActivity.this,0,endTimeTextView,calendar);
            }
        });




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListView();
            }
        });


        super.onStart();
    }







    public static void showDatePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(context, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                if(monthOfYear<9){
                    if(dayOfMonth<10){
                        tv.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
                    }else{
                        tv.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }else{

                    if(dayOfMonth<10){
                        tv.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                    }else{
                        tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }


            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }



    public static void showTimePickerDialog(Activity activity,int themeResId, final TextView tv, Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        new TimePickerDialog( activity,themeResId,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv.setText(" "+hourOfDay + ":" + minute+":00");
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                ,true).show();
    }


    public void updateListView(){
        list= dataRecordDBHelper.findDataRecordByTime(startDateTextView.getText().toString()+startTimeTextView.getText().toString(),endDateTextView.getText().toString()+endTimeTextView.getText().toString());
        if(list!=null){
            adapter=new DataRecordAdapter(list,QueryDataRecordActivity.this);
            listView.setAdapter(adapter);
        }


    }

}
