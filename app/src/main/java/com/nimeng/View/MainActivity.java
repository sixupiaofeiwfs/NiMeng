package com.nimeng.View;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.modbus.SerialClient;
import com.modbus.SerialPortParams;
import com.modbus.SerialUtils;
import com.modbus.modbus.ModBusData;
import com.modbus.modbus.ModBusDataListener;
import com.nimeng.bean.DataRecodeBean;
import com.nimeng.flash.FlashView;
import com.nimeng.flash.VirtualBarUtil;
import com.nimeng.util.DataRecordDBHelper;
import com.serotonin.modbus4j.serial.SerialPortWrapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class MainActivity extends BaseAvtivity {

    /*设置全局变量
     *1.报警开关
     *2.语音播报
     *3.状态提示
     *4.自动拍摄
     * */


    public static boolean SWITCH_ALERM=false;
    public static boolean SWITCH_VOICE=false;
    public static boolean SWITCH_STATUS=false;
    public static boolean SWITCH_AUTO_CAPTURE=false;





    public static final String DATABASE_NAME="NIMENG.db";
    private static final int MIN_DISTANCE=100;//最小滑动距离
    private GestureDetector gestureDetector;
    private Button btn_xsdh;

    private TextView mProgressTv;
    private SeekBar mTemSeekBar;
    private SeekBar mHumSeekBar;
    private FlashView mTemView;
    private FlashView mHumView;
    private Button btn_tem;
    private Button btn_hum;

    private String tem;
    private String hum;
    private final String TAG="MainActivity";

    private DataRecordDBHelper dataRecordDBHelper;
    private DataRecodeBean dataRecodeBean;


    @Override
    public  void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        onPermission(null);

        super.onCreate(savedInstanceState);
        //判断是否需要隐藏底部的虚拟按键
        if(VirtualBarUtil.hasNavBar(this)){
            VirtualBarUtil.hideBottomUIMenu(this);
        }
        setContentView(R.layout.activity_main);
        mTemView=findViewById(R.id.wd);
        mHumView=findViewById(R.id.sd);

        btn_tem=findViewById(R.id.but_tem);
        btn_hum=findViewById(R.id.but_hum);


     //init("/dev/ttyS0");





//        mTemSeekBar=findViewById(R.id.TemSeekBar);
//        mTemSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                //  mProgressTv.setText(i+"%");
//
//                mProgressTv.setText(hum+"%");
//                mTemView.setProgress(i,"tem");
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


//        mHumSeekBar=findViewById(R.id.HumSeekBar);
//        mHumSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                mHumView.setProgress(i,"hum");
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


        btn_tem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent=new Intent();

                if(view.getId()==R.id.but_tem || view.getId()==R.id.but_hum){


                    new AlertDialog.Builder(MainActivity.this).setTitle("请选择启动方式")
                            .setPositiveButton("自定义", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intent.setAction("com.nimeng.View.PlanActivity");
                                    intent.addCategory(Intent.CATEGORY_DEFAULT);

                                    // intent.setClassName("com.nimeng.View","com.nimeng.View.PlanActivity");
                                    startActivity(intent);
                                }
                            }).setNegativeButton("预设方案", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

                }




            }

        });




    }


    private void init(final String address) {
        SerialPortParams build = new SerialPortParams.Builder().serialPortPath(address).build();

        Log.d(TAG, "init: "+build.getSerialPortPath()+" "+build.getSerialPortParity());

        final SerialClient serialClient = SerialUtils.getInstance().getSerialClient(address);

        Log.d(TAG, "init: "+serialClient);





        //读运行值
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);

                    dataRecordDBHelper=new DataRecordDBHelper(MainActivity.this,"NIMENG.db",null,1);

                    serialClient.sendData(new ModBusData<Object>(01, 03, 0000, 04, new ModBusDataListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onSucceed(String hexValue, byte[] bytes) {
                            Log.d(TAG, "读8位: " + hexValue);

                            float floatHum=sixteentofloat(hexValue,0);
                            float floatTem=sixteentofloat(hexValue,1);

                           mHumView.setProgress(floatHum,"hum");
                           mTemView.setProgress(floatTem,"tem");

                           dataRecodeBean=new DataRecodeBean();
                            dataRecodeBean.setRealtimeHum(floatHum);
                            dataRecodeBean.setRealtimeTem(floatTem);
                            Date date =new Date();
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            dataRecodeBean.setTime(sdf.format(date));


                            serialClient.sendData(new ModBusData<Object>(01, 03, 0x001F, 4, new ModBusDataListener() {
                                @Override
                                public void onSucceed(String hexValue, byte[] bytes) {
                                    Log.d(TAG, "读三位: " + hexValue);




                                    float settingTem=sixteentofloat(hexValue,0);
                                    float settingHum=sixteentofloat(hexValue,1);

                                    tem=String.valueOf(settingTem);
                                    //btn_tem.setText(tem);

                                    hum=String.valueOf(settingHum);
                                    // btn_hum.setText(hum);

                                    dataRecodeBean.setSettingHum(settingHum);
                                    dataRecodeBean.setSettingTem(settingTem);



                                   dataRecordDBHelper.add(dataRecodeBean);

                                }

                                @Override
                                public void onFailed(String str) {

                                }
                            }));



                        }

                        @Override
                        public void onFailed(String str) {
                            Log.d(TAG, "onFailed22222222: "+str);
                        }
                    }));
                }
            }
        }).start();




    }




    private  float  sixteentofloat(String s,int code){

        float result=0;
        String resultString="";

        //第一步：删除字符串中的空格
        String h=s.replace(" ","");

        //第二步：提取报文中有效信息
        String h1,q1;
        if(code==0){
             q1=h.substring(6,10);
             h1=h.substring(10,14);
        }else{
             q1=h.substring(14,18);
             h1=h.substring(18,22);
        }

        String s1=h1+q1;

        //第三步：将string类型的数据转化为float
        BigInteger bigInteger=new BigInteger(s1,16);
        float f=Float.intBitsToFloat(bigInteger.intValue());
        BigDecimal bigDecimal=new BigDecimal(f);
        String t=bigDecimal.toPlainString();


        if(t.length()<5){
            resultString=t;
        }else{
            resultString=t.substring(0,5);
        }




        if(resultString!="" && resultString!=null){

            result=Float.valueOf( resultString);


        }


        return result;
    }






}





