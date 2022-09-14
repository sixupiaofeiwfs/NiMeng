package com.nimeng.View;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nimeng.bean.SystemData;
import com.nimeng.util.ChangeDataDBHelper;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.SystemDBHelper;

import java.util.Random;


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
public class SettingSwitchActivity extends CommonUtil {

   // public TextView a,b,c;
    public TextView btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12;

    private Intent intent;
    private TextView textView1,textView2,textView3,textView4,textView5;
    private SystemDBHelper systemDBHelper;
    SystemData systemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settingswitch);


        //systemDBHelper=new SystemDBHelper(SettingSwitchActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(SettingSwitchActivity.this);
        systemData=systemDBHelper.getSystemData();

        btn1 = findViewById(R.id.settingswitch_bt1);
        btn2 = findViewById(R.id.settingswitch_bt2);
        btn3 = findViewById(R.id.settingswitch_bt3);
        btn4 = findViewById(R.id.settingswitch_bt4);
        btn5 = findViewById(R.id.settingswitch_bt5);
        btn6 = findViewById(R.id.settingswitch_bt6);
        btn7 = findViewById(R.id.settingswitch_bt7);//露点仪
        btn8 = findViewById(R.id.settingswitch_bt8);//数字式温度计
        btn9 = findViewById(R.id.settingswitch_bt9);//报警
        btn10 = findViewById(R.id.settingswitch_bt10);//状态指示
        btn11 = findViewById(R.id.settingswitch_bt11);//语音播报
        btn12 = findViewById(R.id.settingswitch_bt12);//自动拍摄






        textView1=findViewById(R.id.settingswitch_textview1);
//        textView2=findViewById(R.id.settingswitch_textview2);
//        textView2.setText(gettime());
        textView3=findViewById(R.id.settingswitch_textview3);
        textView4=findViewById(R.id.settingswitch_textview4);
        textView5=findViewById(R.id.settingswitch_textview5);





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
                intent=new Intent(SettingSwitchActivity.this, TemStandardApparatusActivity.class);
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

                if(systemDBHelper.getSwitch("1")){//开着，本次操作未关闭
                    systemDBHelper.addSwitch("1",false);
                    btn7.setTextColor(Color.RED);

                }else{
                    System.out.println("设置露点仪开启");
                    systemDBHelper.addSwitch("1",true);
                    systemDBHelper.addSwitch("2",false);
                    btn7.setTextColor(Color.BLUE);
                    btn8.setTextColor(Color.RED);

                  // SystemDBHelper  systemDBHelper1=new SystemDBHelper(SettingSwitchActivity.this,"NIMENG.db",null,1);
                    SystemDBHelper systemDBHelper1=new SystemDBHelper(SettingSwitchActivity.this);
                   System.out.println("成功没："+ systemDBHelper1.getSwitch("1"));
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
                if(systemDBHelper.getSwitch("2")){//开着，本次操作未关闭
                    systemDBHelper.addSwitch("2",false);
                    btn8.setTextColor(Color.RED);

                }else{
                    systemDBHelper.addSwitch("2",true);
                    systemDBHelper.addSwitch("1",false);
                    btn8.setTextColor(Color.BLUE);
                    btn7.setTextColor(Color.RED);

                }
            }
        });



        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(systemDBHelper.getSwitch("3")){
                    systemDBHelper.addSwitch("3",false);
                    btn9.setTextColor(Color.RED);


                }else{
                    systemDBHelper.addSwitch("3",true);
                    btn9.setTextColor(Color.BLUE);

                }
            }
        });


        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(systemDBHelper.getSwitch("4")){
                    systemDBHelper.addSwitch("4",false);
                    btn10.setTextColor(Color.RED);

                }else{
                    systemDBHelper.addSwitch("4",true);
                    btn10.setTextColor(Color.BLUE);

                }
            }
        });


        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(systemDBHelper.getSwitch("5")){
                    systemDBHelper.addSwitch("5",false);
                    btn11.setTextColor(Color.RED);

                }else{
                    systemDBHelper.addSwitch("5",true);
                    btn11.setTextColor(Color.BLUE);

                }
            }
        });


        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(systemDBHelper.getSwitch("6")){
                    systemDBHelper.addSwitch("6",false);
                    btn12.setTextColor(Color.RED);

                }else{
                    systemDBHelper.addSwitch("6",true);
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
                        if(edit1.getText().toString().equals(systemData.getSuperPassword())){

                            intent=new Intent(SettingSwitchActivity.this,SettingEquipmentParametersActivity.class);
                            startActivity(intent);
                        }else{
                            showToast(SettingSwitchActivity.this,"密码错误");
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


    @Override
    protected void onStart() {

       // systemDBHelper=new SystemDBHelper(SettingSwitchActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(SettingSwitchActivity.this);
        if(!systemDBHelper.getSwitch("8")){
            btn5.setVisibility(View.INVISIBLE);
            textView4.setVisibility(View.INVISIBLE);
        }
        if(!systemDBHelper.getSwitch("9")){
            btn6.setVisibility(View.INVISIBLE);
            textView5.setVisibility(View.INVISIBLE);
        }
        if(!systemDBHelper.getSwitch("10")){
            btn12.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

        }


        System.out.println("露点仪开关。。。"+systemDBHelper.getSwitch("1"));

        if(!systemDBHelper.getSwitch("1")){
            btn7.setTextColor(Color.RED);
        }else{
            btn7.setTextColor(Color.BLUE);
        }
        if(!systemDBHelper.getSwitch("2")){
            btn8.setTextColor(Color.RED);
        }else{
            btn8.setTextColor(Color.BLUE);
        }
        if(!systemDBHelper.getSwitch("3")){
            btn9.setTextColor(Color.RED);
        }else{
            btn9.setTextColor(Color.BLUE);
        }
        if(!systemDBHelper.getSwitch("4")){
            btn10.setTextColor(Color.RED);
        }else{
            btn10.setTextColor(Color.BLUE);
        }
        if(!systemDBHelper.getSwitch("5")){
            btn11.setTextColor(Color.RED);
        }else{
            btn11.setTextColor(Color.BLUE);
        }
        if(!systemDBHelper.getSwitch("6")){
            btn12.setTextColor(Color.RED);
        }else{
            btn12.setTextColor(Color.BLUE);
        }

        super.onStart();
    }

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


                //右滑
                if (distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN && distanceX > 0) {
                    startActivity(new Intent(this, DataRecordActivity.class));

                } else if (-distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN && -distanceX > 0) {
                    startActivity(new Intent(this, MainActivity.class));

                }
        }

        return super.dispatchTouchEvent(ev);
    }


}
