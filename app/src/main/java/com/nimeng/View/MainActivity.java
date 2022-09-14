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
import com.nimeng.util.SystemDBHelper;
import com.nimeng.util.TemPlanDBHelper;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android_serialport_api.SerialPort;
import tp.xmaihh.serialport.SerialHelper;
import tp.xmaihh.serialport.bean.ComBean;

public class MainActivity extends CommonUtil {

    public static final int REQUEST_CODE = 1024;
    public static final String DATABASE_NAME = "NIMENG.db";
    private static final int MIN_DISTANCE = 100;//最小滑动距离
    private GestureDetector gestureDetector;




    private FlashView mTemView;
    private FlashView mHumView;
    private Button btn_tem;
    private Button btn_hum;



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
    SerialHelper serialHelper;
    ModbusRtuMaster modbusRtuMaster;







    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        changeDataDBHelper =new ChangeDataDBHelper(MainActivity.this,"NIMENG.db",null,1);
        dataRecordDBHelper = new DataRecordDBHelper(MainActivity.this, "NIMENG.db", null, 1);



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
        imageViewLogo = findViewById(R.id.main_logo);
        spinner1 = (Spinner) findViewById(R.id.main_spinner1);
        spinner2 = (Spinner) findViewById(R.id.main_spinner2);

        btn_tem = findViewById(R.id.mian_btn1);
        btn_hum = findViewById(R.id.mian_btn2);

        imageView1 = findViewById(R.id.main_light);

        //----------------------------------------------------------------------------------------------------------------------------------------------------获取文件读写权限
        onPermission();

        temPlanDBHelper = new TemPlanDBHelper(MainActivity.this, "NIMENG.db", null, 1);
        humPlanDBHelper = new HumPlanDBHelper(MainActivity.this, "NIMENG.db", null, 1);

        systemDBHelper = new SystemDBHelper(MainActivity.this, "NIMENG.db", null, 1);
        SystemData primarySystemData = systemDBHelper.getSystemData();


        List<TemPlanBean> temPlanBeans = temPlanDBHelper.query();
        List<HumPlanBean> humPlanBeans = humPlanDBHelper.query();


//----------------------------------------------------------------------------------------------------------------------------------------------------添加温湿度点 和预设方案
        System.out.println("温度预设方案---" + temPlanBeans);
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

       SystemData systemData = systemDBHelper.getSystemData();


        if (!warnThread.isAlive()) {
            warnThread.start();
        }

        if(!realTimeThread.isAlive()){
            realTimeThread.start();
        }

        getOnOrOff();


        if (systemData.getTemOnOrOff() == 0) {
            btn_tem.setText("温度启动");
        } else {
            btn_tem.setText("温度停止");
        }
        if (systemData.getHumOnOrOff() == 0) {
            btn_hum.setText("湿度启动");
        } else {
            btn_hum.setText("湿度停止");
        }

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
            System.out.println("main---》当前处于第" + excutingNumber + "期");

            //获取当前是否已经匹配密码
            System.out.println("判断当前是否已经匹配密码" + systemDBHelper.getPassword().get(excutingNumber - 1).isMatchs());
            if (!systemDBHelper.getPassword().get(excutingNumber - 1).isMatchs()) {
                //获取已经错误的次数
                errorNumber = systemDBHelper.getPassword().get(excutingNumber - 1).getErrorNumbers();
                System.out.println("当前已经错误的次数" + errorNumber);
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


        if (systemData.getTemPlanID() == 1) {
            mTemView.setValue(systemData.getSettingTem(), "tem");
        } else {
            mTemView.setValue(temPlanDBHelper.queryByID(systemData.getTemPlanID(), systemData.getExecutingTemID()), "tem");
        }

        if (systemData.getHumPlanID() == 1) {
            mHumView.setValue(systemData.getSettingHum(), "hum");
        } else {
            mHumView.setValue(humPlanDBHelper.queryByID(systemData.getHumPlanID(), systemData.getExecutingHumID()), "hum");
        }


        //--------------------------------------------------------------------------------------------------------------------------------------------------------长按改变设定值
        mTemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                SystemData systemData1=systemDBHelper.getSystemData();


                if (systemData1.getTemPlanID() != 1 && systemData1.getTemPlanID() != 0) {
                    showToast(MainActivity.this, "手动设置温度时，请先关闭温度并将温度方案置为方案一（不设置）");
                    return false;
                }

                View view1 = View.inflate(MainActivity.this, R.layout.setview_edit, null);
                EditText editText = view1.findViewById(R.id.setview_edit);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请输入温度设定值")
                        .setView(view1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                /**
                                 * 模拟设定值
                                 */
                                mTemView.setValue(Float.valueOf(editText.getText().toString()), "tem");
                                systemData1.setSettingTem(Integer.valueOf(editText.getText().toString()));
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
                return true;
            }
        });
        mHumView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SystemData systemData1=systemDBHelper.getSystemData();
                if (systemData1.getHumPlanID() != 1 && systemData1.getHumPlanID() != 0) {
                    showToast(MainActivity.this, "手动设置湿度时，请先关闭湿度并将湿度方案置为方案一（不设置）");
                    return false;
                }
                View view1 = View.inflate(MainActivity.this, R.layout.setview_edit, null);
                EditText editText = view1.findViewById(R.id.setview_edit);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请输入湿度设定值")
                        .setView(view1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                mHumView.setValue(Float.valueOf(editText.getText().toString()), "hum");
                                systemData1.setSettingHum(Integer.valueOf(editText.getText().toString()));
                                // systemData.setTemPlanID(0);
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
                return true;
            }
        });




        ////----------------------------------------------------------------------------------------------------------------------------------------------------点击展示详细数据
        mTemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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





                System.out.println("温度方案：" + temPlanID + "  湿度方案：" + humPlanID);

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
                ChangeDataDBHelper changeDataDBHelper = new ChangeDataDBHelper(MainActivity.this, "NIMENG.db", null, 1);
                List<Float> floatList = changeDataDBHelper.getNewChangeData();


                System.out.println("最新数据。。。" + floatList);

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
                textView9.setText("20.0");
                TextView textView10 = view1.findViewById(R.id.text_standardHum);
                textView10.setText("40.0");


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("详细数据：")
                        .setView(view1);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });



        mHumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                ChangeDataDBHelper changeDataDBHelper = new ChangeDataDBHelper(MainActivity.this, "NIMENG.db", null, 1);
                List<Float> floatList = changeDataDBHelper.getNewChangeData();
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
                textView9.setText("20.0");
                TextView textView10 = view1.findViewById(R.id.text_standardHum);
                textView10.setText("40.0");


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("详细数据：")
                        .setView(view1);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });





        ////----------------------------------------------------------------------------------------------------------------------------------------------------删除7天前的数据
        DataRecordDBHelper dataRecordDBHelper1 = new DataRecordDBHelper(MainActivity.this, "NIMENG.db", null, 1);
        dataRecordDBHelper1.delete7DaysData();

        ////----------------------------------------------------------------------------------------------------------------------------------------------------删除30分钟前的数据
        ChangeDataDBHelper changeDataDBHelper = new ChangeDataDBHelper(MainActivity.this, "NIMENG.db", null, 1);
        changeDataDBHelper.delete30MinuteData(systemData.getStartTime());


        List<TemPlanBean> temPlanBeanList = temPlanDBHelper.query();
        System.out.println("温度-----" + temPlanBeanList);
        List<String> temPlanNameList = new ArrayList<>();
        ////----------------------------------------------------------------------------------------------------------------------------------------------------设置下拉框显示内容
        for (int i = 0; i < temPlanBeanList.size(); i++) {
            String temPlanName = temPlanBeanList.get(i).getName();
            temPlanNameList.add(temPlanName);

        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, temPlanNameList);


        List<HumPlanBean> humPlanBeanList = humPlanDBHelper.query();
        List<String> humPlanNameList = new ArrayList<>();
        for (int j = 0; j < humPlanBeanList.size(); j++) {
            String humPlanName = humPlanBeanList.get(j).getName();
            humPlanNameList.add(humPlanName);
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, humPlanNameList);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);
        if (systemData.getTemPlanID() >= 1) {
            spinner1.setSelection(systemData.getTemPlanID() - 1);
        }
        spinner2.setAdapter(adapter2);
        if (systemData.getHumPlanID() >= 1) {
            spinner2.setSelection(systemData.getHumPlanID() - 1);
        }


        ////----------------------------------------------------------------------------------------------------------------------------------------------------温湿度启动\停止按钮

        btn_tem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                long temIsCheck = spinner1.getSelectedItemId() + 1;

                int settingTem = temPlanDBHelper.queryByID(Integer.valueOf(String.valueOf(temIsCheck)), 1);



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


                if (btn_tem.getText().toString().equals("温度启动")) {//启动
                    SystemData systemData1 = systemDBHelper.getSystemData();
                    if (temIsCheck == 1) {
                        if (systemData1.getSettingTem() == 0) {
                            showToast(MainActivity.this, "请先选择温度启动方案或手动设置温度");
                            return;
                        }


                        mTemView.setValue(systemData1.getSettingTem(), "tem");
                        //设置温度值
                       String tem= Integer.toHexString(Float.floatToIntBits(Float.valueOf(systemData1.getSettingTem())));
                       String Hex2=tem.substring(4,6)+" "+tem.substring(6,8)+" "+tem.substring(0,2)+" "+tem.substring(2,4);

                       String Hex1="01 10 00 21 00 02 04 ";
                       String hex=Hex1+Hex2;
                       int Icrc= CRC16.compute(modbusUtil.hex2Byte(hex));
                       String SCrc =Integer.toHexString(Icrc);
                       if(SCrc.length()<4){
                           SCrc="0"+SCrc;
                       }
                       String FCrc= SCrc.substring(2,4)+SCrc.substring(0,2);
                       serialHelper.sendHex(modbusUtil.deleteSpace(hex)+FCrc);





                    } else {
                        mTemView.setValue(settingTem, "tem");
                        systemData1.setSettingTem(settingTem);


                    }
                    if (systemData1.getExecutingTemID() == 0 || systemData1.getExecutingTemID() == 1) {
                        systemData1.setExecutingTemID(1);
                    }

                    int afterTemPlanID = systemData1.getTemPlanID();
                    systemData1.setTemPlanID(Integer.valueOf(String.valueOf(temIsCheck)));

                    temPlanDBHelper.updateCheck(Integer.valueOf(String.valueOf(temIsCheck)), afterTemPlanID);

                    if (systemData1.getStartTime() == null) {
                        systemData1.setStartTime(new Date());
                    }
                    systemData1.setTemOnOrOff(1);
                    systemDBHelper.updateSystemData(systemData1);
                    //设置温度开关
//                    if(!warnThread.isAlive()){
//                        warnThread.start();
//                    }



                    try {
                        modbusRtuMaster.writeSingleCoil(1,0000,true);
                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }
                    btn_tem.setText("温度停止");
                } else {
                    btn_tem.setText("温度启动");
                    SystemData systemData1 = systemDBHelper.getSystemData();
                    systemData1.setTemOnOrOff(0);
                    systemDBHelper.updateSystemData(systemData1);


                    try {
                        modbusRtuMaster.writeSingleCoil(1,0000,false);
                        if(btn_hum.getText().toString()=="湿度启动"){
                            warnThread.interrupt();

                            serialHelper.close();
                            serialHelper=null;
                        }
                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }



                }
            }

        });
        btn_hum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long humIsCheck = spinner2.getSelectedItemId() + 1;
                int settingHum = humPlanDBHelper.queryByID(Integer.valueOf(String.valueOf(humIsCheck)), 1);


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





                if (btn_hum.getText().toString().equals("湿度启动")) {
                    SystemData systemData2 = systemDBHelper.getSystemData();
                    if (humIsCheck == 1) {
                        if (systemData2.getSettingHum() == 0) {
                            showToast(MainActivity.this, "请先选择湿度启动方案或手动设置湿度");
                            return;
                        }
                        mHumView.setValue(systemData2.getSettingHum(), "hum");
                        //设定湿度
                        String hum= Integer.toHexString(Float.floatToIntBits(Float.valueOf(systemData2.getSettingHum())));
                        String Hex2=hum.substring(4,6)+" "+hum.substring(6,8)+" "+hum.substring(0,2)+" "+hum.substring(2,4);

                        String Hex1="01 10 00 1F 00 02 04 ";
                        String hex=Hex1+Hex2;
                        int Icrc= CRC16.compute(modbusUtil.hex2Byte(hex));
                        String SCrc =Integer.toHexString(Icrc);
                        if(SCrc.length()<4){
                            SCrc="0"+SCrc;
                        }
                        String FCrc= SCrc.substring(2,4)+SCrc.substring(0,2);
                        serialHelper.sendHex(modbusUtil.deleteSpace(hex)+FCrc);






                    } else {
                        mHumView.setValue(settingHum, "hum");
                        systemData2.setSettingHum(settingHum);

                    }


                    int afterHumPlanID = systemData2.getHumPlanID();
                    if (systemData2.getExecutingHumID() == 0 || systemData2.getExecutingHumID() == 1) {
                        systemData2.setExecutingHumID(1);
                    }

                    systemData2.setHumPlanID(Integer.valueOf(String.valueOf(humIsCheck)));
                    humPlanDBHelper.updateCheck(Integer.valueOf(String.valueOf(humIsCheck)), afterHumPlanID);

                    systemData2.setHumOnOrOff(1);
                    if (systemData2.getStartTime() == null) {
                        systemData2.setStartTime(new Date());
                    }
                    systemDBHelper.updateSystemData(systemData2);




//                    if(!warnThread.isAlive()){
//                        warnThread.start();
//                    }

                    try {
                        modbusRtuMaster.writeSingleCoil(1,0001,true);
                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }


                    btn_hum.setText("湿度停止");
                } else {
                    btn_hum.setText("湿度启动");
                    SystemData systemData2 = systemDBHelper.getSystemData();
                    systemData2.setHumOnOrOff(0);
                    systemDBHelper.updateSystemData(systemData2);


                    try {
                        modbusRtuMaster.writeSingleCoil(1,0001,false);
                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }

                    if(btn_tem.getText().toString()=="温度启动"){
                        warnThread.interrupt();
                        serialHelper.close();
                        serialHelper=null;
                    }


                }

            }
        });


        //------------------------------------------------------------------------------------------------------------------------------------------------------------灯控开关

        String light = getIntent().getStringExtra("light");
        System.out.println("light-----------" + light);


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


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (systemDBHelper.getSwitch("7")) {//开-----》关
                    systemDBHelper.addSwitch("7", false);
                    imageView1.setImageResource(R.drawable.lightoff);
                    setLightOn(true);
                    try {
                        modbusRtuMaster.writeSingleCoil(1,0xFC07,true);
                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }


                } else {//关-----》开
                    systemDBHelper.addSwitch("7", true);
                    imageView1.setImageResource(R.drawable.lighton);
                    systemData.setLightStartTime(new Date());
                    systemDBHelper.updateSystemData(systemData);
                    setLightOn(false);

                    try {
                        modbusRtuMaster.writeSingleCoil(1,0xFC07,false);
                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }

                }
            }
        });


        super.onStart();
    }


    //---------------------------------------------------------------------------------------------------------------------------------------------获取温湿度功率
    private void getPower(){

        try {
            modbusRtuMaster.readHoldingRegisters(1,0x000F,8);
        } catch (ModbusError modbusError) {
            modbusError.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------获取开关状态
    private void getOnOrOff(){
        try {
            modbusRtuMaster.readCoils(1,0000,10);
        } catch (ModbusError modbusError) {
            modbusError.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
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


    //--------------------------------------------------------------------------------------------------------------------------------------------十六进制转浮点型

    /**
     *
     * @param s modbus报文
     * @param startPoint 开始位置
     * @param number     数量
     * @return
     */
    public float sixteentofloat(String s, int startPoint,int number) {

        float result = 0;
        String resultString = "";

        //第一步：删除字符串中的空格
        String h = s.replace(" ", "");

        //第二步：提取报文中有效信息
        String h1, q1;
        q1=h.substring(startPoint,startPoint+number);
        h1=h.substring(startPoint+number,startPoint+2*number);


        String s1 = h1 + q1;

        //第三步：将string类型的数据转化为float
        BigInteger bigInteger = new BigInteger(s1, 16);
        float f = Float.intBitsToFloat(bigInteger.intValue());
        BigDecimal bigDecimal = new BigDecimal(f);
        String t = bigDecimal.toPlainString();


        if (t.length() < 5) {
            resultString = t;
        } else {
            resultString = t.substring(0, 5);
        }


        if (resultString != "" && resultString != null) {

            result = Float.valueOf(resultString);


        }


        return result;
    }





    //------------------------------------------------------------------------------------------------------------------------------------------------执行下个温度点和湿度点
    public void executingNextTemOrHum(int code, int nextPoint) {
        SystemData systemDataNext = systemDBHelper.getSystemData();
        if (code == 0) {
            systemDataNext.setExecutingTemID(systemDataNext.getExecutingTemID() + 1);
            systemDataNext.setSettingTem(nextPoint);

            //设置温度值
            try {
                modbusRtuMaster.writeHoldingRegisters(1,0x001F,1,  new int[]{nextPoint});
            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }
            mTemView.setValue(nextPoint,"tem");


        } else {
            systemDataNext.setExecutingHumID(systemDataNext.getExecutingHumID() + 1);
            systemDataNext.setSettingHum(nextPoint);

            //设定湿度
            try {

                modbusRtuMaster.writeHoldingRegisters(1,0x0021,1,  new int[]{nextPoint});

            } catch (ModbusError modbusError) {
                modbusError.printStackTrace();
            }
            mHumView.setValue(nextPoint,"hum");
        }
        systemDBHelper.updateSystemData(systemDataNext);

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

        write6("datarecord", str, fileName);

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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 1);

        if (isClose) {
            alarmManager.cancel(pendingIntent);
        } else {
            System.out.println("进入定时事件====" + systemData.getLightKeepSecond() * 1000);
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
                        modbusRtuMaster.readHoldingRegisters(1,0000,4);
                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }

                    getPower();

            }
        }
    });



    public Thread warnThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!isFalse) {
                SystemClock.sleep(1000);

                try {
                    modbusRtuMaster.readCoils(1,0xF800,2);
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
            int number2=result.indexOf("010310");//温湿度功率
            int number3=result.indexOf("010101");//报警信息
            int number4=result.indexOf("010102");//开关状态
            System.out.println("数据情况---------"+result+"   "+number1);
            if(number1!=-1 && result.length()>=26){
                float floatTem=sixteentofloat(result,number1+14,4);

                if(systemData.getTemOnOrOff()==1){
                    mTemView.setProgress(floatTem,"tem");
                }if(systemData.getTemOnOrOff()==0){
                    mTemView.setProgress(0,"tem");
                }
                float floatHum=sixteentofloat(result,number1+6,4);
                if(systemData.getHumOnOrOff()==1){
                    mHumView.setProgress(floatHum,"hum");
                }if(systemData.getHumOnOrOff()==0){
                    mHumView.setProgress(0,"hum");
                }
                DataRecodeBean dataRecodeBean=new DataRecodeBean();
                dataRecodeBean.setRealtimeTem(floatTem);
                dataRecodeBean.setRealtimeHum(floatHum);
                dataRecodeBean.setSettingTem(systemData.getSettingTem());
                dataRecodeBean.setSettingHum(systemData.getSettingHum());
                dataRecodeBean.setTime(getDateTimeToString(new Date()));
                dataRecordDBHelper.add(dataRecodeBean);

                int executingTime=(int)getDatePoor();
                if(floatTem-systemData.getSettingTem()<=1 || systemData.getSettingTem()-floatTem<=1){
                    changeDataDBHelper.add(executingTime,floatTem,0);
                }

                if (floatHum - systemData.getSettingHum() <= 0.5 || systemData.getSettingHum() - floatHum <= 0.5) {
                    changeDataDBHelper.add(executingTime, floatHum, 1);
                }

//                            判断是否达到稳定状态
                int  i=   isStable();
                if(i<4){
                    Message temMessage = new Message();
                    temMessage.what=i;
                    temMessage.obj=dataRecodeBean;
                    reachstabilityHandler.sendMessage(temMessage);
                }
            }

            if(number2!=-1 && result.length()>=42){

                temPower=sixteentofloat(result,number2+6,4);
                humPower=sixteentofloat(result,number2+30,4);
                systemData.setTemPower(String.valueOf(temPower));
                systemData.setHumPower(String.valueOf(humPower));

            }if(number3!=-1){
                if(result.length()>=8){
                    String s=result.substring(6, 8);
                    Message message=new Message();
                    if(s=="02"){
                        message.what=1;
                        handler.sendMessage(message);
                    }
                    if(s=="01" || s=="03"){
                        message.what=2;
                        handler.sendMessage(message);
                    }
                }

            }if(number4!=-1 && result.length()>=14){
                String s=result.substring(6,8);
                System.out.println("-----------开关情况"+s);
                if(s=="00"){
                    systemData.setTemOnOrOff(0);
                    systemData.setHumOnOrOff(0);
                }
                if(s=="01"){
                    systemData.setTemOnOrOff(1);
                    systemData.setHumOnOrOff(0);
                }if(s=="02"){
                    systemData.setTemOnOrOff(0);
                    systemData.setHumOnOrOff(1);
                }if(s=="03"){
                    systemData.setTemOnOrOff(1);
                    systemData.setHumOnOrOff(1);
                }

            }

            systemDBHelper.updateSystemData(systemData);

            super.handleMessage(msg);
        }
    };







    private Handler reachstabilityHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {


            SystemData systemData1=systemDBHelper.getSystemData();
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
                unitTimeAndHumWave.setText(humPlanBean.getHumWave()+"℃/"+humPlanBean.getUnitTime()+"min");
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

                    alertDialog.cancel();
                    executingNextTemOrHum(0,getNextTem());
                    onStart();
                    alertDialog=null;


                }
            });


            humButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    alertDialog.cancel();
                    executingNextTemOrHum(1,getNextHum());

                    onStart();
                    alertDialog=null;

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
        ChangeDataDBHelper changeDataDBHelper1 = new ChangeDataDBHelper(MainActivity.this, "NIMENG.db", null, 1);
        SystemDBHelper systemDBHelper1=new SystemDBHelper(MainActivity.this,"NIMENG.db",null,1);
        boolean temIsStable = false;
        boolean humIsStable = false;
        SystemData systemData1 = systemDBHelper1.getSystemData();
        int temPlanID = systemData1.getTemPlanID();
        if (temPlanID == 0) {

            float temMax = changeDataDBHelper1.getAllChangeData("temMax", 5);
            float temMin = changeDataDBHelper1.getAllChangeData("temMin", 5);

            if (temMax - temMin <= 0.2 &&  getDatePoor()>=5  && temMax-temMin>0) {
                temIsStable = true;
            }


            systemData1.setTemChange((temMax-temMin)+"℃/5min");



        } else {
            TemPlanBean temPlanBean = temPlanDBHelper.queryByID(temPlanID);
            int temTime = temPlanBean.getUnitTime();
            float temWave = temPlanBean.getTemWave();


            float temMax = changeDataDBHelper1.getAllChangeData("temMax", temTime);
            float temMin = changeDataDBHelper1.getAllChangeData("temMin", temTime);
            if (temMax - temMin <= temWave  &&  getDatePoor()>=temTime && temMax-temMin>0) {
                temIsStable = true;
            }



            systemData1.setTemChange((temMax-temMin)+"℃/"+temTime+"min");



        }


        int humPlanID = systemData1.getHumPlanID();
        if (humPlanID == 0) {
            float humMax = changeDataDBHelper1.getAllChangeData("humMax", 1);
            float humMin = changeDataDBHelper1.getAllChangeData("humMin", 1);
            if (humMax - humMin < 0.8 &&  getDatePoor()>=1 && humMax!=0 && humMin!=0 && humMax-humMin>0) {
                humIsStable = true;
            }


            systemData1.setHumChange((humMax-humMin)+"%/min");


        } else {
            HumPlanBean humPlanBean = humPlanDBHelper.queryByID(humPlanID);
            int humTime = humPlanBean.getUnitTime();
            float humWave = humPlanBean.getHumWave();
            float humMax = changeDataDBHelper1.getAllChangeData("humMax", humTime);
            float humMin = changeDataDBHelper1.getAllChangeData("humMin", humTime);
            if (humMax - humMin <= humWave  &&  getDatePoor()>=humTime && humMax!=0 && humMin!=0 && humMax-humMin>0) {
                humIsStable = true;
            }


            systemData1.setHumChange((humMax-humMin)+"%/"+humTime+"min");

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



}





