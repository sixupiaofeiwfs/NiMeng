package com.nimeng.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nimeng.flash.FlashView;
import com.nimeng.flash.VirtualBarUtil;

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

    @Override
    public  void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


        mTemSeekBar=findViewById(R.id.TemSeekBar);
        mTemSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //  mProgressTv.setText(i+"%");
                mTemView.setProgress(i,"tem");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mHumSeekBar=findViewById(R.id.HumSeekBar);
        mHumSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mHumView.setProgress(i,"hum");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


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






}





