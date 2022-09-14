package com.nimeng.View;

import static com.nimeng.View.MainActivity.DATABASE_NAME;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nimeng.Adapter.DataRecordAdapter;
import com.nimeng.bean.DataRecodeBean;
import com.nimeng.util.CommonUtil;
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
public class DataRecordActivity extends CommonUtil {


    private ListView listView;
    private DataRecordAdapter adapter;
    private DataRecordDBHelper dataRecordDBHelper;
    private List<DataRecodeBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datarecord);

        listView=findViewById(R.id.datarecord_list);

        ImageView imageView=findViewById(R.id.search);


        dataRecordDBHelper=new DataRecordDBHelper(DataRecordActivity.this,DATABASE_NAME,null,1);



        if(list!=null){
            list.clear();
        }



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataRecordActivity.this,QueryDataRecordActivity.class));
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        if(!thread.isAlive()){
            thread.start();
        }




    }



    public void showToast(String msg){
        Toast.makeText(DataRecordActivity.this,msg,Toast.LENGTH_SHORT).show();
    }


    private void showDate(TextView textView, int y,int mo,int d, int h,int m){
        textView.setText(y+"-"+(mo+1)+"-"+d+" "+h+":"+m+":00");
    }



    public Thread thread=new Thread(new Runnable() {
        @Override
        public void run() {

            while (true){

                SystemClock.sleep(1000);


                list=dataRecordDBHelper.query20DataRecodeBean("20");

                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);

            }

        }
    });


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            if(msg.what==1){
                if(list!=null){
                    adapter=new DataRecordAdapter(list,DataRecordActivity.this);
                    listView.setAdapter(adapter);
                }

            }
            super.handleMessage(msg);
        }
    };


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        createVelocityTracker(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = ev.getRawX();
                yDown = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = ev.getRawX();
                yMove = ev.getRawY();
                //滑动的距离
                int distanceX = (int) (xMove - xDown);
                int distanceY = (int) (yMove - yDown);
                //获取瞬时速度
                int ySpeed = getScrollVelocity();

                if (distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN && distanceX > 0) {
                    thread.interrupt();
                    startActivity(new Intent(this, RealTimeLineChartActivity.class));

                } else if (-distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN && -distanceX > 0) {
                    thread.interrupt();
                    startActivity(new Intent(this, SettingSwitchActivity.class));

                }
        }

        return super.dispatchTouchEvent(ev);
    }

}
