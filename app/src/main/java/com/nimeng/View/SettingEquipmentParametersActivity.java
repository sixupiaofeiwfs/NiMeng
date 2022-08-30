package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nimeng.bean.SystemData;
import com.nimeng.bean.SystemSwitch;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.SystemDBHelper;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_equipment_parameters);
        systemDBHelper=new SystemDBHelper(SettingEquipmentParametersActivity.this,"NIMENG.db",null,1);

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
}
