package com.nimeng.View;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SystemParamActivity extends BaseAvtivity{

    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;

    private DatePicker datePicker1,datePicker2,datePicker3,datePicker4,datePicker5;

    private TimePicker timePicker1,timePicker2,timePicker3,timePicker4,timePicker5;


    private int year,month,day,hour,minute,second;

    private EditText editText;
    private int lightKeepSecond;


    private Button button1,button2,button3,button4,button5;

    AlarmManager alarmManager;
    Intent intent;
    PendingIntent pendingIntent;


    public final String TEMSTARTACTION="android.intent.action.TEMSTART";
    public final String TEMENDACTION="android.intent.action.TEMEND";
    public final String HUMSTARTACTION="android.intent.action.HUMSTART";
    public final String HUMENDACTION="android.intent.action.HUMEND";

    private Date date1,date2,date3,date4,date5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemparam);

        alarmManager=(AlarmManager) this.getSystemService(Context.ALARM_SERVICE);



        editText=findViewById(R.id.huanxiang);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                     lightKeepSecond=Integer.valueOf(editText.getText().toString());
                    System.out.println("照明维持时间-----"+lightKeepSecond);

                    globalVariable.setLightKeepSecond(lightKeepSecond);
                }
            }
        });





        textView1=findViewById(R.id.text_time);
        textView2=findViewById(R.id.set_dingshi1);
        textView3=findViewById(R.id.set_dingshi2);
        textView4=findViewById(R.id.set_dingshi3);
        textView5=findViewById(R.id.set_dingshi4);
        textView6=findViewById(R.id.systemparam_showtextview1);
        textView7=findViewById(R.id.systemparam_showtextview2);
        textView8=findViewById(R.id.systemparam_showtextview3);
        textView9=findViewById(R.id.systemparam_showtextview4);
        textView10=findViewById(R.id.systemparam_showtextview5);

        datePicker1=findViewById(R.id.systemparam_datepicker1);
        datePicker2=findViewById(R.id.systemparam_datepicker2);
        datePicker3=findViewById(R.id.systemparam_datepicker3);
        datePicker4=findViewById(R.id.systemparam_datepicker4);
        datePicker5=findViewById(R.id.systemparam_datepicker5);

        timePicker1=findViewById(R.id.systemparam_timepicker1);
        timePicker2=findViewById(R.id.systemparam_timepicker2);
        timePicker3=findViewById(R.id.systemparam_timepicker3);
        timePicker4=findViewById(R.id.systemparam_timepicker4);
        timePicker5=findViewById(R.id.systemparam_timepicker5);





        button2=findViewById(R.id.Bdingshi1);
        button3=findViewById(R.id.Bdingshi2);
        button4=findViewById(R.id.Bdingshi3);
        button5=findViewById(R.id.Bdingshi4);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button2.getText().toString().equals("开启")){
                    setTemStart(false);
                    button2.setText("关闭");
                }else {
                    setTemStart(true);
                    button2.setText("开启");
                    textView7.setVisibility(View.GONE);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(button3.getText().toString().equals("开启")){
                    setTemEnd(false);
                    button3.setText("关闭");
                }else{
                    setTemEnd(true);
                    button3.setText("开启");
                    textView8.setVisibility(View.GONE);
                }

            }
        });



        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button4.getText().toString().equals("开启")){
                    setHumStart(false);
                    button4.setText("关闭");
                }else {
                    setHumStart(true);
                    button4.setText("开启");
                    textView9.setVisibility(View.GONE);
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(button5.getText().toString().equals("开启")){
                    setHumEnd(false);
                    button5.setText("关闭");
                }else{
                    setHumEnd(true);
                    button5.setText("开启");
                    textView10.setVisibility(View.GONE);
                }

            }
        });



        //获取当前日期和时间
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hour=calendar.get(Calendar.HOUR);
        minute=calendar.get(Calendar.MINUTE);
        second=calendar.get(Calendar.SECOND);



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
                datePicker3.setVisibility(View.VISIBLE);
                timePicker3.setVisibility(View.VISIBLE);
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker4.setVisibility(View.VISIBLE);
                timePicker4.setVisibility(View.VISIBLE);
            }
        });
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker5.setVisibility(View.VISIBLE);
                timePicker5.setVisibility(View.VISIBLE);
            }
        });



        datePicker1.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                SystemParamActivity.this.year=i;
                SystemParamActivity.this.month=i1;
                SystemParamActivity.this.day=i2;

                datePicker1.setVisibility(View.GONE);
                timePicker1.setVisibility(View.GONE);
                showDate(textView6,i,i1,i2,hour,minute);
            }
        });

        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                SystemParamActivity.this.hour=i;
                SystemParamActivity.this.minute=i1;
            }
        });



        datePicker2.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                SystemParamActivity.this.year=i;
                SystemParamActivity.this.month=i1;
                SystemParamActivity.this.day=i2;

                datePicker2.setVisibility(View.GONE);
                timePicker2.setVisibility(View.GONE);
                showDate(textView7,i,i1,i2,hour,minute);

                date1=transferStringToDate(textView7.getText().toString());
            }
        });

        timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                SystemParamActivity.this.hour=i;
                SystemParamActivity.this.minute=i1;
            }
        });



        datePicker3.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                SystemParamActivity.this.year=i;
                SystemParamActivity.this.month=i1;
                SystemParamActivity.this.day=i2;

                datePicker3.setVisibility(View.GONE);
                timePicker3.setVisibility(View.GONE);
                showDate(textView8,i,i1,i2,hour,minute);

                date2=transferStringToDate(textView8.getText().toString());
            }
        });

        timePicker3.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                SystemParamActivity.this.hour=i;
                SystemParamActivity.this.minute=i1;
            }
        });


        datePicker4.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                SystemParamActivity.this.year=i;
                SystemParamActivity.this.month=i1;
                SystemParamActivity.this.day=i2;

                datePicker4.setVisibility(View.GONE);
                timePicker4.setVisibility(View.GONE);
                showDate(textView9,i,i1,i2,hour,minute);
            }
        });

        timePicker5.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                SystemParamActivity.this.hour=i;
                SystemParamActivity.this.minute=i1;
            }
        });


        datePicker5.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                SystemParamActivity.this.year=i;
                SystemParamActivity.this.month=i1;
                SystemParamActivity.this.day=i2;

                datePicker5.setVisibility(View.GONE);
                timePicker5.setVisibility(View.GONE);
                showDate(textView10,i,i1,i2,hour,minute);
            }
        });

        timePicker5.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                SystemParamActivity.this.hour=i;
                SystemParamActivity.this.minute=i1;
            }
        });




    }


    private void setTemStart(boolean isCancel){

        Date date=new Date();
        intent =new Intent(TEMSTARTACTION);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,1);

        if(isCancel){
            alarmManager.cancel(pendingIntent);
        }else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(date1.getTime()-date.getTime()),pendingIntent);
        }


    }
    private void setTemEnd(boolean isCancel){
        Date date=new Date();
        intent=new Intent(TEMENDACTION);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,2);
        if(isCancel){
            alarmManager.cancel(pendingIntent);
        }else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(date2.getTime()-date.getTime()),pendingIntent);
        }

    }


    private void setHumStart(boolean isCancel){

        Date date=new Date();
        intent =new Intent(HUMSTARTACTION);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,3);

        if(isCancel){
            alarmManager.cancel(pendingIntent);
        }else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(date3.getTime()-date.getTime()),pendingIntent);
        }


    }
    private void setHumEnd(boolean isCancel){
        Date date=new Date();
        intent=new Intent(HUMENDACTION);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,4);
        if(isCancel){
            alarmManager.cancel(pendingIntent);
        }else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(date4.getTime()-date.getTime()),pendingIntent);
        }

    }





    private void showDate(TextView textView, int y,int mo,int d, int h,int m){
        textView.setVisibility(View.VISIBLE);
        textView.setText(y+"-"+(mo+1)+"-"+d+" "+h+":"+m+":00");
    }









}
