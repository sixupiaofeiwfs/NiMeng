package com.nimeng.View;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nimeng.bean.SystemData;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.SystemDBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SystemParamActivity extends CommonUtil  implements View.OnClickListener{

    private TextView textView7,textView8,textView9,textView10;



    private int year,month,day,hour,minute,second;

    private TextView editText;
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

    SystemDBHelper systemDBHelper;
    SystemData systemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemparam);

        alarmManager=(AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
       // systemDBHelper=new SystemDBHelper(SystemParamActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(SystemParamActivity.this);
        systemData=systemDBHelper.getSystemData();



        editText=findViewById(R.id.text_lightTime);
        editText.setOnClickListener(this);





        textView7=findViewById(R.id.systemparam_showtextview2);
        textView8=findViewById(R.id.systemparam_showtextview3);
        textView9=findViewById(R.id.systemparam_showtextview4);
        textView10=findViewById(R.id.systemparam_showtextview5);








        button2=findViewById(R.id.Bdingshi1);
        button3=findViewById(R.id.Bdingshi2);
        button4=findViewById(R.id.Bdingshi3);
        button5=findViewById(R.id.Bdingshi4);

        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);



        //获取当前日期和时间
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hour=calendar.get(Calendar.HOUR);
        minute=calendar.get(Calendar.MINUTE);
        second=calendar.get(Calendar.SECOND);






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

        String moS=String.valueOf(mo+1);
        if(mo+1<10){
            moS="0"+moS;
        }
        String dS=String.valueOf(d);
        if(d<10){
            dS="0"+dS;
        }
        String hs=String.valueOf(h);
        if(h<10){
            hs="0"+hs;
        }
        String ms=String.valueOf(m);
        if(m<10){
            ms="0"+ms;
        }

        textView.setVisibility(View.VISIBLE);
        textView.setText(y+"-"+moS+"-"+dS+" "+hs+":"+ms+":00");
    }


    @Override
    protected void onStart() {

        editText.setText(String.valueOf(systemData.getLightKeepSecond()));

        super.onStart();
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.text_lightTime:
                View view1=View.inflate(SystemParamActivity.this,R.layout.input,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(SystemParamActivity.this);
                EditText editText1=view1.findViewById(R.id.text1);
                builder.setView(view1)
                        .setTitle("修改照明时间")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lightKeepSecond=Integer.valueOf(editText1.getText().toString());
                                System.out.println("照明维持时间-----"+lightKeepSecond);

                                systemData.setLightKeepSecond(lightKeepSecond);

                                systemDBHelper.updateSystemData(systemData);
                                onStart();


                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                break;


            case R.id.Bdingshi1:
                method(button2,textView7);
                break;

            case R.id.Bdingshi2:
                method(button3,textView8);
                break;

            case R.id.Bdingshi3:
                method(button4,textView9);
                break;


            case R.id.Bdingshi4:
                method(button5,textView10);
                break;
        }


    }


    public void method(Button button,TextView textView){
        View view1=View.inflate(SystemParamActivity.this,R.layout.layout_dateandtime,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(SystemParamActivity.this);

        DatePicker datePicker=view1.findViewById(R.id.datepicker);
        TimePicker timePicker=view1.findViewById(R.id.timepicker);




        builder.setTitle("选择时间和日期")
                .setView(view1)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(button.getText().toString().equals("启用")){
                            button.setText("修改");


                            System.out.println(datePicker.getYear());
                            showDate(textView,datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),timePicker.getCurrentHour(),timePicker.getCurrentMinute());
                            date1=transferStringToDate(textView.getText().toString());
                            if(button.equals(button2)){
                                setTemStart(false);
                            }else if(button.equals(button3)){
                                setTemEnd(false);
                            }else if (button.equals(button4)){
                                setHumStart(false);
                            }else if (button.equals(button5)){
                                setHumEnd(false);
                            }


                        }else {
                            if(button.equals(button2)){
                                setTemStart(true);
                            }else if(button.equals(button3)){
                                setTemEnd(true);
                            }else if (button.equals(button4)){
                                setHumStart(true);
                            }else if (button.equals(button5)){
                                setHumEnd(true);
                            }
                            button.setText("启用");

                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


}
