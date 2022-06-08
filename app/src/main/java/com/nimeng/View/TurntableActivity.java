package com.nimeng.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/23 14:47
 * <p>
 * Changes (from 2022/6/23)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/23 : Create TurntableActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class TurntableActivity extends BaseAvtivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turntable);

        Spinner spinner=(Spinner) findViewById(R.id.spinner1);

        RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3;
        LinearLayout linearLayout_1,linearLayout_2,linearLayout_3,linearLayout_4,linearLayout_5,linearLayout_6;

        relativeLayout1=findViewById(R.id.turntable_relativelayout1);
        relativeLayout2=findViewById(R.id.turntable_relativelayout2);
        relativeLayout3=findViewById(R.id.turntable_relativelayout3);

        linearLayout_1=findViewById(R.id.turntable_linearLayout_1);
        linearLayout_2=findViewById(R.id.turntable_linearLayout_2);
        linearLayout_3=findViewById(R.id.turntable_linearLayout_3);
        linearLayout_4=findViewById(R.id.turntable_linearLayout_4);
        linearLayout_5=findViewById(R.id.turntable_linearLayout_5);
        linearLayout_6=findViewById(R.id.turntable_linearLayout_6);


//        relativeLayout1.setVisibility(View.GONE);
//        relativeLayout2.setVisibility(View.GONE);
//        relativeLayout3.setVisibility(View.GONE);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select=spinner.getSelectedItem().toString();

                Log.d("获取一下选中的值", "onItemSelected: "+select);
                if(select.equals("1")){
                    Log.d("判断是否进入", "onItemSelected: ");
                    relativeLayout1.setVisibility(View.VISIBLE);
                    linearLayout_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.INVISIBLE);
                    relativeLayout2.setVisibility(View.GONE);
                    linearLayout_3.setVisibility(View.GONE);
                    linearLayout_4.setVisibility(View.GONE);
                    relativeLayout3.setVisibility(View.GONE);
                    linearLayout_5.setVisibility(View.GONE);
                    linearLayout_6.setVisibility(View.GONE);

                }else if(select.equals("2")){
                    relativeLayout1.setVisibility(View.VISIBLE);
                    linearLayout_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                    relativeLayout2.setVisibility(View.GONE);
                    linearLayout_3.setVisibility(View.GONE);
                    linearLayout_4.setVisibility(View.GONE);
                    relativeLayout3.setVisibility(View.GONE);
                    linearLayout_5.setVisibility(View.GONE);
                    linearLayout_6.setVisibility(View.GONE);
                }else if(select.equals("3")){
                    relativeLayout1.setVisibility(View.VISIBLE);
                    linearLayout_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                    relativeLayout2.setVisibility(View.VISIBLE);
                    linearLayout_3.setVisibility(View.VISIBLE);
                    linearLayout_4.setVisibility(View.INVISIBLE);
                    relativeLayout3.setVisibility(View.GONE);
                    linearLayout_5.setVisibility(View.GONE);
                    linearLayout_6.setVisibility(View.GONE);

                }else if(select.equals("4")){
                    relativeLayout1.setVisibility(View.VISIBLE);
                    linearLayout_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                    relativeLayout2.setVisibility(View.VISIBLE);
                    linearLayout_3.setVisibility(View.VISIBLE);
                    linearLayout_4.setVisibility(View.VISIBLE);
                    relativeLayout3.setVisibility(View.GONE);
                    linearLayout_5.setVisibility(View.GONE);
                    linearLayout_6.setVisibility(View.GONE);
                }else if(select.equals("5")){
                    relativeLayout1.setVisibility(View.VISIBLE);
                    linearLayout_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                    relativeLayout2.setVisibility(View.VISIBLE);
                    linearLayout_3.setVisibility(View.VISIBLE);
                    linearLayout_4.setVisibility(View.VISIBLE);
                    relativeLayout3.setVisibility(View.VISIBLE);
                    linearLayout_5.setVisibility(View.VISIBLE);
                    linearLayout_6.setVisibility(View.INVISIBLE);
                }else if(select.equals("6")){
                    relativeLayout1.setVisibility(View.VISIBLE);
                    linearLayout_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                    relativeLayout2.setVisibility(View.VISIBLE);
                    linearLayout_3.setVisibility(View.VISIBLE);
                    linearLayout_4.setVisibility(View.VISIBLE);
                    relativeLayout3.setVisibility(View.VISIBLE);
                    linearLayout_5.setVisibility(View.VISIBLE);
                    linearLayout_6.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}
