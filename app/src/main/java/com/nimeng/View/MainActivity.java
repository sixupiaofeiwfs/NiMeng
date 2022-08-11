package com.nimeng.View;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.modbus.SerialClient;
import com.modbus.SerialPortParams;
import com.modbus.SerialUtils;
import com.modbus.modbus.ModBusData;
import com.modbus.modbus.ModBusDataListener;
import com.nimeng.bean.DataRecodeBean;

import com.nimeng.bean.GlobalVariable;
import com.nimeng.flash.FlashView;
import com.nimeng.flash.VirtualBarUtil;
import com.nimeng.util.DataRecordDBHelper;
import com.nimeng.util.HumPlanDBHelper;
import com.nimeng.util.ModbusUtil;
import com.nimeng.util.TemPlanDBHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseAvtivity  {


    public static final String DATABASE_NAME="NIMENG.db";
    private static final int MIN_DISTANCE=100;//最小滑动距离
    private GestureDetector gestureDetector;

    private FlashView mTemView;
    private FlashView mHumView;
    private Button btn_tem;
    private Button btn_hum;

    private String tem,hum;

    private final String TAG="MainActivity";

    private DataRecordDBHelper dataRecordDBHelper;
    private DataRecodeBean dataRecodeBean;
    private GlobalVariable globalVariable;
    private TemPlanDBHelper temPlanDBHelper;
    private HumPlanDBHelper humPlanDBHelper;
    private ListView listView;
    Intent intent1;

    int errorNumber;
    int excutingNumber;
    AlarmManager alarmManager;

    private ImageView imageView1,imageViewLogo;
    public final String LIGHTONACTION="android.intent.action.LIGHTON";

    private ModbusUtil modbusUtil=new ModbusUtil();
    public float temPower,humPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        globalVariable=(GlobalVariable)getApplicationContext();


        alarmManager=(AlarmManager) this.getSystemService(Context.ALARM_SERVICE);


        //判断是否分期
        if(globalVariable.isInstallmentPayment()){
            //获取一共分了几期
            int numberOfStages= globalVariable.getNumberOfStages();
            //当前时间处在第几期
             excutingNumber=checkTime();
            System.out.println("main---》当前处于第"+excutingNumber+"期");

            //获取当前是否已经匹配密码
            System.out.println("判断当前是否已经匹配密码"+globalVariable.getMatchs().get(excutingNumber-1));
            if(!globalVariable.getMatchs().get(excutingNumber-1)){
                //获取已经错误的次数
                errorNumber=globalVariable.getErrorNumbers().get(excutingNumber-1);
                System.out.println("当前已经错误的次数"+errorNumber);
                if(errorNumber>=3){
                    Toast.makeText(this,"警告！密码连续输错超过三次，系统已停止",Toast.LENGTH_SHORT).show();
                   System.exit(0);
                   return;
                }


                intent1=new Intent(MainActivity.this,PasswordActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                return;


            }



        }



        //判断是否需要隐藏底部的虚拟按键
        if(VirtualBarUtil.hasNavBar(this)){
            VirtualBarUtil.hideBottomUIMenu(this);
        }
        setContentView(R.layout.activity_main);
        mTemView=findViewById(R.id.wd);
        mHumView=findViewById(R.id.sd);
        //长按改变设定值
        mTemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                View view1=View.inflate(MainActivity.this,R.layout.setview_edit,null);
                EditText editText=view1.findViewById(R.id.setview_edit);
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请输入温度设定值")
                        .setView(view1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                /**
                                 * 模拟设定值
                                 */
                                mTemView.setValue(Float.valueOf(editText.getText().toString()),"tem");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                return true;
            }
        });
        mHumView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                View view1=View.inflate(MainActivity.this,R.layout.setview_edit,null);
                EditText editText=view1.findViewById(R.id.setview_edit);
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请输入湿度设定值")
                        .setView(view1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                /**
                                 * 模拟设定值
                                 */
                                mHumView.setValue(Float.valueOf(editText.getText().toString()),"hum");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                return true;
            }
        });




        if(temPower==0){
            temPower=300;
        }if(humPower==0){
            humPower=200;
        }

        //点击展示数据
        mTemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=View.inflate(MainActivity.this,R.layout.maindatashow,null);
                TextView textView1=view1.findViewById(R.id.temP);
                textView1.setText(String.valueOf(temPower));
                TextView textView2=view1.findViewById(R.id.humP);
                textView2.setText(String.valueOf(humPower));


                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("详细数据：")
                        .setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        mHumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=View.inflate(MainActivity.this,R.layout.maindatashow,null);
                TextView textView1=view1.findViewById(R.id.temP);
                textView1.setText(String.valueOf(temPower));
                TextView textView2=view1.findViewById(R.id.humP);
                textView2.setText(String.valueOf(humPower));
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("详细数据：")
                        .setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });


        //删除7天前的数据
        DataRecordDBHelper dataRecordDBHelper=new DataRecordDBHelper(MainActivity.this,"NIMENG.db",null,1);
        dataRecordDBHelper.delete7DaysData();





        //获取文件读写权限
        onPermission(globalVariable);

        temPlanDBHelper=new TemPlanDBHelper(MainActivity.this,"NIMENG.db",null,1);
        humPlanDBHelper=new HumPlanDBHelper(MainActivity.this,"NIMENG.db",null,1);



        //设置下拉框显示内容
        Spinner spinner1=(Spinner) findViewById(R.id.main_spinner1);
        Spinner spinner2=(Spinner)findViewById(R.id.main_spinner2);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,globalVariable.getTemPlanList());
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,globalVariable.getHumPlanList());
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);


        //温湿度启动\停止按钮
        btn_tem=findViewById(R.id.mian_btn1);
        btn_hum=findViewById(R.id.mian_btn2);
        btn_tem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long temIsCheck=spinner1.getSelectedItemId();

                System.out.println("温度选择--->"+temIsCheck);

                if(temIsCheck==0){
//                    globalVariable.setHumID(1001);
//                    globalVariable.setStartTime(new Date());
//                    globalVariable.setHumUnitTime(10);
//                    globalVariable.setHumWave(0.6f);
//                    globalVariable.setStable(false);
//                    globalVariable.setHumPlanName("方案一（不设置）");
//                    globalVariable.setExecutingHumID(1);
//                    globalVariable.setHumIsSystem(true);
                    showToast("已选择方案一（不设置）");
                }else if(temIsCheck==1){
//                    globalVariable.setHumID(1002);
//                    globalVariable.setStartTime(new Date());
//                    globalVariable.setHumUnitTime(10);
//                    globalVariable.setHumWave(0.6f);
//                    globalVariable.setStable(false);
//                    globalVariable.setHumPlanName("方案二（40%）");
//                    globalVariable.setExecutingHumID(1);
//                    globalVariable.setHumIsSystem(true);
                    showToast("已选择方案二（20℃-40℃-60-80℃）");
                }else if(temIsCheck==2){
//                    globalVariable.setHumID(1003);
//                    globalVariable.setStartTime(new Date());
//                    globalVariable.setHumUnitTime(10);
//                    globalVariable.setHumWave(0.6f);
//                    globalVariable.setStable(false);
//                    globalVariable.setHumPlanName("方案三（20%-40%-60%-80%）");
//                    globalVariable.setExecutingHumID(1);
//                    globalVariable.setHumIsSystem(true);
                    showToast("已选择方案三（15℃-20℃-40℃-60℃-80℃）");
                }else if(temIsCheck==3){
//                    globalVariable.setHumID(1004);
//                    globalVariable.setStartTime(new Date());
//                    globalVariable.setHumUnitTime(10);
//                    globalVariable.setHumWave(0.6f);
//                    globalVariable.setStable(false);
//                    globalVariable.setHumPlanName("方案四（20%-40%-60%-80%-90%）");
//                    globalVariable.setExecutingHumID(1);
//                    globalVariable.setHumIsSystem(true);
                    showToast("已选择方案四（15℃-20℃-40-℃-60℃-80℃-90℃）");
                }else{
                    System.out.println(globalVariable.getTemPlanList().get(Integer.valueOf(String.valueOf(temIsCheck))));
                }
            }
        });
        btn_hum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long humIsCheck=spinner1.getSelectedItemId();
                if(humIsCheck==0){
//                    globalVariable.setHumID(1001);
//                    globalVariable.setStartTime(new Date());
//                    globalVariable.setHumUnitTime(10);
//                    globalVariable.setHumWave(0.6f);
//                    globalVariable.setStable(false);
//                    globalVariable.setHumPlanName("方案一（不设置）");
//                    globalVariable.setExecutingHumID(1);
//                    globalVariable.setHumIsSystem(true);
                    showToast("已选择方案一（不设置）");
                }else if(humIsCheck==1){
//                    globalVariable.setHumID(1002);
//                    globalVariable.setStartTime(new Date());
//                    globalVariable.setHumUnitTime(10);
//                    globalVariable.setHumWave(0.6f);
//                    globalVariable.setStable(false);
//                    globalVariable.setHumPlanName("方案二（40%）");
//                    globalVariable.setExecutingHumID(1);
//                    globalVariable.setHumIsSystem(true);
                    showToast("已选择方案二（40%）");
                }else if(humIsCheck==2){
//                    globalVariable.setHumID(1003);
//                    globalVariable.setStartTime(new Date());
//                    globalVariable.setHumUnitTime(10);
//                    globalVariable.setHumWave(0.6f);
//                    globalVariable.setStable(false);
//                    globalVariable.setHumPlanName("方案三（20%-40%-60%-80%）");
//                    globalVariable.setExecutingHumID(1);
//                    globalVariable.setHumIsSystem(true);
                    showToast("已选择方案三（20%-40%-60%-80%）");
                }else if(humIsCheck==3){
//                    globalVariable.setHumID(1004);
//                    globalVariable.setStartTime(new Date());
//                    globalVariable.setHumUnitTime(10);
//                    globalVariable.setHumWave(0.6f);
//                    globalVariable.setStable(false);
//                    globalVariable.setHumPlanName("方案四（20%-40%-60%-80%-90%）");
//                    globalVariable.setExecutingHumID(1);
//                    globalVariable.setHumIsSystem(true);
                    showToast("已选择方案四（20%-40%-60%-80%-90%）");
                }
            }
        });



        /**
         * 获取运行值
          */
     //init("/dev/ttyS0");



        /**
         * 模拟运行值
         */
        mHumView.setProgress(39.99f,"hum");
        mTemView.setProgress(29.90f,"tem");




        //灯控开关
        imageView1=findViewById(R.id.main_light);
        String light= getIntent().getStringExtra("light");
        System.out.println("light-----------"+light);

        if(light==null){
            if(globalVariable.isSwitch_7()){
                imageView1.setImageResource(R.drawable.lighton);
            }else{
                imageView1.setImageResource(R.drawable.lightoff);
            }
        }else{
            if(light.equals("0")){
                imageView1.setImageResource(R.drawable.lightoff);
            }else{
                if(globalVariable.isSwitch_7()){
                    imageView1.setImageResource(R.drawable.lighton);
                }else{
                    imageView1.setImageResource(R.drawable.lightoff);
                }
            }

        }


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globalVariable.isSwitch_7()){//开-----》关
                    globalVariable.setSwitch_7(false);
                    imageView1.setImageResource(R.drawable.lightoff);
                    setLightOn(true);


                }else{//关-----》开
                    globalVariable.setSwitch_7(true);
                    imageView1.setImageResource(R.drawable.lighton);
                    globalVariable.setLightStartTime(new Date());
                    setLightOn(false);


                }
            }
        });


        //logo控制模拟报警
        imageViewLogo=findViewById(R.id.main_logo);
        imageViewLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=View.inflate(MainActivity.this,R.layout.warning,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("警告！！！")
                        .setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }




    //读运行值
    private void init(final String address) {
        SerialPortParams build = new SerialPortParams.Builder().serialPortPath(address).build();
        final SerialClient serialClient = SerialUtils.getInstance().getSerialClient(address);





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




                                   //判断是否达到了稳定状态，达到稳定状态时不再文件中记录
                                    //1.获取当前方案的稳定状态是什么
                                    //2.判断该时间段内的数据是否满足状态



                                    //连续取值最近时间段内的温湿度最大值和最小值  判断在此期间内的波动值是否大于最大值与最小值的差
                                      List<Double> temDoubleList= dataRecordDBHelper.queryColumn_Double("realtimeTem",globalVariable.getTemUnitTime()*60+"");
                                      Double temBigData=temDoubleList.get(0);
                                      Double temSmallData=temDoubleList.get(temDoubleList.size()-1);

                                      List<Double> humDoubleList= dataRecordDBHelper.queryColumn_Double("realtimeHum",globalVariable.getHumUnitTime()*60+"");
                                      Double humBigData=humDoubleList.get(0);
                                      Double humSmallData=humDoubleList.get(humDoubleList.size()-1);


                                    List<Float> temList,humList=null;
                                    temList=getExecutingTemAndNextTem();
                                    humList=getExecutingTemAndNextTem();

                                      //先判断温度
                                    if(temBigData-temSmallData<=globalVariable.getTemWave() && getAbs(temList.get(0),temBigData)<1){
                                        //温度已达稳定状态


                                        //再判断湿度是否达到稳定状态（两种情况，一种是湿度没选   一种是最大值-最小值<=波动值 并且最大值与最小值得围绕设置值波动，不能设置40度，最大值20.3 最小值20.28，就表示达到稳定）
                                        if(globalVariable.getTemWave()==0 || humBigData-humSmallData<=globalVariable.getHumWave()&& getAbs(humList.get(0),humBigData)<1){

                                            //达到稳定状态
                                            dataRecordDBHelper.add(dataRecodeBean,true,globalVariable.getTemPlanName());
                                            globalVariable.setStable(true);
                                            globalVariable.setStableTime(new Date());



                                            //开始记录标准器和被检表中的数值
                                        }

                                    }




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


                    humPower=modbusUtil.getHumPower();
                    temPower=modbusUtil.getTemPower();


                }
            }
        }).start();



    }


    //十六进制转浮点型
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


    //达到稳定状态
    private void reachstability(){

        //1.通知实验者达到稳定状态
        //2.弹窗提示 是否进行下一温湿度点检测
        //3.开始进行数据记录（文件数据  包括标准器 被检表等数据）

        boolean temCanContinue=true,humCanContinue=true;

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);


        String message="";

        List<Float> temList,humList=null;
        temList=getExecutingTemAndNextTem();
        humList=getExecutingHumAndNextHum();


        message="当前正在执行：<br>"+"温度："+temList.get(0)+"   湿度："+humList.get(0)+"<br>";
        if(humList.get(1)==0){
            message=message+"无可以继续执行的湿度设定点";
            humCanContinue=false;
        }else{
            message=message+"下一设置湿度点："+humList.get(1);
        }


        if(temList.get(1)==0){
            message=message+"无可以继续执行的温度设置点";
            temCanContinue=false;
        }else{
            message=message+"下一设置温度点："+temList.get(1);
        }


        boolean finalHumCanContinue = humCanContinue;
        boolean finalTemCanContinue = temCanContinue;
        builder.setTitle("请选择是否执行下一温度或湿度")
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    System.out.println("点击确定之后"+finalTemCanContinue+"__"+globalVariable.getExecutingTemID());
                     if(finalTemCanContinue){
                         globalVariable.setExecutingTemID(globalVariable.getExecutingTemID()+1);
                     }
                     if(finalHumCanContinue){
                         globalVariable.setExecutingHumID(globalVariable.getExecutingHumID()+1);
                     }

                     if(!finalTemCanContinue && !finalHumCanContinue){
                            dialogInterface.dismiss();
                     }

                        System.out.println("此时正在执行的temid："+globalVariable.getExecutingTemID());

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



    //执行下个温度点或湿度点





    //执行数据记录






//获取正在执行的温度点和下一个温度点
    private List<Float> getExecutingTemAndNextTem(){

        boolean temIsSystem=globalVariable.isTemIsSystem();
        int executingTemID= globalVariable.getExecutingTemID();
        int temID=globalVariable.getTemID();

        float executingTem=0,nextExecutingTem=0;


        System.out.println(temID+"----"+executingTemID);

        if(temIsSystem){
            switch (temID){
                case 991:
                    switch (executingTemID){
                        case 1:
                            executingTem=20.0f;
                            nextExecutingTem=40.0f;
                            break;
                        case 2:
                            executingTem=40.0f;
                            nextExecutingTem=60.0f;
                            break;
                        case 3:
                            executingTem=60.0f;
                            nextExecutingTem=80.0f;
                            break;
                        case 4:
                            executingTem=80.0f;
                            nextExecutingTem=0f;


                            break;
                    }

                case 992:
                    switch (executingTemID){
                        case 1:
                            executingTem=15.0f;
                            nextExecutingTem=20.0f;
                            break;
                        case 2:
                            executingTem=20.0f;
                            nextExecutingTem=40.0f;
                            break;
                        case 3:
                            executingTem=40.0f;
                            nextExecutingTem=60.0f;
                            break;
                        case 4:
                            executingTem=60.0f;
                            nextExecutingTem=80.0f;
                            break;
                        case 5:
                            executingTem=80.0f;
                            nextExecutingTem=0f;
                            break;

                    }
                case 993:
                    switch (executingTemID){
                        case 1:
                            executingTem=15.0f;
                            nextExecutingTem=20.0f;
                            break;
                        case 2:
                            executingTem=20.0f;
                            nextExecutingTem=40.0f;
                            break;
                        case 3:
                            executingTem=40.0f;
                            nextExecutingTem=60.0f;
                            break;
                        case 4:
                            executingTem=60.0f;
                            nextExecutingTem=80.0f;
                            break;
                        case 5:
                            executingTem=80.0f;
                            nextExecutingTem=90.0f;
                            break;
                        case 6:
                            executingTem=90.0f;
                            nextExecutingTem=0f;
                            break;
                    }
            }
        }
        else{
            executingTem=temPlanDBHelper.queryByID(temID,executingTemID);
            nextExecutingTem=temPlanDBHelper.queryByID(temID,executingTemID+1);

        }

        List<Float>list=new ArrayList<Float>();
        list.add(executingTem);
        list.add(nextExecutingTem);
        return list;

    }

    //获取正在执行的湿度点和下一个湿度点
    private List<Float>  getExecutingHumAndNextHum(){
        boolean humIsSystem= globalVariable.isHumIsSystem();
        int executingHumID=globalVariable.getExecutingHumID();
        int humID=globalVariable.getHumID();
        float executingHum=0,nextExecutingHum=0;

        if(humIsSystem){
            switch (humID){
                case 1001:
                    executingHum=0;
                    nextExecutingHum=0;
                    break;
                case 1002:
                    executingHum=40.0f;
                    nextExecutingHum=0;
                    break;
                case 1003:
                    switch (executingHumID){
                        case 1:
                            executingHum=20.0f;
                            nextExecutingHum=40.0f;
                            break;
                        case 2:
                            executingHum=40.0f;
                            nextExecutingHum=60.0f;
                            break;
                        case 3:
                            executingHum=60.0f;
                            nextExecutingHum=80.0f;
                            break;
                        case 4:
                            executingHum=80.0f;
                            nextExecutingHum=0;
                            break;
                    }
                case 1004 :
                    switch(executingHumID){
                        case 1:
                            executingHum=20.0f;
                            nextExecutingHum=40.0f;
                            break;
                        case 2:
                            executingHum=40.0f;
                            nextExecutingHum=60.0f;
                            break;
                        case 3:
                            executingHum=60.0f;
                            nextExecutingHum=80.0f;
                            break;
                        case 4:
                            executingHum=80.0f;
                            nextExecutingHum=90.0f;
                            break;
                        case 5:
                            executingHum=90.0f;
                            nextExecutingHum=0;
                            break;
                    }
            }


        }else{
            executingHum=humPlanDBHelper.queryByID(humID,executingHumID);
            nextExecutingHum=humPlanDBHelper.queryByID(humID,executingHumID+1);
        }

        List<Float>list=new ArrayList<Float>();
        list.add(executingHum);
        list.add(nextExecutingHum);
        return list;
    }


    //取绝对值方法
    private double getAbs(double a,double b){
        if(a-b<0){
            return b-a;
        }
        else{
            return a-b;
        }
    }


    //灯泡开关
    private void setLightOn(boolean isClose){
        Date date=new Date();
        Intent intent=new Intent(LIGHTONACTION);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,1);

        if(isClose){
            alarmManager.cancel(pendingIntent);
        }else{
            System.out.println("进入定时事件===="+globalVariable.getLightKeepSecond()*1000);
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(globalVariable.getLightKeepSecond()*1000),pendingIntent);
        }


    }







}





