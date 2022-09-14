package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nimeng.bean.SystemData;
import com.nimeng.bean.SystemSwitch;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.ModbusError;
import com.nimeng.util.ModbusRtuMaster;
import com.nimeng.util.ModbusUtil;
import com.nimeng.util.SystemDBHelper;

import java.io.IOException;

import tp.xmaihh.serialport.SerialHelper;
import tp.xmaihh.serialport.bean.ComBean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/27 16:55
 * <p>
 * Changes (from 2022/6/27)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/27 : Create SettingEquipmentParametersActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class SettingEquipmentParametersActivity extends CommonUtil {

    private TextView textView1,textView2;
    private Intent intent;

    private Button button1,button2,button3;
    private boolean switch1,switch2,switch3;

    SystemDBHelper systemDBHelper;
    SystemData systemData;

    SerialHelper serialHelper;
    ModbusRtuMaster modbusRtuMaster;

    ModbusUtil modbusUtil=new ModbusUtil();


    EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10,editText11,editText12,editText13,editText14,editText15;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_equipment_parameters);
       // systemDBHelper=new SystemDBHelper(SettingEquipmentParametersActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(SettingEquipmentParametersActivity.this);

        if(!thread.isAlive()){
            thread.start();
        }

        editText1=findViewById(R.id.edit1);
        editText2=findViewById(R.id.edit2);
        editText3=findViewById(R.id.edit3);
        editText4=findViewById(R.id.edit4);
        editText5=findViewById(R.id.edit5);
        editText6=findViewById(R.id.edit6);
        editText7=findViewById(R.id.edit7);
        editText8=findViewById(R.id.edit8);
        editText9=findViewById(R.id.edit9);
        editText10=findViewById(R.id.edit10);
        editText11=findViewById(R.id.edit11);
        editText12=findViewById(R.id.edit12);
        editText13=findViewById(R.id.edit13);
        editText14=findViewById(R.id.edit14);
        editText15=findViewById(R.id.edit15);



        serialHelper=new SerialHelper("/dev/ttyS0",9600) {
            @Override
            protected void onDataReceived(ComBean comBean) {

                Message message =handler.obtainMessage();
                message.obj=comBean;
                handler.sendMessage(message);

            }
        };

        try {
            if(!serialHelper.isOpen())
                serialHelper.open();
        } catch (IOException e) {
            e.printStackTrace();
        }


        modbusRtuMaster=new ModbusRtuMaster(serialHelper);

        systemData=systemDBHelper.getSystemData();
        textView1=findViewById(R.id.textview_xtsz);
        textView2=findViewById(R.id.textview_fqfk);

        button1=findViewById(R.id.btn_1);
        button2=findViewById(R.id.btn_2);
        button3=findViewById(R.id.btn_3);

        Spinner spinner1 = (Spinner) findViewById(R.id.wendu);
        Spinner spinner2 = (Spinner) findViewById(R.id.shidu);



         switch1= systemDBHelper.getSwitch("8");
         switch2= systemDBHelper.getSwitch("9");
         switch3= systemDBHelper.getSwitch("10");

        if(switch1){
            button1.setText("关闭");
        }else{
            button1.setText("开启");
        }


        if(switch2){
            button2.setText("关闭");
        }else{
            button2.setText("开启");
        }


        if(switch3){
            button3.setText("关闭");
        }else{
            button3.setText("开启");
        }



        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingEquipmentParametersActivity.this,SettingSwitchActivity.class);
                startActivity(intent);
            }
        });


        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingEquipmentParametersActivity.this,InstallermentActivity.class);
                startActivity(intent);
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(switch1){
                    button1.setText("开启");
                }else{
                    button1.setText("关闭");
                }

                systemDBHelper.addSwitch("8",!switch1);

                switch1=!switch1;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(switch2){
                    button2.setText("开启");
                }else{
                    button2.setText("关闭");
                }
                systemDBHelper.addSwitch("9",!switch2);
                switch2=!switch2;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch3){
                    button3.setText("开启");

                }else{
                    button3.setText("关闭");

                }
                systemDBHelper.addSwitch("10",!switch3);
                switch3=!switch3;
            }
        });



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select1 = spinner1.getSelectedItem().toString();
                Log.d("获取一下选中的值", "onItemSelected:" + select1);
                systemData.setSelect1(select1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select2 = spinner2.getSelectedItem().toString();
                Log.d("获取一下选中的值", "onItemSelected" + select2);
                systemData.setSelect2(select2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    public Thread thread =new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){

                SystemClock.sleep(1000);

                try {
                    modbusRtuMaster.readHoldingRegisters(1,0x0384,16);
                    modbusRtuMaster.readHoldingRegisters(1,0x00C8,2);
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }

            }
        }
    });

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            ComBean comBean=(ComBean) msg.obj;
            String result= ModbusUtil.bytesToHex(comBean.bRec,comBean.bRec.length);

            System.out.println("系统设置-----------");
            System.out.println(result);
            System.out.println("---------------------");

            int number1=result.indexOf("010304");//压缩机保护延时
            int number2=result.indexOf("010320");//系统参数

            if(result.length()>=number2+74 && number2!=-1){
                editText1.setText(String.valueOf(modbusUtil.covert(result.substring(number2+6,number2+10))));
                editText3.setText(String.valueOf(modbusUtil.covert(result.substring(number2+10,number2+14))));
                editText5.setText(String.valueOf(modbusUtil.covert(result.substring(number2+14,number2+18))/100.00f));
                editText6.setText(String.valueOf(modbusUtil.covert(result.substring(number2+18,number2+22))/100.00f));
                editText7.setText(String.valueOf(modbusUtil.covert(result.substring(number2+22,number2+26))));
                editText8.setText(String.valueOf(modbusUtil.covert(result.substring(number2+26,number2+30))));
                editText9.setText(String.valueOf(modbusUtil.covert(result.substring(number2+30,number2+34))/100.00f));
                editText10.setText(String.valueOf(modbusUtil.covert(result.substring(number2+34,number2+38))/100.00f));
                editText11.setText(String.valueOf(modbusUtil.covert(result.substring(number2+38,number2+42))/100.00f));
            }

            if(result.length()>=number1+18 && number1!=-1){
                editText2.setText(String.valueOf(modbusUtil.covert(result.substring(number1+6,number1+10))));
                editText4.setText(String.valueOf(modbusUtil.covert(result.substring(number1+10,number1+14))));
            }





        }

    };

}
