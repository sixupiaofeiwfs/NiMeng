package com.nimeng.View;

import static com.nimeng.View.MainActivity.DATABASE_NAME;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
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

    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datarecord);
        //textView1=findViewById(R.id.datarecord_time1);
       // textView2=findViewById(R.id.datarecord_time2);
        textView3=findViewById(R.id.datarecord_text1);
        listView=findViewById(R.id.datarecord_list);
//        DatePicker datePicker=(DatePicker) findViewById(R.id.datepick1);
//        TimePicker timePicker=(TimePicker) findViewById(R.id.timepicker1);
//        //获取当前日期和时间
//        Calendar calendar=Calendar.getInstance();
//        year=calendar.get(Calendar.YEAR);
//        month=calendar.get(Calendar.MONTH);
//        day=calendar.get(Calendar.DAY_OF_MONTH);
//        hour=calendar.get(Calendar.HOUR);
//        minute=calendar.get(Calendar.MINUTE);
//        //为DatePicker添加监听时间
//        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
//                DataRecordActivity.this.year=i;
//                DataRecordActivity.this.month=i1;
//                DataRecordActivity.this.day=i2;
//                showDate(i,i1,i2,hour,minute);
//            }
//        });
//
//        //时间监听器
//        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
//                DataRecordActivity.this.hour=i;
//                DataRecordActivity.this.minute=i1;
//            }
//        });
//



        if(list!=null){
            list.clear();
        }
        dataRecordDBHelper=new DataRecordDBHelper(DataRecordActivity.this,DATABASE_NAME,null,1);
        //planModel=new PlanModel();
        updateListView();


//
//        textView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                datePicker.setVisibility(View.VISIBLE);
//                timePicker.setVisibility(View.VISIBLE);
//
//            }
//        });



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


    private void showDate(int y,int mo,int d, int h,int m){
        textView2.setText(y+"年"+mo+"月"+d+"日"+h+"时"+m+"分");
    }

}
