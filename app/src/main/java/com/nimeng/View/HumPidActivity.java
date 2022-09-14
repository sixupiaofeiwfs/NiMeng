package com.nimeng.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nimeng.bean.SystemData;
import com.nimeng.util.CRC16;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.ModbusError;
import com.nimeng.util.ModbusRtuMaster;
import com.nimeng.util.ModbusUtil;
import com.nimeng.util.SystemDBHelper;

import java.io.IOException;

import tp.xmaihh.serialport.SerialHelper;
import tp.xmaihh.serialport.bean.ComBean;

public class HumPidActivity extends CommonUtil implements View.OnClickListener {
    Button temmpid_btn,humpid_toSettingSwitch;
    Intent intent;

    SystemDBHelper systemDBHelper;
    SystemData systemData;

    SerialHelper serialHelper;
    ModbusRtuMaster modbusRtuMaster;
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,text16,text17,text18,text19,text20,text21,text22,text23,text24,text25,text26,text27,text28,text29,text30,text31,text32,text33,text34,text35,text36,text37,text38,text39,text40,text41,text42,text43,text44,text45,text46,text47,text48,text49,text50,text51,text52,text53,text54,text55,text56;
    ModbusUtil modbusUtil=new ModbusUtil();

    @Override
    protected void onStart() {

        serialHelper=new SerialHelper("/dev/ttyS0",9600) {
            @Override
            protected void onDataReceived(ComBean comBean) {

               Message message=handler.obtainMessage();
               message.obj=comBean;
               message.what=1;
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


        try {
            modbusRtuMaster.readHoldingRegisters(1,0x0164,60);
        } catch (ModbusError modbusError) {
            modbusError.printStackTrace();
        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_humpid);

       // systemDBHelper=new SystemDBHelper(HumPidActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(HumPidActivity.this);
        systemData=systemDBHelper.getSystemData();

        temmpid_btn=findViewById(R.id.tempid);
        humpid_toSettingSwitch=findViewById(R.id.humpid_toSettingSwitch);

        temmpid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(HumPidActivity.this,TemPidActivity.class);
                startActivity(intent);
            }
        });


        humpid_toSettingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(HumPidActivity.this,SettingSwitchActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6;
        linearLayout1 = findViewById(R.id.humpid_LinearLayout7);
        linearLayout2 = findViewById(R.id.humpid_LinearLayout8);
        linearLayout3 = findViewById(R.id.humpid_LinearLayout9);
        linearLayout4 = findViewById(R.id.humpid_LinearLayout10);
        linearLayout5 = findViewById(R.id.humpid_LinearLayout11);
        linearLayout6 = findViewById(R.id.humpid_LinearLayout12);

        if (systemData.getSelect2()==null||systemData.getSelect2().equals("")){
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.GONE);
            linearLayout5.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);
        }else {
            if (systemData.getSelect2().equals("5")) {
                Log.d("判断是否进入", "onItemSelected: ");
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);

            } else if (systemData.getSelect2().equals("6")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);
            } else if (systemData.getSelect2().equals("7")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);

            } else if (systemData.getSelect2().equals("8")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);
            } else if (systemData.getSelect2().equals("9")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.VISIBLE);
                linearLayout6.setVisibility(View.GONE);

            } else if ((systemData.getSelect2().equals("10"))) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.VISIBLE);
                linearLayout6.setVisibility(View.VISIBLE);
            }



            text1=findViewById(R.id.humpid_bili_1_1);
            text2=findViewById(R.id.humpid_bili_1_2);
            text3=findViewById(R.id.humpid_bili_1_3);
            text4=findViewById(R.id.humpid_bili_1_4);
            text5=findViewById(R.id.humpid_bili_1_5);
            text6=findViewById(R.id.humpid_bili_1_6);
            text7=findViewById(R.id.humpid_bili_1_7);
            text8=findViewById(R.id.humpid_bili_1_8);


            text9=findViewById(R.id.humpid_bili_2_1);
            text10=findViewById(R.id.humpid_bili_2_2);
            text11=findViewById(R.id.humpid_bili_2_3);
            text12=findViewById(R.id.humpid_bili_2_4);
            text13=findViewById(R.id.humpid_bili_2_5);
            text14=findViewById(R.id.humpid_bili_2_6);
            text15=findViewById(R.id.humpid_bili_2_7);
            text16=findViewById(R.id.humpid_bili_2_8);



            text17=findViewById(R.id.humpid_bili_3_1);
            text18=findViewById(R.id.humpid_bili_3_2);
            text19=findViewById(R.id.humpid_bili_3_3);
            text20=findViewById(R.id.humpid_bili_3_4);
            text21=findViewById(R.id.humpid_bili_3_5);
            text22=findViewById(R.id.humpid_bili_3_6);
            text23=findViewById(R.id.humpid_bili_3_7);
            text24=findViewById(R.id.humpid_bili_3_8);



            text25=findViewById(R.id.humpid_bili_4_1);
            text26=findViewById(R.id.humpid_bili_4_2);
            text27=findViewById(R.id.humpid_bili_4_3);
            text28=findViewById(R.id.humpid_bili_4_4);
            text29=findViewById(R.id.humpid_bili_4_5);
            text30=findViewById(R.id.humpid_bili_4_6);
            text31=findViewById(R.id.humpid_bili_4_7);
            text32=findViewById(R.id.humpid_bili_4_8);


            text33=findViewById(R.id.humpid_bili_5_1);
            text34=findViewById(R.id.humpid_bili_5_2);
            text35=findViewById(R.id.humpid_bili_5_3);
            text36=findViewById(R.id.humpid_bili_5_4);
            text37=findViewById(R.id.humpid_bili_5_5);
            text38=findViewById(R.id.humpid_bili_5_6);
            text39=findViewById(R.id.humpid_bili_5_7);
            text40=findViewById(R.id.humpid_bili_5_8);


            text41=findViewById(R.id.humpid_bili_6_1);
            text42=findViewById(R.id.humpid_bili_6_2);
            text43=findViewById(R.id.humpid_bili_6_3);
            text44=findViewById(R.id.humpid_bili_6_4);
            text45=findViewById(R.id.humpid_bili_6_5);
            text46=findViewById(R.id.humpid_bili_6_6);
            text47=findViewById(R.id.humpid_bili_6_7);
            text48=findViewById(R.id.humpid_bili_6_8);


            text49=findViewById(R.id.humpid_bili_7_1);
            text50=findViewById(R.id.humpid_bili_7_2);
            text51=findViewById(R.id.humpid_bili_7_3);
            text52=findViewById(R.id.humpid_bili_7_4);
            text53=findViewById(R.id.humpid_bili_7_5);
            text54=findViewById(R.id.humpid_bili_7_6);
            text55=findViewById(R.id.humpid_bili_7_7);
            text56=findViewById(R.id.humpid_bili_7_8);


            text1.setOnClickListener(this);
            text2.setOnClickListener(this);
            text3.setOnClickListener(this);
            text4.setOnClickListener(this);
            text5.setOnClickListener(this);
            text6.setOnClickListener(this);
            text7.setOnClickListener(this);
            text8.setOnClickListener(this);
            text9.setOnClickListener(this);
            text10.setOnClickListener(this);

            text11.setOnClickListener(this);
            text12.setOnClickListener(this);
            text13.setOnClickListener(this);
            text14.setOnClickListener(this);
            text15.setOnClickListener(this);
            text16.setOnClickListener(this);
            text17.setOnClickListener(this);
            text18.setOnClickListener(this);
            text19.setOnClickListener(this);
            text20.setOnClickListener(this);

            text21.setOnClickListener(this);
            text22.setOnClickListener(this);
            text23.setOnClickListener(this);
            text24.setOnClickListener(this);
            text25.setOnClickListener(this);
            text26.setOnClickListener(this);
            text27.setOnClickListener(this);
            text28.setOnClickListener(this);
            text29.setOnClickListener(this);
            text30.setOnClickListener(this);

            text31.setOnClickListener(this);
            text32.setOnClickListener(this);
            text33.setOnClickListener(this);
            text34.setOnClickListener(this);
            text35.setOnClickListener(this);
            text36.setOnClickListener(this);
            text37.setOnClickListener(this);
            text38.setOnClickListener(this);
            text39.setOnClickListener(this);
            text40.setOnClickListener(this);

            text41.setOnClickListener(this);
            text42.setOnClickListener(this);
            text43.setOnClickListener(this);
            text44.setOnClickListener(this);
            text45.setOnClickListener(this);
            text46.setOnClickListener(this);
            text47.setOnClickListener(this);
            text48.setOnClickListener(this);
            text49.setOnClickListener(this);
            text50.setOnClickListener(this);

            text51.setOnClickListener(this);
            text52.setOnClickListener(this);
            text53.setOnClickListener(this);
            text54.setOnClickListener(this);
            text55.setOnClickListener(this);
            text56.setOnClickListener(this);



        }

    }

   private Handler handler=new Handler()

    {

        @Override
        public void handleMessage(@NonNull Message msg) {

            if(msg.what==1){
                ComBean comBean=(ComBean) msg.obj;
                if(comBean==null){
                    return;
                }

                String result= ModbusUtil.bytesToHex(comBean.bRec,comBean.bRec.length) ;


                if(result.length()>=250 && result.indexOf("010378")!=-1){
                    text1.setText( String.valueOf( modbusUtil.covert(result.substring(6,10))/10.0f));
                    text2.setText(String.valueOf(modbusUtil.covert(result.substring(10,14))));
                    text3.setText(String.valueOf(modbusUtil.covert(result.substring(14,18))));
                    text4.setText(String.valueOf(modbusUtil.covert(result.substring(18,22))/10.0f));
                    text5.setText(String.valueOf(modbusUtil.covert(result.substring(22,26))/10.0f));
                    text6.setText(String.valueOf(modbusUtil.covert(result.substring(26,30))/10.0f));
                    text7.setText(String.valueOf(modbusUtil.covert(result.substring(30,34))/100.0f));
                    text8.setText(String.valueOf(modbusUtil.covert(result.substring(34,38))/100.0f));

                    text9.setText( String.valueOf( modbusUtil.covert(result.substring(38,42))/10.0f));
                    text10.setText(String.valueOf(modbusUtil.covert(result.substring(42,46))));
                    text11.setText(String.valueOf(modbusUtil.covert(result.substring(46,50))));
                    text12.setText(String.valueOf(modbusUtil.covert(result.substring(50,54))/10.0f));
                    text13.setText(String.valueOf(modbusUtil.covert(result.substring(54,58))/10.0f));
                    text14.setText(String.valueOf(modbusUtil.covert(result.substring(58,62))/10.0f));
                    text15.setText(String.valueOf(modbusUtil.covert(result.substring(62,66))/100.0f));
                    text16.setText(String.valueOf(modbusUtil.covert(result.substring(66,70))/100.0f));

                    text17.setText( String.valueOf( modbusUtil.covert(result.substring(70,74))/10.0f));
                    text18.setText(String.valueOf(modbusUtil.covert(result.substring(74,78))));
                    text19.setText(String.valueOf(modbusUtil.covert(result.substring(78,82))));
                    text20.setText(String.valueOf(modbusUtil.covert(result.substring(82,86))/10.0f));
                    text21.setText(String.valueOf(modbusUtil.covert(result.substring(86,90))/10.0f));
                    text22.setText(String.valueOf(modbusUtil.covert(result.substring(90,94))/10.0f));
                    text23.setText(String.valueOf(modbusUtil.covert(result.substring(94,98))/100.0f));
                    text24.setText(String.valueOf(modbusUtil.covert(result.substring(98,102))/100.0f));


                    text25.setText( String.valueOf( modbusUtil.covert(result.substring(102,106))/10.0f));
                    text26.setText(String.valueOf(modbusUtil.covert(result.substring(106,110))));
                    text27.setText(String.valueOf(modbusUtil.covert(result.substring(110,114))));
                    text28.setText(String.valueOf(modbusUtil.covert(result.substring(114,118))/10.0f));
                    text29.setText(String.valueOf(modbusUtil.covert(result.substring(118,122))/10.0f));
                    text30.setText(String.valueOf(modbusUtil.covert(result.substring(122,126))/10.0f));
                    text31.setText(String.valueOf(modbusUtil.covert(result.substring(126,130))/100.0f));
                    text32.setText(String.valueOf(modbusUtil.covert(result.substring(130,134))/100.0f));


                    text33.setText( String.valueOf( modbusUtil.covert(result.substring(134,138))/10.0f));
                    text34.setText(String.valueOf(modbusUtil.covert(result.substring(138,142))));
                    text35.setText(String.valueOf(modbusUtil.covert(result.substring(142,146))));
                    text36.setText(String.valueOf(modbusUtil.covert(result.substring(146,150))/10.0f));
                    text37.setText(String.valueOf(modbusUtil.covert(result.substring(150,154))/10.0f));
                    text38.setText(String.valueOf(modbusUtil.covert(result.substring(154,158))/10.0f));
                    text39.setText(String.valueOf(modbusUtil.covert(result.substring(158,162))/100.0f));
                    text40.setText(String.valueOf(modbusUtil.covert(result.substring(162,166))/100.0f));

                    text41.setText( String.valueOf( modbusUtil.covert(result.substring(166,170))/10.0f));
                    text42.setText(String.valueOf(modbusUtil.covert(result.substring(170,174))));
                    text43.setText(String.valueOf(modbusUtil.covert(result.substring(174,178))));
                    text44.setText(String.valueOf(modbusUtil.covert(result.substring(178,182))/10.0f));
                    text45.setText(String.valueOf(modbusUtil.covert(result.substring(182,186))/10.0f));
                    text46.setText(String.valueOf(modbusUtil.covert(result.substring(186,190))/10.0f));
                    text47.setText(String.valueOf(modbusUtil.covert(result.substring(190,194))/100.0f));
                    text48.setText(String.valueOf(modbusUtil.covert(result.substring(194,198))/100.0f));

                    text49.setText( String.valueOf( modbusUtil.covert(result.substring(198,202))/10.0f));
                    text50.setText(String.valueOf(modbusUtil.covert(result.substring(202,206))));
                    text51.setText(String.valueOf(modbusUtil.covert(result.substring(206,210))));
                    text52.setText(String.valueOf(modbusUtil.covert(result.substring(210,214))/10.0f));
                    text53.setText(String.valueOf(modbusUtil.covert(result.substring(214,218))/10.0f));
                    text54.setText(String.valueOf(modbusUtil.covert(result.substring(218,222))/10.0f));
                    text55.setText(String.valueOf(modbusUtil.covert(result.substring(222,226))/100.0f));
                    text56.setText(String.valueOf(modbusUtil.covert(result.substring(226,230))/100.0f));



                }
                else{
                    try {
                        modbusRtuMaster.readHoldingRegisters(1,0x0164,60);
                    } catch (ModbusError modbusError) {
                        modbusError.printStackTrace();
                    }
                }


            }



            super.handleMessage(msg);
        }


    };



    @Override
    public void onClick(View view) {
        View view1=View.inflate(HumPidActivity.this,R.layout.input,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(HumPidActivity.this);
        EditText editText=view1.findViewById(R.id.text1);
        builder.setView(view1)
                .setTitle("修改参数")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        System.out.println("发送..."+view.getId()+"   "+Float.valueOf(editText.getText().toString()));
                        sendModbusMessage(view.getId(),Float.valueOf(editText.getText().toString()));
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


    private void sendModbusMessage(int id,float f){

        String Hex1="",Hex2="";

        switch (id){
            case  R.id.humpid_bili_1_1:

                Hex1="01 10 01 64 00 01 02";
                Hex2= Integer.toHexString((int)(f*10));
                break;

            case R.id.humpid_bili_1_2:

                Hex1="01 10 01 65 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case  R.id.humpid_bili_1_3:
                Hex1="01 10 01 66 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;

            case R.id.humpid_bili_1_4:
                Hex1="01 10 01 67 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;

            case  R.id.humpid_bili_1_5:
                Hex1="01 10 01 68 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_1_6:
                Hex1="01 10 01 69 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_1_7:

                Hex1="01 10 01 6A 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;
            case R.id.humpid_bili_1_8:

                Hex1="01 10 01 6B 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;
            case  R.id.humpid_bili_2_1:

                Hex1="01 10 01 6C 00 01 02";
                Hex2= Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_2_2:
                Hex1="01 10 01 6D 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case  R.id.humpid_bili_2_3:
                Hex1="01 10 01 6E 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case R.id.humpid_bili_2_4:
                Hex1="01 10 01 6F 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_2_5:
                Hex1="01 10 01 70 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_2_6:
                Hex1="01 10 01 71 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_2_7:

                Hex1="01 10 01 72 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;
            case R.id.humpid_bili_2_8:
                Hex1="01 10 01 73 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;

            case  R.id.humpid_bili_3_1:
                Hex1="01 10 01 74 00 01 02";
                Hex2= Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_3_2:
                Hex1="01 10 01 75 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case  R.id.humpid_bili_3_3:
                Hex1="01 10 01 76 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case R.id.humpid_bili_3_4:
                Hex1="01 10 01 77 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_3_5:
                Hex1="01 10 01 78 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_3_6:
                Hex1="01 10 01 79 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_3_7:
                Hex1="01 10 01 7A 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;
            case R.id.humpid_bili_3_8:
                Hex1="01 10 01 7B 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;

            case  R.id.humpid_bili_4_1:
                Hex1="01 10 01 7C 00 01 02";
                Hex2= Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_4_2:
                Hex1="01 10 01 7D 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case  R.id.humpid_bili_4_3:
                Hex1="01 10 01 7E 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case R.id.humpid_bili_4_4:
                Hex1="01 10 01 7F 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_4_5:
                Hex1="01 10 01 80 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_4_6:
                Hex1="01 10 01 81 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_4_7:
                Hex1="01 10 01 82 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;
            case R.id.humpid_bili_4_8:
                Hex1="01 10 01 83 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;

            case  R.id.humpid_bili_5_1:
                Hex1="01 10 01 84 00 01 02";
                Hex2= Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_5_2:
                Hex1="01 10 01 85 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case  R.id.humpid_bili_5_3:
                Hex1="01 10 01 86 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case R.id.humpid_bili_5_4:
                Hex1="01 10 01 87 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_5_5:
                Hex1="01 10 01 88 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_5_6:
                Hex1="01 10 01 89 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_5_7:
                Hex1="01 10 01 8A 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;
            case R.id.humpid_bili_5_8:
                Hex1="01 10 01 8B 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;

            case  R.id.humpid_bili_6_1:
                Hex1="01 10 01 8C 00 01 02";
                Hex2= Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_6_2:
                Hex1="01 10 01 8D 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case  R.id.humpid_bili_6_3:
                Hex1="01 10 01 8E 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case R.id.humpid_bili_6_4:
                Hex1="01 10 01 8F 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_6_5:
                Hex1="01 10 01 90 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_6_6:
                Hex1="01 10 01 91 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_6_7:
                Hex1="01 10 01 92 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;
            case R.id.humpid_bili_6_8:
                Hex1="01 10 01 93 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;

            case  R.id.humpid_bili_7_1:
                Hex1="01 10 01 94 00 01 02";
                Hex2= Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_7_2:
                Hex1="01 10 01 95 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case  R.id.humpid_bili_7_3:
                Hex1="01 10 01 96 00 01 02";
                Hex2=Integer.toHexString((int)f);
                break;
            case R.id.humpid_bili_7_4:
                Hex1="01 10 01 97 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_7_5:
                Hex1="01 10 01 98 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case R.id.humpid_bili_7_6:
                Hex1="01 10 01 99 00 01 02";
                Hex2=Integer.toHexString((int)(f*10));
                break;
            case  R.id.humpid_bili_7_7:
                Hex1="01 10 01 9A 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;
            case R.id.humpid_bili_7_8:
                Hex1="01 10 01 9B 00 01 02";
                Hex2=Integer.toHexString((int)(f*100));
                break;

        }


        System.out.println("Hex2..."+Hex2+"   "+Hex2.length());

        switch (Hex2.length()){
            case 1:
                Hex2=" 0"+Hex2+" 00";
                break;
            case 2:
                Hex2=" "+Hex2+" 00";
                break;
            case 3:
                Hex2=" "+Hex2.substring(1,3)+" 0"+Hex2.substring(0,1);
                break;
        }


        System.out.println("修改后..."+Hex2);

        String hex=Hex1+Hex2;
        System.out.println("参与校验..."+hex);
        int Icrc= CRC16.compute(ModbusUtil.hex2Byte(hex));
        System.out.println("校验结果..."+Icrc);
        String Scrc=Integer.toHexString(Icrc);
        System.out.println("文本校验结果..."+Scrc);
        if(Scrc.length()<4){
            Scrc="0"+Scrc;
        }

        String fCrc=Scrc.substring(2,4)+Scrc.substring(0,2);
        System.out.println("设置pid参数..."+modbusUtil.deleteSpace(hex)+fCrc);
        serialHelper.sendHex(modbusUtil.deleteSpace(hex)+fCrc);
        serialHelper.sendHex(modbusUtil.deleteSpace(hex)+fCrc);
    }


}




