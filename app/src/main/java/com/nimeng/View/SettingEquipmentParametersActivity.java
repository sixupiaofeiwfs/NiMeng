package com.nimeng.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.nimeng.util.CRC16;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.ModbusError;
import com.nimeng.util.ModbusRtuMaster;
import com.nimeng.util.ModbusUtil;
import com.nimeng.util.SystemDBHelper;

import org.w3c.dom.Text;

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
public class SettingEquipmentParametersActivity extends CommonUtil  implements View.OnClickListener{


    private Intent intent;

    private Button button1,button2,button3;
    private boolean switch1,switch2,switch3;

    SystemDBHelper systemDBHelper;


    SerialHelper serialHelper;
    ModbusRtuMaster modbusRtuMaster;

    ModbusUtil modbusUtil=new ModbusUtil();


    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12,textView13,textView14,textView15;

    TextView textView_xtsz,textView_fqfk;

    Spinner spinner1,spinner2;

    @Override
    protected void onStart() {


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



//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String select1 = spinner1.getSelectedItem().toString();
//                Log.d("获取一下选中的值", "onItemSelected:" + select1);
//                systemData.setSelect1(select1);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String select2 = spinner2.getSelectedItem().toString();
//                Log.d("获取一下选中的值", "onItemSelected" + select2);
//                systemData.setSelect2(select2);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });



        super.onStart();
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting_equipment_parameters);
        systemDBHelper=SystemDBHelper.getInstance(SettingEquipmentParametersActivity.this);
        SystemData systemData=systemDBHelper.getSystemData();


       textView1=findViewById(R.id.asep_edit1);
       textView2=findViewById(R.id.asep_edit2);
       textView3=findViewById(R.id.asep_edit3);
       textView4=findViewById(R.id.asep_edit4);
       textView5=findViewById(R.id.asep_edit5);
       textView6=findViewById(R.id.asep_edit6);
       textView7=findViewById(R.id.asep_edit7);
       textView8=findViewById(R.id.asep_edit8);
       textView9=findViewById(R.id.asep_edit9);
       textView10=findViewById(R.id.asep_edit10);
       textView11=findViewById(R.id.asep_edit11);
       textView12=findViewById(R.id.asep_edit12);
       textView13=findViewById(R.id.asep_edit13);
       textView14=findViewById(R.id.asep_edit14);
       textView15=findViewById(R.id.asep_edit15);


        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
//        textView7.setOnClickListener(this);
//        textView8.setOnClickListener(this);
        textView9.setOnClickListener(this);
        textView10.setOnClickListener(this);
        textView11.setOnClickListener(this);
        textView12.setOnClickListener(this);
        textView13.setOnClickListener(this);
        textView14.setOnClickListener(this);
        textView15.setOnClickListener(this);

        textView_xtsz=findViewById(R.id.textview_xtsz);
        textView_fqfk=findViewById(R.id.textview_fqfk);
        textView_fqfk.setOnClickListener(this);
        textView_xtsz.setOnClickListener(this);




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


        if(!thread.isAlive()){
            thread.start();
        }



        button1=findViewById(R.id.btn_1);
        button2=findViewById(R.id.btn_2);
        button3=findViewById(R.id.btn_3);

        spinner1 = (Spinner) findViewById(R.id.wendu);
        spinner2 = (Spinner) findViewById(R.id.shidu);



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




    super.onCreate(savedInstanceState);


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



            int number1=result.indexOf("010304");//压缩机保护延时
            int number2=result.indexOf("010320");//系统参数

            if(result.length()>=number2+74 && number2!=-1){


               textView1.setText(String.valueOf(modbusUtil.covert(result.substring(number2+6,number2+10))));
               textView3.setText(String.valueOf(modbusUtil.covert(result.substring(number2+10,number2+14))));





               textView5.setText(String.valueOf(modbusUtil.covert(result.substring(number2+14,number2+18))/100.00f));
               textView6.setText(String.valueOf(modbusUtil.covert(result.substring(number2+18,number2+22))/100.00f));
               textView7.setText(String.valueOf(modbusUtil.covert(result.substring(number2+22,number2+26))));
               textView8.setText(String.valueOf(modbusUtil.covert(result.substring(number2+26,number2+30))));
               textView9.setText(String.valueOf(modbusUtil.covert(result.substring(number2+30,number2+34))/100.00f));
               textView10.setText(String.valueOf(modbusUtil.covert(result.substring(number2+34,number2+38))/100.00f));
               textView11.setText(String.valueOf(modbusUtil.covert(result.substring(number2+38,number2+42))/100.00f));
            }

            if(result.length()>=number1+18 && number1!=-1){

                System.out.println("---保护"+result);

               textView2.setText(String.valueOf(modbusUtil.covert(result.substring(number1+6,number1+10))));
               textView4.setText(String.valueOf(modbusUtil.covert(result.substring(number1+10,number1+14))));
            }





        }

    };


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.textview_xtsz:
                intent=new Intent(SettingEquipmentParametersActivity.this,SettingSwitchActivity.class);
                startActivity(intent);
                break;
            case R.id.textview_fqfk:
                intent=new Intent(SettingEquipmentParametersActivity.this,InstallermentActivity.class);
                startActivity(intent);
                break;

            case R.id.asep_edit1:
                method(R.id.asep_edit1);
                break;

            case R.id.asep_edit2:
                method(R.id.asep_edit2);
                break;

            case R.id.asep_edit3:
                method(R.id.asep_edit3);
                break;

            case R.id.asep_edit4:
                method(R.id.asep_edit4);
                break;

            case R.id.asep_edit5:
                method(R.id.asep_edit5);
                break;

            case R.id.asep_edit6:
                method(R.id.asep_edit6);
                break;

            case R.id.asep_edit7:
                method(R.id.asep_edit7);
                break;

            case R.id.asep_edit8:
                method(R.id.asep_edit8);
                break;

            case R.id.asep_edit9:
                method(R.id.asep_edit9);
                break;

            case R.id.asep_edit10:
                method(R.id.asep_edit10);
                break;

            case R.id.asep_edit11:
                method(R.id.asep_edit11);
                break;

            case R.id.asep_edit12:
                method(R.id.asep_edit12);
                break;

            case R.id.asep_edit13:
                method(R.id.asep_edit13);
                break;

            case R.id.asep_edit14:
                method(R.id.asep_edit14);
                break;

            case R.id.asep_edit15:
                method(R.id.asep_edit15);
                break;




        }
    }

    private void updateParam(int id, int value){

        int a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15;


        switch (id){
            case R.id.asep_edit1:
                a1=Integer.parseInt(Integer.toHexString(value),16);
                a2=Integer.valueOf( textView3.getText().toString());
                System.out.println("------"+a1+"   "+a2);
                try {
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,2,new int[]{value,a2});
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,2,new int[]{value,a2});
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;



            case R.id.asep_edit2:
                a1=value;
               // a2=Integer.valueOf( textView4.getText().toString());
                try {
                    modbusRtuMaster.writeSingleRegister(1,0x00C8,a1);
                    modbusRtuMaster.writeSingleRegister(1,0x00C8,a1);
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;



            case R.id.asep_edit3:
                a1=Integer.valueOf( textView1.getText().toString());
                try {
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,2,new int[]{a1,value});
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,2,new int[]{a1,value});
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;


            case R.id.asep_edit4:
                a1=Integer.valueOf( textView2.getText().toString());
                a2=value;

                try {
                    modbusRtuMaster.writeHoldingRegisters(1,0x00C8,2,new int[]{a1,a2});
                    modbusRtuMaster.writeHoldingRegisters(1,0x00C8,2,new int[]{a1,a2});
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;





            case R.id.asep_edit5:
                a1=Integer.valueOf( textView1.getText().toString());
                a2= Integer.valueOf( textView3.getText().toString());
                a3=value*100;
                a4=(int)Float.parseFloat( textView6.getText().toString())*100;
                try {
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,4,new int[]{a1,a2,a3,a4});
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,4,new int[]{a1,a2,a3,a4});
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;
            case R.id.asep_edit6:
                a1=Integer.valueOf( textView1.getText().toString());
                a2=Integer.valueOf( textView3.getText().toString());
                a3=(int)Float.parseFloat( textView5.getText().toString())*100;
                a4=value*100;
                System.out.println(a1+"  "+a2+"  "+a3+"  "+a4);
                try {
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,4,new int[]{a1,a2,a3,a4});
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,4,new int[]{a1,a2,a3,a4});
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;


            case R.id.asep_edit9:
                a1=Integer.valueOf( textView1.getText().toString());
                a2=Integer.valueOf( textView3.getText().toString());
                a3=(int)Float.parseFloat( textView5.getText().toString())*100;
                a4=(int)Float.parseFloat( textView6.getText().toString())*100;
                a5=Integer.valueOf( textView7.getText().toString());;
                a6=Integer.valueOf( textView8.getText().toString());
                a7=value*100;
                a8=(int)Float.parseFloat( textView10.getText().toString())*100;
                System.out.println(a1+"  "+a2+"  "+a3+"  "+a4+"  "+a5+"  "+a6+"   "+a7+"   "+a8);
                try {
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,8,new int[]{a1,a2,a3,a4,a5,a6,a7,a8});
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,8,new int[]{a1,a2,a3,a4,a5,a6,a7,a8});
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;


            case R.id.asep_edit10:
                a1=Integer.valueOf( textView1.getText().toString());
                a2=Integer.valueOf( textView3.getText().toString());
                a3=(int)Float.parseFloat( textView5.getText().toString())*100;
                a4=(int)Float.parseFloat( textView6.getText().toString())*100;
                a5=Integer.valueOf( textView7.getText().toString());;
                a6=Integer.valueOf( textView8.getText().toString());
                a7=(int)Float.parseFloat( textView9.getText().toString())*100;
                a8=value*100;
                System.out.println(a1+"  "+a2+"  "+a3+"  "+a4+"  "+a5+"  "+a6+"   "+a7+"   "+a8);
                try {
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,8,new int[]{a1,a2,a3,a4,a5,a6,a7,a8});
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,8,new int[]{a1,a2,a3,a4,a5,a6,a7,a8});
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;


            case R.id.asep_edit11:
                a1=Integer.valueOf( textView1.getText().toString());
                a2=Integer.valueOf( textView3.getText().toString());
                a3=(int)Float.parseFloat( textView5.getText().toString())*100;
                a4=(int)Float.parseFloat( textView6.getText().toString())*100;
                a5=Integer.valueOf( textView7.getText().toString());;
                a6=Integer.valueOf( textView8.getText().toString());
                a7=(int)Float.parseFloat( textView9.getText().toString())*100;
                a8=(int)Float.parseFloat( textView10.getText().toString())*100;
                a9=value*100;
                System.out.println(a1+"  "+a2+"  "+a3+"  "+a4+"  "+a5+"  "+a6+"   "+a7+"   "+a8+"   "+a9);
                try {
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,9,new int[]{a1,a2,a3,a4,a5,a6,a7,a8,a9});
                    modbusRtuMaster.writeHoldingRegisters(1,0x0384,9,new int[]{a1,a2,a3,a4,a5,a6,a7,a8,a9});
                } catch (ModbusError modbusError) {
                    modbusError.printStackTrace();
                }
                break;

        }



    }



    private void method(int id ){
        View view1=View.inflate(SettingEquipmentParametersActivity.this,R.layout.input,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(SettingEquipmentParametersActivity.this);
        EditText editText=view1.findViewById(R.id.text1);

        builder.setView(view1)
                .setTitle("修改参数")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(editText.getText()==null || editText.getText().toString().equals("")){
                            showToast(SettingEquipmentParametersActivity.this,"修改参数值不能为空");
                            return;
                        }
                        System.out.println("输入的值..."+editText.getText().toString());

                        updateParam(id,Integer.valueOf(editText.getText().toString()));

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
