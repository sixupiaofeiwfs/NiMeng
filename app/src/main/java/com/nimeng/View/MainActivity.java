package com.nimeng.View;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.modbus.SerialClient;
import com.modbus.SerialPortParams;
import com.modbus.SerialUtils;
import com.modbus.modbus.ModBusData;
import com.modbus.modbus.ModBusDataListener;
import com.nimeng.bean.DataRecodeBean;

import com.nimeng.bean.HumPlanBean;
import com.nimeng.bean.StandardApparatus;
import com.nimeng.bean.SystemData;
import com.nimeng.bean.TemPlanBean;
import com.nimeng.flash.FlashView;
import com.nimeng.flash.VirtualBarUtil;
import com.nimeng.util.ByteUtil;
import com.nimeng.util.CRC16;
import com.nimeng.util.ChangeDataDBHelper;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.DataRecordDBHelper;
import com.nimeng.util.HumPlanDBHelper;
import com.nimeng.util.ModbusError;
import com.nimeng.util.ModbusRtuMaster;
import com.nimeng.util.ModbusUtil;
import com.nimeng.util.StandardApparatusDBHelper;
import com.nimeng.util.SystemDBHelper;
import com.nimeng.util.TemPlanDBHelper;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android_serialport_api.SerialPort;
import tp.xmaihh.serialport.SerialHelper;
import tp.xmaihh.serialport.bean.ComBean;

public class MainActivity extends CommonUtil implements View.OnClickListener, View.OnLongClickListener {

    public static final int REQUEST_CODE = 1024;
    public static final String DATABASE_NAME = "NIMENG.db";
    private static final int MIN_DISTANCE = 100;//最小滑动距离
    private GestureDetector gestureDetector;




    private FlashView mTemView;
    private FlashView mHumView;
    private Button btn_tem;
    private Button btn_hum;

    private Button btn_humzd;
    private Button btn_temzd;





    private final String TAG = "MainActivity";

    private DataRecordDBHelper dataRecordDBHelper;
    private DataRecodeBean dataRecodeBean = new DataRecodeBean();

    private TemPlanDBHelper temPlanDBHelper;
    private HumPlanDBHelper humPlanDBHelper;
    private ListView listView;
    Intent intent1;

    int errorNumber;
    int excutingNumber;
    AlarmManager alarmManager;


    private ImageView imageView1, imageViewLogo;
    public final String LIGHTONACTION = "android.intent.action.LIGHTON";

    private ModbusUtil modbusUtil = new ModbusUtil();
    public float temPower, humPower;

    private CommonUtil commonUtil = new CommonUtil();


    Spinner spinner1, spinner2;

    SystemDBHelper systemDBHelper;



    public static final String ADDRESS = "/dev/ttyS0";


    private boolean isFalse;





    public ChangeDataDBHelper changeDataDBHelper;

   private  AlertDialog alertDialog=null;
    SerialHelper serialHelper,temSerialHelper,humSerialHelper;
    ModbusRtuMaster modbusRtuMaster;

    private int temSlave,humSlave,count;
    private String temAddress,humAddress;




    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        changeDataDBHelper=ChangeDataDBHelper.getInstance(MainActivity.this);
        dataRecordDBHelper=DataRecordDBHelper.getInstance(MainActivity.this);




         serialHelper=new SerialHelper("/dev/ttyS0",9600) {
            @Override
            protected void onDataReceived(ComBean comBean) {
            Message message=dataHandler.obtainMessage();
            message.obj=comBean;
            dataHandler.sendMessage(message);

            }


         };



        try {
            if(!serialHelper.isOpen())
                serialHelper.open();
        } catch (IOException e) {
            e.printStackTrace();
        }


        modbusRtuMaster=new ModbusRtuMaster(serialHelper);


        //判断是否需要隐藏底部的虚拟按键
        if (VirtualBarUtil.hasNavBar(this)) {
            VirtualBarUtil.hideBottomUIMenu(this);
        }
        setContentView(R.layout.activity_main);


        mTemView = findViewById(R.id.wd);
        mHumView = findViewById(R.id.sd);
        imageView1 = findViewById(R.id.main_light);
        mTemView.setOnClickListener(this);
        mTemView.setOnLongClickListener(this);
        mHumView.setOnClickListener(this);
        mHumView.setOnLongClickListener(this);
        imageView1.setOnClickListener(this);

        imageViewLogo = findViewById(R.id.main_logo);

        imageViewLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
                return;
            }
        });




        spinner1 = (Spinner) findViewById(R.id.main_spinner1);
        spinner2 = (Spinner) findViewById(R.id.main_spinner2);

        btn_tem = findViewById(R.id.mian_btn1);
        btn_hum = findViewById(R.id.mian_btn2);
        btn_tem.setOnClickListener(this);
        btn_hum.setOnClickListener(this);


        btn_temzd=findViewById(R.id.main_btn_1);
        btn_humzd=findViewById(R.id.main_btn2);
        btn_temzd.setOnClickListener(this);
        btn_humzd.setOnClickListener(this);




        //----------------------------------------------------------------------------------------------------------------------------------------------------获取文件读写权限
        onPermission();

        temPlanDBHelper=TemPlanDBHelper.getInstance(MainActivity.this);
        humPlanDBHelper=HumPlanDBHelper.getInstance(MainActivity.this);


        systemDBHelper=SystemDBHelper.getInstance(MainActivity.this);
        SystemData primarySystemData = systemDBHelper.getSystemData();


        List<TemPlanBean> temPlanBeans = temPlanDBHelper.query();
        List<HumPlanBean> humPlanBeans = humPlanDBHelper.query();



        System.out.println(primarySystemData+"    "+temPlanBeans+ "   "+humPlanBeans);

//----------------------------------------------------------------------------------------------------------------------------------------------------添加温湿度点 和预设方案
        if (temPlanBeans == null || temPlanBeans.size() == 0) {
            TemPlanBean temPlanBean1, temPlanBean2, temPlanBean3, temPlanBean4;
            temPlanBean1 = new TemPlanBean();
            temPlanBean1.setName("方案一（不设置）");
            temPlanBean1.setUnitTime(5);
            temPlanBean1.setTemWave(0.2f);
            temPlanBean1.setTemPoints(0);
            temPlanBean1.setIsCheck(0);
            temPlanDBHelper.add(temPlanBean1);

            temPlanBean2 = new TemPlanBean();
            temPlanBean2.setName("方案二（20℃-40℃-60-80℃）");
            temPlanBean2.setUnitTime(5);
            temPlanBean2.setTemWave(0.2f);
            temPlanBean2.setTemPoints(4);
            temPlanBean2.setTem1(20);
            temPlanBean2.setTem2(40);
            temPlanBean2.setTem3(60);
            temPlanBean2.setTem4(80);
            temPlanBean2.setIsCheck(0);
            temPlanDBHelper.add(temPlanBean2);

            temPlanBean3 = new TemPlanBean();
            temPlanBean3.setName("方案三（15℃-20℃-40℃-60℃-80℃）");
            temPlanBean3.setUnitTime(5);
            temPlanBean3.setTemWave(0.2f);
            temPlanBean3.setTemPoints(5);
            temPlanBean3.setTem1(15);
            temPlanBean3.setTem2(20);
            temPlanBean3.setTem3(40);
            temPlanBean3.setTem4(60);
            temPlanBean3.setTem5(80);
            temPlanBean3.setIsCheck(0);
            temPlanDBHelper.add(temPlanBean3);


            temPlanBean4 = new TemPlanBean();
            temPlanBean4.setName("方案四（15℃-20℃-40-℃-60℃-80℃-90℃）");
            temPlanBean4.setUnitTime(5);
            temPlanBean4.setTemWave(0.2f);
            temPlanBean4.setTemPoints(6);
            temPlanBean4.setTem1(15);
            temPlanBean4.setTem2(20);
            temPlanBean4.setTem3(40);
            temPlanBean4.setTem4(60);
            temPlanBean4.setTem5(80);
            temPlanBean4.setTem6(90);
            temPlanBean4.setIsCheck(0);
            temPlanDBHelper.add(temPlanBean4);
        }


        if (humPlanBeans == null || humPlanBeans.size() == 0) {
            HumPlanBean humPlanBean1, humPlanBean2, humPlanBean3, humPlanBean4;
            humPlanBean1 = new HumPlanBean();
            humPlanBean1.setName("方案一（不设置）");
            humPlanBean1.setHumPoints(0);
            humPlanBean1.setUnitTime(1);
            humPlanBean1.setHumWave(0.8f);
            humPlanBean1.setIsCheck(0);
            humPlanDBHelper.add(humPlanBean1);


            humPlanBean2 = new HumPlanBean();
            humPlanBean2.setName("方案二（40%）");
            humPlanBean2.setUnitTime(1);
            humPlanBean2.setHumWave(0.8f);
            humPlanBean2.setHumPoints(1);
            humPlanBean2.setHum1(40);
            humPlanBean2.setIsCheck(0);
            humPlanDBHelper.add(humPlanBean2);

            humPlanBean3 = new HumPlanBean();
            humPlanBean3.setName("方案三（20%-40%-60%-80%）");
            humPlanBean3.setUnitTime(1);
            humPlanBean3.setHumWave(0.8f);
            humPlanBean3.setHumPoints(4);
            humPlanBean3.setHum1(20);
            humPlanBean3.setHum2(40);
            humPlanBean3.setHum3(60);
            humPlanBean3.setHum4(80);
            humPlanBean3.setIsCheck(0);
            humPlanDBHelper.add(humPlanBean3);


            humPlanBean4 = new HumPlanBean();
            humPlanBean4.setName("方案四（20%-40%-60%-80%-90%）");
            humPlanBean4.setUnitTime(1);
            humPlanBean4.setHumWave(0.8f);
            humPlanBean4.setHumPoints(5);
            humPlanBean4.setHum1(20);
            humPlanBean4.setHum2(40);
            humPlanBean4.setHum3(60);
            humPlanBean4.setHum4(80);
            humPlanBean4.setHum5(90);
            humPlanBean4.setIsCheck(0);
            humPlanDBHelper.add(humPlanBean4);
        }


        //-------------------------------------------------------------------------------------------------------------------------------------------添加系统默认数据 systemData

        if (primarySystemData == null) {
            SystemData systemData1 = new SystemData();
            systemData1.setId(1);
            systemData1.setTemUnitTime(0);
            systemData1.setHumUnitTime(0);
            systemData1.setTemWave(0);
            systemData1.setHumWave(0);
            systemData1.setStable(false);
            systemData1.setTemPlanID(1);
            systemData1.setHumPlanID(1);
            systemData1.setExecutingTemID(1);
            systemData1.setExecutingHumID(1);
            systemData1.setTemStandardID(0);
            systemData1.setHumStandardID(0);
            systemData1.setHaveJurisdiction(true);
            systemData1.setLightKeepSecond(0);
            systemData1.setSelect1("0");
            systemData1.setSelect2("0");
            systemData1.setNumberOfStages(0);
            systemData1.setInstallmentPayment(false);
            systemData1.setSuperPassword("123456");
            systemData1.setTemIsClick(0);
            systemData1.setHumIsClick(0);
            systemData1.setIsFormal(1);
            systemDBHelper.addSystemData(systemData1);
        }


        if (primarySystemData == null) {
            systemDBHelper.addSwitch("1", false);
            systemDBHelper.addSwitch("2", false);
            systemDBHelper.addSwitch("3", false);
            systemDBHelper.addSwitch("4", false);
            systemDBHelper.addSwitch("5", false);
            systemDBHelper.addSwitch("6", false);
            systemDBHelper.addSwitch("7", false);
            systemDBHelper.addSwitch("8", false);
            systemDBHelper.addSwitch("9", false);
            systemDBHelper.addSwitch("10", false);
        }


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String temPlanName=adapterView.getItemAtPosition(i).toString();
                int temPlanID = temPlanDBHelper.findTemPlanByName(temPlanName); //现在选择的温度方案id

                SystemData spinnerSystemData=systemDBHelper.getSystemData();
                int spinnerTem= temPlanDBHelper.queryByID(temPlanID,1);
                System.out.println("选中的方案第一个温度值..."+spinnerTem+ "temPlanID..."+temPlanID+" temState:"+spinnerSystemData.getTemState()+"    "+spinnerSystemData.getSettingTem());
                if(temPlanID!=1){

                    if (spinnerSystemData.getTemState()==1){
                        writeSettingValue(1,spinnerSystemData.getSettingTem());
                        writeSettingValue(1,spinnerSystemData.getSettingTem());
                        mTemView.setValue(spinnerSystemData.getSettingTem(),"tem");
                    }else{
                        writeSettingValue(1,spinnerTem);
                        writeSettingValue(1,spinnerTem);
                        mTemView.setValue(spinnerTem,"tem");
                    }


                }else{
                    writeSettingValue(1,spinnerSystemData.getSettingTem());
                    writeSettingValue(1,spinnerSystemData.getSettingTem());
                    mTemView.setValue(spinnerSystemData.getSettingTem(),"tem");
                }



                int beginTemPlanID=spinnerSystemData.getTemPlanID();//之前选中的温度方案ID
                spinnerSystemData.setTemPlanID(temPlanID);
                temPlanDBHelper.updateCheck(temPlanID,beginTemPlanID);

                if(spinnerSystemData.getExecutingTemID()==0){
                    spinnerSystemData.setExecutingTemID(1);
                }
                if(temPlanID!=1 && spinnerSystemData.getTemState()==0){
                    spinnerSystemData.setSettingTem(spinnerTem);
                }

                systemDBHelper.updateSystemData(spinnerSystemData);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {





            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


              //  System.out.println("这个l什么意思..."+l+"      "+adapterView.getItemAtPosition(i).toString());

                String humPlanName=adapterView.getItemAtPosition(i).toString();

                SystemData spinnerSystemData=systemDBHelper.getSystemData();
                int humPlanID = humPlanDBHelper.findHumPlanByName(humPlanName);//现在选择的温度方案id



                int spinnerHum= humPlanDBHelper.queryByID(humPlanID,1);
                System.out.println("选中的方案第一个湿度值..."+spinnerHum);
                if(humPlanID!=1){

                    if(spinnerSystemData.getHumState()==1){
                        writeSettingValue(2,spinnerSystemData.getSettingHum());
                        writeSettingValue(2,spinnerSystemData.getSettingHum());
                        mHumView.setValue(spinnerSystemData.getSettingHum(),"hum");
                    }else{
                        writeSettingValue(2,spinnerHum);
                        writeSettingValue(2,spinnerHum);
                        mHumView.setValue(spinnerHum,"hum");
                    }


                }else{
                    writeSettingValue(2,spinnerSystemData.getSettingHum());
                    writeSettingValue(2,spinnerSystemData.getSettingHum());
                    mHumView.setValue(spinnerSystemData.getSettingHum(),"hum");
                }



                int beginHumPlanID=spinnerSystemData.getHumPlanID();//之前选中的温度方案ID
                spinnerSystemData.setHumPlanID(humPlanID);
                humPlanDBHelper.updateCheck(humPlanID,beginHumPlanID);

                if(spinnerSystemData.getExecutingHumID()==0){
                    spinnerSystemData.setExecutingHumID(1);
                }

                if(humPlanID!=1 && spinnerSystemData.getHumState()==0){
                    spinnerSystemData.setSettingHum(spinnerHum);
                }
                systemDBHelper.updateSystemData(spinnerSystemData);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        super.onCreate(savedInstanceState);
    }


    //------------------------------------------------------------------------------------------------------------------------------------------------------------ 获取存储权限
    public void onPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (Environment.isExternalStorageManager()) {

            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 先判断有没有权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        } else {

        }


    }


    @Override
    protected void onRestart() {


        super.onRestart();
    }

    @Override
    protected void onStart() {

        System.out.println("getOnOrOff方法后------------------------------");
        System.out.println(systemDBHelper);
        SystemData systemData = systemDBHelper.getSystemData();



        //标准器相关
//        StandardApparatus standardApparatus= getState(1);
//        if(standardApparatus!=null  && standardApparatus.getState()!=0){
//            temSerialHelper=new SerialHelper("/dev/ttyS0",standardApparatus.getRate()) {
//                @Override
//                protected void onDataReceived(ComBean comBean) {
//
//                }
//            };
//
//
//            temSlave=standardApparatus.getSlave();
//            temAddress=standardApparatus.getTemStartAddress();
//            count=standardApparatus.getCount();
//
//            if(!temStandardApparatusThread.isAlive()){
//                temStandardApparatusThread.start();
//            }
//        }
//        if (standardApparatus!=null && standardApparatus.getState()==1){
//
//            StandardApparatus standardApparatus1=getState(2);
//
//
//            humSerialHelper=new SerialHelper("/dev/ttyS0",standardApparatus1.getRate()) {
//                @Override
//                protected void onDataReceived(ComBean comBean) {
//
//                }
//            };
//
//            humSlave=standardApparatus1.getSlave();
//            humAddress=standardApparatus1.getHumStartAddress();
//            if(!humStandardApparatusThread.isAlive()){
//                humStandardApparatusThread.start();
//            }
//
//        }

        if (!warnThread.isAlive()) {
            warnThread.start();
        }

        if(!realTimeThread.isAlive()){
            realTimeThread.start();
        }


        getOnOrOff();






        boolean isInstallmentPayment;
        if (systemData == null) {
            isInstallmentPayment = false;
        } else {
            isInstallmentPayment = systemData.isInstallmentPayment();
        }


        //--------------------------------------------------------------------------------------------------------------------------------------------------------判断是否分期
        if (isInstallmentPayment) {
            //获取一共分了几期
            int numberOfStages = systemData.getNumberOfStages();
            //当前时间处在第几期
            excutingNumber = commonUtil.checkTime();
           // System.out.println("main---》当前处于第" + excutingNumber + "期");

            //获取当前是否已经匹配密码
           // System.out.println("判断当前是否已经匹配密码" + systemDBHelper.getPassword().get(excutingNumber - 1).isMatchs());
            if (!systemDBHelper.getPassword().get(excutingNumber - 1).isMatchs()) {
                //获取已经错误的次数
                errorNumber = systemDBHelper.getPassword().get(excutingNumber - 1).getErrorNumbers();
              //  System.out.println("当前已经错误的次数" + errorNumber);
                if (errorNumber >= 3) {
                    Toast.makeText(this, "警告！密码连续输错超过三次，系统已停止", Toast.LENGTH_SHORT).show();
                    System.exit(0);
                    return;
                }


                intent1 = new Intent(MainActivity.this, PasswordActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                return;


            }


        }


        if(systemData!=null){
            if (systemData.getTemPlanID() == 1) {
                System.out.println("这里调用设置方法..."+systemData.getSettingTem()+"     "+systemData.getSettingHum());
                mTemView.setValue(systemData.getSettingTem(), "tem");
            } else {
                mTemView.setValue(temPlanDBHelper.queryByID(systemData.getTemPlanID(), systemData.getExecutingTemID()), "tem");
            }

            if (systemData.getHumPlanID() == 1) {
                mHumView.setValue(systemData.getSettingHum(), "hum");
            } else {
                mHumView.setValue(humPlanDBHelper.queryByID(systemData.getHumPlanID(), systemData.getExecutingHumID()), "hum");
            }
        }






        DataRecordDBHelper dataRecordDBHelper1=DataRecordDBHelper.getInstance(MainActivity.this);
        dataRecordDBHelper1.delete7DaysData();

        ////----------------------------------------------------------------------------------------------------------------------------------------------------删除30分钟前的数据
        changeDataDBHelper=ChangeDataDBHelper.getInstance(MainActivity.this);
        if(systemData.getStartTime()!=null){
            changeDataDBHelper.delete30MinuteData(getTimeToDate(systemData.getStartTime()));
        }



        List<TemPlanBean> temPlanBeanList = temPlanDBHelper.query();
        if(temPlanBeanList!=null){
            List<String> temPlanNameList = new ArrayList<>();
            ////----------------------------------------------------------------------------------------------------------------------------------------------------设置下拉框显示内容
            for (int i = 0; i < temPlanBeanList.size(); i++) {
                String temPlanName = temPlanBeanList.get(i).getName();
                temPlanNameList.add(temPlanName);

            }
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, temPlanNameList);
            adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner1.setAdapter(adapter1);

            if (systemData.getTemPlanID() >= 1) {
                spinner1.setSelection(systemData.getTemPlanID() - 1);
            }
        }


        List<HumPlanBean> humPlanBeanList = humPlanDBHelper.query();
        if(humPlanBeanList!=null){
            List<String> humPlanNameList = new ArrayList<>();
            for (int j = 0; j < humPlanBeanList.size(); j++) {
                String humPlanName = humPlanBeanList.get(j).getName();
                humPlanNameList.add(humPlanName);
            }

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, humPlanNameList);

            adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


            spinner2.setAdapter(adapter2);
            if (systemData.getHumPlanID() >= 1) {
                spinner2.setSelection(systemData.getHumPlanID() - 1);
            }
        }




        //------------------------------------------------------------------------------------------------------------------------------------------------------------灯控开关

        String light = getIntent().getStringExtra("light");


        if (light == null) {
            if (systemDBHelper.getSwitch("7")) {
                imageView1.setImageResource(R.drawable.lighton);
            } else {
                imageView1.setImageResource(R.drawable.lightoff);
            }
        } else {
            if (light.equals("0")) {
                imageView1.setImageResource(R.drawable.lightoff);
            } else {
                if (systemDBHelper.getSwitch("7")) {
                    imageView1.setImageResource(R.drawable.lighton);
                } else {
                    imageView1.setImageResource(R.drawable.lightoff);
                }
            }

        }

        super.onStart();
    }


    //---------------------------------------------------------------------------------------------------------------------------------------------获取温湿度功率
    private void getPower(){

        try {
            //modbusRtuMaster.readHoldingRegisters(1,0x0015,8);

            modbusRtuMaster.readHoldingRegisters(1,0x000B,12);

        } catch (ModbusError modbusError) {
            modbusError.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------获取开关状态
    private void getOnOrOff(){
        try {
            modbusRtuMaster.readCoils(1,0000,8);
        } catch (ModbusError modbusError) {
            modbusError.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        systemDBHelper.close();
        super.onDestroy();
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------滑动事件
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
                    startActivity(new Intent(this, SettingSwitchActivity.class));

                } else if (-distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN && -distanceX > 0) {
                    startActivity(new Intent(this, RealTimeLineChartActivity.class));

                }
        }

        return super.dispatchTouchEvent(ev);
    }





    //------------------------------------------------------------------------------------------------------------------------------------------------执行下个温度点和湿度点

    /**
     * 执行下个温湿度点
     * @param code  1温度 2湿度
     * @param nextPoint   下个温度或湿度的值
     * @return
     */
    public boolean executingNextTemOrHum(int code, int nextPoint) {
        SystemDBHelper systemDBHelper1=SystemDBHelper.getInstance(MainActivity.this);
        SystemData systemDataNext = systemDBHelper1.getSystemData();
        if (code == 1) {
            systemDataNext.setExecutingTemID(systemDataNext.getExecutingTemID() + 1);
            systemDataNext.setSettingTem(nextPoint);

            //设置温度值
            mTemView.setValue(nextPoint,"tem");
            writeSettingValue(code,nextPoint);
            writeSettingValue(code,nextPoint);

            systemDataNext.setTemState(1);


        } else {
            systemDataNext.setExecutingHumID(systemDataNext.getExecutingHumID() + 1);
            systemDataNext.setSettingHum(nextPoint);

            //设定湿度
            mHumView.setValue(nextPoint,"hum");
            writeSettingValue(code,nextPoint);
            writeSettingValue(code,nextPoint);

            systemDataNext.setHumState(1);

        }


        //System.out.println("点击执行之后..."+systemDataNext);

        systemDBHelper1.updateSystemData(systemDataNext);

        return true;
    }


    //---------------------------------------------------------------------------------------------------------------------------------执行数据记录（写数据到文件中）
    public void writeDataToFile(int code, DataRecodeBean dataRecodeBean) {
        SystemData systemData1 = systemDBHelper.getSystemData();
        String str, fileName;
        if (code == 1) {
            str = dataRecodeBean.getTime() + "    " + dataRecodeBean.getSettingTem() + "    " + dataRecodeBean.getRealtimeTem();
            fileName = temPlanDBHelper.queryByID(systemData1.getTemPlanID()).getName() + "(" + systemData1.getSettingTem() + "℃）";
        }
        if (code == 2) {
            str = dataRecodeBean.getTime() + "    " + dataRecodeBean.getSettingTem() + "    " + dataRecodeBean.getRealtimeTem() + "    " + dataRecodeBean.getSettingHum() + "     " + dataRecodeBean.getRealtimeHum() + "\r\n";
            fileName = temPlanDBHelper.queryByID(systemData1.getTemPlanID()).getName() + "(" + systemData1.getSettingTem() + "℃）" + humPlanDBHelper.queryByID(systemData1.getHumPlanID()).getName() + "(" + systemData1.getSettingHum() + "%)";
        }
        if (code == 3) {
            str = dataRecodeBean.getTime() + "    " + dataRecodeBean.getSettingHum() + "    " + dataRecodeBean.getRealtimeHum();
            fileName = humPlanDBHelper.queryByID(systemData1.getHumPlanID()).getName() + "(" + systemData1.getSettingHum() + "%）";
        } else {
            return;
        }

        write6("datarecord", str+"\n", fileName);

    }


    //-----------------------------------------------------------------------------------------------------------------------------------------获取下一个温度点
    public int getNextTem() {
        SystemData systemData=systemDBHelper.getSystemData();
        //1.得到当前正在执行的方案id
        int temID = systemData.getTemPlanID();
        //2.获取该方案的温度点个数
        //2.1获取当前正在执行的方案对象
        TemPlanBean temPlanBean = temPlanDBHelper.queryByID(temID);
        int temPoints = temPlanBean.getTemPoints();
        //3.判断当前正在执行的温度点是否等于该方案的温度点个数（是否执行到最后一个）
        int executingTem = systemData.getExecutingTemID();
        if (executingTem < temPoints) {
            if (executingTem == 1) {
                return temPlanBean.getTem2();
            } else if (executingTem == 2) {
                return temPlanBean.getTem3();
            } else if (executingTem == 3) {
                return temPlanBean.getTem4();
            } else if (executingTem == 4) {
                return temPlanBean.getTem5();
            } else if (executingTem == 5) {
                return temPlanBean.getTem6();
            } else if (executingTem == 6) {
                return temPlanBean.getTem7();
            } else if (executingTem == 7) {
                return temPlanBean.getTem8();
            } else if (executingTem == 8) {
                return temPlanBean.getTem9();
            } else if (executingTem == 9) {
                return temPlanBean.getTem10();
            } else {
                return -10000;
            }
        } else {
            return -10000;
        }

    }

    //-----------------------------------------------------------------------------------------------------------------------------------------获取下一个湿度点
    public int getNextHum() {
        SystemData systemData=systemDBHelper.getSystemData();
        int humID = systemData.getHumPlanID();
        HumPlanBean humPlanBean = humPlanDBHelper.queryByID(humID);
        int humPoints = humPlanBean.getHumPoints();
        int executingHum = systemData.getExecutingHumID();
        if (executingHum < humPoints) {
            if (executingHum == 1) {
                return humPlanBean.getHum2();
            } else if (executingHum == 2) {
                return humPlanBean.getHum3();
            } else if (executingHum == 3) {
                return humPlanBean.getHum4();
            } else if (executingHum == 4) {
                return humPlanBean.getHum5();
            } else if (executingHum == 5) {
                return humPlanBean.getHum6();
            } else if (executingHum == 6) {
                return humPlanBean.getHum7();
            } else if (executingHum == 7) {
                return humPlanBean.getHum8();
            } else if (executingHum == 8) {
                return humPlanBean.getHum9();
            } else if (executingHum == 9) {
                return humPlanBean.getHum10();
            } else {
                return -10000;
            }
        } else {
            return -10000;
        }
    }


    //---------------------------------------------------------------------------------------------------------------------------------------------取绝对值方法
    public double getAbs(double a, double b) {
        if (a - b < 0) {
            return b - a;
        } else {
            return a - b;
        }
    }


    //灯泡开关
    public void setLightOn(boolean isClose) {
        SystemData systemData=systemDBHelper.getSystemData();
        Date date = new Date();
        Intent intent = new Intent(LIGHTONACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,1);

        if (isClose) {
            alarmManager.cancel(pendingIntent);
        } else {

            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (systemData.getLightKeepSecond() * 1000), pendingIntent);
        }


    }


    //-----------------------------------------------------------------------------------------------------------------------------------------------读运行值
    public Thread realTimeThread = new Thread(new Runnable() {

        public void run() {

            while (true) {
                SystemClock.sleep(1000);



                //获取温湿度实时数据
                    try {
                        modbusRtuMaster.readCoils(1,0000,8);
                        modbusRtuMaster.readHoldingRegisters(1,0000,4);
                        modbusRtuMaster.readHoldingRegisters(1,0x000B,12);
                        modbusRtuMaster.readHoldingRegisters(1,0x001F,8);
                        modbusRtuMaster.readHoldingRegisters(1,0x00C8,2);
//                        System.out.println("发送信息...");
//                        modbusRtuMaster.readHoldingRegisters(2,0000,2);

                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }

                  //  getPower();
                 //   getOnOrOff();

            }
        }
    });



    public Thread warnThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!isFalse) {
                SystemClock.sleep(10000);

                try {
                    modbusRtuMaster.readCoils(1,0xF7F2,16);
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }


            }
        }
    });

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {


            View view1 = View.inflate(MainActivity.this, R.layout.warning, null);

            TextView textViewReason = view1.findViewById(R.id.warningReason);
            TextView textViewTime = view1.findViewById(R.id.warningTime);
            if (msg.what == 1) {
                textViewReason.setText("液位报警");
            }
            if (msg.what == 2) {
                textViewReason.setText("超温报警");
            }

            textViewTime.setText(getDateTimeToString(new Date()));
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(view1)
                    .setCancelable(false);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


            btn_tem.setText("温度启动");
            realTimeThread.interrupt();
            btn_hum.setText("湿度启动");
            SystemData systemData1 = systemDBHelper.getSystemData();
            systemData1.setTemOnOrOff(0);
            systemData1.setHumOnOrOff(0);
            systemDBHelper.updateSystemData(systemData1);
            try {
                modbusRtuMaster.writeSingleCoil(1,0000,false);
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }


            try {
                modbusRtuMaster.writeSingleCoil(1,0001,false);
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }

            isFalse = true;
            warnThread.interrupt();


            super.handleMessage(msg);
        }
    };



    //----------------------------------------------------------------------------------------------------------------------------------------------------------数据处理
    private Handler dataHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            SystemData systemData=systemDBHelper.getSystemData();
            ComBean comBean=(ComBean) msg.obj;
            String result= ModbusUtil.bytesToHex(comBean.bRec,comBean.bRec.length);
            int number1=result.indexOf("010308");//温湿度实时值
            int number2=result.indexOf("010318");//温湿度功率
            int number3=result.indexOf("010102");//报警信息
            int number4=result.indexOf("010101");//开关状态
            int number5=result.indexOf("020304");//标准器读数
            int number6=result.indexOf("010310");//温湿度设定值
            int number7=result.indexOf("010304");//压缩机保护延时
            //System.out.println("标准器读数..."+number5+"    "+result);
            if(number5!=-1 && result.length()>=number5+18){
                String standardTem=String.valueOf(modbusUtil.covert(result.substring(6,10))/100.00);
                String standardHum=String.valueOf(modbusUtil.covert(result.substring(10,14))/100.00);
                systemData.setStandardTem(standardTem);
                systemData.setStandardHum(standardHum);
            }
            if(number6!=-1 &&result.length()>=number6+28){
                float settingTem=modbusUtil.sixteentofloat(result,number6+6,4);

                if(settingTem!=systemData.getSettingTem()){
                   writeSettingValue(1,systemData.getSettingTem());
                }
                float settingHum=modbusUtil.sixteentofloat(result,number6+14,4);

                System.out.println("比较..."+"平板设定温度:"+systemData.getSettingTem()+"  机器设定温度:"+settingTem+"  平板设定湿度:"+systemData.getSettingHum()+"   机器返回湿度:"+settingHum);
                if(settingHum!=systemData.getSettingHum()){
                    writeSettingValue(2,systemData.getSettingHum());
                }

            }






            if(number1!=-1 && result.length()>=number1+26){
                float floatTem=modbusUtil.sixteentofloat(result,number1+14,4);

                DataRecodeBean dataRecodeBean=new DataRecodeBean();
                if(systemData==null){
                    return;
                }


                if(floatTem>-21 && floatTem<101){

//                    //测试时修正
//                    floatTem=floatTem+0.16f;

                    //保留两位小数
                    DecimalFormat decimalFormat=new DecimalFormat("#.00");
                    floatTem=Float.valueOf( decimalFormat.format(floatTem));


                    if(systemData.getTemOnOrOff()==1){

                        mTemView.setProgress(floatTem,"tem");
                        dataRecodeBean.setRealtimeTem(floatTem);

                    }if(systemData.getTemOnOrOff()==0){
                        mTemView.setProgress(0,"tem");
                    }

                }



                float floatHum=modbusUtil.sixteentofloat(result,number1+6,4);


                if(floatHum>=0 && floatHum<101){

//                    //测试时修正
//                    floatHum=floatHum+0.59f;

                    //保留两位小数
                    DecimalFormat decimalFormat=new DecimalFormat("#.00");
                    floatHum=Float.valueOf( decimalFormat.format(floatHum));


                    if(systemData.getHumOnOrOff()==1){
                        mHumView.setProgress(floatHum,"hum");
                        dataRecodeBean.setRealtimeHum(floatHum);

                    }if(systemData.getHumOnOrOff()==0){
                        mHumView.setProgress(0,"hum");
                    }



                }





                dataRecodeBean.setSettingTem(systemData.getSettingTem());
                dataRecodeBean.setSettingHum(systemData.getSettingHum());
                dataRecodeBean.setTime(getDateTimeToString(new Date()));

                System.out.println("************main---1208---"+dataRecodeBean);
                dataRecordDBHelper.add(dataRecodeBean);

                int executingTime=(int)getDatePoor();


                List<DataRecodeBean> list=new ArrayList<>();
                list=dataRecordDBHelper.query20DataRecodeBean("6");
                if(list!=null){
                    if(list.size()>=6){
                        if(getAbs(floatTem,list.get(5).getRealtimeTem())<=1 ){

                            changeDataDBHelper.add(executingTime,floatTem,0);
                        }
                        if(getAbs(floatHum,list.get(5).getRealtimeHum())<=1){
                            changeDataDBHelper.add(executingTime,floatHum,1);
                        }
                    }
                }




//                if(getAbs(floatTem,systemData.getSettingTem())<=0.5){
//
//                    changeDataDBHelper.add(executingTime,floatTem,0);
//                }
//                if(getAbs(floatHum,systemData.getSettingHum())<=0.5){
//
//                    changeDataDBHelper.add(executingTime,floatHum,1);
//                }


//                            判断是否达到稳定状态


            }


            //为避免在某一时间点出现温湿度值为空的情况,将上一秒的温湿度作为该时间点的温湿度保存
            if(number1==-1){
                //1.上一秒有数据
               //1)取上一秒的时间
                String time= getNextTime(new Date(),-1);
                DataRecodeBean dataRecodeBean = dataRecordDBHelper.queryByTime(time);
                if(dataRecodeBean!=null){
                   DataRecodeBean dataRecodeBean1= new DataRecodeBean();
                   float realtimeTem,realtimeHum;
                   if(dataRecodeBean.getRealtimeTem()==0){
                       realtimeTem=0;
                   }else{
                       realtimeTem=dataRecodeBean.getRealtimeTem()+0.01f;
                   }
                    if(dataRecodeBean.getRealtimeHum()==0){
                        realtimeHum=0;
                    }else{
                        realtimeHum=dataRecodeBean.getRealtimeHum()-0.01f;
                    }

                    DecimalFormat decimalFormat=new DecimalFormat("#.00");
                    realtimeHum=Float.valueOf( decimalFormat.format(realtimeHum));
                    realtimeTem=Float.valueOf( decimalFormat.format(realtimeTem));
                    dataRecodeBean1.setRealtimeHum(realtimeHum);
                   dataRecodeBean1.setRealtimeTem(realtimeTem);
                   dataRecodeBean1.setSettingTem(systemData.getSettingTem());
                   dataRecodeBean1.setSettingHum(systemData.getSettingHum());
                   dataRecodeBean1.setTime(getDateTimeToString(new Date()));
                   dataRecordDBHelper.add(dataRecodeBean1);


                    mHumView.setProgress(realtimeHum,"hum");
                    mTemView.setProgress(realtimeTem,"tem");
                }


            }



            if(number2!=-1 && result.length()>=number2+58){


                temPower=modbusUtil.sixteentofloat(result,number2+22,4);
                humPower=modbusUtil.sixteentofloat(result,number2+46,4);
                systemData.setTemPower(String.valueOf(temPower));
                systemData.setHumPower(String.valueOf(humPower));

               // systemDBHelper.updateSystemData(systemData);


            }if(number3!=-1){
                if(result.length()>=number3+10){
                    String s=result.substring(number3+8, number3+10);

                    System.out.println("报警信息..."+s+"    "+result+ "    "+number3);
                    Message message=new Message();
                    if(s.equals("02")){
                        message.what=1;
                        handler.sendMessage(message);
                    }
                    if(s.equals("01") || s.equals("03")){
                        message.what=2;
                        handler.sendMessage(message);
                    }
                }

            }if(number4!=-1 && result.length()>=number4+12){
                String s=result.substring(number4+6,number4+8);
                String crc=result.substring(number4+8,number4+12);

                if(!s.equals("00") && !s.equals("01") && !s.equals("02") && !s.equals("03") && !s.equals("05") && !s.equals("07") && !s.equals("0A") && !s.equals("0B")){
                    getOnOrOff();
                }

                System.out.println("开关状态..."+result);

                if(s.equals("00") && crc.equals("5188")){
                    systemData.setTemOnOrOff(0);
                    systemData.setHumOnOrOff(0);
                    btn_tem.setText("温度启动");
                    btn_hum.setText("湿度启动");
                }
                if(s.equals("01") && crc.equals("9048")){
                    systemData.setTemOnOrOff(1);
                    systemData.setHumOnOrOff(0);
                    btn_tem.setText("温度停止");
                    btn_hum.setText("湿度启动");
                }if(s.equals("02") && crc.equals("D049")){
                    systemData.setTemOnOrOff(0);
                    systemData.setHumOnOrOff(1);
                    btn_tem.setText("温度启动");
                    btn_hum.setText("湿度停止");
                }if(s.equals("03")&& crc.equals("1189")){
                    systemData.setTemOnOrOff(1);
                    systemData.setHumOnOrOff(1);
                    btn_tem.setText("温度停止");
                    btn_hum.setText("湿度停止");
                }if(s.equals("05") && crc.equals("918B")){//温度+温度整定
                    systemData.setTemOnOrOff(1);
                    systemData.setHumOnOrOff(0);

                    btn_tem.setText("温度停止");
                    btn_hum.setText("湿度启动");
                    btn_temzd.setText("整定停止");
                    btn_humzd.setText("整定启动");

                }if(s.equals("07") && crc.equals("104A")){// 温度+温度整定+湿度

                    systemData.setTemOnOrOff(1);
                    systemData.setHumOnOrOff(1);

                    btn_tem.setText("温度停止");
                    btn_hum.setText("湿度停止");
                    btn_temzd.setText("整定停止");
                    btn_humzd.setText("整定启动");
                }if(s.equals("0A") && crc.equals("D18F")){//湿度+湿度整定

                    systemData.setTemOnOrOff(0);
                    systemData.setHumOnOrOff(1);

                    btn_tem.setText("温度启动");
                    btn_hum.setText("湿度停止");
                    btn_temzd.setText("整定启动");
                    btn_humzd.setText("整定停止");
                }if (s.equals("0B") && crc.equals("104F")){//温度+湿度+湿度整定
                    systemData.setTemOnOrOff(1);
                    systemData.setHumOnOrOff(1);

                    btn_tem.setText("温度停止");
                    btn_hum.setText("湿度停止");
                    btn_temzd.setText("整定启动");
                    btn_humzd.setText("整定停止");
                }if(s.equals("0F") && crc.equals("118C")){//温度+湿度+温度整定+湿度整定
                    systemData.setTemOnOrOff(1);
                    systemData.setHumOnOrOff(1);

                    btn_tem.setText("温度停止");
                    btn_hum.setText("湿度停止");
                    btn_temzd.setText("整定停止");
                    btn_humzd.setText("整定停止");
                }



                //onStart();
                //return;
            }if(number7!=-1){
                if(result.length()>=number7+18){
                    int temProtectTime=modbusUtil.covert(result.substring(number7+6, number7+10));
                    int humProtectTime= modbusUtil.covert(result.substring(number7+10,number7+14));
                    systemData.setTemProtectTime(temProtectTime);
                    systemData.setHumProtectTime(humProtectTime);
                }

            }
            systemDBHelper.updateSystemData(systemData);


            int  i=   isStable();
            if(i<4){
                Message temMessage = new Message();
                temMessage.what=i;
                temMessage.obj=dataRecodeBean;
                reachstabilityHandler.sendMessage(temMessage);
            }


          return;


        }
    };







    private Handler reachstabilityHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {


            SystemData systemData1=systemDBHelper.getSystemData();
            //System.out.println("达到稳定状态时的读取..."+systemData1);
            TemPlanBean temPlanBean;
            HumPlanBean humPlanBean;

            View view1 = View.inflate(MainActivity.this, R.layout.reachstability, null);

            TextView temPlanName=view1.findViewById(R.id.temPlanName);
            TextView settingTem=view1.findViewById(R.id.settingTem);
            TextView unitTimeAndTemWave=view1.findViewById(R.id.unitTimeAndTemWave);
            TextView realtimeTem=view1.findViewById(R.id.realtimeTem);
            TextView nextTem=view1.findViewById(R.id.nextTem);
            TextView isNextTem=view1.findViewById(R.id.isNextTem);


            TextView humPlanName=view1.findViewById(R.id.humPlanName);
            TextView settingHum=view1.findViewById(R.id.settingHum);
            TextView unitTimeAndHumWave=view1.findViewById(R.id.unitTimeAndHumWave);
            TextView realtimeHum=view1.findViewById(R.id.realtimeHum);
            TextView nextHum=view1.findViewById(R.id.nextHum);
            TextView isNextHum=view1.findViewById(R.id.isNextHum);

            ImageView imageView1=view1.findViewById(R.id.image1);
            ImageView imageView2=view1.findViewById(R.id.image2);


            Button temButton=view1.findViewById(R.id.buttonTem);
            Button humButton=view1.findViewById(R.id.buttonHum);


            String nextTemMessage,nextHumMessage;
            if(getNextTem()==-10000){
                nextTemMessage="无可以继续执行的温度设定点";
                isNextTem.setVisibility(View.GONE);
                temButton.setVisibility(View.GONE);
            }else{
                nextTemMessage=getNextTem()+"℃";
            }

            if(getNextHum()==-10000){
                isNextHum.setVisibility(View.GONE);
                nextHumMessage="无可以继续执行的湿度设定点";
                humButton.setVisibility(View.GONE);
            }else{
                nextHumMessage=getNextHum()+"%RH";
            }




            //1代表温度稳定 湿度不稳定   2代表温度湿度都稳定  3代表湿度稳定 温度不稳定 4代表温湿度都不稳定

            if (msg.what == 1) {
                temPlanBean=temPlanDBHelper.queryByID(systemData1.getTemPlanID());
                imageView2.setVisibility(View.GONE);
                temPlanName.setText(temPlanBean.getName());
                settingTem.setText(systemData1.getSettingTem()+"");
                unitTimeAndTemWave.setText(temPlanBean.getTemWave()+"℃/"+temPlanBean.getUnitTime()+"min");
                realtimeTem.setText(systemData1.getTemChange());





            }
            if (msg.what == 2) {
                temPlanBean=temPlanDBHelper.queryByID(systemData1.getTemPlanID());
                temPlanName.setText(temPlanBean.getName());
                settingTem.setText(systemData1.getSettingTem()+"");
                unitTimeAndTemWave.setText(temPlanBean.getTemWave()+"℃/"+temPlanBean.getUnitTime()+"min");
                realtimeTem.setText(systemData1.getTemChange());
                nextTem.setText(nextTemMessage);

                humPlanBean=humPlanDBHelper.queryByID(systemData1.getHumPlanID());
                humPlanName.setText(humPlanBean.getName());
                settingHum.setText(systemData1.getSettingHum()+"");
                unitTimeAndHumWave.setText(humPlanBean.getHumWave()+"%/"+humPlanBean.getUnitTime()+"min");
                realtimeHum.setText(systemData1.getHumChange());


            }
            if(msg.what==3){
                imageView1.setVisibility(View.GONE);
                humPlanBean=humPlanDBHelper.queryByID(systemData1.getHumPlanID());
                humPlanName.setText(humPlanBean.getName());
                settingHum.setText(systemData1.getSettingHum()+"");
                unitTimeAndHumWave.setText(humPlanBean.getHumWave()+"℃/"+humPlanBean.getUnitTime()+"min");
                realtimeHum.setText(systemData1.getHumChange());




            }
            if(msg.what==4){
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);


            }




            nextTem.setText(nextTemMessage);
            nextHum.setText(nextHumMessage);


            writeDataToFile(msg.what,(DataRecodeBean)msg.obj);




            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(view1)
                    .setCancelable(false)
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            onStart();

                        }
                    });


            if(alertDialog==null){
                alertDialog = builder.create();
            }
            alertDialog.show();





            temButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   if( executingNextTemOrHum(1,getNextTem())){
                       alertDialog.cancel();
                       onStart();
                       alertDialog=null;
                   }



                }
            });


            humButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if( executingNextTemOrHum(2,getNextHum())){
                        alertDialog.cancel();
                        onStart();
                        alertDialog=null;
                    }


                }
            });


            super.handleMessage(msg);
        }
    };





    //判断是否稳定

    /**
     * @return 1代表温度稳定 湿度不稳定   2代表温度湿度都稳定  3代表湿度稳定 温度不稳定 4代表温湿度都不稳定
     */
    public int isStable() {
       ChangeDataDBHelper changeDataDBHelper1=ChangeDataDBHelper.getInstance(MainActivity.this);
       SystemDBHelper systemDBHelper1=SystemDBHelper.getInstance(MainActivity.this);
        boolean temIsStable = false;
        boolean humIsStable = false;
        SystemData systemData1 = systemDBHelper1.getSystemData();

       // System.out.println("判断稳定时:"+systemData1);

        int temPlanID = systemData1.getTemPlanID();
        if (temPlanID == 0) {

            float temMax = changeDataDBHelper1.getAllChangeData("temMax", 5);
            float temMin = changeDataDBHelper1.getAllChangeData("temMin", 5);




            if (temMax - temMin <= 0.2 &&  getDatePoor()>=5  && temMax-temMin>0  && getAbs(temMax,systemData1.getSettingTem())<=0.5 && getAbs(temMin,systemData1.getSettingTem())<=0.5) {
                temIsStable = true;
            }


           // System.out.println("写入时方案编号为:0,值为:"+(temMax-temMin)+"℃/5min");
            DecimalFormat decimalFormat=new DecimalFormat("#0.00");
            float temChange=temMax-temMin;
            systemData1.setTemChange(decimalFormat.format(temChange)+"℃/5min");



        } else {
            TemPlanBean temPlanBean = temPlanDBHelper.queryByID(temPlanID);
            int temTime = temPlanBean.getUnitTime();
            float temWave = temPlanBean.getTemWave();


            float temMax = changeDataDBHelper1.getAllChangeData("temMax", temTime);
            float temMin = changeDataDBHelper1.getAllChangeData("temMin", temTime);
            if (temMax - temMin <= temWave  &&  getDatePoor()>=temTime && temMax-temMin>0  && getAbs(temMax,systemData1.getSettingTem())<=0.5 && getAbs(temMin,systemData1.getSettingTem())<=0.5) {
                temIsStable = true;
            }


            //System.out.println("写入时方案编号为:"+temPlanID+",值为:"+(temMax-temMin)+"℃/"+temTime+"min");
            DecimalFormat decimalFormat=new DecimalFormat("#0.00");
            float temChange=temMax-temMin;
            systemData1.setTemChange(decimalFormat.format(temChange)+"℃/"+temTime+"min");



        }


        int humPlanID = systemData1.getHumPlanID();
        if (humPlanID == 0) {
            float humMax = changeDataDBHelper1.getAllChangeData("humMax", 1);
            float humMin = changeDataDBHelper1.getAllChangeData("humMin", 1);
            if (humMax - humMin < 0.8 &&  getDatePoor()>=1 && humMax!=0 && humMin!=0 && humMax-humMin>0  && getAbs(humMax,systemData1.getSettingHum())<=0.5 && getAbs(humMin,systemData1.getSettingHum())<=0.5) {
                humIsStable = true;
            }

           // System.out.println("写入时方案编号为:0,值为:"+(humMax-humMin)+"%/min");
            DecimalFormat decimalFormat=new DecimalFormat("#.00");
            float humChange=humMax-humMin;
            systemData1.setHumChange(decimalFormat.format(humChange)+"%/min");


        } else {
            HumPlanBean humPlanBean = humPlanDBHelper.queryByID(humPlanID);
            int humTime = humPlanBean.getUnitTime();
            float humWave = humPlanBean.getHumWave();
            float humMax = changeDataDBHelper1.getAllChangeData("humMax", humTime);
            float humMin = changeDataDBHelper1.getAllChangeData("humMin", humTime);
            if (humMax - humMin <= humWave  &&  getDatePoor()>=humTime && humMax!=0 && humMin!=0 && humMax-humMin>0  && getAbs(humMax,systemData1.getSettingHum())<=0.5 && getAbs(humMin,systemData1.getSettingHum())<=0.5 ) {
                humIsStable = true;
            }

          //  System.out.println("写入时方案编号为:"+humPlanID+",值为:"+(humMax-humMin)+"%/"+humTime+"min");
            DecimalFormat decimalFormat=new DecimalFormat("#.00");
            float humChange=humMax-humMin;
            systemData1.setHumChange(decimalFormat.format(humChange)+"%/"+humTime+"min");

        }


        systemDBHelper1.updateSystemData(systemData1);



        if (systemData1.getTemOnOrOff() == 0 && systemData1.getHumOnOrOff() == 0) {
            return 4;
        }
        if (systemData1.getTemOnOrOff() == 1 && systemData1.getHumOnOrOff() == 0) {

            if (temIsStable) {
                return 1;
            } else {
                return 4;
            }
        }
        if (systemData1.getTemOnOrOff() == 1 && systemData1.getHumOnOrOff() == 1) {
            if (temIsStable && humIsStable) {
                return 2;
            } else {
                return 4;
            }
        }
        if (systemData1.getTemOnOrOff() == 0 && systemData1.getHumOnOrOff() == 1) {
            if (humIsStable) {
                return 3;
            } else {
                return 4;
            }
        }

        return 4;

    }


    /**
     * 写温湿度设定值
     * @param code 1温度 2湿度
     * @param settingValue
     */
    //-------------------------------------------------------------------------------------------------------------------------------------------------写设定温度/湿度值
    private void writeSettingValue(int code,float settingValue){

        getSerialHelper();
        String Hex2="";
        String Hex1="";
        if(code==1){
            Hex1="01 10 00 1F 00 02 04 ";
        }else if(code==2){
            Hex1="01 10 00 21 00 02 04 ";
        }

        if(settingValue==0){
            Hex2="00 00 00 00";
        }else{
            //设置温度值
            String value= Integer.toHexString(Float.floatToIntBits(Float.valueOf(settingValue)));
            Hex2=value.substring(4,6)+" "+value.substring(6,8)+" "+value.substring(0,2)+" "+value.substring(2,4);

        }



        String hex=Hex1+Hex2;
        int Icrc= CRC16.compute(modbusUtil.hex2Byte(hex));
        String SCrc =Integer.toHexString(Icrc);
        if(SCrc.length()<4){
            SCrc="0"+SCrc;
        }
        String FCrc= SCrc.substring(2,4)+SCrc.substring(0,2);
        serialHelper.sendHex(modbusUtil.deleteSpace(hex)+FCrc);



    }


    private void getSerialHelper(){
        if(serialHelper==null){
            serialHelper=new SerialHelper("/dev/ttyS0",9600) {
                @Override
                protected void onDataReceived(ComBean comBean) {
                    Message message=dataHandler.obtainMessage();
                    message.obj=comBean;
                    dataHandler.sendMessage(message);
                }
            };
        }
        if(!serialHelper.isOpen()){
            try {
                serialHelper.open();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        modbusRtuMaster=new ModbusRtuMaster(serialHelper);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.wd:
                //dateClick();
                settings(1,(FlashView) view);
                break;

            case R.id.sd:
               // dateClick();
                settings(2,(FlashView)view);
                break;
            case R.id.main_light:
                lightClick();
                break;
            case R.id.mian_btn1:
                method(1,btn_tem);
                break;

            case R.id.mian_btn2:
                method(2,btn_hum);
                break;


            case R.id.main_btn_1:
                zdOnOrOff(3,btn_temzd);
                break;


            case R.id.main_btn2:
                zdOnOrOff(4,btn_humzd);
                break;
        }
    }



//---------------------------------------------------------------------------------------------------------------------------------------------点击展示详细数据
    private void dateClick(){

        View view1 = View.inflate(MainActivity.this, R.layout.maindatashow, null);
        TextView textView1 = view1.findViewById(R.id.temP);
        TextView textView2 = view1.findViewById(R.id.humP);


        //获取正在执行的温湿度方案
        SystemData systemDataOnClick = systemDBHelper.getSystemData();
        int temPlanID = systemDataOnClick.getTemPlanID();
        int humPlanID = systemDataOnClick.getHumPlanID();

        if(systemDataOnClick.getTemPower()==""){
            textView1.setText("暂无数据");
        }else{
            textView1.setText(systemDataOnClick.getTemPower());
        }
        if(systemDataOnClick.getHumPower()==""){
            textView2.setText("暂无数据");
        }else{
            textView2.setText(systemDataOnClick.getHumPower());
        }






        //获取温湿度方案的稳定范围的单位时间
        int temPlanUnitTime, humPlanUnitTime;
        if (temPlanID <= 4) {
            temPlanUnitTime = 10;
            humPlanUnitTime = 10;
        } else {
            temPlanUnitTime = temPlanDBHelper.queryByID(temPlanID).getUnitTime();
            humPlanUnitTime = humPlanDBHelper.queryByID(humPlanID).getUnitTime();
        }

        TextView textView3 = view1.findViewById(R.id.text_temPlanTime);
        TextView textView4 = view1.findViewById(R.id.text_HumPlanTime);
        textView3.setText(String.valueOf(temPlanUnitTime));
        textView4.setText(String.valueOf(humPlanUnitTime));


        TextView textView5 = view1.findViewById(R.id.temChangeOne);
        TextView textView6 = view1.findViewById(R.id.humChangeOne);
        TextView textView7 = view1.findViewById(R.id.temChangeTen);
        TextView textView8 = view1.findViewById(R.id.humChangeTen);

        //计算变化速率每分钟
        /**
         * 获取数据库中最新一条数据（最近一分钟时间内的信息）
         */
        int executingTime = (int) getDatePoor();
        ChangeDataDBHelper changeDataDBHelper=ChangeDataDBHelper.getInstance(MainActivity.this);
        List<Float> floatList = changeDataDBHelper.getNewChangeData();


        System.out.println("展示详细数据..."+floatList);

        if (floatList == null || floatList.size() <= 0) {
            textView5.setText("暂无数据");
            textView6.setText("暂无数据");
        } else {
            float temChangePerMinute = floatList.get(0) - floatList.get(1);
            float humChangePerMinute = floatList.get(2) - floatList.get(3);
            textView5.setText(floatToString(temChangePerMinute) + "℃/min");
            textView6.setText(floatToString(humChangePerMinute) + "%RH/min");
        }

        //计算单位时间内的变化率


        if (temPlanUnitTime > executingTime) {//稳定时间10分钟，当前仅进行到8分钟
            textView7.setText("暂无数据");
            textView8.setText("暂无数据");


        } else {


            float temMax = changeDataDBHelper.getAllChangeData("temMax", temPlanUnitTime);
            float temMin = changeDataDBHelper.getAllChangeData("temMin", temPlanUnitTime);
            float humMax = changeDataDBHelper.getAllChangeData("humMax", humPlanUnitTime);
            float humMin = changeDataDBHelper.getAllChangeData("humMin", humPlanUnitTime);


            textView7.setText(floatToString((temMax - temMin) / temPlanUnitTime) + "℃/" + temPlanUnitTime + "min");
            textView8.setText(floatToString((humMax - humMin) / humPlanUnitTime) + "%RH/" + humPlanUnitTime + "min");
        }


        //标准器相关内容
        TextView textView9 = view1.findViewById(R.id.text_standardTem);
        textView9.setText(systemDataOnClick.getStandardTem()+"  ℃");
        TextView textView10 = view1.findViewById(R.id.text_standardHum);
        textView10.setText(systemDataOnClick.getStandardHum()+"  %RH");



        //压缩机保护相关内容
        TextView textView11=view1.findViewById(R.id.temProtectTime);
        textView11.setText(systemDataOnClick.getTemProtectTime()+"  s");
        TextView textView12=view1.findViewById(R.id.humProtectTime);
        textView12.setText(systemDataOnClick.getHumProtectTime()+"  s");


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("详细数据：")
                .setView(view1);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void lightClick(){
        SystemData OnclickSystemdata=systemDBHelper.getSystemData();
        if (systemDBHelper.getSwitch("7")) {//开-----》关
            systemDBHelper.addSwitch("7", false);
            imageView1.setImageResource(R.drawable.lightoff);
            setLightOn(true);
            try {
                modbusRtuMaster.writeSingleCoil(1,0xFC07,false);
                modbusRtuMaster.writeSingleCoil(1,0xFC07,false);
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }


        } else {//关-----》开



            systemDBHelper.addSwitch("7", true);
            imageView1.setImageResource(R.drawable.lighton);
            OnclickSystemdata.setLightStartTime(new Date());
            systemDBHelper.updateSystemData(OnclickSystemdata);
            setLightOn(false);

            try {
                modbusRtuMaster.writeSingleCoil(1,0xFC07,true);
                modbusRtuMaster.writeSingleCoil(1,0xFC07,true);
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }

        }
    }
    private void writeOnOrOff(int code,boolean onOrOff){

       getSerialHelper();
        if(code==1){
            try {
                modbusRtuMaster.writeSingleCoil(1,0000,onOrOff);
                modbusRtuMaster.writeSingleCoil(1,0000,onOrOff);
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }


        }else if(code==2){
            try {
                modbusRtuMaster.writeSingleCoil(1,0001,onOrOff);
                modbusRtuMaster.writeSingleCoil(1,0001,onOrOff);
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }
        }else if(code==3){
            try {
                modbusRtuMaster.writeSingleCoil(1,0002,onOrOff);
                modbusRtuMaster.writeSingleCoil(1,0002,onOrOff);
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }
        }else if(code==4){
            try {
                modbusRtuMaster.writeSingleCoil(1,0003,onOrOff);
                modbusRtuMaster.writeSingleCoil(1,0003,onOrOff);
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }
        }

    }
    private void method(int code,Button button){
        SystemData systemData1 = systemDBHelper.getSystemData();
        String type="";//tem 或者 hum
        String name="";//温度 或者 湿度
        FlashView flashView=null;//需要操作的flashview
        int isCheck=0;//选中的预设方案
        int settingValue=0;//自定义值
        int afterPlanID=0;
        int settingValueWithPlan=0;
        switch(code){ //先判断温湿度
            case 1:
                type="tem";
                name="温度";
                flashView=mTemView;
                isCheck= (int)spinner1.getSelectedItemId()+1;
                settingValue=systemData1.getSettingTem();
                break;
            case 2:
                type="hum";
                name="湿度";
                flashView=mHumView;
                isCheck=(int) spinner2.getSelectedItemId()+1;
                settingValue=systemData1.getSettingHum();
                break;
        }


        switch (button.getText().toString().indexOf("启动")){


            case -1://停止


                if(code==1){
                    systemData1.setTemOnOrOff(0);
                    writeOnOrOff(code,false);
                }else if(code==2){
                    systemData1.setHumOnOrOff(0);
                    writeOnOrOff(code,false);
                }


                button.setText(name+"启动");
                systemDBHelper.updateSystemData(systemData1);
                if(btn_hum.getText().toString()=="湿度启动" && btn_tem.getText().toString()=="温度启动"){
                    warnThread.interrupt();
                    serialHelper.close();
                    serialHelper=null;
               }
               return;
            case 2://启动
                if(isCheck==1){//自定义启动
                    if(settingValue==0){//未设置自定义值
                        showToast(MainActivity.this, "请先选择" + name + "启动方案或手动设置" + name);
                        return;
                    }else{//设置了自定义值

                        writeOnOrOff(code,true);
                        flashView.setValue(settingValue, type);
                       // writeSettingValue(code, settingValue);

                        if(code==1){
                            systemData1.setTemOnOrOff(1);
                        }else if(code==2){
                            systemData1.setHumOnOrOff(1);
                        }




                    }
                }else{//按方案启动
                    switch (code){
                        case 1:
//                            settingValueWithPlan=temPlanDBHelper.queryByID(Integer.valueOf(String.valueOf(isCheck)), 1);
//                            afterPlanID=  systemData1.getTemPlanID();
//                            systemData1.setTemPlanID(Integer.valueOf(String.valueOf(isCheck)));
//                            temPlanDBHelper.updateCheck(Integer.valueOf(String.valueOf(isCheck)), afterPlanID);
//                            if (systemData1.getExecutingTemID() == 0 || systemData1.getExecutingTemID() == 1) {
//                                systemData1.setExecutingTemID(1);
//                            }

                            //更新温湿度是否启动
                            systemData1.setTemOnOrOff(1);

                            break;
                        case 2:
//                            settingValueWithPlan=humPlanDBHelper.queryByID(Integer.valueOf(String.valueOf(isCheck)), 1);
//                            afterPlanID=  systemData1.getHumPlanID();
//                            systemData1.setHumPlanID(Integer.valueOf(String.valueOf(isCheck)));
//                            humPlanDBHelper.updateCheck(Integer.valueOf(String.valueOf(isCheck)), afterPlanID);
//
//
//                            if (systemData1.getExecutingHumID() == 0 || systemData1.getExecutingHumID() == 1) {
//                                systemData1.setExecutingHumID(1);
//                            }
                            //更新温湿度是否启动
                            systemData1.setHumOnOrOff(1);

                            break;

                    }

                    writeOnOrOff(code,true);


                }

                //更新开启时间(为了保证曲线图的连续稳定的展示,必须设置为无论温度或湿度哪一个重新启动都会更新启动时间)
                    if(systemData1.getStartTime()==null){
                        systemData1.setStartTime(getDateTimeToString(new Date()));
                    }




                button.setText(name+"停止");
                systemDBHelper.updateSystemData(systemData1);




             return;

        }













    }

    private void settings(int code,FlashView flashView){
        String name="";
        String type="";
        if(code==1){
            name="温度";
            type="tem";
        }else if(code==2){
            name="湿度";
            type="hum";
        }
        SystemData systemData1=systemDBHelper.getSystemData();
        View view1 = View.inflate(MainActivity.this, R.layout.setview_edit, null);
        EditText editText = view1.findViewById(R.id.setview_edit);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        String finalType = type;
        builder.setTitle("请输入"+name+"设定值")
                .setView(view1)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(editText.getText().toString().equals("")){
                            showToast(MainActivity.this,"设定值不能为空");
                            return;
                        }

                        flashView.setValue(Float.valueOf(editText.getText().toString()), finalType);

                        if(code==1){
                            systemData1.setSettingTem(Integer.valueOf(editText.getText().toString()));
                            //如果当前设备正在运行,那么直接改变设定温度
                                writeSettingValue(code,Float.valueOf(editText.getText().toString()));
                                writeSettingValue(code,Float.valueOf(editText.getText().toString()));


                        }if(code==2){
                            systemData1.setSettingHum(Integer.valueOf(editText.getText().toString()));
                                writeSettingValue(code,Float.valueOf(editText.getText().toString()));
                                writeSettingValue(code,Float.valueOf(editText.getText().toString()));
                        }
                        systemDBHelper.updateSystemData(systemData1);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onLongClick(View view) {
        switch(view.getId()){
            case R.id.wd:
               // settings(1,(FlashView) view);
                dateClick();

                break;

            case R.id.sd:

                dateClick();
                break;
        }
        return true;
    }


    /**
     * 获取当前使用的标准器的类型
     * @return
     */
//    private StandardApparatus getState(int code){
//        String tableName="";
//        if(code==1){
//            tableName= "temstandardapparatus";
//        }if(code==2){
//           tableName= "humstandardapparatus";
//        }
//
//        StandardApparatusDBHelper standardApparatusDBHelper=StandardApparatusDBHelper.getInstance(MainActivity.this);
//        List<StandardApparatus> list=standardApparatusDBHelper.query(tableName,1);
//        if(list!=null && list.size()==1){
//          return list.get(0);
//        }
//            return null;
//    }




    public Thread temStandardApparatusThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                SystemClock.sleep(1000);

                ModbusRtuMaster modbusRtuMaster1=new ModbusRtuMaster(temSerialHelper);
                try {
                    modbusRtuMaster1.readHoldingRegisters(temSlave,Integer.valueOf(temAddress),count);
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }

            }
        }
    });

    public Thread humStandardApparatusThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                SystemClock.sleep(1000);
                ModbusRtuMaster modbusRtuMaster2=new ModbusRtuMaster(humSerialHelper);
                try {
                    modbusRtuMaster2.readHoldingRegisters(humSlave,Integer.valueOf(humAddress),count);
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
            }
        }
    });


    public void zdOnOrOff(int code,Button button){

       String text= button.getText().toString();
       String text1=text.substring(0,2);


        System.out.println("点击整定..."+text+"   "+text1);

       if (text.indexOf("启动")!=-1){  //停止-->开启
           button.setText(text1+"停止");
           writeOnOrOff(code,true);
       }else{
           button.setText(text1+"启动");
           writeOnOrOff(code,false);
       }


    }
}





