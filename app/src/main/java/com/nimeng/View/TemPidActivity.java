package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TemPidActivity extends BaseAvtivity {
    Button humpid_btn, tempid_toSettingSwitch;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_tempid);


        humpid_btn = findViewById(R.id.humpid);
        tempid_toSettingSwitch = findViewById(R.id.tempid_toSettingSwitch);

        humpid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(TemPidActivity.this, HumPidActivity.class);
                startActivity(intent);
            }
        });

        tempid_toSettingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(TemPidActivity.this, SettingSwitchActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6;
        linearLayout1 = findViewById(R.id.tempid_LinearLayout7);
        linearLayout2 = findViewById(R.id.tempid_LinearLayout8);
        linearLayout3 = findViewById(R.id.tempid_LinearLayout9);
        linearLayout4 = findViewById(R.id.tempid_LinearLayout10);
        linearLayout5 = findViewById(R.id.tempid_LinearLayout11);
        linearLayout6 = findViewById(R.id.tempid_LinearLayout12);


        Log.d("获取一下选中的值", "globalVariable.getSelect1()");


        if (globalVariable.getSelect1() == null || globalVariable.getSelect1().equals("")) {

            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.GONE);
            linearLayout5.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);


        } else {

            if (globalVariable.getSelect1().equals("5")) {
                Log.d("判断是否进入", "onItemSelected: ");
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);

            } else if (globalVariable.getSelect1().equals("6")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);
            } else if (globalVariable.getSelect1().equals("7")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);

            } else if (globalVariable.getSelect1().equals("8")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);
            } else if (globalVariable.getSelect1().equals("9")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.VISIBLE);
                linearLayout6.setVisibility(View.GONE);

            } else if ((globalVariable.getSelect1().equals("10"))) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.VISIBLE);
                linearLayout6.setVisibility(View.VISIBLE);
            }

        }


    }

}
