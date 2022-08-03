package com.nimeng.View;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nimeng.bean.GlobalVariable;


/**
 * Author: wfs
 * <p>
 * Create: 2022/6/22 9:59
 * <p>
 * Changes (from 2022/6/22)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/22 : Create SettingSwitchActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class SettingSwitchActivity extends BaseAvtivity{

    public Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12;
    private GlobalVariable globalVariable;
    private Intent intent;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settingswitch);

        globalVariable=(GlobalVariable)getApplicationContext();

        btn1 = findViewById(R.id.settingswitch_bt1);
        btn2 = findViewById(R.id.settingswitch_bt2);
        btn3 = findViewById(R.id.settingswitch_bt3);
        btn4 = findViewById(R.id.settingswitch_bt4);
        btn5 = findViewById(R.id.settingswitch_bt5);
        btn6 = findViewById(R.id.settingswitch_bt6);
        btn7 = findViewById(R.id.settingswitch_bt7);
        btn8 = findViewById(R.id.settingswitch_bt8);
        btn9 = findViewById(R.id.settingswitch_bt9);
        btn10 = findViewById(R.id.settingswitch_bt10);
        btn11 = findViewById(R.id.settingswitch_bt11);
        btn12 = findViewById(R.id.settingswitch_bt12);

        textView1=findViewById(R.id.settingswitch_textview1);


        System.out.println("初始化----"+globalVariable.isSwitch_1());

        if(!globalVariable.isSwitch_1()){
            btn7.setTextColor(Color.RED);
        }else{
            btn7.setTextColor(Color.BLUE);
        }
        if(!globalVariable.isSwitch_2()){
            btn8.setTextColor(Color.RED);
        }else{
            btn8.setTextColor(Color.BLUE);
        }
        if(!globalVariable.isSwitch_3()){
            btn9.setTextColor(Color.RED);
        }else{
            btn9.setTextColor(Color.BLUE);
        }
        if(!globalVariable.isSwitch_4()){
            btn10.setTextColor(Color.RED);
        }else{
            btn10.setTextColor(Color.BLUE);
        }
        if(!globalVariable.isSwitch_5()){
            btn11.setTextColor(Color.RED);
        }else{
            btn11.setTextColor(Color.BLUE);
        }
        if(!globalVariable.isSwitch_6()){
            btn12.setTextColor(Color.RED);
        }else{
            btn12.setTextColor(Color.BLUE);
        }



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingSwitchActivity.this,TemPidActivity.class);
                startActivity(intent);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent =new Intent(SettingSwitchActivity.this,SystemParamActivity.class);
                startActivity(intent);
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingSwitchActivity.this,TemPlanActivity.class);
                startActivity(intent);
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingSwitchActivity.this,StandardApparatusActivity.class);
                startActivity(intent);
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingSwitchActivity.this,TurntableActivity.class);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingSwitchActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });




        /**
         * 露点仪开关
         * 如果本次操作为开启露点仪，那么需要做到：
         * 1.全局变量更改状态（switch1改为true）
         * 2.全局变量更改状态（switch2改为false）露点仪与数字式温度计只能开一个
         *
         * 如果本次操作为关闭露点仪，那么仅需要操作全局变量switch1即可
         */
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("_________"+globalVariable.isSwitch_1());
                if(globalVariable.isSwitch_1()){//开着，本次操作未关闭
                    globalVariable.setSwitch_1(false);
                    btn7.setTextColor(Color.RED);
                }else{
                    System.out.println("----------关着");
                    globalVariable.setSwitch_1(true);
                    globalVariable.setSwitch_2(false);
                    btn7.setTextColor(Color.BLUE);
                    btn8.setTextColor(Color.RED);
                }
            }
        });



        /**
         * 数字式温度计开关
         * 如果本次操作为开启数字式温度计，那么需要做到：
         * 1.全局变量更改状态（switch2改为true）
         * 2.全局变量更改状态（switch1改为false）露点仪与数字式温度计只能开一个
         *
         * 如果本次操作为关闭数字式温度计，那么仅需要操作全局变量switch2即可
         */
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globalVariable.isSwitch_2()){//开着，本次操作未关闭
                    globalVariable.setSwitch_2(false);
                    btn8.setTextColor(Color.RED);
                }else{
                    globalVariable.setSwitch_2(true);
                    globalVariable.setSwitch_1(false);
                    btn8.setTextColor(Color.BLUE);
                    btn7.setTextColor(Color.RED);
                }
            }
        });



        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globalVariable.isSwitch_3()){
                    globalVariable.setSwitch_3(false);
                    btn9.setTextColor(Color.RED);
                }else{
                    globalVariable.setSwitch_3(true);
                    btn9.setTextColor(Color.BLUE);
                }
            }
        });


        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globalVariable.isSwitch_4()){
                    globalVariable.setSwitch_4(false);
                    btn10.setTextColor(Color.RED);
                }else{
                    globalVariable.setSwitch_4(true);
                    btn10.setTextColor(Color.BLUE);
                }
            }
        });


        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globalVariable.isSwitch_5()){
                    globalVariable.setSwitch_5(false);
                    btn11.setTextColor(Color.RED);
                }else{
                    globalVariable.setSwitch_5(true);
                    btn11.setTextColor(Color.BLUE);
                }
            }
        });


        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globalVariable.isSwitch_6()){
                    globalVariable.setSwitch_6(false);
                    btn12.setTextColor(Color.RED);
                }else{
                    globalVariable.setSwitch_6(true);
                    btn12.setTextColor(Color.BLUE);
                }
            }
        });



        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(SettingSwitchActivity.this);
                builder.setTitle("请输入密码");
                View view1 =View.inflate(SettingSwitchActivity.this,R.layout.password_edit,null);
                EditText edit1=view1.findViewById(R.id.password_edit_edit1);


                builder.setView(view1);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(edit1.getText().toString().equals("123456")){

                            intent=new Intent(SettingSwitchActivity.this,SettingEquipmentParametersActivity.class);
                            startActivity(intent);
                        }else{
                            showToast("密码错误");
                        }
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




            }
        });


    }


}
