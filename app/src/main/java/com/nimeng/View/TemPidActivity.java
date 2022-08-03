package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TemPidActivity extends BaseAvtivity{
    Button humpid_btn,tempid_toSettingSwitch;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_tempid);


        humpid_btn=findViewById(R.id.humpid);
        tempid_toSettingSwitch=findViewById(R.id.tempid_toSettingSwitch);

        humpid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(TemPidActivity.this,HumPidActivity.class);
                startActivity(intent);
            }
        });

        tempid_toSettingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(TemPidActivity.this,SettingSwitchActivity.class);
                startActivity(intent);
            }
        });

    }

}
