package com.nimeng.View;

import static com.nimeng.View.MainActivity.SWITCH_ALERM;
import static com.nimeng.View.MainActivity.SWITCH_AUTO_CAPTURE;
import static com.nimeng.View.MainActivity.SWITCH_STATUS;
import static com.nimeng.View.MainActivity.SWITCH_VOICE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public Button btn_alerm,btn_voice,btn_status,btn_auto_capture;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settingswitch);


        btn_alerm=findViewById(R.id.btn_alerm);
        btn_voice=findViewById(R.id.btn_voice);

        btn_status=findViewById(R.id.btn_status);
        btn_auto_capture=findViewById(R.id.btn_auto_capture);


        if(SWITCH_ALERM==false){
            btn_alerm.setText("开启");
        }else{
            btn_alerm.setText("关闭");
        }

        if(SWITCH_VOICE==false){
            btn_voice.setText("开启");
        }else{
            btn_voice.setText("关闭");
        }


        if(SWITCH_STATUS==false){
            btn_status.setText("开启");
        }else{
            btn_status.setText("关闭");
        }

        if(SWITCH_AUTO_CAPTURE==false){
            btn_auto_capture.setText("开启");
        }else{
            btn_auto_capture.setText("关闭");
        }


        btn_alerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_alerm.getText()=="开启"){
                    btn_alerm.setText("关闭");
                    SWITCH_ALERM=true;
                }

            }
        });




        btn_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_voice.getText()=="开启"){
                    btn_voice.setText("关闭");
                    SWITCH_VOICE=true;
                }

            }
        });



        btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_status.getText()=="开启"){
                    btn_status.setText("关闭");
                    SWITCH_STATUS=true;
                }

            }
        });



        btn_auto_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_auto_capture.getText()=="开启"){
                    btn_auto_capture.setText("关闭");
                    SWITCH_AUTO_CAPTURE=true;
                }

            }
        });


    }







}
