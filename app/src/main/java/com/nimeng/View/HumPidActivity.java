package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class HumPidActivity extends BaseAvtivity{
    Button temmpid_btn,humpid_toSettingSwitch;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_humpid);


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

        if (globalVariable.getSelect2()==null||globalVariable.getSelect2().equals("")){
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.GONE);
            linearLayout5.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);
        }else {
            if (globalVariable.getSelect2().equals("5")) {
                Log.d("判断是否进入", "onItemSelected: ");
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);

            } else if (globalVariable.getSelect2().equals("6")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);
            } else if (globalVariable.getSelect2().equals("7")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);

            } else if (globalVariable.getSelect2().equals("8")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.GONE);
                linearLayout6.setVisibility(View.GONE);
            } else if (globalVariable.getSelect2().equals("9")) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.VISIBLE);
                linearLayout6.setVisibility(View.GONE);

            } else if ((globalVariable.getSelect2().equals("10"))) {
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
